<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>Change order page</p>
<form action="bookRoom.htm"><input  type="submit" value="BookRoom" /></form>
<form action="viewOrder.htm"><input  type="submit" value="View Order" /></form>
<form action="viewBill.htm"><input  type="submit" value="View Bill" /></form>
<form:form method="post" modelAttribute="existingorder">
      	RoomType:<form:select path="roomType">
	   		<form:option value="singleRoom">SingleRoom</form:option>
	   		<form:option value="standardRoom">StandardRoom</form:option>
	   		<form:option value="bigRoom">bigRoom</form:option>
		</form:select>
		StartDate:<form:input type="Date" path="startDate" />
		EndDate:<form:input type="Date" path="endDate" />
      <input type="submit" value="change order" />
</form:form>
</body>
</html>