<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Product Management</title>
	
	<script src="script/productmanagement.js"></script>

</head>
<body>
<h1>Product Management</h1>
<!-- =============================== -->
<!-- |   FOR ADDING NEW PRODUCTS      | -->
<!-- =============================== -->
Add Category
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
Add Product
<br>
Input items


</body>
</html>