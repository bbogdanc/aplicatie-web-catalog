<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	   <head>
	        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	        <title>Student</title>
	    </head>

	    <body>
	       <h2>Hello, you have just logged in</h2>
           	    <p>You are  user ${student.userName}</p>
           	    <br>
           	    <p>Here are your credentials<p><br>
           	    <p>LastName:${student.lastName}<p><br>
           	    <p>FirstName:${student.firstName}<p><br>
           	    <p>FatherInitial:${student.fatherInitial}<p><br>
           	   <form:form id="Courses" method="POST" action="grades" modelAttribute="student">
           	   	<input type="submit" value="See grades"/>
           	   </form:form>
           	   <form:form id="Logout" method="POST" action="logout" modelAttribute="student">
              	<input type="submit" value="Logout"/>
               </form:form>
	    </body>
	</html>