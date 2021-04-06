<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
  $("button").click(function(){
    $.ajax({url: "admin.htm", success: function(result){
      $("#div1").html(result);
    }});
  });
});
</script>
</head>
<body>
<button>get working waiter name</button>
<div id = "div1"></div>
<form action = "createWaiter.htm" method = "post">
User Name: <input type = "text" name="wname"/><br/>
Password: <input type = "password" name="wpassword"/><br/>
<input type="submit" value="Create new Waiter">
</form>
<h4>${success}</h4>
</body>
</html>