<button class="btnAdd" type="button" onclick="showModal('modalAddCategory', 0)">Add Category</button> <br />
List of Categories:<br />
<table id="tblCategory">
	<tr>
		<th onclick="sortTable('tblCategory', 0)">Name</th>
		<th onclick="sortTable('tblCategory', 1)">Type</th>
		<th onclick="sortTable('tblCategory', 2)">Is Perishable</th>
		<th onclick="sortTable('tblCategory', 3)">Is Recyclable</th>
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
			<option value="true">true</option>
			<option value="false">false</option>
		</select></th>
		<th><select class="colFilter">
			<option value="" selected></option>
			<option value="true">true</option>
			<option value="false">false</option>
		</select>
		<button type="button" onclick="filterTable('tblCategory')">Filter</button></th>
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