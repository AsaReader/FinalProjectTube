<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>
<c:import url="/includes/header.jsp" />
<sec:authentication property="principal.username" var="loginUser"/>
	
	<c:out value="Logged in as ${loginUser}" /><br/>
	
	<h1><c:out value="${user.username}'s profile"/></h1>
	
	<c:out value="${user.username}" /><br/>
	<c:out value="${user.email}" /><br/>
	<c:out value="${user.id}" /><br/>

	<h3>File Upload:</h3>
	
	Click on below links to see FileUpload in action.<br />
	<br /> <a href="<c:url value='/singleUpload' />">Single File Upload</a>
	<a href="<c:url value="/user/logout" />">Logout</a>
	
<c:import url="/includes/footer.jsp" />