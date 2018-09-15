<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>User Management</title>
	<script src="script/modal.js"></script>
	<script src="script/usermanagement.js"></script>
	<link rel="stylesheet" href="css/main.css" />
	<link rel="stylesheet" href="css/modal.css" />
</head>
<body>
	<%@include file="partials/header.jsp" %>
	<%@include file="modals/modalAddUser.html" %>
	<%@include file="modals/modalEditUser.html" %>
	<%@include file="modals/modalChangePassword.html" %>
	<div class="content">
		<h1>User Management</h1>
		<button type="button" onclick="showModal('modalAddUser', 0)">Add User</button>
		List of Users: <br>
		<table>
			<tr>
				<th></th>
				<th colspan="4">Privileges</th>
			</tr>
			<tr>
				<th>Username</th>
				<th>Create</th>
				<th>Update</th>
				<th>Delete</th>
			</tr>
			<c:forEach items="${userList}" var="account">
				<tr>
					<td onclick="showUserDetails('${account.userId}');">${account.username}</td>
					<td>${account.canCreate}</td>
					<td>${account.canUpdate}</td>
					<td>${account.canDelete}</td>
				</tr>
			</c:forEach>
		</table>
		<br><br>
	</div>
</body>
</html>