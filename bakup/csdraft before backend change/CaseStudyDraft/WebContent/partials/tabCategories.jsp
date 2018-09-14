<button type="button" onclick="showModal('modalAddCategory', 0)">Add Category</button> <br />
List of Categories:<br />
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