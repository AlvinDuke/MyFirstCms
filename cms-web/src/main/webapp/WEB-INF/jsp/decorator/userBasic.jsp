<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../inc/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title><decorator:title /></title>

<!-- Bootstrap core CSS -->
<link href="${ctx}/resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${ctx}/resources/css/arts/arts.css" rel="stylesheet">

</head>

<body>
	<link rel="stylesheet" href="${ctx}/resources/css/jquery-ui.css"
		type="text/css">

	<div class="container">
		<!-- 页面主体内容 -->
		<script type="text/javascript"
			src="${ctx}/resources/jQuery/jquery-1.11.1.js"></script>
		<script type="text/javascript">
			//上下文根目录
			var root = $("#root").val();
		</script>

		<div class="blog-masthead">
			<div class="container">
				<nav class="blog-nav"> <a class="blog-nav-item" href="${ctx}">首页</a>

				<c:forEach items="${navCates}" var="k">
					<a class="blog-nav-item nav-cates" href="#" id="${k.key.id}">${k.key.name }</a>
					<div class="sub-cates-holder" id="sub${k.key.id}">
						<c:forEach items="${k.value}" var="v">
							<a class="blog-nav-item cate-Change" id="${v.id}" href="javascript:;">${v.name }</a>
						</c:forEach>
					</div>
				</c:forEach> 
					<!-- 关键字 --> 
					<!-- 文章检索 -->
		       		<form action="${ctx}/arts/${c.id}" id="artSearch" style="display: inline;" method="post">
		       			<input type="hidden" name="dateCon" id="dateCon" value="${dateCon}">
		       			<input type="hidden" name="cid" id="cid" value="${c.id }">
						<input id="condition" name="condition"
							value="${condition }" type="text" class="form-control search-con"
							placeholder="我需要.."> 
					</form>
						
				</nav>
			</div>
		</div>

		<decorator:body />
	</div>
	<!-- /.container -->

	<footer class="blog-footer">
	<p>
		<a href="${ctx}">都较瘦的独立博客©2014</a>
	</p>
	<p>
		<a href="#">回顶部</a>
	</p>
	</footer>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="${ctx}/resources/js/bootstrap.min.js"></script>
	<script src="${ctx}/resources/assets/js/docs.min.js"></script>
	<script src="${ctx}/resources/jspJs/userBasic/userBasic.js"></script>
	<!-- jq ui -->
	<script type="text/javascript" src="${ctx}/resources/js/jquery-ui.js"></script>
	<!-- dwr -->
	<script type='text/javascript' src='${ctx}/dwr/engine.js'></script>
	<script type='text/javascript'
		src='${ctx}/dwr/interface/keywordSearch.js'></script>
	<script type="text/javascript">
	
	$(function(){
		
		//搜索栏自动补全
		$("#condition").autocomplete({
			select: function( event, ui ) {
				//确认选择时，提交查询表单
				$("#artSearch").submit();
			}
		});
		$("#condition").on("keyup", function() {
			if(this.value == null || this.value == ""||event.keyCode==38||event.keyCode==40){
				//空内容不查询，上下选择不查询
				return; 
			}
			//每次搜索框输入完毕，使用搜索的框值查找关键字，进行自动补全
			keywordSearch.findKeywords($("#condition").val(), function(data) {
				if(data!=null&&data !=""){
					$("#condition").autocomplete({
						source : data,
						minLength: 0
					});
					$("#condition").autocomplete("search", "");
				}
			});
		});
		
		//点击栏目，添加栏目筛选条件
		$(".cate-Change").on("click",function(){
			$("#artSearch").attr("action","${ctx}/arts/"+this.id);
			$("#artSearch").submit();
		});
		
		//点击日期分组，直接搜索
		$(".list-unstyled").find("a").on("click",function(){
			//如果点击“全部”进行查询，则去除栏目id和日期这两个查询条件
			//$("#cid").val("");
			//$("#artSearch").attr("action", "${ctx}/arts/9999");
			if(this.id == "noDate"){
				$("#dateCon").val("");
			}
			else{
				//有日期就添加该条件
				$("#dateCon").val(this.id);
			}
			
			$("#artSearch").submit();
		});
	});
	</script>
</body>
</html>