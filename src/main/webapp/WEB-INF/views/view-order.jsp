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
<p>view order page</p>
<form action="bookRoom.htm"><input  type="submit" value="BookRoom" /></form>
<form action="viewBill.htm"><input  type="submit" value="View Bill" /></form>
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
			<td><a href="updateOrder.htm?id=${order.id}">change</a></td>
			<td><a href="removeOrder.htm?id=${order.id}">remove</a></td>
		</tr>       
    </c:forEach> 
</table>
<c:if test = "${rooms != null}">
	<p>you current living room:</p>
	<table>
		<tr>
			<td>RoomId</td>
			<td>RoomType</td>
		</tr>
	    <c:forEach var="room" items="${rooms}">
	        <tr>
				<td>${room.roomid}</td>
				<td>${room.roomType}</td>
				<td><a href="userCheckout.htm?id=${room.roomid}">check out</a></td>
			</tr>       
	    </c:forEach>	  
	</table>
</c:if>
<h4>${success}</h4>
</body>
</html>