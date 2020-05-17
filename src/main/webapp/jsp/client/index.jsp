<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.Date,java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>美食首页</title>
    <%@include file="/jsp/common/style.jsp"%>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="img/logo.png" >
   <!--  <span class="wel_word">美食系统</span> -->
    <div>
        <c:if test="${not empty sessionScope.user}">
            <span>欢迎<span class="um_span">${user.name}</span>光临美食系统</span>
            <a href="jsp/order/order.jsp">我的订单</a>
            <a href="UserServlet?action=logout">注销</a>&nbsp;&nbsp;
             <a href="jsp/cart/cart.jsp">预定信息</a>
        </c:if>
        <c:if test="${empty sessionScope.user}">
            <a href="jsp/user/login.jsp">登录</a> |
            <a href="jsp//user/regist.jsp">注册</a> &nbsp;&nbsp;
        </c:if>
        <%--CartServlet?action=list--%>
       
        <a href="jsp/manager/manager.jsp" id="listen">后台管理</a>
    </div>
</div>
<div id="main">
    <div id="book">
       <input type="hidden" value="${sessionScope.qxmsg}" id="qxmsg"/>
      <%--   <div class="book_cond">
            <form action="client/ClientBookServlet" method="get">
                <input type="hidden" name="action" value="getBookByPrice"/>
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询" />
            </form>
        </div>
        --%>
	    <div style="text-align: center">
	            <c:if test="${not empty cart.items}">
	                <span>您的预定信息中有${sessionScope.cart.totalCount}份预定信息</span>
	                <div>
	                    您刚刚将<span style="color: red">${sessionScope.lastName}</span>加入到了预定信息中
	                </div>
	            </c:if>
	
	        </div> 
      <c:forEach items="${page.items}" var="i">
        <div class="b_list">
            <div class="img_div">
                <img class="food_img" alt="" src="${i.photo}" />
            </div>
            <div class="food_info">
                <div class="food__name">
                    <span class="sp1">菜名:</span>
                    <span class="sp2">${i.name}</span>
                </div>
                <div class="food__author">
                    <span class="sp1">菜系:</span>
                    <span class="sp2">${i.sort}</span>
                </div>
                <div class="food__price">
                    <span class="sp1">价格:</span>
                    <span class="sp2">￥${i.privce}</span>
                </div>
                <div class="food__stock">
                    <span class="sp1">销量:</span>
                    <span class="sp2">${i.stock}</span>
                </div>
                 <div class="food__restaurant">
                    <span class="sp1">商家名称:</span>
                    <span class="sp2">${i.restaurant}</span>
                </div>
                <div class="food_add">
                    <button foodid="${i.name}"  class="addItemBtn">加入预定</button>
                </div>
            </div>
        </div>
     </c:forEach>
    </div>


    <%@include file="/jsp/common/nav.jsp"%>
</div>

<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>
<script type="text/javascript">
    $(function(){
    	if($("#qxmsg").val()!=""){
    		 layer.msg("权限不够", {time:1000, icon:5, shift:6}, function(){});
    	};
    })
    //加入购物车监听事件
    $(".addItemBtn").click(function(){
            window.location.href="${base}CartServlet?action=addItem&id=" + $(this).attr("foodid");
    });
</script>