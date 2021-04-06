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
<p>${sessionScope.username} detail</p><br/>
<ul>
    <c:forEach var="order" items="${orders}">
        <li>
            ${order.roomType}${order.startDate}${order.endDate}
        </li>
    </c:forEach>
     <a href="userCheckin.htm">check in</a>
     <a href="userCheckout.htm">check out</a>
</ul>
	<p>available singleRoom:</p>
     <c:forEach var="room" items="${singleRooms}">
        <a href="setRoom.htm?id=${room.roomid}">${room.roomid}</a>
     </c:forEach>
</body>
</html>