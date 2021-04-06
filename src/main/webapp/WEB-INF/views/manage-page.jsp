<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h4>${success}</h4>
<button><a href="working.htm">Start/Stop working</a></button> 
<c:choose>
    <c:when test="${sessionScope.waiter.state=='true'}">
        <h5>working now</h5> 
        <br />
    </c:when>    
    <c:otherwise>
        <h5>rest now</h5> 
        <br />
    </c:otherwise>
</c:choose><br>
<a href="viewUser.htm">view User</a><br/>
<a href="preRoom.htm">pre-Room and send bill</a><br/>
<a href="manageBill.htm">manage bill</a>
</body>
</html>