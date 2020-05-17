<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>结算页面</title>
<%--<link type="text/css" rel="stylesheet" href="../../static/css/style.css" >--%>
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
		
		<h1>你的订单已结算，订单号为${sessionScope.orderId}</h1>
	</div>
	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>