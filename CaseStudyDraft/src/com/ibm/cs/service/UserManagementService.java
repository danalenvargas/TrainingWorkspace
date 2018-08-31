package com.ibm.cs.service;

import java.util.ArrayList;

import com.ibm.cs.dao.UserManagementDAO;
import com.ibm.cs.entity.User;

public class UserManagementService {
	UserManagementDAO userDao = new UserManagementDAO();

	public UserManagementService() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<User> getStandardUsers(){
		return userDao.getStandardUsers();
	}
	
	public User getUser(int userId) {
		User user = userDao.getUser(userId);
		return user;
	}
	
	public void addUser(String username, String password, String userType, boolean canCreate,
			boolean canUpdate, boolean canDelete) {
		userDao.addUser(username, password, userType, canCreate, canUpdate, canDelete);
	}
	
	public void editUser(int userId, String username, String password, boolean canCreate,
			boolean canUpdate, boolean canDelete) {
		userDao.editUser(userId, username, password, canCreate, canUpdate, canDelete);
	}
	
	public void deleteUser(int userId) {
		userDao.deleteUser(userId);
	}

}
