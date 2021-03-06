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

<title>栏目管理</title>
</head>
<body>
	<!-- ztree css -->
	<link rel="stylesheet" href="${ctx}/resources/css/zTreeStyle/zTreeStyle.css" type="text/css">
	
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="page-header">
			<h1>栏目变更</h1>
		</div>
		
		<div class="row placeholders">
		
			<div class="col-xs-6 col-sm-3 placeholder">
				<ul id="tree" class="ztree"></ul>
			</div>

			<div class="table-responsive">
 				<sf:form role="form" modelAttribute="c">
 				<input type="hidden" name="id" value="${c.id}"/>
 				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">父级栏目</span>
						<input type="text" class="form-control" value="${c.category.name}"
						   name="c.category.name" disabled="disabled"/>
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">栏目名称</span>
						<input type="text" class="form-control" placeholder="Name"
							name="name" value="${c.name}" required autofocus/>
					</div>
				</div>
				<div class="form-group">
					定制栏目： <label><input type="radio" value="0"
						name="isCustom" ${c.isCustom==0?'checked="checked"':'' }/>否</label> <label><input type="radio"
						value="1" name="isCustom" ${c.isCustom==1?'checked="checked"':'' }/>是</label>
				</div>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">链接内容</span> <input type="text"
							class="form-control" placeholder="link" name="customLink" value="${c.customLink}">
					</div>
				</div>
				<div class="form-group">
					属于导航栏： <label><input type="radio" value="0"
						name="isNavCate" ${c.isNavCate==0?'checked="checked"':'' } />否</label> <label><input type="radio"
						value="1" name="isNavCate" ${c.isNavCate==1?'checked="checked"':'' }/>是</label>
				</div>
				<div class="form-group">
					属于列表栏： <label><input type="radio" value="0"
						name="isListCate" ${c.isListCate==0?'checked="checked"':'' }/>否</label> <label><input type="radio"
						value="1" name="isListCate" ${c.isListCate==1?'checked="checked"':'' }/>是</label>
				</div>
				<div class="form-group">
					属于图集栏： <label><input type="radio" value="0"
						name="isImgCate" ${c.isImgCate==0?'checked="checked"':'' }/>否</label> <label><input type="radio"
						value="1" name="isImgCate" ${c.isImgCate==1?'checked="checked"':'' }/>是</label>
				</div>
				<div class="form-group">
					是否推荐： <label><input type="radio" value="0"
						name="isRecommended" ${c.isRecommended==0?'checked="checked"':'' }/>否</label> <label><input type="radio"
						value="1" name="isRecommended" ${c.isRecommended==1?'checked="checked"':'' }/>是</label>
				</div>
				<div class="form-group">
					栏目类别：
					<c:forEach items="${types}" var="t">
						<label> <input type="radio" value="${t.key }"
							name="categoryType" ${c.categoryType==t.key?'checked="checked"':'' }required/>${t.value}
						</label>
					</c:forEach>
				</div>
				<div class="form-group">
					栏目状态： <label><input type="radio" value="0"
						name="status" ${c.status==0?'checked="checked"':'' }/>停用</label> <label><input type="radio"
						value="1" name="status" ${c.status==1?'checked="checked"':'' }/>启用</label>
				</div>
				<button type="submit" class="btn btn-default">更新栏目</button>
			</sf:form>
			</div>
		</div>
	</div>
	<!-- zTree -->
	<script type="text/javascript" src="${ctx}/resources/js/jquery.ztree.core-3.5.js"></script>
	<script src="${ctx}/resources/jspJs/category/categoryList.js"></script>
	<script type="text/javascript">
		var zNodes = ${treeData};
	</script>

</body>
</html>
