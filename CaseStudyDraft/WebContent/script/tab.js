function openTab(evt, tabName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    
    
    for (i = 0; i < tabcontent.length; i++) {	// hide contents of all tabs
        tabcontent[i].style.display = "none";
    }
    document.getElementById(tabName).style.display = "block";	// show content of selected tab
    
    
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    evt.currentTarget.className += " active";	// set class of the clicked tab as active
}