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

<title>用户录入</title>

</head>
<body>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="page-header">
			<h1>用户新增</h1>
			<sf:form role="form" modelAttribute="ud">
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</span>
						<input type="text" class="form-control" placeholder="Username"
							name="user.username" required autofocus
							pattern="^[A-z0-9_-]{6,10}$" title="只能包含字母、数字、下划线或连字符，至少6位，最多10位" />
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</span>
						<input type="text" class="form-control" placeholder="Nickname"
							name="user.nickname" required />
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</span>
						<input type="password" class="form-control" placeholder="password"
							name="user.password" required />
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">密码重复</span> <input type="password"
							class="form-control" placeholder="password Repeat" required>
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">移动电话</span> <input type="text"
							class="form-control" placeholder="mobilePhone"
							name="user.cellPhone" required
							pattern="^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])\d{8}$"
							title="请输入正确的手机号码" />
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">电子邮件</span> <input type="email"
							class="form-control" placeholder="email" name="user.email"
							required />
					</div>
				</div>
				<div class="form-group">
					用户角色：
					<c:forEach items="${roles}" var="r">
						<label> <input type="checkbox" value="${r.id }"
							name="groupIds" />${r.roleName}
						</label>
					</c:forEach>
				</div>
				<div class="form-group">
					用户组：
					<c:forEach items="${groups}" var="g">
						<label><input type="checkbox" value="${g.id }"
							name="roleIds" />${g.name} </label>
					</c:forEach>
				</div>
				<div class="form-group">
					用户状态： <label><input type="radio" value="0"
						name="user.status" />停用</label> <label><input type="radio"
						value="1" name="user.status" />启用</label>
				</div>
				<button type="submit" class="btn btn-default">保存用户</button>
			</sf:form>
		</div>
	</div>
</body>
</html>
