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

<title>角色修正</title>

</head>
<body>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="page-header">
			<h1>角色更新</h1>
			<sf:form role="form" modelAttribute="r">
				<input type="hidden" name="id" value="${r.id }">
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">角色名称</span>
						<input type="text" class="form-control"
							value="${r.roleName}" placeholder="RoleName"
							name="roleName" required/>
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">角色编号</span> 
						<input type="text"
							class="form-control" placeholder="RoleNum" value="${r.roleNum}"
							name="roleNum" required />
					</div>
				</div>
				<div class="form-group">
					角色类型：
					<c:forEach items="${roleTypes}" var="rt">
						<label><input type="radio" value="${rt}" ${rt==r.roleType?' checked="checked"':''}
							name="roleType" />${rt} </label>
					</c:forEach>
				</div>
				<button type="submit" class="btn btn-default">更新角色信息</button>
			</sf:form>
		</div>
	</div>
</body>
</html>
