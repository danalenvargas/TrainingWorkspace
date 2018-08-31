package com.ibm.cs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ibm.cs.entity.User;

public class LoginDAO extends MasterDAO{

	public LoginDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public User getUser(String username, String password) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		
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
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
            	try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
		
		return null;
	}
	
}
