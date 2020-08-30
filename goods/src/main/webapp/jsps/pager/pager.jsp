<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	function _go() {
		var pc = $("#pageCode").val();
		if(!/^[1-9]\d*$/.test(pc))
		{    
			alert('请输入正确的页码！');
			return;
		}
		if(pc > Number(${info.pages})) {//判断当前页码是否大于最大页
			alert('不能超过最大页！');
			return;
		}
		location.href = "${url}&pageNum="+pc;
		
	}
</script>


<div class="divBody">
  <div class="divContent">
    <%--上一页 --%>
		<c:if test="${info.pageNum ==1 }">
       		 <span class="spanBtnDisabled">上一页</span>
        </c:if>
        <c:if test="${info.pageNum !=1 }">
        	<a href="${url}&pageNum=${info.pageNum-1}" class="aBtn bold">上一页</a>
		</c:if>
    
    <c:choose>
    	<c:when test="${info.pages<=10}">
    		<c:set  var="begin"  value="1"  />
    		<c:set   var="end"    value="${info.pages}"/>
    	</c:when>
    	
    	<c:otherwise>
    		<c:set  var="begin"  value="${info.pageNum-5}"  />
    		<c:set   var="end"    value="${info.pageNum+4}"/>
    		
    		<c:if test="${begin<1}">
    			<c:set  var="begin"  value="1"  />
    			<c:set   var="end"    value="10"/>
    		</c:if>
    		
    		<c:if test="${end >info.pages}">
    			<c:set   var="end"  value="${info.pages}"/>
    			<c:set   var="begin"  value="${end -9}"/>
    		</c:if>
    	</c:otherwise>
    </c:choose>
   
	<c:forEach  begin="${begin}"   end="${end}"  var="i">
		<a href="${url}&pageNum=${i}" class="aBtn">${i}</a>
	</c:forEach>
    
   
    
     <%--下一页 --%>
     	<c:if test="${info.pageNum ==info.pages }">
        	<span class="spanBtnDisabled">下一页</span>
        </c:if>
        <c:if test="${info.pageNum !=info.pages }">
        	<a href="${url}&pageNum=${info.pageNum+1}" class="aBtn bold">下一页</a> 
        </c:if>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    
    <%-- 共N页 到M页 --%>
    <span>共${info.pages }页</span>
    <span>到</span>
    <input type="text" class="inputPageCode" id="pageCode" value=""/>
    <span>页</span>
    <a href="javascript:_go();" class="aSubmit">确定</a>
  </div>
</div>