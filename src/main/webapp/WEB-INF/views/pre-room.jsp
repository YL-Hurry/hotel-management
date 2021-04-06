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
<table>
	<tr>
		<td>RoomId</td>
		<td>RoomType</td>
	</tr>
	<c:forEach var="room" items="${finishRooms}">
		<tr>
			<td>${room.roomid}</td>
			<td>${room.roomType}</td>
			<td><a href="sendBill.htm?id=${room.roomid}">submit and send bill</a></td>
		</tr>
	</c:forEach>
</table>
</body>
</html>