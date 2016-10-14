<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page session="false"%>
<c:import url="/includes/header.jsp" />
<c:import url="/includes/sidebar.jsp" />

<div class="content">
	<div class="grids">
		<h2>Video Results</h2>
		<c:forEach items="${subscVideos}" var="video">
			<div class="grid">
				<div class="preview">
					<a href="${pageContext.request.contextPath}/video/${video.id}">
						<video width="150" height="90">
							<source src=<c:url value="${video.fileName}"/> type="video/mp4">
						</video>
						<c:out value="${video.title}"></c:out>
					</a> <br />
				</div>
			</div>
		</c:forEach>
		<div class="clearFloat"></div>
	</div>
</div>
<!-- 
	<c:forEach items="${subscVideos}" var="video">     
       <table>
        <tr>
            <td>Title :</td>
            <td>${video.title}</td>
        </tr>
        <tr>
            <td>ID :</td>
            <td>${video.id}</td>
        </tr>
        <tr>
            <td>File name :</td>
            <td>${video.fileName}</td>
        </tr>
    </table>
    <br/>
</c:forEach>
 -->
<c:import url="/includes/footer.jsp" />