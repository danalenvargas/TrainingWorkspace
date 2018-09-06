package com.ibm.training;

import java.util.ArrayList;


public class Main {

	public Main() {
	}

	public static void main(String[] args) {
		Main prog = new Main();
		prog.execute();
	}
	
	public void execute() {
		ArrayList<User> userList;
		User user;
		UserService userService = new UserService();
		int newUserId;
		
		// SELECTING LIST OF USERS
		userList = userService.getStandardUsers();
		System.out.println("Printing Users List");
		printUserArray(userList);
		
		// SELECTING PARTICULAR USER
		user = userService.getUser(10);
		System.out.println("\nSELECTED the user with id 10:");
		System.out.println(user);
		
		// EDITING USER
		userService.editUser(10, "qwerty023", "123", false, false, false);
		user = userService.getUser(10);
		System.out.println("\nEDITED the user with id 10: ");
		System.out.println(user);
		

		// ADDING USER
		newUserId = userService.addUser("testUser", "123", "user", true, true, false); // addUser returns the ID of last inserted user
		System.out.println("\nADDED a new user, named 'testUser', here's the new list: ");
		printUserArray(userService.getStandardUsers());

		// DELETING USER
		userService.deleteUser(newUserId);
		System.out.println("\nDELETED the previously added user, named 'testUser', here's the new list: ");
		printUserArray(userService.getStandardUsers());
		
		userService.editUser(10, "qwerty", "123", true, true, true); // returning previous values to the edited user;
	}
	
	// utility method to print ArrayList of Users
	private void printUserArray(ArrayList<User> userList) {
		System.out.println("-------------------------");
		for(User user:userList) {
			System.out.println(user);
		}
		System.out.println("-------------------------");
	}

}
