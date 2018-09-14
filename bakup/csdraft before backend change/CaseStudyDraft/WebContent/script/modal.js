var modal;

function showModal(modalName, index){
	var modal = document.getElementById(modalName);
	modal.style.display = "block";
	
	// When the user clicks on <span> (x), close the modal
	var span = document.getElementsByClassName("close")[index];
	span.onclick = function() {
	    modal.style.display = "none";
	}
	
	//When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	    if (modal != null && event.target == modal) {
	        modal.style.display = "none";
	    }
	} 
}