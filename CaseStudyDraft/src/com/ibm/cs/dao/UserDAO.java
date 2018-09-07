package com.ibm.cs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ibm.cs.model.User;

public class UserDAO extends MasterDAO {

	public UserDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<User> getStandardUsers(){
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<User> userList = new ArrayList<>();
		int userId;
		String username, password, userType;
		boolean canCreate, canUpdate, canDelete;
		
		try {
			pst = conn.prepareStatement("SELECT * FROM tbl_user "
					+ "INNER JOIN tbl_access_level ON tbl_user.user_id = tbl_access_level.fk_user_id "
					+ "WHERE usertype=?;");
			pst.setString(1, "user");
			
			rs = pst.executeQuery();
			
			while (rs.next()) {
				userId = rs.getInt("user_id");
				username = rs.getString("username");
				password = rs.getString("password");
				userType = rs.getString("usertype");
				canCreate = rs.getBoolean("can_create");
				canUpdate = rs.getBoolean("can_update");
				canDelete = rs.getBoolean("can_delete");
				
				userList.add(new User(userId, username, password, userType, canCreate, canUpdate, canDelete));
            }
			
			return userList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            closeResources(rs, pst);
        }
		return null;
	}
	
	public void addUser(String username, String password, String userType, boolean canCreate,
			boolean canUpdate, boolean canDelete) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int newUserId;
		try {
			pst = conn.prepareStatement("INSERT INTO tbl_user(username, password, usertype) VALUES(?,?,?)");
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, userType);
			pst.executeUpdate();

			closeResources(pst);
			pst = conn.prepareStatement("SELECT LAST_INSERT_ID() AS lastId from tbl_user");
			rs = pst.executeQuery();
			if(rs.next()) {
				newUserId = rs.getInt("lastId");

				closeResources(pst);
				pst = conn.prepareStatement("INSERT INTO tbl_access_level(fk_user_id, can_create, can_update, can_delete) VALUES(?,?,?,?)");
				pst.setInt(1, newUserId);
				pst.setBoolean(2, canCreate);
				pst.setBoolean(3, canUpdate);
				pst.setBoolean(4, canDelete);
				pst.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources(rs, pst);
        }
	}
	
	public void editUser(int userId, String username, String password, boolean canCreate,
			boolean canUpdate, boolean canDelete) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("UPDATE tbl_user SET username=?, password=? WHERE user_id=?");
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setInt(3, userId);
			pst.executeUpdate();

			closeResources(pst);
			pst = conn.prepareStatement("UPDATE tbl_access_level "
					+ "SET can_create=?, can_update=?, can_delete=? "
					+ "WHERE fk_user_id=?");
			pst.setBoolean(1, canCreate);
			pst.setBoolean(2, canUpdate);
			pst.setBoolean(3, canDelete);
			pst.setInt(4, userId);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources(pst);
        }
	}
	
	public void deleteUser(int userId) {
		PreparedStatement pst = null;
		
		try {
			pst = conn.prepareStatement("DELETE FROM tbl_user WHERE user_id=?;");
			pst.setInt(1, userId);
			
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources(pst);
        }
	}
	
	public User getUser(int userId) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		String username, password, userType;
		boolean canCreate, canUpdate, canDelete;
		User user = null;
		
		try {
			pst = conn.prepareStatement("SELECT * FROM tbl_user "
					+ "INNER JOIN tbl_access_level ON tbl_user.user_id = tbl_access_level.fk_user_id "
					+ "WHERE user_id=?;");
			pst.setInt(1, userId);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				username = rs.getString("username");
				password = rs.getString("password");
				userType = rs.getString("usertype");
				canCreate = rs.getBoolean("can_create");
				canUpdate = rs.getBoolean("can_update");
				canDelete = rs.getBoolean("can_delete");
				
				user = new User(userId, username, password, userType, canCreate, canUpdate, canDelete);
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources(rs, pst);
        }
		return user;
	}
	
	// get a User using username and password, used for login validation
	public User getUser(String username, String password) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		int userId;
		String userType;
		boolean canCreate, canUpdate, canDelete;
		
		try {
			pst = conn.prepareStatement("SELECT * FROM tbl_user "
					+ "INNER JOIN tbl_access_level ON tbl_user.user_id = tbl_access_level.fk_user_id "
					+ "WHERE username=? AND password=?");
			pst.setString(1, username);
			pst.setString(2, password);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				userId = rs.getInt("user_id");
				userType = rs.getString("usertype");
				canCreate = rs.getBoolean("can_create");
				canUpdate = rs.getBoolean("can_update");
				canDelete = rs.getBoolean("can_delete");
				
				return new User(userId, username, password, userType, canCreate, canUpdate, canDelete);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            closeResources(rs, pst);
        }
		return null;
	}
}
