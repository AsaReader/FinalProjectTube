<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<c:import url="/includes/header.jsp" />
<c:import url="/includes/sidebar.jsp" />
<head><title><c:out value="YouPlay - Video Search" /></title></head>
<div class="content">
	<div>
		<p>Sort by:</p>
		<select id="resultSort">
		<c:forEach items = "${sorts}" var = "sort">
			<option value="${sort}"><c:out value = "${sort}"/></option>
			</c:forEach>
			
		</select> <input type="submit" onclick="redirect()" value="SORT" class="button" />
	</div>
	<div class="grids">
		<h2>Video Results</h2>
		<c:choose>
			<c:when test="${empty videos}">
				<p><c:out value="Couldn't find any videos with that name, tag or posted by that user."/></p>
			</c:when>
			<c:otherwise>
				<c:forEach items="${videos}" var="video">
					<div class="grid">
						<div class="preview">
							<a href="${pageContext.request.contextPath}/video/${video.id}">
								<video width="150" height="90">
									<source src=<c:url value="${video.fileName}"/> type="video/mp4">
								</video>
								<c:out value="${video.title} (${video.views})"></c:out>
							</a>
							<c:out value="by "/>
							<a href="${pageContext.request.contextPath}/user/${video.user.username}">
								<c:out value="${video.user.username}"/>
							</a>
						</div>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<div class="clearFloat"></div>
	</div>
</div>

<script type="text/javascript">
	function redirect() {

		var text = getQueryVariable('input');
		var playlistId = $("#resultSort option:selected").val();
		console.log(text);

		location.href = "${pageContext.request.contextPath}/sort?input=" + text
				+ "&sortBy=" + playlistId;
	}
	function getQueryVariable(variable) {
		var query = window.location.search.substring(1);
		var vars = query.split("&");
		for (var i = 0; i < vars.length; i++) {
			var pair = vars[i].split("=");
			if (pair[0] == variable) {
				return pair[1];
			}
		}
		return (false);
	}
</script>


<c:import url="/includes/footer.jsp" />