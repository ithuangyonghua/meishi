<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="header">
    <img class="logo_img" alt="" src="img/logo.png" >
    <div>
        <span>欢迎<span class="um_span">${user.name}</span>光临美食之城</span>
        <a href="client/OrderServlet?action=page">我的订单</a>
        <a href="UserServlet?action=logout">注销</a>&nbsp;&nbsp;
        <a href="${path}/index.jsp">返回</a>
    </div>
</div>
