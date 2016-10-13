<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:import url="/includes/header.jsp" />
<c:import url="/includes/sidebar.jsp"/>
	<h1><c:out value="${playlist.name}"/></h1>
	<c:forEach var="video" items="${playlist.videos}">
		<a href="${pageContext.request.contextPath}/video/${video.id}"><c:out value="${video.title}"/></a><br>
	</c:forEach>
	
	<script type="text/javascript">
	
		
	
	</script>
	
	<input type="text"/>
	<div id="comment">
	
	</div>
<c:import url="/includes/footer.jsp" />>