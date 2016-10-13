<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
</head>

	
<body>
	<div class="wrap">
		<div class="one sidebar">
			<div class="widget">
				<ul>
					<li class="active"></li>
					<li><a href="${pageContext.request.contextPath}/">Home</a></li>
					<li><a href="${pageContext.request.contextPath}/subscribtions">Subscriptions</a></li>
					<li><a href="${pageContext.request.contextPath}/user/me">My Profile</a></li>
					<li><a href="wallpaper.html">Playlists</a></li>
				</ul>
			</div>
		</div>
	</div>

</body>
</html>