<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>你好!</title>

<link href="${ctx}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/resources/css/profile/profile.css" rel="stylesheet">
</head>

<body>

	<div class="valid-layer" id="valid-div">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">请输入贵司网址作为查阅码</h3>
			</div>
			<div class="panel-body">
				<input type="text" class="form-control" id="enterCode"
					placeHolder="形如：www.example.com" />
			</div>
		</div>
	</div>

	<div class="coverDiv" id="coverDiv">
		<img class="loadImg" alt="载入中"
			src="${ctx}/resources/common/c62b549185c110cb3.gif">
	</div>

	<script type="text/javascript"
		src="${ctx}/resources/jQuery/jquery-1.11.1.js"></script>
	<script type="text/javascript"
		src="${ctx}/resources/js/bootstrap.min.js"></script>
		<script type="text/javascript"
		src="${ctx}/resources/jspJs/profile/profile.js"></script>
</body>
</html>
