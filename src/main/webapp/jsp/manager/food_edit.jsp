<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.Date,java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑美食</title>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<%@include file="/jsp/common/manage.jsp"%>
		<div id="main">
			<form action="manager/FoodServlet" method="post">
				<table>
					<tr>
			             <td>美食图片</td>
			             <td>菜系</td>
			             <td>商家名称</td>
			             <td>价格</td>
			             <td>销量</td>
						 <td colspan="2">操作</td>
					</tr>		
					<tr>
						<input type="hidden" name="pageNo" value="${param.pageNo}"/>
						<input type="hidden" name="action" value="update"/>
						<input name="name"  type="hidden" value="${foodEntity.name}"/>
						<td><input name="photo"  type="text" value="${foodEntity.photo}"/></td>
						<td><input name="sort" type="text" value="${foodEntity.sort}"/></td>
						<td><input name="restaurant" type="text" value="${foodEntity.restaurant}"/></td>
						<td><input name="privce" type="text" value="${foodEntity.privce}"/></td>
						<td><input name="stock" type="text" value="${foodEntity.stock}"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>

		<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>