package com.ibm.cs.service;

import com.ibm.cs.dao.UserDAO;
import com.ibm.cs.model.User;

/**
 * Service class to handle logging in of users
 * 
 * @author Dan Alejandro A. Vargas
 * @see User
 */
public class LoginService {
	UserDAO dao = new UserDAO();
	private User user;

	public LoginService() {
	}

	/**
	 * Checks if login inputs are valid
	 * 
	 * @param username
	 *            String username of user
	 * @param password
	 *            String password of user
	 * @return True if username and password finds a match in the database,
	 *         otherwise returns false
	 * @see User
	 * @see UserDAO
	 */
	public boolean isValidUser(String username, String password) {
		dao.openConnection();
		user = dao.authenticateUser(username, password);
		dao.closeConnection();

		if (user != null)
			return true;
		else
			return false;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
