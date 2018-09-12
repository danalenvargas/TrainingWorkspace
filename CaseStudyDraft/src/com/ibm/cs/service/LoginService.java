package com.ibm.cs.service;

import com.ibm.cs.dao.UserDAO;
import com.ibm.cs.model.User;

public class LoginService {
	UserDAO dao = new UserDAO();
	private User user;

	public LoginService() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean isValidUser(String username, String password) {
		dao.openConnection();
		user = dao.authenticateUser(username, password);
		dao.closeConnection();
		
		if(user != null) 
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
