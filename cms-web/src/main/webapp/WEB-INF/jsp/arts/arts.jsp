<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>JavaWeb参考手册</title>

</head>

<body>
	<div class="container" style="margin-top: 5%;">
		<div class="blog-header">
			<h1 class="blog-title">
				<c:choose>
					<c:when test="${dateCon!=null&&dateCon!=''}">
						${dateCon}期间
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${c!=null}">
						${c.name}栏目
					</c:when>
					<c:otherwise>
						${c.name}
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${condition!=null&&condition!=''}">
						包含关键字[${condition}]的内容
					</c:when>
					<c:otherwise>
						的内容
					</c:otherwise>
				</c:choose>
			</h1>
			<!-- 副标题 -->
			<!-- <p class="lead blog-description">The official example template of
				creating a blog with Bootstrap.</p> -->
		</div>

		<div class="row">

			<div class="col-sm-8 blog-main">
				<c:if test="${articles.datas.size() == 0}">
					暂无
				</c:if>

				<c:forEach items="${articles.datas}" var="a">
					<div class="blog-post">
						${a.isRepost==0?'<span class="label label-primary" style="line-height: 24px;vertical-align: bottom;margin-right: 10px;">原</span>':'<span class="label label-danger" style="line-height: 24px;vertical-align: bottom;margin-right: 10px;">转</span>'}
						<h2 class="blog-post-title" style="display: inline;">
							<a href="${ctx}/arts/show/${a.id}">${a.title }</a>
						</h2>
						<p class="blog-post-meta">
							<fmt:formatDate value="${a.creDate }" type="date"
								pattern="yyyy/MM/dd  aa HH:mm " />
							&nbsp;作者：${a.author}&nbsp;阅读(0)
						</p>
						<p>${a.summary }</p>
						<hr>
					</div>
				</c:forEach>

				<nav>
				<ul class="pager">
					<jsp:include page="../inc/simplePager.jsp">
						<jsp:param value="${ctx}/arts/${c.id}" name="url" />
						<jsp:param value="${articles.totalRecords }" name="items" />
						<jsp:param value="condition,cid,status" name="params" />
					</jsp:include>
				</ul>
				</nav>

			</div>
			<!-- /.blog-main -->

			<div class="col-sm-3 col-sm-offset-1 blog-sidebar">
				<div class="sidebar-module sidebar-module-inset">
					<h4>关于</h4>
					<p>${about}</p>
				</div>
				<div class="sidebar-module">
					<h4>文章</h4>
					<ol class="list-unstyled">
						<li><a href="javascript:;" id="noDate">全部</a></li>
						<c:forEach items="${artsGroupByMonth}" var="agm">
							<li><a href="#" id="${agm[0]}">${agm[0]}(${agm[1]})</a></li>
						</c:forEach>
					</ol>
				</div>
				<div class="sidebar-module">
					<h4>其他</h4>
					<ol class="list-unstyled">
						<li><a href="http://www.blogjava.net/produ">blogjava</a></li>
					</ol>
				</div>
			</div>
			<!-- /.blog-sidebar -->

		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->

</body>
</html>