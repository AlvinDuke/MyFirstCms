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
<title>关于信息</title>
</head>
<body>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="page-header">
			<h1>关于</h1>
			<sf:form role="form" modelAttribute="g" action="${ctx}/webInfo/saveInfos">
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">内容：</span>
						<input type="text" class="form-control" placeholder="博客信息" value="${about}"
							name="websiteInfo" required autofocus/>
					</div>
				</div>
				<button type="submit" class="btn btn-default">保存</button>
			</sf:form>
		</div>
		<div class="page-header">
			<h1>个人简历查阅码</h1>
			<sf:form role="form" modelAttribute="g" action="${ctx}/webInfo/saveSites">
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">内容：</span>
						<input type="text" class="form-control" placeholder="个人简历查阅码" value="${invitedSite}"
							name="accessCode" required autofocus/>
					</div>
				</div>
				<button type="submit" class="btn btn-default">保存</button>
			</sf:form>
		</div>
	</div>
</body>
</html>
