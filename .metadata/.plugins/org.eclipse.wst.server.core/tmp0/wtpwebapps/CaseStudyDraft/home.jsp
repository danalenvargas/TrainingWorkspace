<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ page import="com.ibm.cs.entity.User" %> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Home Page</title>
	<style>
		body {
			background-color: lightgray;
		}
	</style>
<%-- 	<% --%>
<%-- 		User user = (User) session.getAttribute("user");
<%-- 	%> --%>
</head>
<body>
	<h1 align="center">Product Inventory System</h1>
<%-- 	<h6>Hello <%=user.getUsername()%>, you have successfully logged in!</h6> --%>
	<h6>Hello ${user.username}, you have successfully logged in!</h6>
	<button type="button" name="btnProd">Product List</button> <br>
<%-- 	<%if(user.getUserType().equals("admin")){ --%>
<%-- 	%> --%>
<!-- 		<button type="button" name="btnManageUsers">Manage User Accounts</button> -->
<%-- 	<%}  --%>
<%-- 	%> --%>
	<c:if test="${user.userType == 'admin'}">
		<button type="button" name="btnManageUsers" onclick="location.href='UserManagement'">Manage User Accounts</button> <br>
	</c:if>
	<button type="button" name="btnLogOut" onclick="location.href='Login?action=logout'">Log Out</button> <br>
</body>
</html>