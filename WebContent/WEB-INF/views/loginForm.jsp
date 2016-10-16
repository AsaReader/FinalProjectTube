<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:import url="/includes/header.jsp" />
<c:import url="/includes/sidebar.jsp" />
<head><title><c:out value="YouPlay - Login" /></title></head>
<div class="content">
	<div class="grids">
		<h2>Log in</h2>
		<sf:form method="POST"
			action="${pageContext.request.contextPath}/user/login">
			<div class="row" id="div1">
				<div class="form-group col-md-12">
					<br /> <br />
					<div class="search-box">
						<input type="text" name="username" placeholder="Username..." /> <br />
						<br /> <input type="password" name="password"
							placeholder="Password..." /><br /> <br />
					</div>
				</div>
				<br /> <br />
				<div class="form-actions floatRight upload" align="middle">
					<br /> <br /> <input id="remember_me" name="remember-me"
						type="checkbox" /> <label for="remember_me"><c:out
							value="Remember me" /></label><br />
				</div>
				<div class="form-actions floatRight upload" align="middle">
					<br /> <input type="submit" value="Login" />
				</div>
				<div class="form-actions floatRight upload" align="middle">
					<br /> <br /> <a href="<c:url value="../forgotenpassword"/>">Forgotten
						Password?</a>
				</div>
			</div>
		</sf:form>
	</div>
</div>

<c:import url="/includes/footer.jsp" />
