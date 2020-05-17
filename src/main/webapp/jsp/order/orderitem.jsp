<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单-订单详情</title>
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
		      <td colspan="4">订单编号:${itempage[0].orderId}</td>
		    </tr>
			<tr>
				<td>菜名</td>
				<td>数量</td>
				<td>金额</td>
				<td>小计</td>
			</tr>	
			 <c:forEach items="${itempage}" var="i">
				<tr>
					<td>${i.name}</td>
					<td>${i.count}</td>
					<td>
					 ${i.price}
                    </td>
                    <td>
					 <span class="xj">${i.totalPrice}</span>
                    </td>
					<%-- <td><a href="client/OrderServlet?action=itempage&id=${i.orderId}">查看详情</a></td> --%>
				</tr>	
			</c:forEach> 
			<td colspan="4">总计:￥<span id="zj"></span></td>
		</table>
	</div>

	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>
<script>
    $(function(){
        var result = 0;
    	$(".xj").each(function(i,item){
    		result += Number($(this).text());
    	});
    	$("#zj").text(result);
    });
</script>