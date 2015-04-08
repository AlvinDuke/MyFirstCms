<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>首页管理</title>
</head>
<body>
	<link rel="stylesheet" href="${ctx}/resources/css/article/article.css"
		type="text/css">
	<link rel="stylesheet" href="${ctx}/resources/css/system/indexImg.css"
		type="text/css">
	<link rel="stylesheet" href="${ctx}/resources/css/uploadify.css"
		type="text/css">

	<!-- jcrop -->
	<script type="text/javascript"
		src="${ctx}/resources/js/jcrop/jquery.Jcrop.js"></script>
	<link rel="stylesheet"
		href="${ctx}/resources/css/jcrop/jquery.Jcrop.css" type="text/css">


	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="page-header">
			<h1>新增首页欢迎信息</h1>
		</div>
		<sf:form role="form" modelAttribute="iiDto" method="POST">
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">主标题</span> <input type="text"
						class="form-control" placeHolder="请填写文章主标题（选填）"
						name="mainTitle" />
				</div>
			</div>

			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">附标题</span> <input type="text"
						class="form-control" placeHolder="请填写文章附标题（选填）"
						name="subTitle" />
				</div>
			</div>

			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">超链接</span> <input type="text"
						class="form-control" placeHolder="请填写文章超链接（选填）"
						name="link" />
				</div>
			</div>

			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">图片上传</span>
					<div class="eleholder">
						<input id="file_upload" name="file_upload" type="file"
							multiple="true">
							<a class="btn btn-sm btn-primary"
							href="javascript:$('#file_upload').uploadify('upload','*')">上传已选文件</a>
							<p>图片的尺寸不小于1920*500</p>
					</div>
					<input id="idxId" type="hidden" name="imgId"/>
				</div>
			</div>

			<div class="form-group imgPreview">
				<div class="input-group">
					<span class="input-group-addon">图像剪裁</span>
					<div class="eleholder target">
						<span class="cropTip">请拖动选框至合适的位置后保存信息</span><img id="target"
							class="idximg">
					</div>
				</div>
				<input id="cropedY" type="hidden" name="cropedY"/>
			</div>
			<button type="submit" class="btn btn-default" id="addIdxImg">保存信息</button>
		</sf:form>
	</div>


	<script type="text/javascript"
		src="${ctx}/resources/js/uploadify/jquery.uploadify.js"></script>
	<script type="text/javascript"
		src="${ctx}/resources/jspJs/system/indexImgInput.js"></script>

</body>
</html>