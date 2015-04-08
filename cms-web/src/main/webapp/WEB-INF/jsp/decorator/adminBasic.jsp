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

<title><decorator:title/></title>

<!-- Bootstrap core CSS -->
<link href="${ctx}/resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${ctx}/resources/css/adminBasic/index.css" rel="stylesheet">

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
			<a class="navbar-brand" href="/">你好[${sessionScope.loginUser.nickname }],欢迎来到都较瘦博客后台管理</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${ctx}/user/updateSelf">资料变更</a></li>
				<li><a href="${ctx}/user/updatePwdInput">密码变更</a></li>
				<li><a href="${ctx}/common/logout">退出登录 </a></li>
			</ul>
			<!-- <form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="Search...">
			</form> -->
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
					<li><a href="${ctx}/article/articles?status=1">文章管理</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="${ctx}/system/indexImgs">首页管理</a></li>
					<li><a href="${ctx}/webInfo/websiteInfos">系统管理</a></li>
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
	<script type="text/javascript">
		//上下文根目录
		var root = $("#root").val();
	</script>
	<decorator:body/>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="${ctx}/resources/js/bootstrap.min.js"></script>
	<script src="${ctx}/resources/assets/js/docs.min.js"></script>
	<script src="${ctx}/resources/assets/js/ie10-viewport-bug-workaround.js"></script>
	<!-- 自定义 -->
	<script src="${ctx}/resources/jspJs/common/commonMenu.js"></script>
</body>
</html>
