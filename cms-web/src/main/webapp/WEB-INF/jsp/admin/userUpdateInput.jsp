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

<title>用户修正</title>

</head>
<body>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="page-header">
			<h1>用户更新</h1>
			<sf:form role="form" modelAttribute="ud">
				<input type="hidden" name="user.id" value="${ud.user.id }">
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</span>
						<input type="text" class="form-control"
							value="${ud.user.nickname}" placeholder="Nickname"
							name="user.nickname" required />
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">移动电话</span> <input type="text"
							class="form-control" value="${ud.user.cellPhone}"
							placeholder="mobilePhone" name="user.cellPhone" required
							pattern="^(13[0-9]|15[0|3|6|7|8|9]|18[5|8|9])\d{8}$"
							title="请输入正确的手机号码" />
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">电子邮件</span> <input type="email"
							class="form-control" placeholder="email" value="${ud.user.email}"
							name="user.email" required />
					</div>
				</div>
				<div class="form-group">
					用户角色： <input type="hidden" value="${ud.roleIds}" id="selRids" />
					<c:forEach items="${roles}" var="r">
						<label><input type="checkbox" value="${r.id }"
							name="roleIds" />${r.roleName} </label>
					</c:forEach>
				</div>
				<div class="form-group">
					用户组： <input type="hidden" value="${ud.groupIds}" id="selGids" />
					<c:forEach items="${groups}" var="g">
						<label><input type="checkbox" value="${g.id }"
							name="groupIds" />${g.name} </label>
					</c:forEach>
				</div>
				<div class="form-group">
					用户状态： <label><input type="radio" value="0"
						${ud.user.status == 0?'checked="checked"':''} name="user.status" />停用</label>
					<label><input type="radio" value="1"
						${ud.user.status == 1?'checked="checked"':''} name="user.status" />启用</label>
				</div>
				<button type="submit" class="btn btn-default">更新用户</button>
			</sf:form>
		</div>
	</div>
	<!-- 自定义 -->
	<script src="${ctx}/resources/jspJs/admin/userUpdateInput.js"></script>
</body>
</html>
