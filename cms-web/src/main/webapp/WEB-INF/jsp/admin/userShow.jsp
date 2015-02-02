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

<title>用户详情</title>

</head>
<body>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="page-header">
			<h1>用户详情</h1>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</span>
					<input type="text" class="form-control" value="${user.username}"
						placeholder="Nickname" name="user.nickname" required />
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</span>
					<input type="text" class="form-control" value="${user.nickname}"
						placeholder="Nickname" name="user.nickname" required />
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">移动电话</span> <input type="text"
						class="form-control" value="${user.cellPhone}"
						placeholder="mobilePhone" name="user.cellPhone" required
						pattern="^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])\d{8}$"
						title="请输入正确的手机号码" />
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">电子邮件</span> <input type="email"
						class="form-control" placeholder="email" value="${user.email}"
						name="user.email" required />
				</div>
			</div>
			<div class="form-group">
				本用户拥有的角色：
				<c:forEach items="${roles}" var="r">
					<a href="${ctx}/role/show/${r.id}" class="btn btn-xs btn-primary">${r.roleName}</a>
				</c:forEach>
			</div>
			<div class="form-group">
				本用户所在的组：
				<c:forEach items="${groups}" var="g">
					<a href="${ctx}/group/show/${g.id}" class="btn btn-xs btn-primary">${g.name}</a>
				</c:forEach>
			</div>
			<div class="form-group">用户状态： ${user.status == 0?'<span class="label label-danger">停用</span>'
						:'<span class="label label-success">启用</span>'}
			</div>
		</div>
	</div>
	<!-- 自定义 -->
	<script src="${ctx}/resources/jspJs/common/commonShow.js"></script>
</body>
</html>
