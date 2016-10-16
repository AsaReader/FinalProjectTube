<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>
<c:import url="/includes/header.jsp" />
<c:import url="/includes/sidebar.jsp" />
<head><title><c:out value="YouPlay - Change Password"/></title></head>
<div class="content">
	<div class="grids">
		<h2>Forgotten Password</h2>
		<sf:form method="POST"
			action="${pageContext.request.contextPath}/changePassword">
			<div class="row" id="div1">
				<div class="form-group col-md-12">
					<br /> <br />
					<div class="search-box">
						<input type="password" name="oldPassword" placeholder="Old Password..." /><br /><br />
						<input type="password" name="newPassword" placeholder="New Password..." /><br /><br />
						<input type="password" name="repeatPassword" placeholder="New Password(again)..." /><br /><br />
						<span class="part1">
						<c:out value="${errors}" /><br /></span>
						<span class="part2">
						<c:out value="${success}" /><br /></span>
						<br />
					</div>
				</div>
				<br /> <br />
				<div class="form-actions floatRight upload" align="middle">
					<br /> <input type="submit" value=" Send " />
				</div>
				<div class="form-actions floatRight upload" align="middle">
					<br /> <br /> <a href="<c:url value="/"/>">Home</a>
				</div>
			</div>
		</sf:form>
	</div>
</div>

<c:import url="/includes/footer.jsp" />