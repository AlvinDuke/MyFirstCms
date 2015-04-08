<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>文章录入</title>

</head>
<body>
	<link rel="stylesheet" href="${ctx}/resources/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/resources/css/article/article.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/resources/css/jquery-ui.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/resources/css/uploadify.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/resources/js/sh/styles/shCore.css" type="text/css" media="screen" >
	<link rel="stylesheet" href="${ctx}/resources/js/sh/styles/shCoreEclipse.css" type="text/css" media="screen">

	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="page-header">
			<h1>新增文章</h1>
			<sf:form role="form" modelAttribute="adto" method="POST">
				<input type="hidden" name="artId" value="${art.id }" id="artId"/>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">文章标题</span> <input type="text"
							class="form-control" name="article.title" required autofocus value="${art.title }"
							placeHolder="请输入文章的标题（必填）" />
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">创建时间</span> <input type="text"
							id="datepicker" class="form-control" readonly="readonly" value="<fmt:formatDate value="${art.creDate }" type="date" dateStyle="medium"/>" 
							name="creDate" placeHolder="请选择创建时间（选填，不填写默认是今天）" />
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">所属栏目</span> <input type="hidden" value="${art.category.id }"
							id="categoryId" name="cid"/> <input type="text" id="categoryTxt" value="${art.category.name }"
							readonly="readonly" class="form-control" required placeHolder="请选择所属栏目（必填）" />
					</div>
					<div class="cateholder">
						<ul id="tree" class="ztree"></ul>
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">文章摘要</span> <input type="text" value="${art.summary }"
							class="form-control" placeHolder="请填写文章摘要（选填）" name="article.summary"/>
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">文章关键字</span>
						<div class="form-control" id="keywordDiv"
							style="border-bottom: none; border-top-right-radius: 4px; border-bottom-right-radius: 4px; display: none;"></div>
						<input type="text" id="artKeywords" class="form-control"
							placeHolder="请添加文章关键字，按逗号键确认，最多添加5个关键字（选填）">
						<input id="keywords" name="keywords" type="hidden" value="${art.keyword }"/>
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">文章内容</span>
						<textarea id="editor_id"  class="form-control" name="article.content">
							<c:out value="${art.content }"/>
						</textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">文章附件</span>
						<div class="eleholder">
							<input id="file_upload" name="file_upload" type="file"
								multiple="true"> <a class="btn btn-sm btn-primary"
								href="javascript:$('#file_upload').uploadify('upload','*')">上传已选文件</a>
						</div>
						<input id="attachIds" name="attachIds" type="hidden"/>
					</div>
				</div>
				
				<div id="attachDiv" class="table-responsive input-group-addon" style="border-radius: 4px;display:none;width: 100%;margin-bottom: 15px">
					<!-- 更新功能时，判断当前文章有无附件 -->
					<input id="attachsSize" type="hidden" value="${attachs.size()}"/>
					<table class="table table-striped" style="margin-bottom: 0px;text-align: left;">
						<thead>
							<tr>
								<th>附件名称</th>
								<th>附件大小</th>
								<th>附件拓展名</th>
								<th>附件URL</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="attachList">
							<c:forEach items="${attachs}" var="a">
								<tr>
									<td>${a.oriName}</td>
									<td>${a.size/1000}KB</td>
									<td>${a.extention}</td>
									<td>${a.accessUrl}</td>
									<td><a class="btn btn-xs btn-danger deleteAtt" id="${a.id}">删除</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div class="form-group">
					<div class="input-group">
					<span class="input-group-addon">是否推荐本文章：</span>
						<div class="eleholder">	
							<label><input type="radio" value="0" ${art.isrecommend==0?'checked="checked"':''} 
								name="article.isrecommend" required/>不推荐</label> <label><input type="radio"
								value="1" name="article.isrecommend" ${art.isrecommend==1?'checked="checked"':''}  required/>推荐</label>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="input-group">
					<span class="input-group-addon">是否是转发文章：</span>
						<div class="eleholder">	
							<label><input type="radio" value="0" ${art.isRepost==0?'checked="checked"':''} 
								name="article.isRepost" required/>原创</label> <label><input type="radio"
								value="1" name="article.isRepost" ${art.isRepost==1?'checked="checked"':''}  required/>转发</label>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="input-group">
					<span class="input-group-addon">是否发布本文章：</span>
						<div class="eleholder">	
							<label><input type="radio" value="0" ${art.status==0?'checked="checked"':''} 
								name="article.status" required/>不发布</label> <label><input type="radio"
								value="1" name="article.status" ${art.status==1?'checked="checked"':''} required/>发布</label>
						</div>
					</div>
				</div>
				<button type="submit" class="btn btn-default" id="addArticle">保存文章</button>
			</sf:form>
		</div>
	</div>
	<script type="text/javascript" src="${ctx}/resources/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${ctx}/resources/jspJs/article/articleAddinput.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery-ui.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/datepicker-zh-CN.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/kindeditor.js" charset="utf-8"></script>
	<script type="text/javascript" src="${ctx}/resources/js/kindEditor_lang/zh_CN.js" charset="utf-8"></script>
	<script type="text/javascript" src="${ctx}/resources/js/uploadify/jquery.uploadify.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/plugins/code/prettify.js"></script>
	
	<script type="text/javascript" src="${ctx}/resources/js/sh/scripts/shCore.js"/></script>
	<script type="text/javascript" src="${ctx}/resources/js/sh/scripts/shAutoloader.js"/></script>
	
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
	
	<!-- dwr -->
	<script type='text/javascript' src='${ctx}/dwr/engine.js'></script>
    <script type='text/javascript' src='${ctx}/dwr/interface/attachDelete.js'></script>
	<script type="text/javascript">
		var zNodes = ${treeData};
		if($("#editor_id").html()!=null||$("#editor_id").html()!=""){
			SyntaxHighlighter.all(); 
		}
	</script>
</body>
</html>
