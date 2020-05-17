<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.Date,java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加美食</title>
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
						 <td>菜名</td>
			             <td>美食图片</td>
			             <td>菜系</td>
			             <td>商家名称</td>
			             <td>价格</td>
						 <td colspan="2">操作</td>
					</tr>		
					<tr>
						<input type="hidden" name="pageNo" value="${param.pageNo}"/>
						<input type="hidden" name="action" value="add"/>
						
						<td><input name="name"  type="text" /></td>
						<td><input name="photo" type="text" value="img/a.png"/></td>
						<td><input name="sort" type="text" value="粵菜"/></td>
						<td><input name="restaurant" type="text" value=""/></td>
						<td><input name="privce" type="text" value="19"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>

		<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>