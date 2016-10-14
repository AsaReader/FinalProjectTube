<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false"%>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>

<c:import url="/includes/header.jsp" />
<c:import url="/includes/sidebar.jsp" />

<video width="854" height="480" controls>
	<source src=<c:url value="../${video.fileName}"/> type="video/mp4">
</video>
<br />
<c:out value="${video.fileName}" />
<br>
<c:out value="TITLE: ${video.title}" />
<br />
<c:out value="POSTED BY: ${video.user.username}" />
<br />
<c:out value="DESCRIPTION: ${video.description}" />
<br />
<c:out value="POSTED ON: ${video.date}" />
<br />
<c:out value="VIEWS: ${video.views}" />
<br />
<c:out value="TAGS:" />

<sec:authorize access="isAuthenticated()">
<button class="dislike-button" id="likeIt" onclick="getLikes(${video.id}, ${1})" ><c:out value="${likesHelper.likeButton}"/></button>

<button class="dislike-button" id="dislikeIt" onclick="getDisLikes(${video.id} , ${2})"><c:out value="${likesHelper.dislikeButton}"/></button>
</sec:authorize>	

<sec:authorize access="isAnonymous()">
<sf:form method="get" action="${pageContext.request.contextPath}/video/like">
<input type="submit" value="<c:out value="${likesHelper.likeButton}"/>" class="margin_left">
<input type="submit" value="<c:out value="${likesHelper.dislikeButton}"/>" class="margin_left">
</sf:form>
</sec:authorize>
<script type="text/javascript">



function getLikes(videoId, likeId){
	var like = $("#likeIt").val();
	
	
	console.log(likeId);
	console.log(videoId);
	

	$.ajax({
		
		url: "./like",
		type:"POST",

		data:{
			
			videoId: videoId,
			likeId: likeId,
		},
		success: function(data){
			$("#likeIt").empty();
			$("#dislikeIt").empty();
			var dislikeButton = data.dislikeButton;
			var likeButton = data.likeButton;
			
			$("#likeIt").append(likeButton);
			$("#dislikeIt").append(dislikeButton);
			
		}
		
	});
	
}

function getDisLikes(videoId, likeId){
	var like = $("#dislikeIt").val();
	
	
	console.log(likeId);
	console.log(videoId);
	

	$.ajax({
		
		url: "./like",
		type:"POST",

		data:{
			
			videoId: videoId,
			likeId: likeId,
		},
		success: function(data){
			$("#likeIt").empty();
			$("#dislikeIt").empty();
			var dislikeButton = data.dislikeButton;
			var likeButton = data.likeButton;
			
			$("#likeIt").append(likeButton);
			$("#dislikeIt").append(dislikeButton);
			
		}
		
	});
	
}
</script>

<button class="dislike-button"
	onclick="getPlaylists()">Add to playlist</button>
	
	
<button class="dislike-button" id="addToPlaylist"
	onclick="addToPlaylist(${1}, ${video.id})">Add to MyPlaylist</button>
	
	<script type="text/javascript">
		function getPlaylists(videoId) {
		
			$.ajax({
				 type : "POST",
				 url : "./getPlaylists",
				 
				data:{},
				 success : function(data) {
					 var index;
					 for (index in data) {
							var playlist = document.createElement("playlist");
							playlist.value = data[index];
							$("#suggestions").append(option);
						}
				 }
			});
		}
		
		function addToPlaylist(playlistId, videoId) {
			
			$.ajax({
				type : "POST",
				url : "./addToPlaylist",
				
				data: {
					videoId: videoId,
					playlistId: playlistId,
				},
				
				success : function(data) {
					var index;
					$("#addToPlaylist").empty();
					$("#addToPlaylist").append(data);
				}
			});
		}
		
	</script>





<c:import url="/includes/footer.jsp" />