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
	
			<c:forEach var="playlist" items="${playlists}">
				<a href="${pageContext.request.contextPath}/playlist/${playlist.id}"><c:out value="${playlist.name}"/></a><br>
			</c:forEach>
		</div>
	</div>
<c:import url="/includes/footer.jsp" />>