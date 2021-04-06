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
<p>view-user</p>
<a href="goManage.htm">go back</a><br/>
<h4>${success}</h4>
<ul>
    <c:forEach var="user" items="${users}">
        <li>
            ${user.username}
            <a href="userDetail.htm?id=${user.userid}">Detail</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>