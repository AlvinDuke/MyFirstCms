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

<title>变更密码</title>

</head>
<body>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="page-header">
			<h1>变更密码</h1>
			<form role="form" id="loginform">

				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">旧密码</span>
						<input type="password" class="form-control" placeholder="old password" id="oldpwd"
							required />
					</div>
				</div>
				
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">新密码</span>
						<input type="password" class="form-control" placeholder="new password" id="newpwd"
							name="newPassword" required />
					</div>
				</div>
				
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">重复新密码</span>
						<input type="password" class="form-control" placeholder="new password repeat" id="newpwdre"
						required />
					</div>
				</div>
				
				<button type="submit" id="updatePwd" class="btn btn-default">确认修改</button>
				<input type="hidden" name="id" id="uid" value="${sessionScope.loginUser.id}"/>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			//旧密码检查
			$("#loginform").on("submit",function(){
				//新密码前后一致
				if($("#newpwd").val() != $("#newpwdre").val()){
					alert("新密码两次填写的不同，请检查");
					return false;
				}
				//旧密码检查
				$.ajax({
					url : '${ctx}/user/Chkpwd',
					data: {"pwd":$("#oldpwd").val(),"uid":$("#uid").val()},
				}).done(function(data) {
					if(data == 0){
						alert("旧密码不正确");
					}
					else{
						$.ajax({
							url : '${ctx}/user/updatePwd',
							type: 'post',
							data: {"id":$("#uid").val(),"newPassword":$("#newpwd").val()}
						}).done(function(data){
							if(data){
								window.location.href = '${ctx}/user/users';
							}
							else{
								 alert("更新失败请重试");
							}
							
						});
					}
				}).fail(function(){
					alert("修改失败，请重试");
				});
				return false;
			});

		});
	</script>
</body>
</html>
