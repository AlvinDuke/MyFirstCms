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

<title>角色详情</title>

</head>
<body>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="page-header">
			<h1>角色详情</h1>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">角色名称</span>
					<input type="text" class="form-control" value="${r.roleName}"
						placeholder="RoleName" name="r.roleName" required />
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">角色编号</span>
					<input type="text" class="form-control" value="${r.roleNum}"
						placeholder="RoleNum" name="r.roleNum" required />
				</div>
			</div>
			<div class="form-group">
				属于本角色的用户：
				<c:forEach items="${us}" var="u">
					<a href="${ctx}/user/show/${u.id}" class="btn btn-xs btn-primary">${u.nickname}</a>
				</c:forEach>
			</div>
		</div>
	</div>
	<!-- 自定义 -->
	<script src="${ctx}/resources/jspJs/common/commonShow.js"></script>
</body>
</html>
