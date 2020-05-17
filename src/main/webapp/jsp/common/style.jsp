<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme();//获取协议
    String ip = request.getServerName();//获取id
    int port = request.getServerPort();//获取端口
    String project = request.getContextPath();//获取工程名
    String path = basePath + "://" + ip + ":" + port + project + "/";
    request.setAttribute("path",path);
%>
<base href="<%=path %>">
<link type="text/css" rel="stylesheet" href="css/style.css" >
<!--导入jquery-->
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="layer/layer.js"></script>