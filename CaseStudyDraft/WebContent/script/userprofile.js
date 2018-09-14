/**
 * 
 */
window.onload = function() {
	setActiveNavTab();

	changePassword = document.getElementById("changePassword")
	confirmChangePassword = document.getElementById("changeConfirmPassword");
	changePassword.onchange = validateChangePassword;
	confirmChangePassword.onkeyup = validateChangePassword;
}

function setActiveNavTab(){
	document.getElementById("navProfile").classList.add('active');
}

function validateChangePassword(){
	if(changePassword.value != changeConfirmPassword.value) {
		changeConfirmPassword.setCustomValidity("Passwords Don't Match");
	} else {
		changeConfirmPassword.setCustomValidity('');
	}
}