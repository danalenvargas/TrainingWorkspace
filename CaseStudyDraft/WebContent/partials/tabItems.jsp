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