$(function() {
	//1. 注册按钮实现切换图片功能
	$("#submitBtn").hover(
		function() {
			this.src = "/goods/images/regist2.jpg";
		},
		function() {
			this.src = "/goods/images/regist1.jpg";
		}
	);
	//2.隐藏提示框
	$(".inputClass").focus(function() {
		var lab_id = $(this).attr("id") + "Error";
		$("#" + lab_id).text("").css("display", "none");
	});
	//3.丢失焦点,调用对应函数
	$(".inputClass").blur(function() {
		eval("yanzheng" + $(this).attr("id") + "()");
	});
	//统计5个框都是true，就提交表单
	$("#submitBtn").click(function(){
		if(yanzhengloginname() && yanzhengloginpass() && yanzhengreloginpass() && yanzhengemail && yanzhengverifyCode){
			$("registFrm").submit();
			return true;
		} else {
			alert("表单有问题，请检查！！！");
			return false;
		}
	});

});

function yanzhengloginname() {
	//1. 非空校验 
	var val = $("#loginname").val();
	if(val == "") {
		$("#loginnameError").text("用户名不能为空！");
		showError($("#loginnameError"));
		return false;
	}
	//2. 校验 2-12位
	if(val.length<2 || val.length>12){
		$("#loginnameError").text("用户名长度2-12位！");
		showError($("#loginnameError"));
		return false;
	}
	//3. 校验用户名 ajax
	var flag = false;
	$.ajax({
		cache: false,
		async: false,
		type: "post",
		url:"/goods/user/verifyLoginname.action",
		data: {loginname:val},
		
		success: function(res) {
			if(res) {
				flag = true;
			} else {
				$("#loginnameError").text("用户名已存在！")
				showError($("#loginnameError"));
				flag = false;
			}
		}
	})
	return flag;
};
function yanzhengloginpass() {
	//1.非空校验 
	var val = $("#loginpass").val();
	if(val == "") {
		$("#loginpassError").text("密码不能为空！！！");
		showError($("#loginpassError"));
		return false;
	}
	//2.校验2-12位
	if(val.length<2 || val.length>12){
		$("#loginpassError").text("密码长度2-12位！");
		showError($("#loginpassError"));
		return false;
	}
	return true;
};
function yanzhengreloginpass() {
	//1.非空校验 
	var val = $("#reloginpass").val();
	if(val == "") {
		$("#reloginpassError").text("确认密码不能为空！");
		showError($("#reloginpassError"));
		return false;
	}
	//2.两次密码是否一致
	var password = $("#loginpass").val();
	if(val != password) {
		$("#reloginpassError").text("两次密码不一致！");
		showError($("#reloginpassError"));
		return false;
	}
	return true;
};
function yanzhengemail() {
	//1.非空校验
	var val = $("#email").val();
	if(val == "") {
		$("#emailError").text("邮箱不能为空！！！");
		showError($("#emailError"));
		return false;
	}
	// /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/
	//2.正则校验
	if (!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(val)) {

		$("#emailError").text("邮箱格式不正确");
		showError($("#emailError"));
		return false;
	}
	var flag = false;
	//3. 校验邮箱名 ajax
	$.ajax({
		async : false,
		cache : false,
		type : "post",
		url : "/goods/user/verifyEmail.action",
		data : {
			email : val
		},
		success : function(res) {
			if (!res) {
				$("#emailError").text("邮箱已存在，请更换!");
				showError($("#emailError"));
				flag = false ;
			} else {
				flag = true;
			}
		}
	});
	return flag;
};
function yanzhengverifyCode() {
	var  val  =   $("#verifyCode").val();
	//1. 非空校验
	if(val==""){
		$("#verifyCodeError").text("验证码不能为空!");
		showError($("#verifyCodeError"));
		return false;
	}
	//2. 长度4 位  
	if(val.length != 4){
		$("#verifyCodeError").text("验证码长度为4！");
		showError($("#verifyCodeError"));
		return false;
	}
	var  flag = false ;
	//3. 文本框的值  和 Session域 中的值  比较    ajax  vCode
	$.ajax({
		cache:false ,
		async:false , 
		type:"post",
		url:"/goods/user/verifyCode.action",
		data:{verifyCode:val},
		success: function(res){
			if(res){
				flag = true ;	
			}else{
				$("#verifyCodeError").text("验证码不正确!");
				showError($("#verifyCodeError"));
				flag =  false;
			}	
		}
	});
	return flag;
};
//显示和隐藏错误提示label
function showError(jq_obj){
	var txt = jq_obj.text();
	if(txt == "") {
		jq_obj.css("display", "none");
	} else {
		jq_obj.css("display", "");
	}
}
//换一张
function _hyz(){
	$("#imgVerifyCode").attr("src","/goods/user/getVerify.action?"+new Date());
}

