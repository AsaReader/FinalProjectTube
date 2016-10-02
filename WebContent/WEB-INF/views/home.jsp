<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<c:import url="/includes/header.jsp" />
	<h1>WeLcome to OurTube</h1>
	
	<a href="<c:url value="/user/login"/>">Login</a> <br> 
	<a href="<c:url value="/user/register"/>">Register</a>
<c:import url="/includes/footer.jsp" />