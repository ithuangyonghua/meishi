<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.Date,java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会员注册页面</title>
<%@include file="/jsp/common/style.jsp"%>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
</style>
	<script type="text/javascript">
		 $(function(){
			  //验证用户名是否存在
			  $("#username").blur(function(){
				  //获取用户名
				  var username  = $(this).val();
				  $.getJSON("${path}/UserServlet",{"action":"exitsUserName",
					  "username":username},function(data){
						  if(!data.code){
							  $(".errorMsg").text("用户名已存在");
						  }else{
							  $(".errorMsg").text(""); 
						  }
					  });
			  });
             //切换验证码
             $("#yzmimg").click(function () {
                this.src="${path}kaptcha.jpg?d" + new Date();
             });
             //表单提交检验
			 $("#sub_btn").click(function(){
                 //1.验证用户名
				 debugger;
                 var username = $("#username").val();
                 var usernamePat = /^\w{5,12}$/;
                 if(!usernamePat.test(username)){
                     $(".errorMsg").text("用户名格式有误,请重新输入");
                     return false;//注意是return false
                 }
                 //2.验证密码
                 var password = $("#password").val();
                 var passwordPat = /^\w{5,12}$/;
				 if(!passwordPat.test(password)){
                     $(".errorMsg").text("密码格式有误,请重新输入");
                     return false;//注意是return false
				 }
				 //3.验证确认密码
				 var repassword = $("#repwd").val();
                 if(repassword!=password){
                     $(".errorMsg").text("两次输入密码不一致,请重新输入");
                     return false;//注意是return false
				 }
                 //4.验证性别
                 var sex = $('input:radio[name=sex]:checked').val();
                 if(sex==null){
                	 layer.msg("请选择性别", {time:1000, icon:5, shift:6}, function(){
                		 
                	 });
                	 return false;
                 }
                 //5.验证籍贯
                 if($("#address option:selected").val()==""){
                      layer.msg("请选择籍贯", {time:1000, icon:5, shift:6}, function(){
                		 
                	  });
                	  return false;
                 }
                 //6.验证验证码
                 var code = $("#code").val();
                 if(code==""){
                     layer.msg("请输入验证码", {time:1000, icon:5, shift:6}, function(){
                		 
                	 });
                	 return false;
                 }
                 $(".errorMsg").text("");
			 });
		 });


	</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="img/logo.png" >
		</div>
			<div class="login_banner">
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册会员</h1>
								<span class="errorMsg">
								    ${empty msg?"":msg}
								</span>
							</div>
							<div class="form">
								<form action="UserServlet" method="post">
									<input type="hidden" name="action" value="regist"/>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" value="hyh19981" tabindex="1" name="name" id="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" value="123456" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off"  value="123456" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<%-- <label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" value="${email}" tabindex="1" name="email" id="email" />
									<br /> --%>
									<label class="addcs">性别：</label>
									 <span>男</span> &nbsp;&nbsp;<input type="radio" name="sex" value="0"/> <span>女</span>&nbsp;&nbsp;<input name="sex" value="1" type="radio"/>
									<br />
									<br />
									<label class="addcs">籍贯：</label>
									<!-- 江苏、山东、上海、安徽 -->
									 <select class="jg" id="address" name="address">
									  <option value="">请选择籍贯</option>
									  <option value="江苏">江苏</option>
									  <option value="山东">山东</option>
									  <option value="上海">上海</option>
									  <option value="安徽">安徽</option>
									 </select>
									<br />
									<br />
									<label class="yzm">验证码：</label>
									<input class="itxt" type="text" style="width: 100px;"  name="yzm" id="code"/>
									<img alt=""  id="yzmimg" src="kaptcha.jpg" style="float: right; margin-right: 70px ;width:100px;height:40px" >
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>
<script>

</script>