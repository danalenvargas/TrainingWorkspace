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
		<!-- =============================== -->
		<!-- |   LISTING EXISTING USERS      | -->
		<!-- =============================== -->
		<button type="button" onclick="showModal('modalAddUser', 0)">Add User</button>
		List of Users: <br>
<!-- 		<div style="width:50%; float:left;"> -->
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
		<!-- =============================== -->
		<!-- |   FOR ADDING NEW USERS      | -->
		<!-- =============================== -->
<!-- 			<h3>Add New User</h3> -->
<!-- 			<form action="UserManagement" method="post"> -->
<!-- 				Username: <input type="text" name="username" placeholder="username" /><br /> -->
<!-- 				Password: <input type="password" name="password" placeholder="password" /><br /> -->
<!-- 				Privileges:<br> -->
<!-- 				Create: <input type="checkbox" name="canCreate" value="1" /><br /> -->
<!-- 				Update: <input type="checkbox" name="canUpdate" value="1" /><br /> -->
<!-- 				Delete: <input type="checkbox" name="canDelete" value="1" /><br /> -->
<!-- 				<br /> -->
<!-- 				<input type="hidden" name="action" value="addUser" /> -->
<!-- 				<input type="submit" value="Submit" /> -->
<!-- 			</form> -->
<!-- 		</div> -->
		<!-- =============================== -->
		<!-- |   FOR MODIFYING SELECTED USER      | -->
		<!-- =============================== -->
<!-- 		<div id="editForm" style="width:50%; float:right; visibility: hidden"> -->
<!-- 			User Details: -->
<!-- 			<form action="UserManagement" method="post"> -->
<!-- 				Username: <input id="txtUsername" type="text" name="username" placeholder="username" /><br /> -->
<!-- 				Password: <input id="txtPassword" type="password" name="password" placeholder="password" /><br /> -->
<!-- 				Privileges:<br /> -->
<!-- 				Create: <input id="chkCreate" type="checkbox" name="canCreate" value="1" /><br /> -->
<!-- 				Update: <input id="chkUpdate" type="checkbox" name="canUpdate" value="1" /><br/> -->
<!-- 				Delete: <input id="chkDelete" type="checkbox" name="canDelete" value="1" /><br /> -->
<!-- 				<br /> -->
<!-- 				<input id="txtUserId" type="hidden" name="userId" /> -->
<!-- 				<input type="hidden" name="action" value="editUser" /> -->
<!-- 				<input type="submit" value="Update" /><br /> -->
<!-- 			</form> -->
<!-- 			<form action="UserManagement" method="post"> -->
<!-- 				<input id="txtUserId2" type="hidden" name="userId" /> -->
<!-- 				<input type="hidden" name="action" value="deleteUser" /> -->
<!-- 				<input type="submit" value="Delete User" /> -->
<!-- 			</form> -->
<!-- 		</div> -->
	</div>
</body>
</html>