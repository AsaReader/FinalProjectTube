<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page session="false"%>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>

<c:import url="/includes/header.jsp" />
<c:import url="/includes/sidebar.jsp" />

<sec:authentication property="principal.id" var="userId" />


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
<c:forEach var="tag" items="${video.tags}">
	<c:out value="${tag.name} " />
</c:forEach>
<br />

<c:out value="${userId}"></c:out>

<button  id="likeIt" a href="./like"
	onclick="getLikes(${video.id}, ${1})" >Likes</button>
	

<button class="dislike-button" id="dislikeIt" a href="./dislike"
	onclick="getDisLikes(${video.id} , ${2})">Dislike</button>
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






<c:import url="/includes/footer.jsp" />