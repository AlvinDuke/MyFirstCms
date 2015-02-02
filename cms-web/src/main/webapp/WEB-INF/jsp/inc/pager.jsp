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
			总记录数：${param.items}
			<pg:last>
			总页数:${pageNumber }
			</pg:last>
	<pg:first>
		<a href="${pageUrl }">首页</a>
	</pg:first>
	<pg:prev>
		<a href="${pageUrl }">上一页</a>
	</pg:prev>
	<pg:pages>
		<!-- 页码是当前页的不要显示连接 -->
		<c:if test="${curPage eq pageNumber }">
					[${pageNumber }]
				</c:if>
		<c:if test="${curPage ne pageNumber }">
			<a href="${pageUrl }">${pageNumber }</a>
		</c:if>
	</pg:pages>
	<pg:next>
		<a href="${pageUrl }">下一页</a>
	</pg:next>
	<pg:last>
		<a href="${pageUrl }">尾页</a>
	</pg:last>
</pg:pager>