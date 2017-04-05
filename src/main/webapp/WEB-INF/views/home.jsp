<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<c:url var="login" value="/login"></c:url>
<form action ="${login}" method = "GET">
	Email ID : <input type="text" name ="emailid">
	Password : <input type="text" name ="password">
	<input type="Submit" value ="Submit">
</form>

</body>
</html>
