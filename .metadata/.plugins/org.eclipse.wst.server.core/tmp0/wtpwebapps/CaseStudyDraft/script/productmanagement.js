/**
 * 
 */
var selectedItems = [];

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

//Fetch a category's details from server, then display on the page
function showCategoryDetails(categoryId){
	var myInit = {
			method:'GET',
			credentials: 'same-origin'
	};
	
	fetch('ProductManagement?action=getCategoryDetails&categoryId=' + categoryId, myInit) // request category info from server
		.then(function(response){
			response.json().then(function(category){
//				console.log(category);
				showModal('modalEditCategory', 1);
				document.getElementById("txtCategoryName").value = category.categoryName;
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
				
//				document.getElementById("editCategoryForm").style.visibility = "visible";
			})
			.catch(function(err){
				console.log("error with parsing");
			});
		})
		.catch(function(err){
			console.log("Fetch Error: ", err)
		});
}

function showProductDetails(productId){
	var myInit = {
			method:'GET',
			credentials: 'same-origin'
	};
	
	fetch('ProductManagement?action=getProductDetails&productId=' + productId, myInit) // request category info from server
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
				document.getElementById("editProdId").value = product.productId;
				document.getElementById("editProdId2").value = product.productId;
				
//				document.getElementById("editProductForm").style.visibility = "visible";
			})
			.catch(function(err){
				console.log("error with parsing");
			});
		})
		.catch(function(err){
			console.log("Fetch Error: ", err)
		});
}

function showBatchDetails(batchId){
	var myInit = {
			method:'GET',
			credentials: 'same-origin'
	};
	
	fetch('ProductManagement?action=getBatchDetails&batchId=' + batchId, myInit) // request batch info from server
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
//				document.getElementById("editBatchManufactureDate").value = batch.manufactureDate;
//				document.getElementById("editBatchExpirationDate").value = batch.ExpirationDate;
//				document.getElementById("editBatchPurchasePrice").value = batch.PurchasePrice;
				
//				document.getElementById("editBatchForm").style.visibility = "visible";
			})
			.catch(function(err){
				console.log("error with parsing");
			});
		})
		.catch(function(err){
			console.log("Fetch Error: ", err)
		});
}

function showItemEditForm(){
	var myInit = {
			method:'GET',
			credentials: 'same-origin'
	};
	
	fetch('ProductManagement?action=getItemDetails&itemIds=' + encodeURIComponent(JSON.stringify(selectedItems)), myInit) // request batch info from server
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

function setIdsToDelete(){
	document.getElementById("deleteItemIds").value = JSON.stringify(selectedItems);
}

function generateSKU(location){
	var SKU = "";
	if(location == 'onAdd'){
		e = document.getElementById("addProdCategory");
		SKU += e.options[e.selectedIndex].text.substring(0,2);
		SKU += document.getElementById("addProdBrand").value.substring(0,2);
		SKU += document.getElementById("addProdVariant").value.substring(0,2);
		SKU += "-";
		SKU += document.getElementById("addProdSize").value;
		SKU += document.getElementById("addProdUnit").value.substring(0,2);
		
		document.getElementById("addProdSKU").value = SKU.toUpperCase();
	} else if (location == 'onEdit'){
		e = document.getElementById("editProdCategory");
		SKU += e.options[e.selectedIndex].text.substring(0,2);
		SKU += document.getElementById("editProdBrand").value.substring(0,2);
		SKU += document.getElementById("editProdVariant").value.substring(0,2);
		SKU += "-";
		SKU += document.getElementById("editProdSize").value;
		SKU += document.getElementById("editProdUnit").value.substring(0,2);
		
		document.getElementById("editProdSKU").value = SKU.toUpperCase();
	}
}

function toggleSelected(checkbox){
	if(checkbox.checked == true){
		selectedItems.push(checkbox.value);
	}else{
		selectedItems.splice(selectedItems.indexOf(checkbox.value));
	}
}

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

window.onload = function() {
	setActiveNavTab();
	setActiveInventoryTab();
	console.log(JSON.parse(test));
};

function setActiveNavTab(){
	document.getElementById("navInventory").classList.add('active');
};

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
