<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="form-container">
		<h1>Welcome to FileUploader Example</h1>

		Click on below links to see FileUpload in action.<br />
		<br /> <a href="<c:url value='/singleUpload' />">Single File Upload</a>
		 <!-- OR <a href="<c:url value='multiUpload' />">Multi File	Upload</a> -->
	</div>
</body>
</html>