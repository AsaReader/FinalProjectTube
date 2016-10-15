<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<c:import url="/includes/header.jsp" />
<c:import url="/includes/sidebar.jsp" />
<div class="content">
	<div class="grids">
		<h2>
			You look lost, maybe you should head on <a href="${pageContext.request.contextPath}/home">home</a>.
		</h2>
	</div>
</div>
<c:import url="/includes/footer.jsp" />