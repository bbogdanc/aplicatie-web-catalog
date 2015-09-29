<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>HomePage</h2>

<h4>Please select your status</h4>
<form id = "SelectStatus" action="status" method="POST">
<input type="radio" name="Status" id="1" value="Teacher">Teacher
<input type="radio" name="Status" id="2" value="Student">Student
<input type="submit" value="Submit">
</from>
</body>
</html>