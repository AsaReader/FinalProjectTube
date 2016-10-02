<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<c:import url="/includes/header.jsp" />
	<h1>Log in</h1>

    <form method="POST" commandName="login">
		Username: <input type="text" name="username" /><br/>
		Password: <input type="password" name="password" /><br/>
		<input type="submit" value="Login" />
    </form>
<c:import url="/includes/footer.jsp" />
