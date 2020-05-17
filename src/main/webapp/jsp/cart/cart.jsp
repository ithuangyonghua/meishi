<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>预定信息</title>
<%@include file="/jsp/common/style.jsp"%>
<link rel="stylesheet" type="text/css" href="css/global.css"/>
<link rel="stylesheet" href="css/smoothness/jquery.ui.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/css.css"/>
</head>
<style>
  #main{
   margin: 0px auto;
   height:500px;
  }
  #header {
    margin: 0px auto;
   }
</style>
<body>
	<%@include file="/jsp/common/top.jsp"%>
	<div id="main">
		<table>
			<tr>
				<td>菜名</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">亲,预定信息为空！快点去首页添加菜品把</a></td>
				</tr>
			</c:if>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="item">
					<tr>
						<td>${item.value.name}</td>
						<td><input type="text" style="width:80px;" foodid="${item.value.name}" class="numberBtn" value="${item.value.count}"/></td>
						<td>${item.value.price}</td>
						<td>${item.value.totalPrice}</td>
						<%-- <td><a class="itemDeleteBtn" foodid="${item.value.name}" href="CartServlet?action=deleteItem&id=${item.value.name}">删除</a></td> --%>
						<td><a class="itemDeleteBtn" foodid="${item.value.name}" href="#">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<c:if test="${not empty cart.items}">
		<div class="cart_info" style="margin-left:90px;margin-top:20px;">
			<span class="cart_span">预定信息中共有<span class="b_count">${sessionScope.cart.totalCount}</span>份菜预定</span>
			<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
			<span class="cart_span"><a id="clearBtn" href="#">清空预定信息</a></span>
		</div>
		<form action="${path}client/OrderServlet" method="POST" id="orderForm">
		<div class="cart_info" style="margin-top:10px;">
		    <input type="hidden" name="action" value="createOrder"/>
			<div class="sea-div">
               <input type="text" readonly  name="createtime" id="startDate"/>
            </div>
            </form>
		</div>
		<div class="cart_info" style="margin-top:30px;margin-left:200px;">
		  <span class="cart_span"><button id="createOder">去结账</button></span>
		</div>
		 </form>
		</c:if>
	</div>
	
	<%--<div id="bottom">
		<span>
			""书城.Copyright &copy;2015
		</span>
	</div>--%>
	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.ui.js"></script>
<script type="text/javascript" src="js/moment.min.js"></script>
<script type="text/javascript" src="js/hotel/hotel.search.js"></script>
<script type="text/javascript" src="js/stay.js"></script>
<script>
	 $(function(){
		 //删除
	     $(".itemDeleteBtn").click(function(){
	         var foodid = $(this).attr("foodid")
	         layer.confirm("你确定删除【"+$(this).parent().parent().find("td:first").text()+"】吗?",  {icon: 3, title:'提示'}, function(cindex){
				    window.location.href="${path}CartServlet?action=deleteItem&id="+foodid;
	        	    layer.close(cindex);
				}, function(cindex){
				    layer.close(cindex);
				});
	         return false;
		 });
	     //清空
	     $("#clearBtn").click(function(){
             layer.confirm("你确定清空预定信息吗?",  {icon: 3, title:'提示'}, function(cindex){
				    window.location.href="${path}CartServlet?action=clear";
	        	    layer.close(cindex);
				}, function(cindex){
				    layer.close(cindex);
				});
	         return false;
		 });
         //修改数量
	     $(".numberBtn").change(function(){
			var foodid = $(this).attr("foodid");
			var count = $(this).attr("value");
			var defultValue = this.defaultValue;
			var btn = $(this);
	        layer.confirm("你确认将【"+$(this).parent().parent().find("td:first").text()+"】预定数量修改为"+count+"吗?",  {icon: 3, title:'提示'}, function(cindex){
	        	 window.location.href="${path}CartServlet?action=updateCount&id="+foodid+"&count=" + count;
			}, function(cindex){
				btn.val(defultValue);
			     layer.close(cindex);
			});
             return false;
		 });
         $("#createOder").click(function(){
        	 $("#orderForm").submit();
         });
	 });



</script>