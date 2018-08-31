package com.ibm.cs.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable{
	private int userId;

	private String username, password, userType;
	private boolean canCreate, canUpdate, canDelete;

	public User() {
	}

	public User(int userId, String username, String password, String userType, boolean canCreate,
			boolean canUpdate, boolean canDelete) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.canCreate = canCreate;
		this.canUpdate = canUpdate;
		this.canDelete = canDelete;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public boolean isCanCreate() {
		return canCreate;
	}

	public void setCanCreate(boolean canCreate) {
		this.canCreate = canCreate;
	}

	public boolean isCanUpdate() {
		return canUpdate;
	}

	public void setCanUpdate(boolean canUpdate) {
		this.canUpdate = canUpdate;
	}

	public boolean isCanDelete() {
		return canDelete;
	}

	public void setCanDelete(boolean canDelete) {
		this.canDelete = canDelete;
	}
}
