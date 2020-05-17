<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.Date,java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
<%@include file="/jsp/common/style.jsp"%>
</head>
<body>

     <%@include file="/jsp/common/top.jsp"%>
	
	<div id="main">
		<table>
			<tr>
			      <td>订单号</td>
			    <td>用户名</td>
			   
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
				<td>操作</td>
			</tr>	
			<c:if test="${ empty page.items}">
			<tr>
			  <td>无订单信息</td>
			</tr>
			</c:if>
			<c:if test="${not empty page.items}">
			<c:forEach items="${page.items}" var="order">
				<tr>
				    <td>${order.orderId}</td>
				    <td>${order.userId}</td>
				    
					<td>${order.createTime}</td>
					<td>${order.price}</td>
					<td>
					  <c:if test="${order.status ==0}">等待处理</c:if>
					  <c:if test="${order.status == 1}">预约成功</c:if>
                    </td>
					<td><a href="manager/ManagerOrderServlet?action=itempage&id=${order.orderId}">查看详情</a></td>
					<td>
					<c:if test="${order.status ==0}">
					   <button orderId="${order.orderId}" class="yy">预约审核</button>
					 </c:if>
					<c:if test="${order.status ==1}">
					   <button orderId="${order.orderId}" disabled="disabled" class="yy">预约审核</button>
					 </c:if>
					</td>
				</tr>	
			</c:forEach>
			</c:if>	
		</table>
		<c:if test="${not empty page.items}">
		  <%@include file="/jsp/common/nav.jsp"%> 
		  </c:if>
	</div>

	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>
<script>

    $(function(){
    	$(".yy").click(function(){
    		var orderId = $(this).attr("orderId");
    		layer.confirm("确定订单号为【"+$(this).parent().parent().find("td:first").text()+"】审核通过吗?",  {icon: 3, title:'提示'}, function(cindex){
			  //  window.location.href="${path}manager/ManagerOrderServlet?action=updateStatus";
			    $.getJSON("${path}manager/ManagerOrderServlet",{"action":"updateStatus",
					  "orderId":orderId},function(data){
						  layer.close(cindex);
						  if(data.code){
							  layer.msg("审核成功", {time:1000, icon:6}, function(){});
						  }else{
							  layer.msg("审核失败", {time:1000, icon:5, shift:6}, function(){});
						  }
					  });
			}, function(cindex){
			    layer.close(cindex);
			});
         return false;
    	});
    })
</script>