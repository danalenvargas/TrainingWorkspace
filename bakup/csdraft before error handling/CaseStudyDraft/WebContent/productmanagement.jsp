<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="content-Type" content="text/html; charset=ISO-8859-1">
	<title>Product Management</title>
	<script src="script/modal.js"></script>
	<script src="script/productmanagement.js"></script>
	<script src="script/tab.js"></script>
	<link rel="stylesheet" href="css/main.css" />
	<link rel="stylesheet" href="css/modal.css" />
	<link rel="stylesheet" href="css/tab.css" />
	
	<script type="text/javascript">  // make some request-scope variables available in javascript later on
	    var selectedTab = '${selectedTab}';
	    var privileges = {
		    canCreate : '${user.canCreate}',
		    canUpdate : '${user.canUpdate}',
		    canDelete : '${user.canDelete}',
	    };
	</script>
</head>
<body>
	<%@include file="partials/header.jsp" %>
	<%@include file="modals/modalAddCategory.html" %>
	<%@include file="modals/modalEditCategory.html" %>
	<%@include file="modals/modalAddProduct.html" %>
	<%@include file="modals/modalEditProduct.html" %>
	<%@include file="modals/modalInputBatch.html" %>
	<%@include file="modals/modalEditBatch.html" %>
	<%@include file="modals/modalEditItems.html" %>
	
	<div class="content">
		<div class="tab">
			<button id="tabBtnCategories" class="tablinks" onclick="openTab(event, 'tabCategories')">Categories</button>
			<button id="tabBtnProducts" class="tablinks" onclick="openTab(event, 'tabProducts')">Products</button>
			<button id="tabBtnBatches" class="tablinks" onclick="openTab(event, 'tabBatches')">Batches</button>
			<button id="tabBtnItems" class="tablinks" onclick="openTab(event, 'tabItems')">Items</button>
		</div>
		<div id="tabCategories" class="tabcontent">
			<%@include file="partials/tabCategories.jsp" %>
		</div>
		<div id="tabProducts" class="tabcontent">
			<%@include file="partials/tabProducts.jsp" %>
		</div>
		<div id="tabBatches" class="tabcontent">
			<%@include file="partials/tabBatches.jsp" %>
		</div>
		<div id="tabItems" class="tabcontent">
			<%@include file="partials/tabItems.jsp" %>
		</div>
	</div>
</body>
</html>