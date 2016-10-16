<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:import url="/includes/header.jsp" />
<c:import url="/includes/sidebar.jsp" />
<head><title><c:out value="YouPlay - Nonexistant"/></title></head>

<div class="content">
	<div class="grids">
		<h1>
			<c:out value="Couldn't find ${missing}..."/>
		</h1>
		<p>
			<c:out value="Maybe you're looking in the wrong place."></c:out>
		</p>
	</div>
</div>

<c:import url="/includes/footer.jsp" />