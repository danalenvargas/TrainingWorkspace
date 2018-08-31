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