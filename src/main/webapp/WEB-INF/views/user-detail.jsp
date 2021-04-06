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
<a href="goManage.htm">go back</a><br/>
<p>${sessionScope.username} detail:</p><br/>
<c:if test = "${userRooms != null}">
	<p>user checked in room</p>
     <c:forEach var="room" items="${userRooms}">
        <p>${room.roomid} <a href="checkoutRoom.htm?id=${room.roomid}">check out</a></p>
     </c:forEach>
</c:if>
<p>user order</p>
<table>
	<tr>
		<td>RoomType</td>
		<td>StartDate</td>
		<td>EndDate</td>
		<td>State</td>
	</tr>
    <c:forEach var="order" items="${orders}">
        <tr>
			<td>${order.roomType}</td>
			<td>${order.startDate}</td>
			<td>${order.endDate}</td>
			<td>${order.state}</td>
			<td><a href="userCheckin.htm?id=${order.id}">check in</a></td>
		</tr>       
    </c:forEach>
  
</table>
<c:if test = "${singleRooms != null}">
	<p>available singleRoom</p>
     <c:forEach var="room" items="${singleRooms}">
        <a href="setRoom.htm?id=${room.roomid}">${room.roomid}</a>
     </c:forEach>
</c:if>
<c:if test = "${standardRooms != null}">
	<p>available standardRoom</p>
     <c:forEach var="room" items="${standardRooms}">
        <a href="setRoom.htm?id=${room.roomid}">${room.roomid}</a>
     </c:forEach>
</c:if>
<c:if test = "${bigRooms != null}">
	<p>available bigRoom</p>
     <c:forEach var="room" items="${bigRooms}">
        <a href="setRoom.htm?id=${room.roomid}">${room.roomid}</a>
     </c:forEach>
</c:if>
</body>
</html>