<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src='<c:url value = "http://code.jquery.com/jquery-1.9.1.min.js"/>'></script>
<script type="text/javascript"
	src='<c:url value = "http://code.jquery.com/ui/1.10.3/jquery-ui.js"/>'></script>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/styles/style.css" />">
<title>Insert title here</title>
</head>
<body>



	<div class="wrap">
		<div class="logo">
			<a href=<c:url value="/"/>><img
				src=<c:url value="/images/logo.png"/> alt=""></a>
		</div>






		<div class="content">

			<div class="preview">

				<div class="search-box">
					<sf:form method="GET"
						action="${pageContext.request.contextPath}/getJson">
						<input type="text" class="icon" name="input" id="text"
							onkeypress="getLink()" placeholder="search..." />


					</sf:form>
				</div>
			</div>
			<br><br><br><br><br><br><br><br><br><br><br><br>
			<h1>Get your video list here:</h1>
			
			<div class="preview">
				<h2>
				<div  class="preview" id="link"></div>
				</h2>

				<div id="listOfVideos"></div>
			</div>
		</div>

	</div>


	<script type="text/javascript">
		function getLink() {
			$("#link").empty();

			var search = $("#text").val();
			if(search.length != 0){

			var pieces = location.pathname.split("/")[1];

			var host = location.host;

			var link1 = 'http://' + location.host + '/' + pieces + "/prefix/"
					+ search;

			console.log(link1);

			var link = document.createElement('a');
			var newLine = document.createElement("h1");
			var linkText = document.createTextNode(link);
			link.appendChild(linkText);
			link.appendChild(newLine);
			link.title = name;
			link.href = link1;
			link.innerHTML = link1;
			link.appendChild(document.createElement("h1"));
			$("#link").append(link);
			}

		}
	</script>
</body>
</html>
<c:import url="/includes/footer.jsp" />