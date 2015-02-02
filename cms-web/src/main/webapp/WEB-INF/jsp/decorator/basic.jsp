<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@include file="../inc/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!-- <link rel="icon" href="../../favicon.ico"> -->

<title><decorator:title/></title>

<!-- Bootstrap core CSS -->
<link href="${ctx}/resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${ctx}/resources/css/index/index.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="${ctx}/resources/assets/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">都较瘦的博客</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">博客首页</a></li>
				<li><a href="#">用户设置</a></li>
				<li><a href="#">个人履历</a></li>
				<li><a href="#">帮助中心</a></li>
			</ul>
			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="Search...">
			</form>
		</div>
	</div>
	</nav>

	<div class="container-fluid" id="left-menu">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li><a href="${ctx}/user/users">用户管理</a></li>
					<li><a href="${ctx}/group/groups">用户组管理</a></li>
					<li><a href="${ctx}/role/roles">用户角色管理</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="${ctx}/category/categorys">栏目管理</a></li>
					<li><a href="">Nav item again</a></li>
					<li><a href="">One more nav</a></li>
					<li><a href="">Another nav item</a></li>
					<li><a href="">More navigation</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="">Nav item again</a></li>
					<li><a href="">One more nav</a></li>
					<li><a href="">Another nav item</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- 当前选中模块的标记，例如：user,role,group等等 -->
	<input type="hidden" value="${seldModlName}" id="seldModlName">
	<!-- 上下文根路径 -->
	<input type="hidden" value="${ctx}" id="root">
	<!-- 页面主体内容 -->
	<script type="text/javascript" src="${ctx}/resources/jQuery/jquery-1.11.1.js"></script>
	<decorator:body/>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="${ctx}/resources/js/bootstrap.min.js"></script>
	<script src="${ctx}/resources/assets/js/docs.min.js"></script>
	<script
		src="${ctx}/resources/assets/js/ie10-viewport-bug-workaround.js"></script>
	<!-- 自定义 -->
	<script src="${ctx}/resources/jspJs/common/commonMenu.js"></script>
</body>
</html>
