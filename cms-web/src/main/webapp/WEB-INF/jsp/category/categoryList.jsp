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
			<h1>栏目中心</h1>
		</div>
		
		<div class="row placeholders">
		
			<div class="col-xs-6 col-sm-3 placeholder">
				<ul id="tree" class="ztree"></ul>
			</div>
			<!-- 当前选择栏目的id -->
			<input type="hidden" id="cid" value="${c.id}"/>
			当前栏目[${c.name}]<a href="${ctx}/category/add/${c.id}" class="btn btn-sm btn-primary">添加栏目</a>
			<div class="table-responsive">
 				<table class="table table-striped">
					<thead>
						<tr>
							<th>栏目名称</th>
							<th>栏目类别</th>
							<th>定制栏目</th>
							<th>推荐栏目</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="sortable">
						<c:forEach items="${cs}" var="c">
							<tr id="${c.id}">
								<td>${c.name }</td>
								<td>${c.categoryType.name }</td>
								<td>${c.isCustom == 0?'否':'是' }</td>
								<td>${c.isRecommended == 0?'否':'是'  }</td>
								<td>${c.status == 0?'停用':'启用' }</td>
								<td><a href="${ctx}/category/delete/${c.id}"
										class="btn btn-xs btn-danger deleteObj">删除</a>
									<a href="${ctx}/category/update/${c.id}"
										class="btn btn-xs btn-primary">更新</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<a href="javascript:;" class="btn btn-sm btn-primary" id="setUpSort">拖动栏目重新排序</a>
				<a href="javascript:;" class="btn btn-sm btn-primary" id="saveSort">保存当前栏目排序</a>
			</div>
		</div>
	</div>
</ul>
	<!-- zTree -->
	<script type="text/javascript" src="${ctx}/resources/js/jquery.ztree.core-3.5.js"></script>
	<!-- jQuery sortable -->
	<script type="text/javascript" src="${ctx}/resources/js/jquery.ui.core.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery.ui.mouse.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery.ui.sortable.js"></script>
	<!-- 自定义 -->
	<script src="${ctx}/resources/jspJs/common/commonList.js"></script>
	<script src="${ctx}/resources/jspJs/category/categoryList.js"></script>
	<script type="text/javascript">
		var zNodes = ${treeData};
		$(function() {
			//保存当前表格的初始化顺序
			var defaultOrders = [];
			$("#sortable tr").each(function(){
				defaultOrders.push(this.id);
			});
			
			//是否处于排序状态
			var isSortStatus = false;
			//保存排序默认不可用
			$("#saveSort").attr("disabled","disabled");
			//排序表格初始化
			$("#sortable").sortable({
				axis: "y",
				cursor: "move",
				helper: function(e,ele){
					//拖动时保持宽度不变
					var $ori = ele.children();
					var $helper = ele.clone();
					$helper.children().each(function(index){
						$(this).width($ori.eq(index).width());
					});
					return $helper;
				},
				update: function() {
					initSortOrders();
				}
			});
			$("#sortable").disableSelection().sortable("disable");
			
			//开启排序
			$("#setUpSort").click(function(){
				$("#sortable").sortable("enable");
				initSortOrders();
				//排序按钮不可用
				$(this).attr("disabled","disabled");
				//保存排序可用
				$("#saveSort").removeAttr("disabled");
				isSortStatus = true;
			});
			
			//保存排序
			$("#saveSort").click(function(){
				var curOrders = $("#sortable").sortable("toArray");
				//检查排序是否产生变化，有变化则保存
				for(var i = 0;i < defaultOrders.length;i++){
					//排序有变化则保存
					if(defaultOrders[i] != curOrders[i]){
						$.ajax({
					    	url: root + "/category/updateOrders?cids="+curOrders,
						}).done(function(data){
							if(data == "success"){
								alert("保存成功");
							}
						}).fail(function(data){
							if(data == "fail"){
								alert("保存失败，请重试");
							}
						});
						break;
					}
				}
				
				//清除排序序号
				$("thead tr th:last").remove();
				$("#sortable tr").each(function(index){
					$(this).find("td:last").remove();				
				});
				
				$("#sortable").sortable("disable");
				$(this).attr("disabled","disabled");
				//排序可用
				$("#setUpSort").removeAttr("disabled");
				isSortStatus = false;
			});
			
			//初始化排序，添加直观的排序序号
			function initSortOrders(){
				if(isSortStatus){
					$("#sortable tr").each(function(index){
						$(this).find("td:last").html(index+1);					
					});
				}
				else{
					$("thead tr:last").append("<th>排序</th>");
					$("#sortable tr").each(function(index){
						$(this).append("<td>" + (index+1) + "</td>");					
					});
				}
			}
		});
	</script>
</body>
</html>
