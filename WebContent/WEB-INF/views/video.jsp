<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>
<c:import url="/includes/header.jsp" />
<c:import url="/includes/sidebar.jsp"/>
	<video width="320" height="240" controls>
	  <source src="${video.fileName}" type="video/mp4">
	</video>
<c:import url="/includes/footer.jsp" />