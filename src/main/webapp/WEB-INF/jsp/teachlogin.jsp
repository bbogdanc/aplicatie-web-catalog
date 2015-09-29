<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	   <head>
	        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	        <title>TeacherLogin</title>
	    </head>
	    <body>
	    <c:set var="incorect" value="${incorrectlogin}"/>
	    <c:if test="${incorect ne null}">
	    	<p style="color:red">Invalid credentials!</p>
	    </c:if>

	    <c:set var="unauthenticated" value="${unauthenticated}"/>
	    <c:if test="${unauthenticated ne null}">
	    	<p style="color:blue">Session has expired.Please login again</p>
	    </c:if>
	        <form id="loginTeacherForm" method="post" action="tlogin">
	        	LastName:<br>
	       		<input type="text" id="1" name="LastName">
				<br>
				FirstName:<br>
				<input type="text" id="2" name="FirstName">
				<br>
				EmployeeId:<br>
				<input type="text" id="3" name="Id">
				<br>
				Password:<br>
				<input type="password" id="4" name="Password">
				<input type="submit" value="Submit">
				</form>
	        </form>
	    </body>
	</html>