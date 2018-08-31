/**
 * 
 */
// Fetch a user's details from server, then display on the page
function showUserDetails(userId){
	var myInit = { 
			method:'GET',
			credentials: 'same-origin'
	};
	
	fetch('UserManagement?action=getUser&userId=' + userId, myInit) // Ajax request to server
		.then(function(response){
			response.json().then(function(data){
				console.log(data);
				document.getElementById("txtUsername").value = data.username;
				document.getElementById("txtPassword").value = data.password;
				document.getElementById("txtUserId").value = data.userId;
				document.getElementById("chkCreate").checked = data.canCreate;
				document.getElementById("chkUpdate").checked = data.canUpdate;
				document.getElementById("chkDelete").checked = data.canDelete;
				document.getElementById("txtUserId2").value = data.userId;
				
				document.getElementById("editForm").style.visibility = "visible";
			})
			.catch(function(err){
				console.log("error with parsing");
			});
		})
		.catch(function(err){
			console.log("Fetch Error: ", err)
		});
}