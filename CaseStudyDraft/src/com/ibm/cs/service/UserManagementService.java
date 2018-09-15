package com.ibm.cs.service;

import java.util.ArrayList;

import com.ibm.cs.dao.UserDAO;
import com.ibm.cs.model.User;

/**
 * Service class for management of user accounts Handles CRUD operations for
 * Users
 * 
 * @author Dan Alejandro A. Vargas
 * @see User
 */
public class UserManagementService {
	UserDAO userDao;
	boolean isSuccessful;

	public UserManagementService() {
	}

	/**
	 * Gets list of standard users from User DAO. Does not include admin accounts
	 * 
	 * @return ArrayList of all standard users from database
	 * @see User
	 * @see UserDAO
	 */
	public ArrayList<User> getStandardUsers() {
		userDao = new UserDAO();
		ArrayList<User> userList = userDao.getStandardUsers();
		userDao.closeConnection();
		return userList;
	}

	/**
	 * Checks if a username is unique
	 * 
	 * @param username
	 *            String username that a user is trying to input
	 * @return true if username is unique, otherwise false
	 * @see User
	 * @see UserDAO
	 */
	public boolean validateUsername(String username) {
		userDao = new UserDAO();
		boolean isUnique = userDao.validateUsername(username);
		userDao.closeConnection();
		return isUnique;
	}

	// ========= User CRUD ============

	public User getUser(int userId) {
		userDao = new UserDAO();
		User user = userDao.getUser(userId);
		userDao.closeConnection();
		return user;
	}

	public void addUser(String username, String password, String userType, boolean canCreate, boolean canUpdate,
			boolean canDelete) {
		userDao = new UserDAO();
		userDao.addUser(username, password, userType, canCreate, canUpdate, canDelete);
		userDao.closeConnection();
	}

	public void editUser(int userId, String username, boolean canCreate, boolean canUpdate, boolean canDelete) {
		userDao = new UserDAO();
		userDao.editUser(userId, username, canCreate, canUpdate, canDelete);
		userDao.closeConnection();
	}

	public boolean editProfile(int userId, String username) {
		userDao = new UserDAO();
		isSuccessful = userDao.editProfile(userId, username);
		userDao.closeConnection();
		return isSuccessful;
	}

	public void deleteUser(int userId) {
		userDao = new UserDAO();
		userDao.deleteUser(userId);
		userDao.closeConnection();
	}

	public void changePassword(int userId, String password) {
		userDao = new UserDAO();
		userDao.changePassword(userId, password);
		userDao.closeConnection();
	}
}
