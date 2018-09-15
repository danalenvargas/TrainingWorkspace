<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>User Profile</title>
	<script src="script/modal.js"></script>
	<script src="script/userprofile.js"></script>
	<link rel="stylesheet" href="css/main.css" />
	<link rel="stylesheet" href="css/modal.css" />
</head>
<body>
	<%@include file="partials/header.jsp" %>
	<%@include file="modals/modalProfileChangePass.html" %>
	<div class="content">
		<h2>User Profile</h2>
		<form action="Profile" method="post">
			Username: <input type="text" name="username" placeholder="username" value="${user.username}" onchange="validateUsername(this)" /><br />
			User Type: <input type="text" name="userType" value="${user.userType}" readonly/><br />
			Privileges:<br />
			Create: <input type="checkbox" name="canCreate" value="1" ${user.canCreate == true ? 'checked' : ''} onclick="return false;" /><br />
			Update: <input type="checkbox" name="canUpdate" value="1" ${user.canUpdate == true ? 'checked' : ''} onclick="return false;" /><br/>
			Delete: <input type="checkbox" name="canDelete" value="1" ${user.canDelete == true ? 'checked' : ''} onclick="return false;" /><br />
			<br />
			<button type="button" onclick="showModal('modalProfileChangePass', 0)">Change Password</button> <br />
			<input type="hidden" name="action" value="editUser" />
			<input type="submit" value="Update" /><br />
		</form>
	</div>
</body>
</html>