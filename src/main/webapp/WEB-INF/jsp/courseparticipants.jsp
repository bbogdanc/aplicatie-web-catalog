<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<h2>These are the participants in your course</h2>

<table border="1" style="width:100%>
    <tr align="center">
        <th align="center">Nume Student</th>
        <th align="center">Nota</th>
    </th>
        <tr align="center">
            <c:forEach var="item" items="${info}">
                <td rowspan="${item.grade.size()}"><c:out value="${item.s.lastName}"/></td>
                <c:forEach var="nota" items="${item.grade}">
                    <td align="center"><c:out value="${nota}"/></td>
                    </tr>
                    <tr align="center">

                </c:forEach>
            </c:forEach>
        </tr>

</table>
   <form id="loginTeacherForm" method="get" action="tlogin">
            <input type="submit" value="Homepage"/>
   </form>

 <form id="Logout" method="GET" action="logout">
      	<input type="submit" value="Logout"/>
 </form>



</head>
</html>