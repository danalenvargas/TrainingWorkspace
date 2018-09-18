<button class="btnAdd" type="button" onclick="showModal('modalInputBatch', 4)">Input Batch of Items</button> <br />
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