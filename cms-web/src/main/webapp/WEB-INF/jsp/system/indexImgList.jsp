<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>文章管理</title>

</head>
<body>

	<link rel="stylesheet" href="${ctx}/resources/css/system/indexImgList.css"
		type="text/css">

	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="page-header">
			<h1>首页信息管理</h1>
		</div>
		<p>
			<a href="${ctx}/system/indexImgInput" class="btn btn-sm btn-primary">新增滚动栏目信息</a>
			<a id="updateIndex" class="btn btn-sm btn-primary">调整滚动栏目顺序</a>
			<a id="cofim" class="btn btn-sm btn-primary">确定</a>
			<a id="cacle" class="btn btn-sm btn-danger">取消</a>
			<span class="updTip">请按照希望出现的顺序选择首页图，单击确定保存</span>
		</p>
		<div class="table-responsive">
			<table class="table table-striped">
				<tbody>
					<c:choose>
						<c:when test="${indexImgs.size() == 0}">
						还没有记录
						</c:when>
						<c:otherwise>
							<thead>
								<tr>
									<th></th>
									<th>预览</th>
									<th>创建日期</th>
									<th>大小</th>
									<th>主标题</th>
									<th>副标题</th>
									<th>超链接</th>
									<th>操作</th>
								</tr>
							</thead>
							<c:forEach items="${indexImgs}" var="i">
								<tr>
									<td><input type="checkbox" value="${i.id}" class="forOpt"/><div></div></td>
									<td><img src="${i.accessUrl}/preview_${i.newName}"> </td>
									<td><fmt:formatDate value="${i.creDate }" type="date" dateStyle="LONG"/></td>
									<td>${i.indexImgSize/1000}KB</td>
									<td>${i.mainTitle }</td>
									<td>${i.subTitle }</td>
									<td>${i.link==""?"无":i.link}</td>
									<td><a href="${ctx}/system/delete/${i.id}"
										class="btn btn-xs btn-danger deleteObj">删除</a></td>
								</tr>
							</c:forEach>
						</c:otherwise>	
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
	<!-- 自定义 -->
	<script src="${ctx}/resources/jspJs/common/commonList.js"></script>
	<script src="${ctx}/resources/jspJs/system/indexImgList.js"></script>
	<!-- dwr -->
	<script type='text/javascript' src='${ctx}/dwr/engine.js'></script>
    <script type='text/javascript' src='${ctx}/dwr/interface/updIdxOrder.js'></script>
	<script type="text/javascript">
		$(function(){
			
			
		});
	
	</script>
</body>
</html>
