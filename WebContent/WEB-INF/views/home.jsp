<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page session="false"%>
<c:import url="/includes/header.jsp" />
<c:import url="/includes/sidebar.jsp" />
<div class="content">
	<div class="grids">
		<h2>Latest Videos</h2>
		<c:forEach items="${videosLast}" var="videoL">
			<div class="grid">
				<div class="preview">
					<a href="${pageContext.request.contextPath}/video/${videoL.id}">
						<video width="150" height="90">
							<source src=<c:url value="${videoL.fileName}"/> type="video/mp4">
						</video>
						<c:out value="${videoL.title} (${videoL.views})"></c:out>
					</a>
					<c:out value="by "/>
					<a href="${pageContext.request.contextPath}/user/${videoL.user.username}">
						<c:out value="${videoL.user.username}"/>
					</a>
				</div>
			</div>
		</c:forEach>
		<div class="clearFloat"></div>
	</div>
	<br />
	<div class="grids">
		<h2>Most Watched</h2>
		<c:forEach items="${videosMostWatched}" var="videoW">
			<div class="grid">
				<div class="preview">
					<a href="${pageContext.request.contextPath}/video/${videoW.id}">
						<video width="150" height="90">
							<source src=<c:url value="${videoW.fileName}"/> type="video/mp4">
						</video>
						<c:out value="${videoW.title} (${videoW.views})"></c:out>
					</a>
						<c:out value="by "/>
						<a href="${pageContext.request.contextPath}/user/${videoW.user.username}">
							<c:out value="${videoW.user.username}"/>
						</a>
				</div>
			</div>
		</c:forEach>
		<div class="clearFloat"></div>
	</div>
</div>

<c:import url="/includes/footer.jsp" />