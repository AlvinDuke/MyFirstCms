<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>用户管理</title>

</head>
<body>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="page-header">
			<h1>用户中心</h1>
		</div>
		<p>
			<a href="${ctx}/user/add" class="btn btn-sm btn-primary">添加用户</a>
		</p>
		<div class="table-responsive">
			<table class="table table-striped">

				<tbody>
					<c:choose>
						<c:when test="${users.datas.size() == 0}">
						还没有记录
						</c:when>
						<c:otherwise>
							<thead>
								<tr>
									<th>用户名</th>
									<th>昵称</th>
									<th>状态</th>
									<th>手机</th>
									<th>添加时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<c:forEach items="${users.datas}" var="u">
								<tr>
									<td><a href="${ctx}/user/show/${u.id}"
										class="btn btn-xs btn-link">${u.username }</a></td>
									<td>${u.nickname }</td>
									<td><c:choose>
											<c:when test="${u.status == 0}">
												<a href="${ctx}/user/toggleStatus/${u.id}"
													class="btn btn-xs btn-danger" title="单击启用该用户">停用中</a>
											</c:when>
											<c:otherwise>
												<a href="${ctx}/user/toggleStatus/${u.id}"
													class="btn btn-xs btn-primary" title="单击停用该用户">启用中</a>
											</c:otherwise>
										</c:choose></td>
									<td>${u.cellPhone }</td>
									<td><fmt:formatDate value="${u.credate }" type="date" pattern="yyyy/MM/dd  aa HH:mm "/></td>
									<td><a href="${ctx}/user/delete/${u.id}"
										class="btn btn-xs btn-danger deleteObj">删除</a> <a
										href="${ctx}/user/update/${u.id}"
										class="btn btn-xs btn-primary">更新</a> <a
										href="${ctx}/user/showCategories/${u.id}"
										class="btn btn-xs btn-primary">查看管理栏目</a></td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="6"><jsp:include page="../inc/pager.jsp">
										<jsp:param value="${ctx}/user/users" name="url" />
										<jsp:param value="${users.totalRecords }" name="items" />
										<jsp:param value="searchDep" name="params" />
									</jsp:include></td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
	<!-- 自定义 -->
	<script src="${ctx}/resources/jspJs/common/commonList.js"></script>
</body>
</html>
