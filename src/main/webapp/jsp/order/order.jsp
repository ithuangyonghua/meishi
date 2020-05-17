<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
<%@include file="/jsp/common/style.jsp"%>
<style type="text/css">
h1 {
	text-align: center;
	margin-top: 200px;
}
</style>
</head>
<body>

	<%@include file="/jsp/common/top.jsp"%>

	<div id="main">

		<table>
			<tr>
				<td>订单号</td>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>
			<c:if test="${empty page.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">亲,订单信息为空！快点去首页添加订单把</a></td>
				</tr>
			</c:if>
			<c:if test="${not empty page.items}">
				<c:forEach items="${page.items}" var="i">
					<tr>
						<td>${i.orderId}</td>
						<td>${i.createTime}</td>
						<td>${i.price}</td>
						<td><c:if test="${i.status ==0}">正在处理</c:if> <c:if
								test="${i.status == 1}">已接受预约</c:if> <c:if
								test="${i.status == 2}">已完成</c:if></td>
						<td><a
							href="client/OrderServlet?action=itempage&id=${i.orderId}">查看详情</a></td>
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