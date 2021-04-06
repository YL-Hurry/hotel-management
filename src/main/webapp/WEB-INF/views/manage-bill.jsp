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
		<td>BillId</td>
		<td>UserId</td>
		<td>RoomId</td>
		<td>Check in Date</td>
		<td>Check out Date</td>
		<td>Days</td>
		<td>Price</td>
	</tr>
	<c:forEach var="bill" items="${bills}">
		<tr>
			<td>${bill.id}</td>
			<td>${bill.userid}</td>
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