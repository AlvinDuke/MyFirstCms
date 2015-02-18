<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>请登录</title>

<!-- Bootstrap core CSS -->
<link href="${ctx}/resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${ctx}/resources/css/signin/signin.css" rel="stylesheet">

</head>
<body>

	<div class="container">

		<form class="form-signin" id="loginform" action="login" method="post">
			<h2 class="form-signin-heading">用户登录</h2>
			<div class="form-group">
				<input type="text" id="inputUsername" class="form-control" name="username" 
				placeholder="用户名称" required autofocus> 
				
				<input type="password" id="inputPassword" class="form-control" name="password" 
				placeholder="密       码" required>
				
				<input type="text" id="inputCheckCode" class="form-control captcha" name="checkcode" 
				placeholder="输入验证码" required>
				<img id="cCodeImg" alt="验证码" src="${ctx}/common/captcha" style="display: inline;width: 140px;cursor: pointer;"/>
				
			</div>
				
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					记住我
				</label>
			</div>
			<button id="postFrom" class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
		</form>

	</div>
	<script type="text/javascript" src="${ctx}/resources/jQuery/jquery-1.11.1.js"></script>
	<script type="text/javascript">
		$(function(){
			//验证码重设
			$("#cCodeImg").on("click",function(){
				this.src = '${ctx}/common/captcha?' + new Date();
			});
			//用户名密码检查
			$("#loginform").on("submit",function(){
				//验证码校验
				$.ajax({
					url : '${ctx}/common/ChkCaptcha',
					data: {"checkcode":$("#inputCheckCode").val()},
				}).done(function(data) {
					if(data == 0){
						alert("请输入正确的验证码");
						$("#cCodeImg").click();
					}
					else{
						//继续验证
						$.ajax({
							url : '${ctx}/common/login',
							data: {"username":$("#inputUsername").val(),"password":$("#inputPassword").val()},
							type: 'post'
						}).done(function(data) {
							if(data == 0){
								alert("用户名或密码错误");
								$("#cCodeImg").click();
							}
							else{
								window.location.href = '${ctx}/user/users';
							}
						});
					}
				}).fail(function(){
					alert("提交失败，请重试");
					$("#cCodeImg").click();
				});
				return false;
			});

		});
	</script>
</body>
</html>