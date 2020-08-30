<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单详细</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/order/desc.css'/>">
	<script type="text/javascript"   src="/goods/jquery/jquery-1.5.1.js"></script>
  </head>
  
<body>
	<div class="divOrder">
		<span>订单号：${order.oid }
		<c:if test="${order.status  eq  1 }">(等待付款)</c:if>
		<c:if test="${order.status  eq  2 }">(准备发货)</c:if>
		<c:if test="${order.status  eq  3 }">(等待确认)</c:if>
		<c:if test="${order.status  eq  4 }">(交易成功)</c:if>
		<c:if test="${order.status  eq  5 }">(已取消)</c:if>
			
		　　　下单时间：${order.ordertime }</span>
	</div>
	<div class="divContent">
		<div class="div2">
			<dl>
				<dt>收货人信息</dt>
				<dd>${order.address } </dd>
			</dl>
		</div>
		<div class="div2">
			<dl>
				<dt>商品清单</dt>
				<dd>
					<table cellpadding="0" cellspacing="0">
						<tr>
							<th class="tt">商品名称</th>
							<th class="tt" align="left">单价</th>
							<th class="tt" align="left">数量</th>
							<th class="tt" align="left">小计</th>
						</tr>
						<c:forEach  items="${orderdescpovos}"  var="orderitem">
						<tr style="padding-top: 20px; padding-bottom: 20px;">
							<td class="td" width="400px">
								<div class="bookname">
								  <img align="middle" width="70" src="<c:url value='/${orderitem.imageB }'/>"/>
								  <a href="<c:url value='/book/getBooksByExample.action?bid=${orderitem.bid}'/>">${orderitem.bname }</a>
								</div>
							</td>
							<td class="td" >
								<span>&yen;${orderitem.currprice }</span>
							</td>
							<td class="td">
								<span>${orderitem.quantity}</span>
							</td>
							<td class="td">
								<span>&yen;${orderitem.subtotal}</span>
							</td>			
						</tr>
						</c:forEach>
					</table>
				</dd>
			</dl>
		</div>
		<div style="margin: 10px 10px 10px 550px;">
			<span style="font-weight: 900; font-size: 15px;">合计金额：</span>
			<span class="price_t">&yen;${order.total }</span><br/>
<!--update  `order`  set   status =  ?  where   oid = ?  -->
	<c:if test="${order.status eq 1}">
		<a href="<c:url value='/order/prePay.action?oid=${order.oid }&total=${order.total }'/>" class="pay"></a><br/>
    	<a id="cancel" href="javascript:changeStatus('${order.oid }','${order.status }','${order.uid }');">取消订单</a><br/>
	</c:if>
	<c:if test="${order.status  eq 3 }">
		<a id="confirm" href="javascript:changeStatus('${order.oid }','${order.status }','${order.uid }');">确认收货</a><br/>	
	</c:if>		
		</div>
	</div>
	<script type="text/javascript">
		function   changeStatus(oid,status,uid){
			var   sta  =   Number(status);
			var    msg  ="" ;  
			
			if(sta==1){
				msg =  "确定取消订单吗？";
				sta = 5 ; 
				
			}else if(sta==3){
				msg=  "确认收货后，钱打给卖家了。";
				sta =  4 ; 
			}
			if(confirm(msg)){
				$.ajax({
					async:false,
					cache:false,
					type :"post",
					url :"/goods/order/changeStatus.action",
					data:{oid:oid,status:sta,uid:uid},
					
					success  : function(mav){
						// jsp  +  域中的值     desc.jsp
						document.write(mav);	
					}
				});
			}	
		}
	</script>
</body>
</html>