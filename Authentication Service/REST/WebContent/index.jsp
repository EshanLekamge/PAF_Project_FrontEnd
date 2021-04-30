<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <link rel="stylesheet" href="Views/bootstrap.min.css">
    <script src="Componenets/jquery-3.6.0.js"></script>
    <script src="Componenets/main.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form id="formLogin" name="formLogin" method="post" action="index.jsp">
User Name:<input id="itemCode" name="itemCode" type="text"class="form-control form-control-sm">
<br> 
Password:<input id="itemName" name="itemName" type="text"class="form-control form-control-sm">
<br> 
<input id="btnLgoin" name="btnLogin" type="button" value="Save"class="btn btn-primary">
</form>
</body>
</html>