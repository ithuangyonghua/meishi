<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.Date,java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会员注册页面</title>
<%@include file="/jsp/common/style.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<%--<div id="header">
				<img class="logo_img" alt="" src="static/img/logo.png" >
				<span class="wel_word"></span>
				<div>
					<span>欢迎<span class="um_span">韩总</span>光临""书城</span>
					<a href="../order/order.jsp">我的订单</a>
					<a href="../../index.jsp">注销</a>&nbsp;&nbsp;
					<a href="../../index.jsp">返回</a>
				</div>
		</div>--%>
		<%@include file="/jsp/common/top.jsp"%>
		<div id="main">
		
			<h1>注册成功! <a href="index.jsp">转到主页</a></h1>
	
		</div>
		
	<%--	<div id="bottom">
			<span>
				""书城.Copyright &copy;2015
			</span>
		</div>--%>
		<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>