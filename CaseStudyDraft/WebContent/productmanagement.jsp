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
List of Categories:<br />
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
				<td onclick="showCategoryDetails('${category.categoryId}')">${category.categoryName}</td>
				<td>${category.categoryType}</td>
				<td>${category.isPerishable}</td>
				<td>${category.isRecyclable}</td>
			</tr>
		</c:forEach>
	</table>
	<br /><br />
	
	<!-- =============================== -->
	<!-- |   FOR ADDING NEW CATEGORY      | -->
	<!-- =============================== -->
	Add Category:
	<fieldset>
		<form action="ProductManagement" method="post" >
			Name: <input type="text" name="categoryName" placeholder="category name"> <br />
			Category Type:  <select name="categoryType">
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
			</select> <br />
			<fieldset>
				Perishable: <input type="radio" name="perishable" value="true" onclick="disableRecyclableButtons()"> 
				Non Perishable: <input type="radio" name="perishable" value="false" onclick="enableRecyclableButtons()" checked><br />
				Recyclable: <input id="radRecyclable" type="radio" name="recyclable" value="true"> 
				Not Recyclable: <input id="radNotRecyclable" type="radio" name="recyclable" value="false" checked>
			</fieldset>
			<br />
			<input type="hidden" name="action" value="addCategory" />
			<input type="submit" value="Submit" />
		</form>
	</fieldset>
	<br />
	<!-- =============================== -->
	<!-- |   LIST OF PRODUCTS      | -->
	<!-- =============================== -->
	List of Products:<br />
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
		<c:forEach items="${categoryList}" var="category">
			<tr>
				<td>${category.categoryName}</td>
			</tr>
			<c:forEach items="${category.products}" var="product">
				<tr onclick="showProductDetails('${product.productId}')">
					<td></td>
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
		</c:forEach>
	</table>
	<br /><br />
	<!-- =============================== -->
	<!-- |   FOR ADDING NEW PRODUCT      | -->
	<!-- =============================== -->
	Add Product:
	<fieldset>
		<form action="ProductManagement" method="post">
			Category:  <select id="addProdCategory" name="categoryId">
				<c:forEach items="${categoryList}" var="category">
					<option value="${category.categoryId}" title="${category.categoryType}">${category.categoryName}</option>
				</c:forEach>
			</select> <br />
			Brand: <input id="addProdBrand" type="text" name="brand" placeholder="brand" /> <br />
			Variant: <input id="addProdVariant" type="text" name="variant" value="none" placeholder="none" /> <br />
			Size: <input id="addProdSize" type="text" name="size" placeholder="size" />
			<input id="addProdUnit" type="text" name="measurementUnit" placeholder="unit" /> <br />
			Description: <textarea name="description" placeholder="description" rows="3" cols="40"></textarea> <br />
			Special Handling: <select name="specialHandling">
				<option value="" selected></option>
				<option value="keep frozen">keep frozen</option>
				<option value="keep refrigerated">keep refrigerated</option>
				<option value="fragile">fragile</option>
			</select> <br />
			Selling Price: Php<input type="number" name="sellPrice" placeholder="sell price" step="0.01"> <br /><br />
			SKU: <input id="addProdSKU" type="text" name="SKU" placeholder="Stock Keeping Unit">
			<button type="button" onclick="generateSKU('onAdd')">Generate</button> <br />
			<input type="hidden" name="action" value="addProduct" /> 
			<input type="submit" value="Submit" />
		</form>
	</fieldset>
	<br />
	<br />
	<!-- =============================== -->
	<!-- |   LIST OF BATCHES      | -->
	<!-- =============================== -->
	List of Batches:<br />
	<table>
		<tr>
			<th>Category</th>
			<th>Product</th>
			<th>Batch id</th>
			<th>Amount</th>
			<th>Supplier</th>
			<th>Comments</th>
			<th>Entry Timestamp</th>
		</tr>
		<c:forEach items="${categoryList}" var="category">
			<tr>
				<td>${category.categoryName}</td>
			</tr>
			<c:forEach items="${category.products}" var="product">
				<tr>
					<td></td>
					<td>${product.SKU} (${product.brand}/${product.variant}/${product.size} ${product.measurementUnit})</td>
				</tr>
				<c:forEach items="${product.batches}" var="batch">
					<tr onclick="showBatchDetails('${batch.batchId}')">
						<td></td>
						<td></td>
						<td>${batch.batchId}</td>
						<td>${batch.remainingAmount} / ${batch.amount}</td>
						<td>${batch.supplier}</td>
						<td>${batch.comments}</td>
						<td>${batch.entryTimestamp}</td>
					</tr>
				</c:forEach>
			</c:forEach>
		</c:forEach>
	</table>
	<br /><br />
	<!-- =============================== -->
	<!-- |   FOR ADDING NEW BATCH      | -->
	<!-- =============================== -->
	Add Batch:
	<fieldset>
		<form action="ProductManagement" method="post" accept-charset=utf-8>
			Product:  <select name="productId">
				<c:forEach items="${categoryList}" var="category">
					<c:forEach items="${category.products}" var="product">
						<option value="${product.productId}" title="${category.categoryType}">${product.SKU} (${category.categoryName}-${product.brand}/${product.variant}/${product.size} ${product.measurementUnit})</option>
					</c:forEach>
				</c:forEach>
			</select> <br />
			Amount: <input type="number" name="amount" /> <br />
			Supplier: <input type="text" name="supplier" value="" /> <br />
			Comments: <textarea name="comments" placeholder="comments" rows="3" cols="40"></textarea> <br />
			Manufacture Date: <input type="date" name="manufactureDate" /> <br />
			Expiration Date: <input type="date" name="expirationDate" /> <br />
			Purchase Price (per item): Php<input type="number" name="purchasePrice" step="0.01" /> <br />
			<input type="hidden" name="action" value="inputBatch" />
			<input type="submit" value="Submit" />
		</form>
	</fieldset>
	<br /><br />
	<!-- =============================== -->
	<!-- |   LIST OF ITEMS      | -->
	<!-- =============================== -->
	List of Items:<br />
	<button type="button" onclick="showItemEditForm()">Edit Items</button>
	<form action="ProductManagement" method="post" onsubmit="setIdsToDelete()">
		<input id="deleteItemIds" type="hidden" name="itemIds">
		<input type="hidden" name="action" value="deleteItems" />
		<input type="submit" value="Delete Items" /> <br />
	</form>
	<table>
		<tr>
			<th>Category</th>
			<th>Product</th>
			<th>Batch</th>
			<th>Item Id</th>
			<th>Manufacture Date</th>
			<th>Expiration Date</th>
			<th>Purchase Price</th>
		</tr>
		<c:forEach items="${categoryList}" var="category">
			<tr>
				<td>${category.categoryName}</td>
			</tr>
			<c:forEach items="${category.products}" var="product">
				<tr>
					<td></td>
					<td>${product.SKU} (${product.brand}/${product.variant}/${product.size} ${product.measurementUnit})</td>
				</tr>
				<c:forEach items="${product.batches}" var="batch">
					<tr onclick="toggleCollapse('${batch.batchId}')">
						<td></td>
						<td></td>
						<td>${batch.batchId}</td>
					</tr>
					<c:forEach items="${batch.items}" var="item">
						<tr class="items-${batch.batchId}" style="display: none">
							<td></td>
							<td></td>
							<td></td>
							<td><input type="checkbox" value="${item.itemId}" onchange="toggleSelected(this);">${item.itemId}</td>
							<td>${item.manufactureDate}</td>
							<td>${item.expirationDate}</td>
							<td>${item.purchasePrice}</td>
						</tr>
					</c:forEach>
				</c:forEach>
			</c:forEach>
		</c:forEach>
	</table>
	<br /><br />
</div>

<!-- ==============================================================================================================-->
<!-- ======================================== EDITING AND DELETING ================================================-->
<!-- ==============================================================================================================-->

<div style="width:50%; float:right;">
	<!-- =============================== -->
	<!-- |   FOR EDITING CATEGORY      | -->
	<!-- =============================== -->
<!-- 	<div id="editCategoryForm" style="width:50%; float:right; visibility: hidden"> -->
	<div id="editCategoryForm" style="visibility: hidden">
		Category Details:
		<form action="ProductManagement" method="post">
			Name: <input id="txtCategoryName" type="text" name="categoryName" placeholder="category name" /> <br />
			Category Type:  <select id="selCategoryType" name="categoryType">
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
			</select> <br />
			<fieldset>
				Perishable: <input id="radEditPerishable" type="radio" name="perishable" value="true" onclick="disableEditRecyclableButtons()" /> 
				Non Perishable: <input id="radEditNonPerishable" type="radio" name="perishable" value="false" onclick="enableEditRecyclableButtons()" checked /><br />
				Recyclable: <input id="radEditRecyclable" type="radio" name="recyclable" value="true" /> 
				Not Recyclable: <input id="radEditNotRecyclable" type="radio" name="recyclable" value="false" checked />
			</fieldset>
			<br />
			<input id="txtCategoryId" type="hidden" name="categoryId" />
			<input type="hidden" name="action" value="editCategory" />
			<input type="submit" value="Update" /><br />
		</form>
		<form action="ProductManagement" method="post">
			<input id="txtCategoryId2" type="hidden" name="categoryId" />
			<input type="hidden" name="action" value="deleteCategory" />
			<input type="submit" value="Remove Category" />
		</form>
	</div>
	<!-- =============================== -->
	<!-- |   FOR EDITING PRODUCT      | -->
	<!-- =============================== -->
	<div id="editProductForm" style="visibility: hidden">
		<form action="ProductManagement" method="post" >
			Category:  <select id="editProdCategory" name="categoryId">
				<c:forEach items="${categoryList}" var="category">
					<option value="${category.categoryId}" title="${category.categoryType}">${category.categoryName}</option>
				</c:forEach>
			</select> <br />
			Brand: <input id="editProdBrand" type="text" name="brand" placeholder="brand" /> <br />
			Variant: <input id="editProdVariant" type="text" name="variant" value="none" placeholder="none" /> <br />
			Size: <input id="editProdSize" type="text" name="size" placeholder="size" />
			<input id="editProdUnit" type="text" name="measurementUnit" placeholder="unit" /> <br />
			Description: <textarea id = "editProdDescription" name="description" placeholder="description" rows="3" cols="40"></textarea> <br />
			Special Handling: <select id="editProdSpecialHandling" name="specialHandling">
				<option value="" selected></option>
				<option value="keep frozen">keep frozen</option>
				<option value="keep refrigerated">keep refrigerated</option>
				<option value="fragile">fragile</option>
			</select> <br />
			Selling Price: Php<input id="editProdSellPrice" type="number" name="sellPrice" placeholder="sell price" step="0.01"> <br /><br />
			SKU: <input id="editProdSKU" type="text" name="SKU" placeholder="Stock Keeping Unit" />
			<button type="button" onclick="generateSKU('onEdit')">Generate</button> <br />
			<input id="editProdId" type="hidden" name="productId" />
			<input type="hidden" name="action" value="editProduct" />
			<input type="submit" value="Update" />
		</form>
		<form action="ProductManagement" method="post">
			<input id="editProdId2" type="hidden" name="productId" />
			<input type="hidden" name="action" value="deleteProduct" />
			<input type="submit" value="Remove Product" />
		</form>
	</div>
	<!-- =============================== -->
	<!-- |   FOR EDITING BATCH      | -->
	<!-- =============================== -->
	<div id="editBatchForm" style="visibility: hidden">
		<form action="ProductManagement" method="post" >
			Batch ID: <span id="editBatchId"></span> <br />
			Entry time: <span id="editBatchEntryTimestamp"></span> <br />
			Product:  <select id="editBatchProduct" name="productId">
				<c:forEach items="${categoryList}" var="category">
					<c:forEach items="${category.products}" var="product">
						<option value="${product.productId}" title="${category.categoryType}">${product.SKU} (${category.categoryName}-${product.brand}/${product.variant}/${product.size} ${product.measurementUnit})</option>
					</c:forEach>
				</c:forEach>
			</select> <br />
<!-- IMPORTANT ----			add warning that editing amount does not modify the inputted items -->
			Amount: <span id="editBatchRemainingAmount"></span> / <input id="editBatchAmount" type="number" name="amount" /> <br /> 
			Supplier: <input id="editBatchSupplier" type="text" name="supplier" value="" /> <br />
			Comments: <textarea id="editBatchComments" name="comments" placeholder="comments" rows="3" cols="40"></textarea> <br />
<!-- IMPORTANT ----			FIGURE OUT HOW TO MANAGE EDITING OF EXPIRATION AND MANUFACTURING DATES (I can just do this at the item modification level) -->
<!-- 			Manufacture Date: <input id="editBatchManufactureDate" type="date" name="manufactureDate" /> <br /> -->
<!-- 			Expiration Date: <input id="editBatchExpirationDate" type="date" name="expirationDate" /> <br /> -->
<!-- 			Purchase Price (per item): Php<input id="editBatchPurchasePrice" type="number" name="purchasePrice" step="0.01" /> <br /> -->
			<input id="editBatchId2" type="hidden" name="batchId" />
			<input type="hidden" name="action" value="editBatch" />
			<input type="submit" value="Update" />
		</form>
		<form action="ProductManagement" method="post">
			<input id="editBatchId3" type="hidden" name="batchId" />
			<input type="hidden" name="action" value="deleteBatch" />
			<input type="submit" value="Remove Batch" />
		</form>
	</div>
	<!-- =============================== -->
	<!-- |   FOR EDITING ITEM      | -->
	<!-- =============================== -->
	<div id="editItemForm" style="visibility: hidden">
		<form action="ProductManagement" method="post" >
			Item ID: <span id="editItemIds"></span> <br />
			Manufacture Date: <input id="editItemManufactureDate" type="date" name="manufactureDate" /> <br />
			Expiration Date: <input id="editItemExpirationDate" type="date" name="expirationDate" /> <br />
			Purchase Price (per item): Php<input id="editItemPurchasePrice" type="number" name="purchasePrice" step="0.01" /> <br />
			<input id="editItemIdsHidden" type="hidden" name="itemIds" />
			<input type="hidden" name="action" value="editItems" />
			<input type="submit" value="Update" />
		</form>
	</div>
</div>
</body>
</html>