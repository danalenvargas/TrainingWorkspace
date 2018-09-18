<button class="btnAdd" type="button" onclick="showModal('modalAddProduct', 2)">Add Product</button> <br />
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