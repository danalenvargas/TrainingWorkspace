package com.ibm.cs.service;

import com.ibm.cs.dao.LoginDAO;
import com.ibm.cs.entity.User;

public class LoginService {
	
	private User user;

	public LoginService() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean isValidUser(String username, String password) {
		LoginDAO loginDao = new LoginDAO();
		
		user = loginDao.getUser(username, password);
		
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
