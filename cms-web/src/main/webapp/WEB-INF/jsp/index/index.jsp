<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>JavaWeb参考手册</title>

<!-- Bootstrap core CSS -->
<link href="${ctx}/resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${ctx}/resources/css/index/carousel.css" rel="stylesheet">
<style type="text/css">
</style>

</head>


<body>
	<!-- 导航 -->

	<div class="navbar-wrapper">
		<div class="container">
			<div class="blog-masthead">
				<div class="container">
					<nav class="blog-nav"> 
						<a class="blog-nav-item fixed-width" href="${ctx}/arts/10006">java</a>
						<a class="blog-nav-item fixed-width" href="${ctx}/arts/10007">mySql</a>
						<a class="blog-nav-item fixed-width" href="${ctx}/arts/10011">linux</a>
						<a class="blog-nav-item fixed-width" href="${ctx}/arts/10008">struts2</a>
						<a class="blog-nav-item fixed-width" href="${ctx}/arts/10010">jQuery</a>
						<a class="blog-nav-item fixed-width" href="${ctx}/arts/10018">css</a>
						<a class="blog-nav-item fixed-width" href="${ctx}/arts/9999">更多&gt;</a>  
					</nav>
				</div>
			</div>
		</div>
	</div>

	<!-- 滚动栏目 -->
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<c:forEach items="${iis}" var="i" varStatus="curSt">
				<li data-target="#myCarousel" data-slide-to="${curSt.index}"
					${curSt.index == 0?'class="active"':''}></li>
			</c:forEach>
		</ol>
		<div class="carousel-inner" role="listbox">
			<c:forEach items="${iis}" var="i" varStatus="curSt">
				<div class="item ${curSt.index == 0?'active':''}">
					<img src="${i.accessUrl}thumbnail_${i.newName}"
						title="${i.subTitle }" style="cursor: pointer;"
						onclick="javascript:window.location.href='${ctx}${i.link}';">
					<div class="container">
						<div class="carousel-caption">
							<h1>${i.mainTitle}</h1>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">上一个</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">下一个</span>
		</a>
	</div>

	<!-- 主体内容 -->
	<div class="container marketing">

		<!-- 最新添加 -->
		<div class="container marketing">
			<div class="col-lg-6" style="width: 100%">
				<c:forEach items="${latestArts }" var="a">
					<h4>${a.isRepost==0?'<span class="label label-primary" style="line-height: 19px;vertical-align: bottom;margin-right: 10px;">原</span>':'<span class="label label-danger" style="line-height: 19px;vertical-align: bottom;margin-right: 10px;">转</span>'}<a
							href="${ctx}/arts/show/${a.id}">${a.title }</a>
					</h4>
					<p>
						<fmt:formatDate value="${a.creDate }" type="date"
							pattern="yyyy/MM/dd  aa HH:mm " />
						&nbsp;作者：${a.author}&nbsp;阅读(${a.readCount})
					</p>
					<p>${a.summary}</p>
					<hr>
				</c:forEach>
			</div>
		</div>

		<!-- 底部 -->
		<footer>
		<p class="pull-right">
			<a href="#">回顶部</a>
		</p>
		<p>
			<a href="${ctx}/user/users">Powered by 都较瘦 Copyright © 都较瘦</a>
			<object type="application/x-shockwave-flash" style="outline:none;" data="http://cdn.abowman.com/widgets/fish/fish.swf?" width="300" height="200"><param name="movie" value="http://cdn.abowman.com/widgets/fish/fish.swf?"></param><param name="AllowScriptAccess" value="always"></param><param name="wmode" value="opaque"></param><param name="scale" value="noscale"/><param name="salign" value="tl"/></object>
		</p>
		</footer>

	</div>


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript"
		src="${ctx}/resources/jQuery/jquery-1.11.1.js"></script>
	<script type="text/javascript"
		src="${ctx}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${ctx}/resources/assets/js/docs.min.js"></script>
</body>
</html>
