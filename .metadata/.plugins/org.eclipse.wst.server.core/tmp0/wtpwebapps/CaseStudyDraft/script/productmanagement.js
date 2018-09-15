/**
 * Scripts for functionalities in the Product/Inventory Management page (productmanagement.jsp)
 */

// array of selected items for edit/delete
var selectedItems = [];

// at page load, set the active navigation tab, set the active inventory tab, and apply user privileges
window.onload = function() {
	setActiveNavTab();
	setActiveInventoryTab();
	applyPrivileges(privileges);
};

//algorithm to sort the inventory table
//clicking a table header will trigger the sorting, clicking the same header again will sort it at the opposite direction
//parameters: tableId = id of table to be sorted, colIndex = which column to use for sorting the table
function sortTable(tableId, colIndex) {
	var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
	table = document.getElementById(tableId);
	switching = true;
	dir = "asc";
	
	while (switching) {
		switching = false;
		rows = table.rows;

		for (i = 2; i < (rows.length - 1); i++) {
			shouldSwitch = false;
			x = rows[i].getElementsByTagName("TD")[colIndex];
			y = rows[i + 1].getElementsByTagName("TD")[colIndex];
			if (dir == "asc") {
				if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
					shouldSwitch = true;
					break;
				}
			} else if (dir == "desc") {
				if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
					shouldSwitch = true;
					break;
				}
			}
		}
		
		if (shouldSwitch) {
			rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
			switching = true;
			switchcount ++;
		} else {
			if (switchcount == 0 && dir == "asc") {
				dir = "desc";
				switching = true;
			}
		}
	}
}

//filters the results shown on the table based on criteria per column
function filterTable(tableId) {
	var filterList, filter, table, rows, cell, i, j;
	table = document.getElementById(tableId);
	filterList = table.getElementsByClassName("colFilter");
	rows = table.getElementsByTagName("tr");
	
	for(j=0; j<rows.length; j++){
		rows[j].style.display = "";
	}
	
	for(i = 0; i<filterList.length; i++){
		filter = filterList[i].value.toUpperCase();
		if (filter != ""){
			for(j=0; j<rows.length; j++){
				cell = rows[j].getElementsByTagName("td")[i];
				if(cell){
					if(cell.innerHTML.toUpperCase().indexOf(filter) == -1){
						rows[j].style.display = "none";
					}
				}
			}
		}
	}
}

//disables some functionalities based on the priviliges available to user
//standard users can have any combination of Create, Update, Delete privileges
function applyPrivileges(privileges){
	var buttons = [];

	if(privileges.canCreate == "false"){
		buttons = buttons.concat(document.getElementsByClassName("btnAdd"));
	}
	if(privileges.canUpdate == "false"){
		buttons = buttons.concat(document.getElementsByClassName("btnEdit"));
	}
	if(privileges.canDelete == "false"){
		buttons = buttons.concat(document.getElementsByClassName("btnDelete"));
	}
	
	for(var j=0; j<buttons.length; j++){
		for(var i=0; i<buttons[j].length; i++){
			buttons[j][i].disabled = true;
		}
	}
}

//generates a Stock Keeping Unit (SKU) base on category, brand, variant, size
function generateSKU(location){
	var SKU = "";
	var skuInput;
	if(location == 'onAdd'){
		e = document.getElementById("addProdCategory");
		SKU += e.options[e.selectedIndex].text.substring(0,2);
		SKU += document.getElementById("addProdBrand").value.substring(0,2);
		SKU += document.getElementById("addProdVariant").value.substring(0,2);
		SKU += "-";
		SKU += document.getElementById("addProdSize").value;
		SKU += document.getElementById("addProdUnit").value.substring(0,2);
		
		skuInput = document.getElementById("addProdSKU");
	} else if (location == 'onEdit'){
		e = document.getElementById("editProdCategory");
		SKU += e.options[e.selectedIndex].text.substring(0,2);
		SKU += document.getElementById("editProdBrand").value.substring(0,2);
		SKU += document.getElementById("editProdVariant").value.substring(0,2);
		SKU += "-";
		SKU += document.getElementById("editProdSize").value;
		SKU += document.getElementById("editProdUnit").value.substring(0,2);
		
		skuInput = document.getElementById("editProdSKU")
	}
	skuInput.value = SKU.toUpperCase();
	validateProductSKU(skuInput);
}

//When checkbox beside an item is clicked, it is added to the selectedItems array to be edited or deleted later
function toggleSelected(checkbox){
	if(checkbox.checked == true){
		selectedItems.push(checkbox.value);
	}else{
		selectedItems.splice(selectedItems.indexOf(checkbox.value));
	}
}

//sets the items to delete when delete-items form is submitted
function setIdsToDelete(){
	document.getElementById("deleteItemIds").value = JSON.stringify(selectedItems);
}

//Collapses and expands a batch row is clicked to show/hide the contained items
function toggleCollapse(batchId){
	itemRows = document.getElementsByClassName("items-" + batchId);
	var styleToSet;
	if (itemRows[0] != null && itemRows[0].style.display == "none"){
		styleToSet = "";
	} else if(itemRows[0] != null && itemRows[0].style.display == ""){
		styleToSet = "none";
	}
	for(var i=0; i<itemRows.length; i++){
		itemRows[i].style.display = styleToSet;
	}
}

// sets the active Navigation tab to Inventory tab for styling purposes
function setActiveNavTab(){
	document.getElementById("navInventory").classList.add('active');
};

// sets the active Inventory tab to be shown, triggered at page load after a transaction 
// to remember which tab was active before the page reload
function setActiveInventoryTab(){
	switch(selectedTab){
	case "category":
		document.getElementById("tabBtnCategories").click();
		break;
	case "product":
		document.getElementById("tabBtnProducts").click();
		break;
	case "batch":
		document.getElementById("tabBtnBatches").click();
		break;
	case "item":
		document.getElementById("tabBtnItems").click();
		break;
	}
}

// on add-category and edit-category modals, if a category is selected as perishable, 
// disables the options to choose if category is recyclable or not
function enableRecyclableButtons(){
	document.getElementById("radRecyclable").disabled = false;
	document.getElementById("radNotRecyclable").disabled = false;
}

function disableRecyclableButtons(){
	document.getElementById("radRecyclable").disabled = true;
	document.getElementById("radNotRecyclable").disabled = true;
}
function enableEditRecyclableButtons(){
	document.getElementById("radEditRecyclable").disabled = false;
	document.getElementById("radEditNotRecyclable").disabled = false;
}

function disableEditRecyclableButtons(){
	document.getElementById("radEditRecyclable").disabled = true;
	document.getElementById("radEditNotRecyclable").disabled = true;
}

// Fetch a category's details from server, then display on the modal
function showCategoryDetails(categoryId){
	var myInit = {
			method:'GET',
			credentials: 'same-origin'
	};
	
	fetch('ProductManagement?action=getCategoryDetails&categoryId=' + categoryId, myInit)
		.then(function(response){
			response.json().then(function(category){
				showModal('modalEditCategory', 1);
				document.getElementById("txtCategoryName").value = category.categoryName;
				document.getElementById("txtOrigCategoryName").value = category.categoryName;
				document.getElementById("txtCategoryId").value = category.categoryId;
				document.getElementById("txtCategoryId2").value = category.categoryId;
				document.getElementById("selCategoryType").value = category.categoryType;
				if(category.isPerishable){
					document.getElementById("radEditPerishable").checked = true;
					disableEditRecyclableButtons();
				} else {
					document.getElementById("radEditNonPerishable").checked = true;
					enableEditRecyclableButtons();
				}
				if(category.isRecyclable){
					document.getElementById("radEditRecyclable").checked = true;
				}
				else{
					document.getElementById("radEditNotRecyclable").checked = true;
				}
			})
			.catch(function(err){
				console.log("error with parsing");
			});
		})
		.catch(function(err){
			console.log("Fetch Error: ", err)
		});
}

// request product info from server, then display on modal
function showProductDetails(productId){
	var myInit = {
			method:'GET',
			credentials: 'same-origin'
	};
	
	fetch('ProductManagement?action=getProductDetails&productId=' + productId, myInit) 
		.then(function(response){
			response.json().then(function(product){
				showModal('modalEditProduct', 3);
				document.getElementById("editProdCategory").value = product.fkCategoryId;
				document.getElementById("editProdBrand").value = product.brand;
				document.getElementById("editProdVariant").value = product.variant;
				document.getElementById("editProdSize").value = product.size;
				document.getElementById("editProdUnit").value = product.measurementUnit;
				document.getElementById("editProdDescription").value = product.description;
				document.getElementById("editProdSpecialHandling").value = product.specialHandling;
				document.getElementById("editProdSellPrice").value = product.sellPrice;
				document.getElementById("editProdSKU").value = product.SKU;
				document.getElementById("editProdOrigSKU").value = product.SKU;
				document.getElementById("editProdId").value = product.productId;
				document.getElementById("editProdId2").value = product.productId;
				
			})
			.catch(function(err){
				console.log("error with parsing");
			});
		})
		.catch(function(err){
			console.log("Fetch Error: ", err)
		});
}

// request batch info from server, then display on modal
function showBatchDetails(batchId){
	var myInit = {
			method:'GET',
			credentials: 'same-origin'
	};
	
	fetch('ProductManagement?action=getBatchDetails&batchId=' + batchId, myInit)
		.then(function(response){
			response.json().then(function(batch){
				showModal('modalEditBatch', 5);
				document.getElementById("editBatchId").innerHTML = batch.batchId;
				document.getElementById("editBatchId2").value = batch.batchId;
				document.getElementById("editBatchId3").value = batch.batchId;
				document.getElementById("editBatchEntryTimestamp").innerHTML = batch.entryTimestamp;
				document.getElementById("editBatchProduct").value = batch.fkProductId;
				document.getElementById("editBatchAmount").value = batch.amount;
				document.getElementById("editBatchRemainingAmount").innerHTML = batch.remainingAmount;
				document.getElementById("editBatchSupplier").value = batch.supplier;
				document.getElementById("editBatchComments").value = batch.comments;
			})
			.catch(function(err){
				console.log("error with parsing");
			});
		})
		.catch(function(err){
			console.log("Fetch Error: ", err)
		});
}

//request items info from server, then display on modal
function showItemEditForm(){
	var myInit = {
			method:'GET',
			credentials: 'same-origin'
	};
	
	fetch('ProductManagement?action=getItemDetails&itemIds=' + encodeURIComponent(JSON.stringify(selectedItems)), myInit)
		.then(function(response){
			response.json().then(function(items){
				showModal('modalEditItems', 6);
				var sameManufactureDates=true, sameExpirationDates=true, samePurchasePrices=true;
				for(var i=0; i<items.length - 1; i++){
					if(items[i].manufactureDate != items[i+1].manufactureDate) sameManufactureDates = false;
					if(items[i].expirationDate != items[i+1].expirationDate) sameExpirationDates = false;
					if(items[i].purchasePrice != items[i+1].purchasePrice) samePurchasePrices = false;
				}
				
				if(sameManufactureDates == true) 
					document.getElementById("editItemManufactureDate").value = items[0].manufactureDate;
				else
					document.getElementById("editItemManufactureDate").value = "";
				
				if(sameExpirationDates == true) 
					document.getElementById("editItemExpirationDate").value = items[0].expirationDate;
				else
					document.getElementById("editItemExpirationDate").value = "";
				
				if(samePurchasePrices == true) 
					document.getElementById("editItemPurchasePrice").value = items[0].purchasePrice;
				else
					document.getElementById("editItemPurchasePrice").value = "";
				
				if(items.length == 1){
					document.getElementById("editItemIds").innerHTML = items[0].itemId;
				}else if(items.length > 1){
					document.getElementById("editItemIds").innerHTML = items.length + " items selected";
				}
				
				document.getElementById("editItemIdsHidden").value = JSON.stringify(selectedItems);
				document.getElementById("editItemForm").style.visibility = "visible";
			})
			.catch(function(err){
				console.log("error with parsing");
			});
	})
	.catch(function(err){
		console.log("Fetch Error: ", err)
	});
}

// checks if the category name is unique, blocks submission of add/edit-category form if name is not unique 
function validateCategoryName(cNameInput){
	var categoryName = cNameInput.value;
	
	var myInit = {
			method:'GET',
			credentials: 'same-origin'
	};
	
	fetch('ProductManagement?action=validateCategoryName&categoryName=' + categoryName, myInit)
		.then(function(response){
			response.json().then(function(isUnique){
				if(isUnique == false){
					cNameInput.setCustomValidity("Category name already exists.");
				}else{
					cNameInput.setCustomValidity('');
				}
				
				if(cNameInput.id == 'txtCategoryName' && categoryName == document.getElementById('txtOrigCategoryName').value){
					cNameInput.setCustomValidity('');
				}
			})
			.catch(function(err){
				console.log("error with parsing");
			});
		})
		.catch(function(err){
			console.log("Fetch Error: ", err)
		});
}

// checks if the product SKU is unique, blocks submission of add/edit-product form if SKU is not unique 
function validateProductSKU(skuInput){
	skuInput.value = skuInput.value.toUpperCase();
	var sku = skuInput.value;
	
	var myInit = {
			method:'GET',
			credentials: 'same-origin'
	};
	
	fetch('ProductManagement?action=validateProductSKU&SKU=' + sku, myInit)
		.then(function(response){
			response.json().then(function(isUnique){
				if(isUnique == false){
					skuInput.setCustomValidity("Product SKU already exists.");
				}else{
					skuInput.setCustomValidity('');
				}
				
				if(skuInput.id == 'editProdSKU' && sku == document.getElementById('editProdOrigSKU').value){
					skuInput.setCustomValidity('');
				}
			})
			.catch(function(err){
				console.log("error with parsing");
			});
		})
		.catch(function(err){
			console.log("Fetch Error: ", err)
		});
}
