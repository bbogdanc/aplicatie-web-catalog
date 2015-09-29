<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	   <head>
	        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	        <title>Login</title>
	    </head>
	    <body>
	    <c:set var="incorect" value="${incorectlogin}"/>
	    <c:if test="${incorect ne null}">
	    	<p style="color:red">Either password or username is wrong!</p>
	    </c:if>

	    <c:set var="unauthenticated" value="${unauthenticated}"/>
	    <c:if test="${unauthenticated ne null}">
	    	<p style="color:blue">Session has expired.Please login again</p>
	    </c:if>
	        <form id="loginForm" method="post" action="login">
	        	Username:<br>
	       		<input type="text" id="1" name="Username">
				<br>
				LastName:<br>
				<input type="password" id="2" name="Password">
				<br>
				<input type="submit" value="Submit">
				</form>

	        </form>
	    </body>
	</html>