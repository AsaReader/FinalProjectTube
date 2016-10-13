<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<title>YouTube knock-off</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/styles/style.css" />">
</head>
<body>
<header>

<script type="text/javascript">
		
		function reloadSuggestions() {
			
			var text = $("#text").val();

			$.get("http://localhost:8080/FinalProjectTube/title/"
				+ text,
			function(data) {
				$("#suggestions").empty();
				for (var index=0; index < data.length; index++) {
					var option = document.createElement("option");
					option.value = data[index];
					$("#suggestions").append(option);
				}
			});
		}
	</script>

	<div class="wrap">
		<div class="logo">
			<a href=<c:url value="/"/>><img src=<c:url value="/images/logo.png"/> alt=""></a>
		</div>
		<div class="search-box">
		<sf:form method = "GET" action = "${pageContext.request.contextPath}/search"> 
			<input type="text" class="icon" name = "input" id="text" placeholder="search..." 
				onkeypress="reloadSuggestions()" list="suggestions" />
				<input type="submit" value="" class="icon">
				

			<datalist id="suggestions"> 
			
			</datalist>

			<div id="products">
				<!-- Put the content from the server -->
			</div>
			</sf:form>
		</div>

		<sec:authorize access="isAnonymous()">
			<div class="menu">
				<ul>
				<li>
					<div class="widget">
						<a href="<c:url value="/user/login"/>">Login</a>
					</div>
				</li>
				</ul>
			</div>
			<div class="menu">
				<ul>
					<li>
					<div class="widget">
						<a href="<c:url value="/user/register"/>">Register</a>
					</div>
					</li>
				</ul>
			</div>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<div class="menu">
				<ul>
					<li>
					<div class="widget">
						<a href="<c:url value="/user/logout"/>">Logout</a>
					</div>
					</li>
				</ul>
			</div>
			<div class="menu">
				<ul>
					<li>
					<div class="widget">
						<a href="<c:url value="/upload"/>">Upload</a>
					</div>
					</li>
				</ul>
			</div>
			<div class="menu">
				<ul>
					<li>
					<div class="widget">
						<sec:authentication property="principal.username" var="loggedInUser"/>
						<a href="<c:url value="/user/me"/>">${loggedInUser}</a>
					</div>
					</li>
				</ul>
			</div>
    	</sec:authorize>
		<div class="clearFloat"></div>
	</div>
	</header>
	</body>