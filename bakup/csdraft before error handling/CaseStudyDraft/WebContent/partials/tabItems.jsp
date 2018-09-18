<button class="btnEdit" type="button" onclick="showItemEditForm()">Edit Items</button>
<form action="ProductManagement" method="post" onsubmit="setIdsToDelete()">
	<input id="deleteItemIds" type="hidden" name="itemIds">
	<input type="hidden" name="action" value="deleteItems" />
	<input class="btnDelete" type="submit" value="Delete Items" /> 
</form> <br />
Geouping level: <select id="selGrouping" >
		<option value="none">None</option>
		<option value="category">Category</option>
		<option value="product">Product</option>
		<option value="batch" selected>Batch</option>
</select> 
<button type="button" onclick="applyGrouping()">Apply Grouping</button>
<br />
<!-- style="overflow-x: scroll" -->
<table id="tblItem" style="overflow-x: scroll"> 
	<thead>
		<tr>
			<th class="categoryColMinimized">Category <button type="button" onclick="expandColumns('categoryCol')"> &gt; </button></th>
			<th class="categoryCol" style="display:none">Category <button type="button" onclick="minimizeColumns('categoryCol')"> &lt; </button></th>
			<th class="categoryCol" style="display:none">Type</th>
			<th class="categoryCol" style="display:none">Is Perishable</th>
			<th class="categoryCol" style="display:none">Is Recyclable</th>
			
			<th class="productColMinimized">Product <button type="button" onclick="expandColumns('productCol')"> &gt; </button></th>
			<th class="productCol" style="display:none">Product <button type="button" onclick="minimizeColumns('productCol')"> &lt; </button></th>
			<th class="productCol" style="display:none">Brand</th>
			<th class="productCol" style="display:none">Variant</th>
			<th class="productCol" style="display:none">Size</th>
			<th class="productCol" style="display:none">Description</th>
			<th class="productCol" style="display:none">Special Handling</th>
			<th class="productCol" style="display:none">Selling Price</th>
			<th class="productCol" style="display:none">Stock Amount</th>
			
			<th onClick="" class="batchColMinimized">Batch <button type="button" onclick="expandColumns('batchCol')"> &gt; </button></th>
			<th class="batchCol" style="display:none">Batch <button type="button" onclick="minimizeColumns('batchCol')"> &lt; </button></th>
			<th class="batchCol" style="display:none">Amount</th>
			<th class="batchCol" style="display:none">Supplier</th>
			<th class="batchCol" style="display:none">Comments</th>
			<th class="batchCol" style="display:none">Entry Timestamp</th>
			
			<th onClick="">Item Id</th>
			<th onClick="">Manufacture Date</th>
<!-- 			<th onClick="sortItemTable('tblItem', 21)">Manufacture Date</th> -->
			<th>Expiration Date</th>
			<th>Purchase Price</th>
		</tr>
		<tr>
			<th class="colFilter"></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${categoryList}" var="category">
			<tr id="cat${category.categoryId}" class="categoryRow">
				<td class="categoryColMinimized">${category.categoryName}</td>
				<td class="categoryCol" style="display:none">${category.categoryName}</td>
				<td class="categoryCol" style="display:none">${category.categoryType}</td>
				<td class="categoryCol" style="display:none">${category.isPerishable == true ? 'Perishable' : 'Non-perishable'}</td>
				<td class="categoryCol" style="display:none">${!empty category.isRecyclable ? (category.isRecyclable == true ? 'Recyclable' : 'Non-recyclable') : '---'}</td>
			</tr>
			<c:forEach items="${category.products}" var="product">
				<tr id="prod${product.productId}" class="productRow">
					<td class="categoryColMinimized"></td>
					<td class="categoryCol" style="display:none"></td>
					<td class="categoryCol" style="display:none"></td>
					<td class="categoryCol" style="display:none"></td>
					<td class="categoryCol" style="display:none"></td>
					
					<td class="productColMinimized">${product.SKU} (${product.brand}/${product.variant}/${product.size} ${product.measurementUnit})</td>
					<td class="productCol" style="display:none">${product.SKU}</td>
					<td class="productCol" style="display:none">${product.brand}</td>
					<td class="productCol" style="display:none">${product.variant}</td>
					<td class="productCol" style="display:none">${product.size} ${product.measurementUnit}</td>
					<td class="productCol" style="display:none">${product.description}</td>
					<td class="productCol" style="display:none">${product.specialHandling}</td>
					<td class="productCol" style="display:none">${product.sellPrice}</td>
					<td class="productCol" style="display:none">${product.stockAmount}</td>
				</tr>
				<c:forEach items="${product.batches}" var="batch">
					<tr id="bat${batch.batchId}" class="batchRow batchRow-${product.productId}" onclick="toggleCollapse('bat${batch.batchId}')">
						<td class="categoryColMinimized"></td>
						<td class="categoryCol" style="display:none"></td>
						<td class="categoryCol" style="display:none"></td>
						<td class="categoryCol" style="display:none"></td>
						<td class="categoryCol" style="display:none"></td>
						
						<td class="productColMinimized"></td>
						<td class="productCol" style="display:none"></td>
						<td class="productCol" style="display:none"></td>
						<td class="productCol" style="display:none"></td>
						<td class="productCol" style="display:none"></td>
						<td class="productCol" style="display:none"></td>
						<td class="productCol" style="display:none"></td>
						<td class="productCol" style="display:none"></td>
						<td class="productCol" style="display:none"></td>
						
						<td class="batchColMinimized">&num;${batch.batchId} (${batch.remainingAmount} / ${batch.amount})</td>
						<td class="batchCol" style="display:none">&num;${batch.batchId}</td>
						<td class="batchCol" style="display:none">${batch.remainingAmount} / ${batch.amount}</td>
						<td class="batchCol" style="display:none">${batch.supplier}</td>
						<td class="batchCol" style="display:none">${batch.comments}</td>
						<td class="batchCol" style="display:none">${batch.entryTimestamp}</td>
					</tr>
					<c:forEach items="${batch.items}" var="item">
						<tr class="itemRow itemRow-bat${batch.batchId} itemRow-prod${product.productId} itemRow-cat${category.categoryId}" style="display: none">
							<td class="categoryColMinimized"><span class="categoryColSpan" style="display:none">${category.categoryName}</span></td>
							<td class="categoryCol" style="display:none"><span class="categoryColSpan" style="display:none">${category.categoryName}</span></td>
							<td class="categoryCol" style="display:none"><span class="categoryColSpan" style="display:none">${category.categoryType}</span></td>
							<td class="categoryCol" style="display:none"><span class="categoryColSpan" style="display:none">${category.isPerishable == true ? 'Perishable' : 'Non-perishable'}</span></td>
							<td class="categoryCol" style="display:none"><span class="categoryColSpan" style="display:none">${!empty category.isRecyclable ? (category.isRecyclable == true ? 'Recyclable' : 'Non-recyclable') : '---'}</span></td>
							
							<td class="productColMinimized"><span class="productColSpan" style="display:none">${product.SKU} (${product.brand}/${product.variant}/${product.size} ${product.measurementUnit})</span></td>
							<td class="productCol" style="display:none"><span class="productColSpan" style="display:none">${product.SKU}</span></td>
							<td class="productCol" style="display:none"><span class="productColSpan" style="display:none">${product.brand}</span></td>
							<td class="productCol" style="display:none"><span class="productColSpan" style="display:none">${product.variant}</span></td>
							<td class="productCol" style="display:none"><span class="productColSpan" style="display:none">${product.size} ${product.measurementUnit}</span></td>
							<td class="productCol" style="display:none"><span class="productColSpan" style="display:none">${product.description}</span></td>
							<td class="productCol" style="display:none"><span class="productColSpan" style="display:none">${product.specialHandling}</span></td>
							<td class="productCol" style="display:none"><span class="productColSpan" style="display:none">${product.sellPrice}</span></td>
							<td class="productCol" style="display:none"><span class="productColSpan" style="display:none">${product.stockAmount}</span></td>
						
							<td class="batchColMinimized"><span class="batchColSpan" style="display:none">${batch.batchId}</span></td>
							<td class="batchCol" style="display:none"><span class="batchColSpan" style="display:none">${batch.batchId}</span></td>
							<td class="batchCol" style="display:none"><span class="batchColSpan" style="display:none">${batch.remainingAmount} / ${batch.amount}</span></td>
							<td class="batchCol" style="display:none"><span class="batchColSpan" style="display:none">${batch.supplier}</span></td>
							<td class="batchCol" style="display:none"><span class="batchColSpan" style="display:none">${batch.comments}</span></td>
							<td class="batchCol" style="display:none"><span class="batchColSpan" style="display:none">${batch.entryTimestamp}</span></td>
							
							<td><input type="checkbox" value="${item.itemId}" onchange="toggleSelected(this);">${item.itemId}</td>
							<td>${item.manufactureDate}</td>
							<td>${item.expirationDate}</td>
							<td>${item.purchasePrice}</td>
						</tr>
					</c:forEach>
				</c:forEach>
			</c:forEach>
		</c:forEach>
	</tbody>
</table>