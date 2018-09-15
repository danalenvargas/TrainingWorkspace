<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Home Page</title>
	<script src="script/home.js"></script> 
	<link rel="stylesheet" href="css/main.css" />
</head>
<body>
	<%@include file="partials/header.jsp" %>
	<div class="content">
		<h3>Hello ${user.username}!</h3>
	</div>
</body>
</html>