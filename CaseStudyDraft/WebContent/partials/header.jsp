<!-- HEADER PART, ONLY MEANT TO BE INCLUDED INTO SEVERAL OTHER PAGES -->
<div class="header">
	<h2>Inventory Management System</h2>
	<div class="nav">
		<ul>
		  <li><a id="navHome" href="home.jsp">Home</a></li>
		  <li><a id="navInventory" href="ProductManagement">Inventory</a></li>
		  <c:if test="${user.userType == 'admin'}">
		  	<li><a id="navUser" href="UserManagement" onload="setActiveNavTab()">User Management</a></li>
		  </c:if>
		  <li><a id="navProfile" href="Profile">Profile</a></li>
		  <li><a id="" href="Login?action=logout">Log Out</a></li>
		</ul>
	</div>
</div>