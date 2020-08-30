$(function() {
	//////
	total();

	// 给  购物车条目的   checkbox  注册    单击事件 ，   只影响  总计 。
	$("input:checkbox[name=checkboxBtn]").click(function() {
		// 计算总计
		total();
		// 影响  结算 按钮
		jiesuan();
	})

	//给全选按钮  注册   点击事件
	$("#selectAll").click(function() {
		var flag = $("#selectAll").attr("checked");
		$("input:checkbox[name=checkboxBtn]").attr("checked", flag);
		total();
		jiesuan();

	});



	var count = $(".removeCartitemid").size();
	//
	$(".removeCartitemid").click(function() {
		var cartitemid = $(this).attr("id").substr(0, 32);
		$.ajax({
			async : false,
			cache : false,
			type : "post",
			url : "/goods/cart/removeCartByCartitemid.action",
			data : {
				cartitemid : cartitemid
			},
			success : function(res) {
				if (res) {
					count-- ;

					// 把当前  表格    tr当前行  所有标签  都删除掉
					$("#" + cartitemid + "Tr").remove();
					//重新计算总计
					total();

				} else {

					alert("请再试一次！");
				}
			}
		});
		//
		if (count == 0) {
			location.href = "/goods/cart/getCartitemsByUid/${user.uid}.action";
		}
	});
	/////给所有的 checkbox 注册 一个单击事件就行了 
	$("input:checkbox").click(function() {
		var size = $("input:checkbox[name=checkboxBtn][checked=true]").size();
		if (size == 0) {

			$("#piliang_a").css("display", "none");
		} else {
			$("#piliang_a").css("display", "");
		}
	});

	////////////给 所有 加号    注册   点击   事件 
	$(".jia").click(function() {
		var cartitemid = $(this).attr("id").substr(0, 32);
		var quantity = 1 + Number($("#" + cartitemid + "Quantity").val());
		var currprice = Number($("#" + cartitemid + "CurrPrice").text());
		var st = subtotal(currprice, quantity);
		$.ajax({
			cache : false,
			async : false,
			type : "post",
			url : "/goods/cart/changeQuantity.action",
			//  update   cartitem   set   quantity =  ?  where   cartitemId = ?
			data : {
				'cartitemid' : cartitemid,
				'quantity' : quantity
			},
			success : function(res) {
				if (res) {
					// 小计 更新
					$("#" + cartitemid + "Subtotal").text(st);
					// 数量更新
					$("#" + cartitemid + "Quantity").val(quantity);
					// 总计更新
					total();
				} else {
					alert("重新尝试");
				}
			}
		});
	});
	/////////减
	$(".jian").click(function() {
		var cartitemid = $(this).attr("id").substr(0, 32);
		var quantity = Number($("#" + cartitemid + "Quantity").val()) - 1;
		var size = $("input:checkbox[name=checkboxBtn][checked=true]").size();
		if (quantity == 0) {
			if (confirm("确认删除该购物车条目吗？")) {
				// 按主键  删除    购物车 条目了
				$.ajax({
					cache : false,
					async : false,
					type : "post",
					url : "/goods/cart/removeCartByCartitemid.action",
					data : {
						cartitemid : cartitemid
					},
					success : function(res) {
						if (res) {
							// shanchu删除 成功  
							$("#" + cartitemid + "Tr").remove();
							// 重新计算总计
							total();
							// 重新  统计 一下  被选中  checkBoxBtn 的个数   
							size = $("input:checkbox[name=checkboxBtn][checked=true]").size();
						}
					}
				});
			} else {
				// 都行
				$("#" + cartitemid + "Quantity").val(1);
			}
		} else {
			// update quantity  -1
			$.ajax({
				cache : false,
				async : false,
				type : "post",
				data : {
					cartitemid : cartitemid,
					quantity : quantity
				},
				url : "/goods/cart/changeQuantity.action",
				success : function(res) {
					if (res) {
						// 新数量
						$("#" + cartitemid + "Quantity").val(quantity);
						//新小计
						var currprice = Number($("#" + cartitemid + "CurrPrice").text());
						var st = subtotal(currprice, quantity);
						$("#" + cartitemid + "Subtotal").text(st);
						// 新的总计
						total();
					}
				}
			});
		}
		///
		if (size == 0) {
			location.href = "/goods/cart/getCartitemsByUid/${user.uid}.action";
		}
	});




});
// 重新  计算  小计
function subtotal(currprice, quantity) {
	return (currprice * quantity).toFixed(2);

}

//批量删除

function piliang() {
	var ids = new Array();
	$("input:checkbox[name=checkboxBtn][checked=true]").each(function() {

		var cartitemid = $(this).attr("id").substr(0, 32);

		ids.push(cartitemid);
	});
	if (ids.length == 0) {

		alert("您在逗我");
		return;
	}
	location.href = "/goods/cart/removeByBatch.action?ids=" + ids;
}
//计算总计
function total() {
	var total = 0;
	// 获取 所有的  name为  checkboxBtn  并且选中的   checkbox
	$("input:checkbox[name=checkboxBtn][checked=true]").each(function() {
		var cartitemid = $(this).attr("id").substr(0, 32);

		total += Number($("#" + cartitemid + "Subtotal").text());
	});


	// 保留2位小数
	total = total.toFixed(2);
	$("#total").text(total);

}
// 结算 按钮的 状态    
function jiesuan() {
	var size = $("input:checkbox[name=checkboxBtn][checked=true]").size();
	//alert(size);
	if (size == 0) {

		$("#jiesuan").removeClass("jiesuan").addClass("kill").click(function() {
			return false;
		});

	} else {
		// 解绑点击事件       就变成 了 正常的  一个超链接了
		$("#jiesuan").removeClass("kill").addClass("jiesuan").unbind("click");
	}

}
// 点击  结算按钮       执行的工作
function preOrders() {
	var ids = new Array();
	// huoq获取  全部的  选中的  复选框
	$("input:checkbox[name=checkboxBtn][checked=true]").each(function() {
		var id = $(this).attr("id").substr(0, 32);

		ids.push(id);
	});

	location.href = "/goods/cart/preOrders.action?ids=" + ids;

}