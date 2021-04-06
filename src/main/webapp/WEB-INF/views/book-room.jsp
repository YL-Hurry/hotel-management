<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>book room page</p>
<form action="viewOrder.htm"><input  type="submit" value="View Order" /></form>
<form action="viewBill.htm"><input  type="submit" value="View Bill" /></form>
<p>Price:</p>
<p>SingleRoom: 100/day &nbsp;&nbsp; StandardRoom:200/day &nbsp;&nbsp; BigRoom:150/day</p>
<p>book room here</p>
<form action="orderRoom.htm" method="post">
	RoomType:<select name="roomType">
   		<option value="singleRoom">SingleRoom</option>
   		<option value="standardRoom">StandardRoom</option>
   		<option value="bigRoom">bigRoom</option>
	</select>
	StartDate:<input type="date" name="startDate" />
	EndDate:<input type="date" name="endDate" />
	<input type="submit" value="order" />
</form>
<p>${success}</p>
</body>
</html>