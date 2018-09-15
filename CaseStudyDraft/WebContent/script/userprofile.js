/**
 * Scripts for Profile page (userprofile.jsp)
 */
window.onload = function() {
	setActiveNavTab();

	changePassword = document.getElementById("changePassword")
	confirmChangePassword = document.getElementById("changeConfirmPassword");
	
	changePassword.onchange = validateChangePassword;
	confirmChangePassword.onkeyup = validateChangePassword;
}

// sets the active Navigation tab to Profile tab for styling purposes
function setActiveNavTab(){
	document.getElementById("navProfile").classList.add('active');
}

// checks if passwords match on change password modal
function validateChangePassword(){
	if(changePassword.value != changeConfirmPassword.value) {
		changeConfirmPassword.setCustomValidity("Passwords Don't Match");
	} else {
		changeConfirmPassword.setCustomValidity('');
	}
}

// checks if the username is unique, prevents editing of profile
// if new username is not unique
function validateUsername(unameInput){
	var username = unameInput.value;
	
	var myInit = {
			method:'GET',
			credentials: 'same-origin'
	};
	
	fetch('Profile?action=validateUsername&username=' + username, myInit)
		.then(function(response){
			response.json().then(function(isUnique){
				console.log(isUnique);
				if(isUnique == false){
					unameInput.setCustomValidity("Username already taken");
				}else{
					unameInput.setCustomValidity('');
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