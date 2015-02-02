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

<title>用户组录入</title>

</head>
<body>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="page-header">
			<h1>新增组</h1>
			<sf:form role="form" modelAttribute="g">
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">组&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</span>
						<input type="text" class="form-control" placeholder="name"
							name="name" required autofocus/>
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述</span>
						<input type="text" class="form-control" placeholder="description"
							name="descri" required />
					</div>
				</div>
				<button type="submit" class="btn btn-default">保存组信息</button>
			</sf:form>
		</div>
	</div>
</body>
</html>
