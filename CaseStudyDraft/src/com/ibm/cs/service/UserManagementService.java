package com.ibm.cs.service;

import java.util.ArrayList;

import com.ibm.cs.dao.UserDAO;
import com.ibm.cs.model.User;

public class UserManagementService {
	UserDAO userDao = new UserDAO();

	public UserManagementService() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<User> getStandardUsers(){
		userDao.openConnection();
		ArrayList<User> userList = userDao.getStandardUsers();
		userDao.closeConnection();
		return userList;
	}
	
	public User getUser(int userId) {
		userDao.openConnection();
		User user = userDao.getUser(userId);
		userDao.closeConnection();
		return user;
	}
	
	public void addUser(String username, String password, String userType, boolean canCreate,
			boolean canUpdate, boolean canDelete) {
		userDao.openConnection();
		userDao.addUser(username, password, userType, canCreate, canUpdate, canDelete);
		userDao.closeConnection();
	}
	
	public void editUser(int userId, String username, String password, boolean canCreate,
			boolean canUpdate, boolean canDelete) {
		userDao.openConnection();
		userDao.editUser(userId, username, password, canCreate, canUpdate, canDelete);
		userDao.closeConnection();
	}
	
	public void deleteUser(int userId) {
		userDao.openConnection();
		userDao.deleteUser(userId);
		userDao.closeConnection();
	}

}
