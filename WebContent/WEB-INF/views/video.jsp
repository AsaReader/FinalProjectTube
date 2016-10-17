<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<c:import url="/includes/header.jsp" />
<head>
<title><c:out value="YouPlay - Watch ${video.title}" /></title>
</head>

<body onload=" uploadComment(${video.id} , playlistButtonValue(${video.id}))" />
<div class="upload">
	<video width="854" height="480" controls>
		<source src=<c:url value="${video.fileName}"/> type="video/mp4">
	</video>
	<br />

	<div class="content">
		<div class="grids">
			<h1>
				<c:out value="${video.title} - ${video.views} views" />
			</h1>
			<h2>
				<c:out value="Posted by " />
				<a href="../user/${video.user.username}"><c:out
						value="${video.user.username}" /></a>
				<c:out value=" on ${video.date}" />
			</h2>
			<h2>
				<c:out value="Description:" />
			</h2>
			<p>
				<c:out value="${video.description}" />
			</p>
			<h2>
				<c:out value="Tags:" />
			</h2>
			<p>
				<c:forEach var="tag" items="${tags}">
					<a
						href="${pageContext.request.contextPath}/search?input=${tag.name}"><c:out
							value="${tag.name}" /></a>
				</c:forEach>
			</p>
		</div>

		<sec:authorize access="isAuthenticated()">
			<p>
				<button class="dislike-button" id="likeIt"
					onclick="getLikes(${video.id}, ${1})">
					<c:out value="${likesHelper.likeButton}" />
				</button>
				<button class="dislike-button" id="dislikeIt"
					onclick="getDisLikes(${video.id} , ${2})">
					<c:out value="${likesHelper.dislikeButton}" />
				</button>
			</p>
		</sec:authorize>
		<br />
		<sec:authorize access="isAnonymous()">
			<sf:form method="get"
				action="${pageContext.request.contextPath}/video/like">
				<input type="submit"
					value="<c:out value="${likesHelper.likeButton}"/>"
					class="margin_left">
				<input type="submit"
					value="<c:out value="${likesHelper.dislikeButton}"/>"
					class="margin_left">
			</sf:form>
		</sec:authorize>
		
		<sec:authorize access="isAuthenticated()">
			<c:if test="${not empty playlists}">
				<select  id="playlistId" onclick="playlistButtonValue(${video.id})"  >
				
				<c:forEach items = "${playlists}" var = "playlist">
					<option value="${playlist.id}"><c:out value = " ${playlist.name}"/></option>
					</c:forEach>
				</select >
				<button class="dislike-button" id = "addToPlaylist"
					onclick="addToPlaylist(${video.id})"></button>
			</c:if>
			
			<div class="clearFloat"></div>
			<div class="grid">
				<div class="preview">
					<textarea id="commentId" rows="2" cols="30" placeholder="Comment..."></textarea>
					<input type="submit" placeholder="Comment" id="submit"
						onclick="uploadComment(${video.id})" class="btn btn-primary btn-sm" /><br />
					<br />
				</div>
			</div>
			<br />
		</sec:authorize>

		<div class="grid" id="playlists"></div>
		<div class="clearFloat"></div>
		<div class="grid" id="comments"></div>
	</div>
</div>
<script type="text/javascript">
function addToPlaylist(videoId) {
	var playlistId =  $( "#playlistId option:selected" ).val();
	
	console.log(playlistId);
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
function playlistButtonValue( videoId) {
	var playlistId =  $( "#playlistId option:selected" ).val();
	
	console.log(playlistId);
	$.ajax({
		type : "POST",
		url : "./changePlaylistButtonValue",
		
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

function uploadComment(videoId){
	
	var comment = $("#commentId").val().replace(/</g, "&lt;").replace(/>/g, "&gt;");
	
	if(comment == null){
		console.log('vleze');
		comment = '';
	}
	
	$("#commentId").val('');
	
	
	console.log(comment);
	console.log(videoId);
	

	$.ajax({
	
		url: "./comment",
		type:"POST",

		data:{
			videoId: videoId,
			comment: comment,
		},
		success: function(data){
			$("#comments").empty();
			console.log(data);
		
			for (index in data) {
				var object = data[index];
				var container = document.createElement("div");
				container.id = object.id;
				var username = document.createElement('a');
				var newLine = document.createElement("h1");
				var linkText = document.createTextNode(username);
				username.appendChild(linkText);
				username.appendChild(newLine);
			
				username.href = "${pageContext.request.contextPath}/user/"+object.username;
				username.innerHTML = object.username;
				username.appendChild(document.createElement("h1"));
				container.appendChild(username);
				var text = document.createElement("p");
				text.innerHTML = object.text;
				var size = text.length;
				container.appendChild(text);
				var commentUserId = object.commentUserId;
				var currentUserId = object.currentUserId;
				console.log(currentUserId);
				var videoUserId = object.videoUserId;
				console.log(videoUserId);
				var x = document.createElement("input");
				var commentId = object.commentId;
				if(commentUserId == currentUserId || currentUserId == videoUserId){
					x.type = "submit";
					x.value = "delete";
					x.onclick = function(event) {
						var id = event.target.parentNode.id;
						console.log(id);
						$.ajax({
						    url: './delete',
						    
						    data:{
						    	id:id,
						    },
						    		 
						    type: 'POST',
						    success: function(result) {
						    	document.getElementById(event.target.parentNode.id).remove();
						    }
						});
					};
					container.appendChild(x);
				}
				
				container.appendChild(document.createElement("hr"));
				$("#comments").append(container);
				console.log(object);
			}
		}
	});
}



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
	function loadComments(videoId ){
		
	}

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
		
	
		
		function showProfile(username){
			
			


		}
		
	</script>



<c:import url="/includes/footer.jsp" />