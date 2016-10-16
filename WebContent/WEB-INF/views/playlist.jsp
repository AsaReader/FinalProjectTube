<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:import url="/includes/header.jsp" />
<c:import url="/includes/sidebar.jsp"/>
<head><title><c:out value="YouPlay - Playlist ${playlist.name}"/></title></head>
	<div class="content">
		<div class="grids">
			<br />
			<h1><c:out value="${playlist.name}"/></h1>
			<c:choose>
				<c:when test="${not empty playlist.videos}">
					<c:forEach items="${playlist.videos}" var="video">
						<div class="grid">
							<div class="preview">
								<a class="preview-title" href="${pageContext.request.contextPath}/video/${video.id}">
									<video width="150" height="90">
										<source src=<c:url value="${video.fileName}"/>
											type="video/mp4">
									</video> 
									<c:out value="${video.title} (${video.views})"></c:out>
								</a> <br>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p><c:out value="No videos added to this playlist yet."/></p>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	
<c:import url="/includes/footer.jsp" />>