<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Grades</title>
</head>
<body>

    <c:set var="count" value="0"/>
    <table border="1">`
            <c:forEach var = "course" items = "${info}">
                <tr>
                    <c:set var="materia" value="${course.course.nume}"/>
                        <td><c:out value="${materia}"/></td>
                        <td><c:out value="${info[count].grade}"/></td>
                        <c:set var="count" value="${count+1}"/>
                </tr>
            </c:forEach>
    </table>
    <form id="Logout" method="GET" action="logout">
      	<input type="submit" value="Logout"/>
    </form>
     <form id="mainpage" method="GET" action="login">
        <input type="submit" value="Main Page"/>
     </form>

</body>
</html>