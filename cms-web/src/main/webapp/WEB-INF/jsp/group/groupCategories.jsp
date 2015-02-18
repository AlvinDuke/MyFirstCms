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

<title>组内栏目</title>
</head>
<body>
	<!-- ztree css -->
	<link rel="stylesheet" href="${ctx}/resources/css/zTreeStyle/zTreeStyle.css" type="text/css">
	
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="page-header">
			<h1>组内栏目</h1>
		</div>
		
		<div class="row placeholders">
		
			<div class="col-xs-6 col-sm-3 placeholder">
				<ul id="tree" class="ztree"></ul>
			</div>

		</div>
	</div>
	<!-- zTree -->
	<script type="text/javascript" src="${ctx}/resources/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery.ztree.excheck-3.5.js"></script>
	<script src="${ctx}/resources/jspJs/group/groupCategories.js"></script>
	<script type="text/javascript">
		var zNodes = ${treeData};
	</script>

</body>
</html>
