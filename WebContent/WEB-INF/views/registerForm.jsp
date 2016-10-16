<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:import url="/includes/header.jsp" />
<c:import url="/includes/sidebar.jsp" />
<head><title><c:out value="YouPlay - Registration" /></title></head>
<div class="content">
	<div class="grids">
		<h2>
			<spring:message code="user.register" />
		</h2>
		<br />

		<sf:form method="POST" commandName="user">
			<!-- Handle errors -->
			<div class="search-box">
				<sf:input path="username" cssErrorClass="error"
					placeholder="Username..." />
				<sf:errors path="username" style="color:red;" />
				<br /> <br />

				<sf:input path="email" cssErrorClass="error" placeholder="Email..." />
				<sf:errors path="email" style="color:red;" />
				<br /> <br />

				<sf:password path="password" cssErrorClass="error"
					placeholder="Password..." />
				<sf:errors path="password" style="color:red;" />
				<br /> <br />
			</div>
			<div class="form-actions floatRight upload" align="middle">
				<input type="submit" value="Register" />
			</div>
		</sf:form>
	</div>
</div>
<c:import url="/includes/footer.jsp" />
