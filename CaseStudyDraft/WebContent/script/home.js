/** 
 * Script for functionalities in the home page (home.jsp)
 */
window.onload = function() {
	setActiveNavTab();
};

// sets the active navigation tab to 'Home' for styling purposes
function setActiveNavTab(){
	document.getElementById("navHome").classList.add('active');
};