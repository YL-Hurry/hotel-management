<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<form action = "checklogin.htm" method = "post">
Login role<input type="radio" name="role" value="user"/>user
<input type="radio" name="role" value="waiter"/>waiter
<input type="radio" name="role" value="admin"/>admin<br/>
User Name: <input type = "text" name="username"/><br/>
Password: <input type = "password" name="password"/><br/>
<input type="submit" value="LOGIN">
</form>

<form action="signin.htm" method="post">
<input type="submit" value="create new account" />
</form>
</body>
</html>
