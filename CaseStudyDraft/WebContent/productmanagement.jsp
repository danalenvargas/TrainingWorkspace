<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-Type" content="text/html; charset=ISO-8859-1">
	<title>Product Management</title>
	<script src="script/productmanagement.js"></script>
</head>
<body>
<h1>Product Management</h1>
<!-- =============================== -->
<!-- |   LIST OF CATEGORIES      | -->
<!-- =============================== -->
List of Categories:<br>
<div style="width:50%; float:left;">
	<table>
		<tr>
			<th>Name</th>
			<th>Type</th>
			<th>Is Perishable</th>
			<th>Is Recyclable</th>
		</tr>
		<c:forEach items="${categoryList}" var="category">
			<tr>
				<td onclick="showCategoryDetails('${category.categoryId}');">${category.name}</td>
				<td>${category.productType}</td>
				<td>${category.isPerishable}</td>
				<td>${category.isRecyclable}</td>
			</tr>
		</c:forEach>
	</table>
	<br><br>
	
	<!-- =============================== -->
	<!-- |   FOR ADDING NEW CATEGORY      | -->
	<!-- =============================== -->
	Add Category:
	<fieldset>
		<form action="ProductManagement" method="post" >
			Name: <input type="text" name="name" placeholder="category name"> <br>
			Product Type:  <select name="productType">
				<option value="Beverages" title="coffee/tea, juice, soda">Beverages</option>
				<option value="Bread/Bakery" title="sandwich loaves, dinner rolls, tortillas, bagels">Bread/Bakery</option>
				<option value="Canned/Jarred Goods" title="vegetables, spaghetti sauce, ketchup">Canned/Jarred Goods</option>
				<option value="Dry/Baking Goods">Dry/Baking Goods</option>
				<option value="Frozen Foods">Frozen Foods</option>
				<option value="Meat">Meat</option>
				<option value="Produce">Produce</option>
				<option value="Cleaners">Cleaners</option>
				<option value="Paper Goods">Paper Goods</option>
				<option value="Personal Care">Personal Care</option>
				<option value="Other" selected>Other</option>
			</select> <br>
			<fieldset>
				Perishable: <input type="radio" name="perishable" value="true" onclick="disableRecyclableButtons()"> 
				Non Perishable: <input type="radio" name="perishable" value="false" onclick="enableRecyclableButtons()" checked><br>
				Recyclable: <input id="radRecyclable" type="radio" name="recyclable" value="true"> 
				Not Recyclable: <input id="radNotRecyclable" type="radio" name="recyclable" value="false" checked>
			</fieldset>
			<br>
			<input type="hidden" name="action" value="addCategory">
			<input type="submit" value="Submit">
		</form>
	</fieldset>
	<br>
	<!-- =============================== -->
	<!-- |   LIST OF PRODUCTS      | -->
	<!-- =============================== -->
	List of Products:<br>
	<table>
		<tr>
			<th>Category</th>
			<th>SKU</th>
			<th>Brand</th>
			<th>Variant</th>
			<th>Size</th>
			<th>Description</th>
			<th>Special Handling</th>
			<th>Selling Price</th>
			<th>Stock Amount</th>
		</tr>
		<c:forEach items="${productList}" var="product">
			<tr>
				<td onclick="showProductDetails('${product.productId}');">${product.category.name}</td>
				<td>${product.SKU}</td>
				<td>${product.brand}</td>
				<td>${product.variant}</td>
				<td>${product.size} ${product.measurementUnit}</td>
				<td>${product.description}</td>
				<td>${product.specialHandling}</td>
				<td>${product.sellPrice}</td>
				<td>${product.stockAmount}</td>
			</tr>
		</c:forEach>
	</table>
	<br><br>
	<!-- =============================== -->
	<!-- |   FOR ADDING NEW PRODUCT      | -->
	<!-- =============================== -->
	Add Product:
	<fieldset>
		<form action="ProductManagement" method="post" >
			Category:  <select id="addProdCategory" name="categoryId">
				<c:forEach items="${categoryList}" var="category">
					<option value="${category.categoryId}" title="${category.productType}">${category.name}</option>
				</c:forEach>
			</select> <br>
			Brand: <input id="addProdBrand" type="text" name="brand" placeholder="brand"> <br>
			Variant: <input id="addProdVariant" type="text" name="variant" value="none" placeholder="none"> <br>
			Size: <input id="addProdSize" type="text" name="size" placeholder="size">
			<input id="addProdUnit" type="text" name="measurementUnit" placeholder="unit"> <br>
			Description: <textarea name="description" placeholder="description" rows="3" cols="40"></textarea> <br>
			Special Handling: <select name="specialHandling">
				<option value="" selected></option>
				<option value="keep frozen">keep frozen</option>
				<option value="keep frozen">keep refrigerated</option>
				<option value="keep frozen">fragile</option>
			</select> <br>
			Selling Price: Php<input type="number" name="sellPrice" placeholder="sell price" step="0.01"> <br><br>
			SKU: <input id="addProdSKU" type="text" name="SKU" placeholder="Stock Keeping Unit">
			<button type="button" onclick="generateSKU()">Generate</button> <br>
			<input type="hidden" name="action" value="addProduct"> 
			<input type="submit" value="Submit">
		</form>
	</fieldset>
	<br>
	<br>
	Input items
</div>

<!-- ==============================================================================================================-->
<!-- ======================================== EDITING AND DELETING ================================================-->
<!-- ==============================================================================================================-->

<div style="width:50%; float:right;">
	<!-- =============================== -->
	<!-- |   FOR EDITING CATEGORY      | -->
	<!-- =============================== -->
	<div id="editCategoryForm" style="width:50%; float:right; visibility: hidden">
		Category Details:
		<form action="ProductManagement" method="post">
			Name: <input id="txtCategoryName" type="text" name="name" placeholder="category name"> <br>
			Product Type:  <select id="selProductType" name="productType">
				<option value="Beverages" title="coffee/tea, juice, soda">Beverages</option>
				<option value="Bread/Bakery" title="sandwich loaves, dinner rolls, tortillas, bagels">Bread/Bakery</option>
				<option value="Canned/Jarred Goods" title="vegetables, spaghetti sauce, ketchup">Canned/Jarred Goods</option>
				<option value="Dry/Baking Goods">Dry/Baking Goods</option>
				<option value="Frozen Foods">Frozen Foods</option>
				<option value="Meat">Meat</option>
				<option value="Produce">Produce</option>
				<option value="Cleaners">Cleaners</option>
				<option value="Paper Goods">Paper Goods</option>
				<option value="Personal Care">Personal Care</option>
				<option value="Other">Other</option>
			</select> <br>
			<fieldset>
				Perishable: <input id="radEditPerishable" type="radio" name="perishable" value="true" onclick="disableEditRecyclableButtons()"> 
				Non Perishable: <input id="radEditNonPerishable" type="radio" name="perishable" value="false" onclick="enableEditRecyclableButtons()" checked><br>
				Recyclable: <input id="radEditRecyclable" type="radio" name="recyclable" value="true"> 
				Not Recyclable: <input id="radEditNotRecyclable" type="radio" name="recyclable" value="false" checked>
			</fieldset>
			<br>
			<input id="txtCategoryId" type="hidden" name="categoryId">
			<input type="hidden" name="action" value="editCategory">
			<input type="submit" value="Update"><br>
		</form>
		<form action="ProductManagement" method="post">
			<input id="txtCategoryId2" type="hidden" name="categoryId">
			<input type="hidden" name="action" value="deleteCategory">
			<input type="submit" value="Remove Category">
		</form>
	</div>
	<div id="editProductForm" >
		<form action="ProductManagement" method="post" >
			Category:  <select id="editProdCategory" name="categoryId">
				<c:forEach items="${categoryList}" var="category">
					<option value="${category.categoryId}" title="${category.productType}">${category.name}</option>
				</c:forEach>
			</select> <br>
			Brand: <input id="editProdBrand" type="text" name="brand" placeholder="brand"> <br>
			Variant: <input id="editProdVariant" type="text" name="variant" value="none" placeholder="none"> <br>
			Size: <input id="editProdSize" type="text" name="size" placeholder="size">
			<input id="editProdUnit" type="text" name="measurementUnit" placeholder="unit"> <br>
			Description: <textarea id = "editProdDescription" name="description" placeholder="description" rows="3" cols="40"></textarea> <br>
			Special Handling: <select id="editProdSpecialHandling" name="specialHandling">
				<option value="" selected></option>
				<option value="keep frozen">keep frozen</option>
				<option value="keep frozen">keep refrigerated</option>
				<option value="keep frozen">fragile</option>
			</select> <br>
			Selling Price: Php<input id="editProdSellPrice" type="number" name="sellPrice" placeholder="sell price" step="0.01"> <br><br>
			SKU: <input id="editProdSKU" type="text" name="SKU" placeholder="Stock Keeping Unit">
			<button type="button" onclick="generateSKU()">Generate</button> <br>
			<input type="hidden" name="action" value="addProduct">
			<input type="submit" value="Submit">
		</form>
	</div>
</div>
</body>
</html>