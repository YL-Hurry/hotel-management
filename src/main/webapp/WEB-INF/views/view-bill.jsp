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
<p>View bill page</p>
<form action="bookRoom.htm"><input  type="submit" value="BookRoom" /></form>
<form action="viewOrder.htm"><input  type="submit" value="View Order" /></form>
<table>
	<tr>
		<td>RoomId</td>
		<td>Check in Date</td>
		<td>Check out Date</td>
		<td>Days</td>
		<td>Price</td>
	</tr>
	<c:forEach var="bill" items="${bills}">
		<tr>
			<td>${bill.roomid}</td>
			<td>${bill.inDate}</td>
			<td>${bill.outDate}</td>
			<td>${bill.days}</td>
			<td>${bill.price}</td>
			
		</tr>
	</c:forEach>
</table>
</body>
</html>