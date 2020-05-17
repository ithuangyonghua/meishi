<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>美食管理</title>
</head>
<body>
<%@include file="/jsp/common/manage.jsp" %>
<div id="main">
    <table>
        <tr>
            <td>菜名</td>
            <td>美食图片</td>
            <td>菜系</td>
            <td>商家名称</td>
            <td>价格</td>
            <td>销量</td>
            <td colspan="2">操作</td>
        </tr>
        <c:forEach items="${page.items}" var="food">
            <tr>
                <td>${food.name}</td>
                <td><img width=100px src="${food.photo}"/></td>
                <td>${food.sort}</td>
                <td>${food.restaurant}</td>
                <td>${food.privce}</td>
                <td>${food.stock}</td>
                <td><a href="manager/FoodServlet?id=${food.name}&action=getFood&pageNo=${requestScope.page.pageNo}">修改</a></td>
                <td><a class="deleteBtn" href="manager/FoodServlet?id=${food.name}&action=delete&pageNo=${requestScope.page.pageNo}">删除</a></td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="8"><a href="jsp/manager/food_add.jsp?pageNo=${requestScope.page.pageTotal}">添加美食</a></td>
        </tr>
    </table>
     <%@include file="/jsp/common/nav.jsp"%>

</div>

<%@include file="/jsp/common/footer.jsp" %>
</body>
</html>
<script>
    $(".deleteBtn").click(function () {
        return confirm("你确认删除【" + $(this).parent().parent().find("td:first").text() + "】");
    });

</script>