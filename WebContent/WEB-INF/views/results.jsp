<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/includes/header.jsp" />
<c:import url="/includes/sidebar.jsp" />

<div class="content">
	<div class="grids">
		<h2>Video Results</h2>
		<c:forEach items="${videos}" var="video">
			<div class="grid">
				<div class="preview">
					<a href="${pageContext.request.contextPath}/video/${video.id}">
						<video width="150" height="90">
							<source src=<c:url value="${video.fileName}"/> type="video/mp4">
						</video>
						<c:out value="${video.title} (${video.views})"></c:out>
					</a><br>
						<c:out value="by "/>
						<a href="${pageContext.request.contextPath}/user/${video.user.username}">
							<c:out value="${video.user.username}"/>
						</a>
				</div>
			</div>
		</c:forEach>
		<div class="clearFloat"></div>
	</div>
</div>

<c:import url="/includes/footer.jsp" />