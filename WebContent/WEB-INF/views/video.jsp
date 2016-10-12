<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>
<c:import url="/includes/header.jsp" />
<c:import url="/includes/sidebar.jsp"/>
	<video width="854" height="480" controls>
	  <source src=<c:url value="../${video.fileName}"/> type="video/mp4">
	</video><br/>
	<c:out value="${video.fileName}"/><br>
	<c:out value="TITLE: ${video.title}"/><br/>
	<c:out value="POSTED BY: ${video.user.username}"/><br/>
	<c:out value="DESCRIPTION: ${video.description}"/><br/>
	<c:out value="POSTED ON: ${video.date}"/><br/>
	<c:out value="TAGS:"/>
	<c:forEach var="tag" items="${video.tags}">
		<c:out value="${tag.name} "/>
	</c:forEach><br/>
<c:import url="/includes/footer.jsp" />