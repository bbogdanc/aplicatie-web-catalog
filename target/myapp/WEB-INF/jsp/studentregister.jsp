<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Student Information</h2>
    <c:if test="${incorrectregister ne null}">
    	<p style="color:red">The credentials you entered are invalid</p>
    </c:if>
    <form action="registerstud" method="post">
       Last Name: <input type="text" name="LastName"/><br>
       First Name: <input type="text" name="FirstName"/><br>
       Father Initial: <input type="text" name="FatherInitial"/><br>
       Username <input type="text" name="Username"/><br>
       Password: <input type="text" name="Password"/><br>
       <p>Also state the name of your course in which you iwh to enroll the new student</p>
       Course-><input type="text" name="Course"/>
        <input type="submit" value="Register Student"/> <br>
    </form>
</body>
</html>