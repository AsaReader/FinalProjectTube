<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:import url="/includes/header.jsp" />
<c:import url="/includes/sidebar.jsp"/>

	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.username" var="loggedInUser" />
	</sec:authorize>
<head><title><c:out value="YouPlay - ${loggedInUser}'s Playlists"/></title></head>	
	<div class="content">
		<div class="grids">
			<h1><c:out value="${user.username}'s playlists"/></h1>
	
			<c:if test="${loggedInUser eq user.username}">
				<sf:form method="post" action="${pageContext.request.contextPath}/newPlaylist">
					<div class="search-box">
						<input type="text" name="playlistName" placeholder="Playlist name..."/><br>
					</div>
					<div class="form-actions floatRight upload" align="middle">
						<input type="submit" value="Create new playlist" />
					</div>
				</sf:form>
			</c:if>
			<h2>
				<c:forEach var="playlist" items="${playlists}">
					<a href="${pageContext.request.contextPath}/playlist/${playlist.id}"><c:out value="${playlist.name}"/></a><br>
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
				</c:forEach>
			</h2>
		</div>
	</div>
<c:import url="/includes/footer.jsp" />>