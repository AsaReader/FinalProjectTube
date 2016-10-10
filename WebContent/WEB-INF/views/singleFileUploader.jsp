<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Video File Upload</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet" type="text/css"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"
	type="text/css"></link>

<title>Upload Video</title>
</head>
<body>

	<div class="form-container">
		<h1>Video Upload</h1>
		<sf:form commandName="fileBucket" method="POST"
			enctype="multipart/form-data" class="form-horizontal">
			<h1>
				<c:out value="Session username = ${loggedUser.username}" />
			</h1>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="file">Upload a
						file</label> <br /> <br /> <label for="textinput1">Video Title:
					</label>
					<sf:input type="text" path="title" />
					<br /> <br /> <label for="textinput2">Video Description:
					</label><br />
					<sf:textarea rows="4" cols="30" path="descr" />
					<br /> <br /> <label for="textinput3">Video Tags: </label>
					<sf:input type="text" path="tags" />
					<br /> <br />
					<div class="col-md-7">
						<sf:input type="file" path="file" id="file" accept="video/mp4"
							class="form-control input-sm" />
						<div class="has-error">
							<sf:errors path="file" class="help-inline" style="color:red;" />
						</div>
					</div>
					<br />
				</div>
			</div>

			<div class="row">
				<div class="form-actions floatRight">
					<input type="submit" value="Upload" class="btn btn-primary btn-sm">
				</div>
			</div>
		</sf:form>

		<a href="<c:url value='/user/${loggedUser.username}' />">Home</a>
	</div>
</body>
</html>