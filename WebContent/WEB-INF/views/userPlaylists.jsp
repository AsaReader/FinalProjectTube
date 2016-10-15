<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:import url="/includes/header.jsp" />
<c:import url="/includes/sidebar.jsp"/>
	<h1><c:out value="${user.username}'s playlists"/></h1>
	<c:forEach var="playlist" items="${playlists}">
		<a href="${pageContext.request.contextPath}/playlist/${playlist.id}"><c:out value="${playlist.name}"/></a><br>
	</c:forEach>
<c:import url="/includes/footer.jsp" />>