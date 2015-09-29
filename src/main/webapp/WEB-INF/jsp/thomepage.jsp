<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	   <head>
	        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	        <title>Student</title>
	    </head>

	    <body>
	       <h2> Good day, Mr. ${teacher.lastName} </h2>
	       <p style:color="blue">These are your credentials</p>
	       <p>Last Name: ${teacher.lastName}</p>
	       <p>First Name: ${teacher.firstName}</p>

	       <p style:color="red">Choose one of the following actions</p>

	       <form id="list courses and the number of participants" method="post" action="courses">
	        <input type="submit" value="List courses"/>
	       </form>
	       <form id="register" method="post" action="register">
	       		<input type="submit" value="Add Student"/>
			</form>
			<form id="Logout" method="GET" action="logout">
                  	<input type="submit" value="Logout"/>
            </form>

            <form id="Addgrade" method="Post" action="gradeform">
            		<input type="submit" value="Add a grade"/>
            </form>
	</html>