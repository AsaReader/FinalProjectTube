<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:import url="/includes/header.jsp" />
<c:import url="/includes/sidebar.jsp" />

<div class="content">
	<div class="grids">
		<br />
		<h2>
			<c:out value="${user.username}'s profile" />
		</h2>

		<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal.username" var="loggedInUser" />
			<br />
			<!-- <h3>
				<c:out value="  Logged in as ${loggedInUser}" />
			</h3>  -->
			<br />
			<div class="grids">
				<h2>
					<c:out value="My Videos" />
				</h2>
				<c:forEach items="${myVideos}" var="videoM">
					<div class="grid">
						<div class="preview">
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
					<c:out value="My Info" />
				</h2>
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
							<sf:form method="post"
								action="${pageContext.request.contextPath}/subscribe">
								<input type="hidden" name="username" value="${user.username}">
								<input type="submit" value="Subscribe" class="margin_left">
							</sf:form>
						</c:otherwise>
					</c:choose>
				</c:if>
			</div>
		</sec:authorize>

		<c:out value="SUBSCRIBERS" />
		<c:forEach var="sub" items="${user.subscribers}">
			<c:out value="${sub.username}" />
		</c:forEach>

		<c:out value="SUBSCRIPTIONS" />
		<c:forEach var="sub" items="${user.userSubscriptions}">
			<c:out value="${sub.username}" />
		</c:forEach>

		<sec:authorize access="isAnonymous()">
			<sf:form method="post"
				action="${pageContext.request.contextPath}/subscribe">
				<input type="hidden" name="subject" value="${user}">
				<input type="submit" value="Subscribe" class="margin_left">
			</sf:form>
		</sec:authorize>
		<h1>
			<c:out value="${user.username}'s profile" />
		</h1>

		<c:out value="${user.username}" />
		<br />
		<c:out value="${user.email}" />
		<br />
		<c:out value="${user.id}" />
		<br />
	</div>
</div>
<c:import url="/includes/footer.jsp" />