<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Product Inventory Login</title>
	<style>
		form {
			margin: auto;
			width: 50%;
			border-left: 5px solid blue;
			padding: 10px 25px;
		}
		
		input {
			margin: 5px;
		}
	</style>
</head>
<body>
	<h1 align="center">Inventory Management System<br>Login</h1>
	<form action="Login" method="post">
		<input type="text" name="username" placeholder="username" /><br />
		<input type="password" name="password" placeholder="password" /><br />
		<input type="hidden" name="action" value="login" /><br />
		<input type="submit" name="submit" value="Login" />
	</form>
</body>
</html>