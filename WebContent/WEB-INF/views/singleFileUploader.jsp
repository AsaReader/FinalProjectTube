<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<spring:url value="/resources/js/jquery-3.1.1.min.js" var="jqueryJs" />
<spring:url value="/resources/js/main.js" var="mainJs" />
<title>Upload Video</title>
<script type='text/javascript'>
	function showFileSize() {
		var input = document.getElementById('file');
		var file = input.files[0].size / 1048576;
		if (file > 0 && file <= 500) {
			alert("File size is OK!");
			document.getElementById("submit").disabled = false;
		} else {
			alert("File " + input.name + " is over size. " + Math.ceil(file)
					+ " MB.");
			bodyAppend("p",
					"File  is over size!!! Max size 500MB. Please change the file.");
			document.getElementById("submit").disabled = true;
		}
	}

	function bodyAppend(tagName, innerHTML) {
		var elm;
		elm = document.createElement(tagName);
		elm.innerHTML = innerHTML;
		elm.style.color = "red";
		document.body.appendChild(elm);
	}
</script>
</head>
<body>

	<div class="form-container">
		<h1>Video Upload</h1>
		<sf:form commandName="fileBucket" method="POST"
			enctype="multipart/form-data" class="form-horizontal">
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="file">Upload a
						file</label> <br /> <br /> <label for="textinput1">Video Title:
					</label>
					<sf:input type="text" path="title" required="required" id="title" />
					<br /> <br /> <label for="textinput2">Video Description:
					</label><br />
					<sf:textarea rows="4" cols="30" path="descr" />
					<br /> <br /> <label for="textinput3">Video Tags: </label>
					<sf:input type="text" path="tags" />
					<br /> <br />
					<div class="col-md-7">
						<sf:input type="file" path="file" id="file" accept="video/mp4"
							class="form-control input-sm" onchange="showFileSize()" />
						<div class="has-error">
							<sf:errors path="file" class="help-inline" style="color:red;" />
						</div>
					</div>
					<br />
				</div>
			</div>

			<div class="row">
				<div class="form-actions floatRight">
					<input type="submit" value="Upload" id="submit"
						class="btn btn-primary btn-sm">
				</div>
			</div>
		</sf:form>
		<br />
		<a href="<c:url value='/home' />">Home</a>
	</div>
</body>

</html>