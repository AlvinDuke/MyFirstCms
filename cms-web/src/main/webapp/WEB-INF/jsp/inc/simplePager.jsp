<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@include file="taglib.jsp"%>
<!-- 将分页代码拆分，公共使用 -->
<!-- 请求参数需要通过param获取 -->
<pg:pager items="${param.items}" maxPageItems="8"
	export="curPage=pageNumber" url="${param.url}">
	<!-- 此处可以传入查询条件 -->
	<c:forEach items="${param.params}" var="p">
		<pg:param name="${p}" />
	</c:forEach>
	<pg:prev>
		<li><a href="${pageUrl }">上一页</a></li>
	</pg:prev>
	
	<pg:next>
		<li><a href="${pageUrl }">下一页</a></li>
	</pg:next>
</pg:pager>