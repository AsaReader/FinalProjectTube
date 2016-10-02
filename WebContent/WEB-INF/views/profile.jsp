<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<c:import url="/includes/header.jsp" />
	<h1>WELCOME</h1>
	
	<c:out value="${user.username}" /><br/>
	<c:out value="${user.email}" /><br/>
	<c:out value="${user.id}" /><br/>
	
	<h3>File Upload:</h3>
	
<c:import url="/includes/welcome.jsp" />
	
<c:import url="/includes/footer.jsp" />