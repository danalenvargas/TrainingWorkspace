<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Product Inventory Login</title>
	<style>
/* 		body { */
/* 			background-color: lightgray; */
/* 		} */
		
		form {
			margin: auto;
			width: 50%;
			border-left: 5px solid blue;
			padding: 10px 25px;
/* 			background-color: dodgerblue; */
		}
		
		input {
			margin: 5px;
		}
	</style>
</head>
<body>
	<h1 align="center">Product Inventory Login</h1>
	<form action="Login" method="post">
		<input type="text" name="username" placeholder="username" /><br />
		<input type="password" name="password" placeholder="password" /><br />
		<input type="submit" name="submit" value="Login" />
	</form>
</body>
</html>