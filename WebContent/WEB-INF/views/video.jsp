<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>
<c:import url="/includes/header.jsp" />
<c:import url="/includes/sidebar.jsp"/>
	<video width="854" height="480" controls>
	  <source src=<c:url value="../${video.fileName}"/> type="video/mp4">
	</video><br/>
	<div id="videoId"><c:out value="${video.fileName}"/><br></div>
	<c:out value="TITLE: ${video.title}"/><br/>
	<c:out value="POSTED BY: ${video.user.username}"/><br/>
	<c:out value="DESCRIPTION: ${video.description}"/><br/>
	<c:out value="POSTED ON: ${video.date}"/><br/>
	<c:out value="VIEWS: ${video.views}"/><br/>
	<c:out value="TAGS:"/>
	<c:forEach var="tag" items="${video.tags}">
		<c:out value="${tag.name} "/>
	</c:forEach><br/>
	
	<input type="submit" value="Add to playlist" onClick="getPlaylists();"/>
	
	<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.1.1.min.js"></script>	
	<script type="text/javascript">
		function getPlaylists() {
			$.ajax({
				 type : "GET",
				 contentType : "application/json", 
				 url : "${home}addToPlaylist"
				 data : JSON.stringify(data),
				 dataType : 'json',
				 timeout : 100000,
				 success : function(data) {
					 alert('hi');
				 }
				 error : function(e) {
					 alert('oops');
				 }
				 done : function(e) {
					 alert('DONE');
				 }
			}
				$.getJSON( "${home}getPlaylists.get",
					{ videoId: 'hi' },
					function(data) {
				alert('response received ' + data)
			});
		}
	</script>
<c:import url="/includes/footer.jsp" />