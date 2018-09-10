/**
 * 
 */
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
			response.json().then(function(data){
//				console.log(data);
				document.getElementById("txtCategoryName").value = data.name;
				document.getElementById("txtCategoryId").value = data.categoryId;
				document.getElementById("txtCategoryId2").value = data.categoryId;
				document.getElementById("selCategoryType").value = data.productType;
				if(data.isPerishable){
					document.getElementById("radEditPerishable").checked = true;
					disableEditRecyclableButtons();
				} else {
					document.getElementById("radEditNonPerishable").checked = true;
					enableEditRecyclableButtons();
				}
				if(data.isRecyclable){
					document.getElementById("radEditRecyclable").checked = true;
				}
				else{
					document.getElementById("radEditNotRecyclable").checked = true;
				}
				
				document.getElementById("editCategoryForm").style.visibility = "visible";
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
				
				document.getElementById("editProductForm").style.visibility = "visible";
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
				document.getElementById("editBatchId").innerHTML = batch.batchId;
				document.getElementById("editBatchId2").value = batch.batchId;
				document.getElementById("editBatchId3").value = batch.batchId;
				document.getElementById("editBatchEntryTimestamp").innerHTML = batch.entryTimestamp;
				document.getElementById("editBatchProduct").value = batch.fkProductId;
				document.getElementById("editBatchAmount").value = batch.amount;
				document.getElementById("editBatchSupplier").value = batch.supplier;
				document.getElementById("editBatchComments").value = batch.comments;
//				document.getElementById("editBatchManufactureDate").value = batch.manufactureDate;
//				document.getElementById("editBatchExpirationDate").value = batch.ExpirationDate;
//				document.getElementById("editBatchPurchasePrice").value = batch.PurchasePrice;
				
				document.getElementById("editBatchForm").style.visibility = "visible";
			})
			.catch(function(err){
				console.log("error with parsing");
			});
		})
		.catch(function(err){
			console.log("Fetch Error: ", err)
		});
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


