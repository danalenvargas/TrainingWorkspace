package com.ibm.cs.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ibm.cs.model.User;

/**
 * Data Access Object for User
 * 
 * @author Dan Alejandro A. Vargas
 * @see User
 */
public class UserDAO extends MasterDAO {

	public UserDAO() throws Exception {
		super();
	}

	/**
	 * Gets list of standard users from User DAO. Does not include admin accounts
	 * 
	 * @return ArrayList of all standard users from database
	 * @see User
	 */
	public ArrayList<User> getStandardUsers() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<User> userList = new ArrayList<>();
		int userId;
		String username, userType;
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
				userType = rs.getString("usertype");
				canCreate = rs.getBoolean("can_create");
				canUpdate = rs.getBoolean("can_update");
				canDelete = rs.getBoolean("can_delete");

				userList.add(new User(userId, username, userType, canCreate, canUpdate, canDelete));
			}

			return userList;
		} catch (SQLException e) {
			throw e;
		} finally {
			closeResources(rs, pst);
		}
	}

	/**
	 * Checks if a username is unique
	 * 
	 * @param username
	 *            String username that a user is trying to input
	 * @return true if username is unique, otherwise false
	 * @see User
	 */
	public boolean validateUsername(String username) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn.prepareStatement("SELECT * FROM tbl_user WHERE username=? LIMIT 1");
			pst.setString(1, username);

			rs = pst.executeQuery();
			if (rs.next()) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			closeResources(rs, pst);
		}
	}

	/**
	 * Authenticates a user and creates a User object at the same time
	 * 
	 * @param username
	 *            String user's username
	 * @param password
	 *            String user's password
	 * @return User object created from entered login information
	 * @see User
	 */
	public User authenticateUser(String username, String password) throws SQLException {
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
			pst.setString(2, generateHash(password));

			rs = pst.executeQuery();

			if (rs.next()) {
				userId = rs.getInt("user_id");
				userType = rs.getString("usertype");
				canCreate = rs.getBoolean("can_create");
				canUpdate = rs.getBoolean("can_update");
				canDelete = rs.getBoolean("can_delete");

				return new User(userId, username, userType, canCreate, canUpdate, canDelete);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			closeResources(rs, pst);
		}
		return null;
	}

	/**
	 * Utility function to hash password for secure storage
	 * 
	 * @param input
	 *            String password to be hashed
	 * @return String representation of hashed password
	 */
	private static String generateHash(String input) throws SQLException {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] hashedBytes = md5.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			System.out.println("error hashing");
		}
		return hash.toString();
	}

	// v======= USER CRUD ===========v

	public void addUser(String username, String password, String userType, boolean canCreate, boolean canUpdate,
			boolean canDelete) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int newUserId;
		try {
			pst = conn.prepareStatement("INSERT INTO tbl_user(username, password, usertype) VALUES(?,?,?)");
			pst.setString(1, username);
			pst.setString(2, generateHash(password));
			pst.setString(3, userType);
			pst.executeUpdate();

			closeResources(pst);
			pst = conn.prepareStatement("SELECT LAST_INSERT_ID() AS lastId from tbl_user");
			rs = pst.executeQuery();
			if (rs.next()) {
				newUserId = rs.getInt("lastId");

				closeResources(pst);
				pst = conn.prepareStatement(
						"INSERT INTO tbl_access_level(fk_user_id, can_create, can_update, can_delete) VALUES(?,?,?,?)");
				pst.setInt(1, newUserId);
				pst.setBoolean(2, canCreate);
				pst.setBoolean(3, canUpdate);
				pst.setBoolean(4, canDelete);
				pst.executeUpdate();
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			closeResources(rs, pst);
		}
	}

	public void editUser(int userId, String username, boolean canCreate, boolean canUpdate, boolean canDelete) throws SQLException {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("UPDATE tbl_user SET username=? WHERE user_id=?");
			pst.setString(1, username);
			pst.setInt(2, userId);
			pst.executeUpdate();

			closeResources(pst);
			pst = conn.prepareStatement("UPDATE tbl_access_level " + "SET can_create=?, can_update=?, can_delete=? "
					+ "WHERE fk_user_id=?");
			pst.setBoolean(1, canCreate);
			pst.setBoolean(2, canUpdate);
			pst.setBoolean(3, canDelete);
			pst.setInt(4, userId);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			closeResources(pst);
		}
	}

	public boolean editProfile(int userId, String username) throws SQLException {
		PreparedStatement pst = null;
		int resultCount;

		try {
			pst = conn.prepareStatement("UPDATE tbl_user SET username=? WHERE user_id=?");
			pst.setString(1, username);
			pst.setInt(2, userId);

			resultCount = pst.executeUpdate();
			if (resultCount > 0) {
				return true;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			closeResources(pst);
		}
		return false;
	}

	public void deleteUser(int userId) throws SQLException {
		PreparedStatement pst = null;

		try {
			pst = conn.prepareStatement("DELETE FROM tbl_user WHERE user_id=?;");
			pst.setInt(1, userId);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			closeResources(pst);
		}
	}

	public User getUser(int userId) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		String username, userType;
		boolean canCreate, canUpdate, canDelete;
		User user = null;

		try {
			pst = conn.prepareStatement("SELECT * FROM tbl_user "
					+ "INNER JOIN tbl_access_level ON tbl_user.user_id = tbl_access_level.fk_user_id "
					+ "WHERE user_id=?;");
			pst.setInt(1, userId);

			rs = pst.executeQuery();

			if (rs.next()) {
				username = rs.getString("username");
				userType = rs.getString("usertype");
				canCreate = rs.getBoolean("can_create");
				canUpdate = rs.getBoolean("can_update");
				canDelete = rs.getBoolean("can_delete");

				user = new User(userId, username, userType, canCreate, canUpdate, canDelete);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			closeResources(rs, pst);
		}
		return user;
	}

	public void changePassword(int userId, String password) throws SQLException {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("UPDATE tbl_user SET password=? WHERE user_id=?");
			pst.setString(1, generateHash(password));
			pst.setInt(2, userId);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw e;
		} finally {
			closeResources(pst);
		}
	}
}
