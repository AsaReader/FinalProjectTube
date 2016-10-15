<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<h2>
			<c:out value="${user.username}'s profile" /> 
			<c:if test="${user.username ne loggedInUser}">
				<button class="dislike-button" id="subscribe" onclick="subscribe('${user.username}')"><c:out value="${subscribeButton}"/></button>
				
				
				<sf:form method="post" action="${pageContext.request.contextPath}/subscribe">
					<input type="hidden" name="username" value="${user.username}">
					<input type="submit" value="NON ${subscribeButton}" class="margin_left">
				</sf:form>
			</c:if>
		</h2>

			<div class="grids">
				<h2>
					<c:out value="${user.username}'s Videos" />
				</h2>
				<c:forEach items="${myVideos}" var="videoM">
					<div class="grid">
						<div class="preview">
						<a href="#" onclick="deleteVideo(${videoM.id});return false;"
								id="${videoM.id}">Delete</a>
							<a href="${pageContext.request.contextPath}/video/${videoM.id}">
								<video width="150" height="90">
									<source src=<c:url value="${videoM.fileName}"/>
										type="video/mp4">
								</video> <c:out value="${videoM.title}"></c:out>
							</a> <br />
						</div>
					</div>
				</c:forEach>
				<div class="clearFloat"></div>
			</div>
			<div class="grids">
				<h2>
					<c:out value="${user.username}'s Info" />
				</h2>
				<h2><c:out value="SUBSCRIBERS" /></h2>
				<c:forEach var="sub" items="${user.subscribers}">
					<p><c:out value="${sub.username}" /></p>
				</c:forEach>
		
				<h2><c:out value="SUBSCRIPTIONS" /></h2>
				<c:forEach var="sub" items="${user.userSubscriptions}">
					<p><c:out value="${sub.username}" /></p>
				</c:forEach>
				<c:if test="${loggedInUser ne user.username}">
					<c:set var="subscribed" value="false" />
					<c:forEach var="subscriber" items="${user.subscribers}">
						<c:if test="${subscriber.username eq loggedInUser}">
							<c:set var="subscribed" value="true" />
						</c:if>
					</c:forEach>
					<c:choose>
						<c:when test="${subscribed == true}">
							<sf:form method="post"
								action="${pageContext.request.contextPath}/unsubscribe">
								<input type="hidden" name="username" value="${user.username}">
								<input type="submit" value="Unsubscribe" class="margin_left">
							</sf:form>
						</c:when>
						<c:otherwise>
							
						</c:otherwise>
					</c:choose>
				</c:if>
			</div>
	</div>
</div>
<c:import url="/includes/footer.jsp" />