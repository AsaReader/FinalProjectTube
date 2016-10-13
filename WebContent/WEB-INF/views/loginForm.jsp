<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:import url="/includes/header.jsp" />
	<h1>Log in</h1>
	<br/>
    <sf:form method="POST" action="${pageContext.request.contextPath}/user/login">
		Username: <input type="text" name="username" /><br/><br/>
		Password: <input type="password" name="password" /><br/><br/>
		<input id="remember_me" name="remember-me" type="checkbox"/>
		<label for="remember_me" class="inline">Remember me</label>
		<input type="submit" value="Login" />
    </sf:form>
    
<c:import url="/includes/footer.jsp" />
