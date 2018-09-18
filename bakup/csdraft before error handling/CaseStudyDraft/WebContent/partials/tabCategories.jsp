<button class="btnAdd" type="button" onclick="showModal('modalAddCategory', 0)">Add Category</button> <br />
List of Categories:<br />
<table id="tblCategory">
	<tr>
		<th onclick="sortTable('tblCategory', 0, 'catRow')">Name</th>
		<th onclick="sortTable('tblCategory', 1, 'catRow')">Type</th>
		<th onclick="sortTable('tblCategory', 2, 'catRow')">Is Perishable</th>
		<th onclick="sortTable('tblCategory', 3, 'catRow')">Is Recyclable</th>
	</tr>
	<tr>
		<th><input class="colFilter" type="text" value=""></th>
		<th><select class="colFilter">
				<option value="" selected></option>
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
		</select></th>
		<th><select class="colFilter">
			<option value="" selected></option>
			<option value="Perishable">Perishable</option>
			<option value="Non-perishable">Non-perishable</option>
		</select></th>
		<th><select class="colFilter">
			<option value="" selected></option>
			<option value="Recyclable">Recyclable</option>
			<option value="Non-recyclable">Non-recyclable</option>
		</select>
		<button type="button" onclick="filterTable('tblCategory')">Filter</button></th>
	</tr>
	<c:forEach items="${categoryList}" var="category">
		<tr class="catRow">
			<td onclick="showCategoryDetails('${category.categoryId}')">${category.categoryName}</td>
			<td>${category.categoryType}</td>
			<td>${category.isPerishable == true ? 'Perishable' : 'Non-perishable'}</td>
			<td>${!empty category.isRecyclable ? (category.isRecyclable == true ? 'Recyclable' : 'Non-recyclable') : '---'}</td>
		</tr>
	</c:forEach>
</table>