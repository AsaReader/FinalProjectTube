<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:import url="/includes/header.jsp" />
<c:import url="/includes/sidebar.jsp"/>
	<div class="content">
		<div class="grids">
			<br />
			<h1><c:out value="${playlist.name}"/></h1>
			<c:choose>
				<c:when test="${not empty playlist.videos}">
					<c:forEach var="video" items="${playlist.videos}">
						<p><a href="${pageContext.request.contextPath}/video/${video.id}"><c:out value="${video.title}"/></a></p>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p><c:out value="No videos added to this playlist yet."/></p>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	
<c:import url="/includes/footer.jsp" />>