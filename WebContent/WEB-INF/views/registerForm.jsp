<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<c:import url="/includes/header.jsp" /> 
	<h1><spring:message code="user.register" /></h1>
	<sf:form method="POST" commandName="user">
				<!-- Handle errors -->
	
		<label for="textinput1"><spring:message code="user.name" /></label>
		<sf:input path="username" cssErrorClass="error" /><sf:errors path="username" style="color:red;" /><br/><br/>
		
		<label for="textinput2"><spring:message code="user.email" /></label>
		<sf:input path="email" cssErrorClass="error" /><sf:errors path="email" style="color:red;" /><br/><br/>
		
		<label for="textinput3"><spring:message code="user.password" /></label>
		<sf:password path="password" cssErrorClass="error" /><sf:errors path="password" style="color:red;" /><br/><br/>
		
		<input type="submit" value=<spring:message code="user.button" /> />
    </sf:form>
    
<c:import url="/includes/footer.jsp" />
