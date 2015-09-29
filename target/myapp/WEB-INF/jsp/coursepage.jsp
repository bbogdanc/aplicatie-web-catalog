<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Courses</title>
</head>
<body>
    <c:set var="size" value="${courses.size()}"/>
    <table border="1" style="width:100%">
    <tr>
    <th>Prof. Last Name</th>
    <th>Course Name </th>
    <th>Number of participants</th>
    </tr>
       <tr>
       <td rowspan="${size}"> ${teacher.lastName} </td>
           <c:forEach var="cpop" items="${courses}">
            <td><c:out value="${cpop.course.nume}"/></td>
            <td>
            <form id="listStudents" action="allstudentgrades" method="POST"/>
                <input type="submit" value="${cpop.nrStud}"/>
                <input type="hidden" name="C_id" value="${cpop.course.courseId}"/>
            </form>
            </td>
            <td></td>
           </c:forEach>
       </tr>
    </table>
      <form id="loginTeacherForm" method="get" action="tlogin">
            <input type="submit" value="Homepage"/>
      </form>
     <form id="Logout" method="GET" action="logout">
          	<input type="submit" value="Logout"/>
        </form>
</body>
</html>