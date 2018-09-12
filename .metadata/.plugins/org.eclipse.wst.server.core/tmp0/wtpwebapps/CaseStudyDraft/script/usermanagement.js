window.onload = function() {
	setActiveNavTab();
};

function setActiveNavTab(){
	document.getElementById("navUser").classList.add('active');
};

// Fetch a user's details from server, then display on the page
function showUserDetails(userId){
	var myInit = { 
			method:'GET',
			credentials: 'same-origin'
	};
	
	fetch('UserManagement?action=getUser&userId=' + userId, myInit) // Ajax request to server
		.then(function(response){
			response.json().then(function(user){
//				console.log(data);
				showModal('modalEditUser', 1)
				document.getElementById("txtUsername").value = user.username;
				document.getElementById("txtUserId").value = user.userId;
				document.getElementById("chkCreate").checked = user.canCreate;
				document.getElementById("chkUpdate").checked = user.canUpdate;
				document.getElementById("chkDelete").checked = user.canDelete;
				document.getElementById("txtUserId2").value = user.userId;
				
//				document.getElementById("editForm").style.visibility = "visible";
			})
			.catch(function(err){
				console.log("error with parsing");
			});
		})
		.catch(function(err){
			console.log("Fetch Error: ", err)
		});
}

function togglePrivileges(selUserType){
	if(selUserType.value == "admin"){
		document.getElementById("chkCreate").readOnly = true;
		document.getElementById("chkCreate").checked = true;
		document.getElementById("chkUpdate").readOnly = true;
		document.getElementById("chkUpdate").checked = true;
		document.getElementById("chkDelete").readOnly = true;
		document.getElementById("chkDelete").checked = true;
	}else{
		document.getElementById("chkCreate").readOnly = false;
		document.getElementById("chkUpdate").readOnly = false;
		document.getElementById("chkDelete").readOnly = false;
	}
}

//var modal;
//
//function showModal(modalName, index){
//	var modal = document.getElementById(modalName);
//	modal.style.display = "block";
//	
//	// When the user clicks on <span> (x), close the modal
//	var span = document.getElementsByClassName("close")[index];
//	span.onclick = function() {
//	    modal.style.display = "none";
//	}
//	
//	//When the user clicks anywhere outside of the modal, close it
//	window.onclick = function(event) {
//	    if (modal != null && event.target == modal) {
//	        modal.style.display = "none";
//	    }
//	} 
//}

