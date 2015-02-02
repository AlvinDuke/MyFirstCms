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

<title>用户角色管理</title>

</head>
<body>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="page-header">
			<h1>用户角色中心</h1>
		</div>
		<p>
			<a href="${ctx}/role/add" class="btn btn-sm btn-primary">添加角色</a>
		</p>
		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>角色名称</th>
						<th>角色编号</th>
						<th>角色类型</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${roles.datas}" var="r">
						<tr>
							<td><a href="${ctx}/role/show/${r.id}"
								class="btn btn-xs btn-link">${r.roleName}</a></td>
							<td>${r.roleNum}</td>
							<td>${r.roleType}</td>
							<td><a href="${ctx}/role/delete/${r.id}"
								class="btn btn-xs btn-danger deleteObj">删除</a> <a
								href="${ctx}/role/update/${r.id}" class="btn btn-xs btn-primary">更新</a></td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="6"><jsp:include page="../inc/pager.jsp">
								<jsp:param value="${ctx}/role/roles" name="url" />
								<jsp:param value="${roles.totalRecords }" name="items" />
							</jsp:include></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<!-- 自定义 -->
	<script src="${ctx}/resources/jspJs/common/commonList.js"></script>
</body>
</html>
