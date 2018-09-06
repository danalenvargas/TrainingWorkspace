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
	
	fetch('ProductManagement?action=getCategoryDetails&categoryId=' + categoryId, myInit) // Ajax request to server
		.then(function(response){
			response.json().then(function(data){
				console.log(data);
				document.getElementById("txtCategoryName").value = data.name;
				document.getElementById("txtCategoryId").value = data.categoryId;
				document.getElementById("txtCategoryId2").value = data.categoryId;
				document.getElementById("selProductType").value = data.productType;
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

function generateSKU(){
	var SKU = "";
	e = document.getElementById("addProdCategory");
	SKU += e.options[e.selectedIndex].text.substring(0,2);
	SKU += document.getElementById("addProdBrand").value.substring(0,2);
	SKU += document.getElementById("addProdVariant").value.substring(0,2);
	SKU += "-";
	SKU += document.getElementById("addProdSize").value;
	SKU += document.getElementById("addProdUnit").value.substring(0,2);
	
	document.getElementById("addProdSKU").value = SKU.toUpperCase();
}


