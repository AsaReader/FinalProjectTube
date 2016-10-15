<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:import url="/includes/header.jsp" />
<c:import url="/includes/sidebar.jsp" />

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.username" var="loggedInUser" />
</sec:authorize>

<script type="text/javascript">
	function subscribe(username) {
		
		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/subscribe",
			
			data: {
				username: username,
			},
			
			success : function(data) {
				$("#subscribe").empty();
				$("#subscribe").append(data);
			}
		});
	}
	
	function deleteVideo(id){
		var x = document.getElementById(id);
		console.log(id , x.getAttribute('id'));
		
		$.ajax({
			type: 'POST',
		    url: '../videoDelete',
		   
		    data:{
		    	videoId : id,
		    },
		    success: function(){
				console.log("hi");
				location.reload();
			}

		}).done(function(){
			console.log("check");
		});
	}
</script>

<div class="content">
	<div class="grids">
		<br />
		<h1>
			<c:out value="${user.username}'s profile" /> 
			<c:if test="${user.username ne loggedInUser}">
				<button class="dislike-button" id="subscribe" onclick="subscribe('${user.username}')"><c:out value="${subscribeButton}"/></button>
			</c:if>
		</h1>

			<div class="grids">
				<h2>
					<c:out value="${user.username}'s Videos (${fn:length(videos)})" />
				</h2>
				<c:choose>
					<c:when test="${empty videos}">
						<p><c:out value="No vidoes uploaded yet."></c:out></p>
					</c:when>
					<c:otherwise>
						<c:forEach items="${videos}" var="video">
							<div class="grid">
								<div class="preview">
								<a href="#" onclick="deleteVideo(${videoM.id});return false;"
								id="${videoM.id}">Delete</a>
									<a class="preview-title" href="${pageContext.request.contextPath}/video/${video.id}">
										<video width="150" height="90">
											<source src=<c:url value="${video.fileName}"/>
												type="video/mp4">
										</video> 
										<c:out value="${video.title} (${video.views})"></c:out>
									</a> <br />
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<div class="clearFloat"></div>
			</div>
			<div class="grids">
				<h2><c:out value="${user.username}'s subscribers (${fn:length(user.subscribers)})" /></h2>
				<c:choose>
					<c:when test="${empty user.subscribers}">
						<p><c:out value="No subscribers yet."></c:out></p>
					</c:when>
					<c:otherwise>
						<c:forEach var="sub" items="${user.subscribers}">
							<p><a href="${pageContext.request.contextPath}/user/${sub.username}"><c:out value="${sub.username}" /></a></p>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<h2><c:out value="${user.username}'s subscribptions (${fn:length(user.userSubscriptions)})" /></h2>
				<c:choose>
					<c:when test="${empty user.userSubscriptions}">
						<p><c:out value="Not subscribed to anyone yet."></c:out></p>
					</c:when>
					<c:otherwise>
						<c:forEach var="sub" items="${user.userSubscriptions}">
							<p><a href="${pageContext.request.contextPath}/user/${sub.username}"><c:out value="${sub.username}" /></a></p>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
	</div>
</div>
<c:import url="/includes/footer.jsp" />