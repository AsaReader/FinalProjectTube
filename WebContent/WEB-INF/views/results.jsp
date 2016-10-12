<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/includes/header.jsp" />
<c:import url="/includes/sidebar.jsp" />

<div class="content">
	<div class="grids">
		<h2>Video Results</h2>
		<c:forEach items="${videos}" var="video">
			<div class="grid">
				<div class="preview">
					<a href="single.html"><img src="images/album.jpg" alt=""></a>
					<div class="data">
						<h3>
							<a href=""><c:out value="${video.title}"></c:out></a> 
							<a href=""><c:out value="${video.fileName}"></c:out></a>
						</h3>
					</div>
				</div>
			</div>
			
		</c:forEach>
		<div class="clearFloat"></div>
	</div>
	<div class="more">
		<a href="#">More</a>
	</div>
	
</div>

<c:import url="/includes/footer.jsp" />