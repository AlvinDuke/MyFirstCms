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

<title>文章管理</title>

</head>
<body>
	<link rel="stylesheet" href="${ctx}/resources/css/jquery-ui.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/resources/css/article/articleList.css" type="text/css">
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="page-header">
			<h1>文章列表</h1>
		</div>
		<p>
			<!-- 栏目下拉框 -->
			<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1" id="cate">
				<li role="presentation" class="${cid == null?'active':''}"><a role="menuitem" href="javascript:;" class="catename">全部栏目</a></li>
				<c:forEach items="${categorys }" var="c">
					<input type="hidden" value="${c.id }"/>
					<li role="presentation" class="${cid == c.id?'active':''}"><a role="menuitem" href="javascript:;" class="catename">${c.name }</a></li>
				</c:forEach>
	        </ul>
	        <!-- 文章检索 -->
	        <form action="" id="artSearch">
	        	<!-- 文章状态 -->
	        	<input type="hidden" name="status" value="${status }"/>
	        	<!-- 所属栏目id -->
	        	<input type="hidden" name="cid" id="cid" value="${cid }"/>
	        	<!-- 关键字 -->
	        	<input type="text" id="condition" name="condition" value="${condition }"  class="form-control" style="display: inline-block;width: 300px;vertical-align: middle;" placeHolder="文章标题，内容，关键字或者摘要">
				<a href="javascript:;" class="btn btn-sm btn-primary" id="cateToggle">栏目筛选</a>
				<button type="submit" href="javascript:;" class="btn btn-sm btn-primary" >检索</button>
				<a href="${ctx}/article/addInput" class="btn btn-sm btn-primary" style="float: right;">新建文章</a>
	        </form>	
			
		</p>
		<div class="table-responsive">
			<table class="table table-striped">
				<tbody>
					<c:choose>
						<c:when test="${articles.datas.size() == 0}">
						还没有记录
						</c:when>
						<c:otherwise>
							<thead>
								<tr>
									<th>标题</th>
									<th>作者</th>
									<th>所属栏目</th>
									<th>所属原创</th>
									<th>是否推荐</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<c:forEach items="${articles.datas}" var="a">
								<tr>
									<td><a href="${ctx}/arts/show/${a.id}"
										class="btn btn-xs btn-link">${a.title }</a></td>
									<td>${a.author }</td>
									<td>${a.cateName }</td>
									<td><c:choose>
											<c:when test="${a.isRepost == 0}">
												<span class="btn btn-xs btn-primary" >原创</span>
											</c:when>
											<c:otherwise>
												<span class="btn btn-xs btn-danger">转发</span>
											</c:otherwise>
										</c:choose></td>
									<td><c:choose>
											<c:when test="${a.isrecommend == 0}">
												<span class="btn btn-xs btn-danger" >暂不推荐</span>
											</c:when>
											<c:otherwise>
												<span class="btn btn-xs btn-primary">推荐阅读</span>
											</c:otherwise>
										</c:choose></td>
									<td><c:choose>
											<c:when test="${a.status == 0}">
												<span class="btn btn-xs btn-danger" >暂未发布</span>
											</c:when>
											<c:otherwise>
												<span class="btn btn-xs btn-primary">已经发布</span>
											</c:otherwise>
									</c:choose></td>
									<td><a href="${ctx}/article/delete/${a.id}"
										class="btn btn-xs btn-danger deleteObj">删除</a> <a
										href="${ctx}/article/updateInput/${a.id}"
										class="btn btn-xs btn-primary">更新</a> 
								</tr>
							</c:forEach>
							<tr>
								<td colspan="7"><jsp:include page="../inc/pager.jsp">
										<jsp:param value="${ctx}/article/articles" name="url" />
										<jsp:param value="${articles.totalRecords }" name="items" />
										<jsp:param value="condition,cid,status" name="params" />
									</jsp:include></td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
	<!-- 自定义 -->
	<script src="${ctx}/resources/jspJs/common/commonList.js"></script>
	<!-- jq ui -->
	<script type="text/javascript" src="${ctx}/resources/js/jquery-ui.js"></script>
	<!-- dwr -->
	<script type='text/javascript' src='${ctx}/dwr/engine.js'></script>
    <script type='text/javascript' src='${ctx}/dwr/interface/keywordSearch.js'></script>
	<script type="text/javascript">
		
		$(function(){
			//栏目下拉框隐藏/显示
			$("#cateToggle").on("click",function(event){
				event.stopPropagation();
				$("#cate").toggle();
			});
			$("html").on("click",function(){
				if($("#cate").css("display") == 'block'){
					$("#cate").hide();
				}
			});
			
			//栏目下拉框绑定搜索事件
			$(".catename").on("click",function(){
				var cid = $(this).parent().prev().val();
				$("#cid").val(cid);
				$("#artSearch").submit();
			});
			
			//搜索栏自动补全
			$("#condition").autocomplete({
				select: function( event, ui ) {
					//确认选择时，提交查询表单
					$("#artSearch").submit();
				}
			});
			$("#condition").on("keyup", function(event) {
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
			
		});
	
	</script>
</body>
</html>
