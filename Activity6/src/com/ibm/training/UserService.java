package com.ibm.training;

import java.util.ArrayList;

public class UserService {
	UserDAO userDao = new UserDAO();
	boolean isSuccessful;

	public UserService() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<User> getStandardUsers(){
		return userDao.getStandardUsers();
	}
	
	public User getUser(int userId) {
		User user = userDao.getUser(userId);
		return user;
	}
	
	public int addUser(String username, String password, String userType, boolean canCreate, boolean canUpdate, boolean canDelete) {
		int newUserId = userDao.addUser(username, password, userType, canCreate, canUpdate, canDelete);
		return newUserId;
	}
	
	public boolean editUser(int userId, String username, String password, boolean canCreate, boolean canUpdate, boolean canDelete) {
		isSuccessful = userDao.editUser(userId, username, password, canCreate, canUpdate, canDelete);
		return isSuccessful;
	}
	
	public boolean deleteUser(int userId) {
		isSuccessful = userDao.deleteUser(userId);
		return isSuccessful;
	}
}
