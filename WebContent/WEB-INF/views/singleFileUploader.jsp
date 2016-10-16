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
<title><c:out value="YouPlay - Video Upload"/></title>
<style type="text/css">
label.filebutton {
    width:120px;
    height:40px;
    overflow:hidden;
    position:relative;
    background-color:#ccc;
}

label span input {
    z-index: 999;
    line-height: 0;
    font-size: 50px;
    position: absolute;
    top: -40px;
    left: -700px;
    opacity: 0;
    filter: alpha(opacity = 0);
    -ms-filter: "alpha(opacity=0)";
    cursor: pointer;
    _cursor: hand;
    margin: 0;
    padding:0;
}
</style>
<script type='text/javascript'>
	function showFileSize() {
		var input = document.getElementById('file');
		var file = input.files[0].size / 1048576;
		if (file > 0 && file <= 500) {
			document.getElementById("submit").disabled = false;
		} else {
			bodyAppend("p",
					"File  is over size!!! Max size 500MB. Please change the file.");
			document.getElementById("submit").disabled = true;
		}
	}

	function bodyAppend(tagName, innerHTML) {
		var theDiv = document.getElementById("div1");
		var elm;
		elm = document.createElement(tagName);
		elm.innerHTML = innerHTML;
		elm.style.color = "red";
		theDiv.appendChild(elm);
	}
</script>
</head>
<body>
	<c:import url="/includes/header.jsp" />
	<c:import url="/includes/sidebar.jsp" />
	<div class="content">
		<div class="grids">
			<br />
			<h2>Video Upload</h2>
			<sf:form commandName="fileBucket" method="POST"
				enctype="multipart/form-data" class="form-horizontal">
				<div class="row" id="div1">
					<div class="form-group col-md-12">
						<br /> <br />
						<div class="search-box">
							<sf:input type="text" path="title" required="required" id="title"
								placeholder="Title..." />
						</div>
						<br /> <br />
						<div class="search-box">
							<sf:textarea rows="4" cols="30" path="descr"
								placeholder="Description..." />
						</div>
						<br /> <br />
						<div class="search-box">
							<sf:input type="text" path="tags" placeholder="Tags..." /><br/>
						</div>
						<br/> <br/>
						<div class="col-md-7 upload">
						<br />
							<label class="filebutton">
								<img src=<c:url value="/images/mzl.rhhhohdt.png" /> alt="HTML5 Icon" style="width:68px;height:68px;">
							<span>							
							<sf:input type="file" path="file" id="file" accept="video/mp4"
								class="form-control input-sm" value="Browse"
								onchange="showFileSize()" /></span></label>
							<div class="has-error">
								<sf:errors path="file" class="help-inline" style="color:red;" />
							</div>
						</div>
					</div>
				</div>	
				<br />			
				<div class="row">
				<br />
					<div class="form-actions floatRight upload">
						<input type="submit" value="Upload" id="submit"
							class="btn btn-primary btn-sm">
					</div>
				</div>
			</sf:form>
		</div>
	</div>
<c:import url="/includes/footer.jsp" />
