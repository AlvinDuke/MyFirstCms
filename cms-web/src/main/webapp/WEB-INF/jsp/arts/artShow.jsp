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
	
	<%-- <link rel="stylesheet" href="${ctx}/resources/js/plugins/code/EditorNeededCss.css" type="text/css">  --%>
	<link rel="stylesheet" href="${ctx}/resources/js/sh/styles/shCore.css" type="text/css" >
	<link rel="stylesheet" href="${ctx}/resources/js/sh/styles/shCoreMidnight.css" type="text/css" >
	<div class="container" style="margin-top: 5%;">
		<div class="blog-header">
			<h1 class="blog-title">${a.title }</h1>
		</div>

		<div class="row" style="position: relative;">

			<div class="col-sm-8 blog-main" style="width: 100%">
				<div class="blog-post">
					<p>${a.content}</p>
					<hr>
					<c:forEach items="${attachs}" var="a">
						<a href="${a.accessUrl}">${a.oriName}</a><br>
					</c:forEach>
					
					
				</div>
			</div>
			<!-- /.blog-main -->

			<div class="col-sm-3 col-sm-offset-1 blog-sidebar" style="position: absolute;top: 0;left: 100%">
				<div class="sidebar-module sidebar-module-inset">
					<h4>关于</h4>
					<p>
						${about}
					</p>
				</div>
				<div class="sidebar-module">
					<h4>文章</h4>
					<ol class="list-unstyled">
						<li><a href="#" id="noDate">全部</a></li>
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
	<script type="text/javascript" src="${ctx}/resources/js/sh/scripts/shCore.js"/></script>
	<script type="text/javascript" src="${ctx}/resources/js/sh/scripts/shBrushJava.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/sh/scripts/shBrushBash.js"></script>       
    <script type="text/javascript" src="${ctx}/resources/js/sh/scripts/shBrushCpp.js"></script>       
    <script type="text/javascript" src="${ctx}/resources/js/sh/scripts/shBrushCSharp.js"></script>       
    <script type="text/javascript" src="${ctx}/resources/js/sh/scripts/shBrushCss.js"></script>       
    <script type="text/javascript" src="${ctx}/resources/js/sh/scripts/shBrushDelphi.js"></script>       
    <script type="text/javascript" src="${ctx}/resources/js/sh/scripts/shBrushDiff.js"></script>       
    <script type="text/javascript" src="${ctx}/resources/js/sh/scripts/shBrushGroovy.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/sh/scripts/shBrushJScript.js"></script>       
    <script type="text/javascript" src="${ctx}/resources/js/sh/scripts/shBrushPhp.js"></script>       
    <script type="text/javascript" src="${ctx}/resources/js/sh/scripts/shBrushPlain.js"></script>       
    <script type="text/javascript" src="${ctx}/resources/js/sh/scripts/shBrushPython.js"></script>       
    <script type="text/javascript" src="${ctx}/resources/js/sh/scripts/shBrushRuby.js"></script>       
    <script type="text/javascript" src="${ctx}/resources/js/sh/scripts/shBrushScala.js"></script>       
    <script type="text/javascript" src="${ctx}/resources/js/sh/scripts/shBrushSql.js"></script>       
    <script type="text/javascript" src="${ctx}/resources/js/sh/scripts/shBrushVb.js"></script>       
    <script type="text/javascript" src="${ctx}/resources/js/sh/scripts/shBrushXml.js"></script>
	<script type="text/javascript">
		SyntaxHighlighter.all(); 
	</script>
</body>
</html>