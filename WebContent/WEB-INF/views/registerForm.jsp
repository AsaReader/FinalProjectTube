<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>
<c:import url="/includes/header.jsp" />
	<h1>Register</h1>

	<sf:form method="POST" commandName="user">
		Username: <sf:input path="username" /><sf:errors path="username" /><br/>
		Email: <sf:input path="email" /><sf:errors path="email" /><br/>
		Password: <sf:password path="password" /><sf:errors path="password" /><br/>
		<input type="submit" value="Register" />
    </sf:form>
    
<c:import url="/includes/footer.jsp" />
