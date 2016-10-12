<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:import url="/includes/header.jsp" />

<sec:authentication property="principal.username" var="loggedInUser"/>
	
	<c:if test="${loggedInUser ne null}">
	<c:out value="Logged in as ${loggedInUser}" /><br/>
	
	<c:if test="${loggedInUser ne user.username}">
		<c:set var="subscribed" value="false" />
		<c:forEach var="subscriber" items="${user.subscribers}">
			<c:if test="${subscriber.username == user}">
				<c:out value="${subscriber.username}"/>
				<c:set var="subscribed" value="true" />
			</c:if>
		</c:forEach>
	
		<c:choose>
			<c:when test="${subscribed == true}">
				<form action="home" method="post">
					<input type="hidden" name="action" value="unfollow">
					<input type="hidden" name="subjectName" value="${subject.username}"> 
					<input type="submit" value="Unsubscribe" class="margin_left">
				</form>
			</c:when>
			<c:otherwise>
				<form action="home" method="post">
					<input type="hidden" name="action" value="follow">
					<input type="hidden" name="subjectName" value="${subject.username}">
					<input type="submit" value="Subscribe" class="margin_left">
				</form>
			</c:otherwise>
		</c:choose>
	</c:if>
	</c:if>
	<h1><c:out value="${user.username}'s profile"/></h1>
	
	<c:out value="${user.username}" /><br/>
	<c:out value="${user.email}" /><br/>
	<c:out value="${user.id}" /><br/>

	<h3>File Upload:</h3>
	
	Click on below links to see FileUpload in action.<br />
	<br /> <a href="<c:url value='/singleUpload' />">Single File Upload</a>
	<a href="<c:url value="/user/logout" />">Logout</a>
	
<c:import url="/includes/footer.jsp" />