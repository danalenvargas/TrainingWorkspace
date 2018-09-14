var password, confirmPassword, changePassword, confirmChangePassword;

window.onload = function() {
	setActiveNavTab();

	password = document.getElementById("password")
	confirmPassword = document.getElementById("confirmPassword");
	password.onchange = validatePassword;
	confirmPassword.onkeyup = validatePassword;

	changePassword = document.getElementById("changePassword")
	confirmChangePassword = document.getElementById("changeConfirmPassword");
	changePassword.onchange = validateChangePassword;
	confirmChangePassword.onkeyup = validateChangePassword;
};

function setActiveNavTab(){
	document.getElementById("navUser").classList.add('active');
}

// Fetch a user's details from server, then display on the page
function showUserDetails(userId){
	var myInit = { 
			method:'GET',
			credentials: 'same-origin'
	};
	
	fetch('UserManagement?action=getUser&userId=' + userId, myInit) // fetch request to server
		.then(function(response){
			response.json().then(function(user){
				console.log(user);
				showModal('modalEditUser', 1)
				document.getElementById("txtUsername").value = user.username;
				document.getElementById("txtUserId").value = user.userId;
				document.getElementById("chkEditCreate").checked = user.canCreate;
				document.getElementById("chkEditUpdate").checked = user.canUpdate;
				document.getElementById("chkEditDelete").checked = user.canDelete;
				document.getElementById("txtUserId2").value = user.userId;
				document.getElementById("txtUserId3").value = user.userId;
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
		document.getElementById("chkCreate").checked = true;
		document.getElementById("chkUpdate").checked = true;
		document.getElementById("chkDelete").checked = true;
	}else{
		document.getElementById("chkCreate").readonly = false;
		document.getElementById("chkUpdate").readonly = false;
		document.getElementById("chkDelete").readonly = false;
	}
}

// work-around method to virtually make checkboxes read-only when userType is admin
function checkIfReadOnly(checkBox){
	var userType = document.getElementById("selUserType").value;
	if(userType == "admin"){
		checkBox.checked = true;
	}
}

function validatePassword(){
	if(password.value != confirmPassword.value) {
		confirmPassword.setCustomValidity("Passwords Don't Match");
	} else {
		confirmPassword.setCustomValidity('');
	}
}

function validateChangePassword(){
	if(changePassword.value != changeConfirmPassword.value) {
		changeConfirmPassword.setCustomValidity("Passwords Don't Match");
	} else {
		changeConfirmPassword.setCustomValidity('');
	}
}