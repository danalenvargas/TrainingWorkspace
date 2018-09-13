package com.ibm.cs.service;

import java.util.ArrayList;

import com.ibm.cs.dao.UserDAO;
import com.ibm.cs.model.User;

public class UserManagementService {
	UserDAO userDao;

	public UserManagementService() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<User> getStandardUsers(){
		userDao = new UserDAO();
		ArrayList<User> userList = userDao.getStandardUsers();
		userDao.closeConnection();
		return userList;
	}
	
	public User getUser(int userId) {
		userDao = new UserDAO();
		User user = userDao.getUser(userId);
		userDao.closeConnection();
		return user;
	}
	
	public void addUser(String username, String password, String userType, boolean canCreate,
			boolean canUpdate, boolean canDelete) {
		userDao = new UserDAO();
		userDao.addUser(username, password, userType, canCreate, canUpdate, canDelete);
		userDao.closeConnection();
	}
	
	public void editUser(int userId, String username, boolean canCreate,
			boolean canUpdate, boolean canDelete) {
		userDao = new UserDAO();
		userDao.editUser(userId, username, canCreate, canUpdate, canDelete);
		userDao.closeConnection();
	}
	
	public void deleteUser(int userId) {
		userDao = new UserDAO();
		userDao.deleteUser(userId);
		userDao.closeConnection();
	}

}
