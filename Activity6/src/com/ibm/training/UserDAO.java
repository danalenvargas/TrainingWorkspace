package com.ibm.training;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO extends MasterDAO {
	boolean isSuccessful;
	int count;
	
	public UserDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<User> getStandardUsers(){
		conn = getConnection();
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
			System.out.println("SQL Error");
			e.printStackTrace();
		} finally {
            closeConnection();
        }
		return null;
	}
	
	public int addUser(String username, String password, String userType, boolean canCreate,
			boolean canUpdate, boolean canDelete) {
		conn = getConnection();
		int newUserId;
		try {
			pst = conn.prepareStatement("INSERT INTO tbl_user(username, password, usertype) VALUES(?,?,?)");
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, userType);
			pst.executeUpdate();
			
			pst = conn.prepareStatement("SELECT LAST_INSERT_ID() AS lastId from tbl_user");
			rs = pst.executeQuery();
			if(rs.next()) {
				newUserId = rs.getInt("lastId");
				
				pst = conn.prepareStatement("INSERT INTO tbl_access_level(fk_user_id, can_create, can_update, can_delete) VALUES(?,?,?,?)");
				pst.setInt(1, newUserId);
				pst.setBoolean(2, canCreate);
				pst.setBoolean(3, canUpdate);
				pst.setBoolean(4, canDelete);
				
				count = pst.executeUpdate();
				if(count > 0) {
					return newUserId;
				}
			}
		} catch (SQLException e) {
			System.out.println("SQL Error");
			e.printStackTrace();
		} finally {
            closeConnection();
        }
		return -1;
	}
	
	public boolean editUser(int userId, String username, String password, boolean canCreate,
			boolean canUpdate, boolean canDelete) {
		conn = getConnection();
		try {
			pst = conn.prepareStatement("UPDATE tbl_user SET username=?, password=? WHERE user_id=?");
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setInt(3, userId);
			pst.executeUpdate();
			
			pst = conn.prepareStatement("UPDATE tbl_access_level "
					+ "SET can_create=?, can_update=?, can_delete=? "
					+ "WHERE fk_user_id=?");
			pst.setBoolean(1, canCreate);
			pst.setBoolean(2, canUpdate);
			pst.setBoolean(3, canDelete);
			pst.setInt(4, userId);

			count = pst.executeUpdate();
			if(count > 0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("SQL Error");
			e.printStackTrace();
		} finally {
			closeConnection();
        }
		return false;
	}
	
	public boolean deleteUser(int userId) {
		conn = getConnection();
		
		try {
			pst = conn.prepareStatement("DELETE FROM tbl_user WHERE user_id=?;");
			pst.setInt(1, userId);
			
			count = pst.executeUpdate();
			if(count > 0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("SQL Error");
			e.printStackTrace();
		} finally {
			closeConnection();
        }
		return false;
	}
	
	public User getUser(int userId) {
		conn = getConnection();
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
			System.out.println("SQL Error");
			e.printStackTrace();
		} finally {
			closeConnection();
        }
		return user;
	}
}
