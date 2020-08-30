<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>cartlist.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script src="<c:url value='/js/round.js'/>"></script>
	<script src="<c:url value='/jsps/js/cart/cart.js'/>"></script>
	
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/cart/list.css'/>">
	
  </head>
  <body>

<c:if test="${fn:length(cartitempovos) eq  0}">
	<table width="95%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td align="right">
				<img align="top" src="<c:url value='/images/icon_empty.png'/>"/>
			</td>
			<td>
				<span class="spanEmpty">您的购物车中暂时没有商品</span>
			</td>
		</tr>
	</table>  

<br/>
</c:if>
<br/>

<c:if test="${fn:length(cartitempovos) !=  0}">
<table width="95%" align="center" cellpadding="0" cellspacing="0">
	<tr align="center" bgcolor="#efeae5">
		<td align="left" width="50px">
			<input type="checkbox" id="selectAll" checked="checked"/><label for="selectAll">全选</label>
		</td>
		<td colspan="2">商品名称</td>
		<td>单价</td>
		<td>数量</td>
		<td>小计</td>
		<td>操作</td>
	</tr>


<c:forEach  items="${cartitempovos}"  var="povo">

	<tr align="center"  id="${povo.cartitem.cartitemid}Tr">
		<td align="left">
			<input value="12345" type="checkbox" name="checkboxBtn" checked="checked"  id="${povo.cartitem.cartitemid}checkboxBtn"/>
		</td>
		<td align="left" width="70px">
			<a class="linkImage" href="<c:url value='/book/getBooksByExample.action?bid=${povo.bid }'/>">
			<img border="0" width="54" align="top" src="<c:url value='/${povo.imageB }'/>"/></a>
			</td>
			<td align="left" width="400px">
		    <a href="<c:url value='/book/getBooksByExample.action?bid=${povo.bid }'/>"><span>${povo.bname }</span></a>
		</td>
		<td><span>&yen;<span class="currPrice" id="${povo.cartitem.cartitemid }CurrPrice">${povo.currprice }</span></span></td>
		<td>
			<a class="jian" id="${povo.cartitem.cartitemid }Jian"></a>
			<input class="quantity" readonly="readonly" id="${povo.cartitem.cartitemid }Quantity" type="text" value="${povo.cartitem.quantity}"/>
			<a class="jia" id="${povo.cartitem.cartitemid }Jia"></a>
		</td>
		<td width="100px">
			<span class="price_n">&yen;<span class="subTotal" id="${povo.cartitem.cartitemid }Subtotal">${povo.subTotal }</span></span>
		</td>
		<td>
			<a id="${povo.cartitem.cartitemid}RemoveA" class="removeCartitemid"   href="#">删除</a>
		</td>
	</tr>
	
</c:forEach>
	
	<tr>
		<td colspan="4" class="tdBatchDelete">
			<a href="javascript:piliang();"   id="piliang_a">批量删除</a>
		</td>
		<td colspan="3" align="right" class="tdTotal">
			<span>总计：</span><span class="price_t">&yen;<span id="total"></span></span>
		</td>
	</tr>
	<tr>
		<td colspan="7" align="right">
			<a href="javascript:preOrders();" id="jiesuan" class="jiesuan"></a>
		</td>
	</tr>
</table>
	<%-- <form id="form1" action="<c:url value='/jsps/cart/showitem.jsp'/>" method="post">
		<input type="hidden" name="cartItemIds" id="cartItemIds"/>
		<input type="hidden" name="method" value="loadCartItems"/>
	</form>
 --%>
</c:if>
  </body>
</html>