<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:import url="/includes/header.jsp" />
<c:import url="/includes/sidebar.jsp"/>
	<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.username" var="loggedInUser"/>
	
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
				<sf:form method="post" action="${pageContext.request.contextPath}/unsubscribe">
					<input type="hidden" name="subjectName" value="${user.username}"> 
					<input type="submit" value="Unsubscribe" class="margin_left">
				</sf:form>
			</c:when>
			<c:otherwise>
				<sf:form method="post" action="${pageContext.request.contextPath}/subscribe">
					<input type="hidden" name="subjectName" value="${user.username}">
					<input type="submit" value="Subscribe" class="margin_left">
				</sf:form>
			</c:otherwise>
		</c:choose>
	</c:if>
	</sec:authorize>
	
	<sec:authorize access="isAnonymous()">
		<sf:form method="post" action="${pageContext.request.contextPath}/subscribe">
			<input type="hidden" name="subjectName" value="${user.username}">
			<input type="submit" value="Subscribe" class="margin_left">
		</sf:form>
	</sec:authorize>
	<h1><c:out value="${user.username}'s profile"/></h1>
	
	<c:out value="${user.username}" /><br/>
	<c:out value="${user.email}" /><br/>
	<c:out value="${user.id}" /><br/>

	<h3>File Upload:</h3>
	
	Click on below links to see FileUpload in action.<br />
	<br /> <a href="<c:url value='/singleUpload' />">Single File Upload</a>
	<a href="<c:url value="/user/logout" />">Logout</a>
	
<c:import url="/includes/footer.jsp" />