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

<title>个人资料</title>

</head>
<body>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="page-header">
			<h1>个人资料</h1>
			<sf:form role="form" modelAttribute="u">

				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</span>
						<input type="text" class="form-control" value="${sessionScope.loginUser.nickname}"
							placeholder="Nickname" name="nickname" required />
					</div>
				</div>
				
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">移动电话</span> <input type="text"
							class="form-control" value="${sessionScope.loginUser.cellPhone}"
							placeholder="mobilePhone" name="cellPhone" required
							pattern="^(13[0-9]|15[0|3|6|7|8|9]|18[5|8|9])\d{8}$"
							title="请输入正确的手机号码" />
					</div>
				</div>
				
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">电子邮件</span> <input type="email"
							class="form-control" placeholder="email" value="${sessionScope.loginUser.email}"
							name="email" required />
					</div>
				</div>
				
				<button type="submit" class="btn btn-default">确认修改</button>
				<input type="hidden" name="id" value="${sessionScope.loginUser.id}"/>
			</sf:form>
		</div>
	</div>
</body>
</html>
