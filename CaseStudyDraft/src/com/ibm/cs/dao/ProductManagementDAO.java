package com.ibm.cs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductManagementDAO extends MasterDAO {
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private Connection conn = getConnection();

	public ProductManagementDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean addCategory(String name, String productType, boolean isPerishable, Boolean isRecyclable) {
			try {
				pst = conn.prepareStatement("INSERT INTO tbl_category(name, product_type, is_perishable, is_recyclable) VALUES(?,?,?,?)");
				pst.setString(1, name);
				pst.setString(2, productType);
				pst.setBoolean(3, isPerishable);
				if (isRecyclable != null) pst.setBoolean(4, isRecyclable);
				else pst.setNull(4, java.sql.Types.BOOLEAN);
				
				
				int count = pst.executeUpdate();
				if(count>0) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
	            if (rs != null) {
	                try {rs.close();} catch (SQLException e) { e.printStackTrace();}
	            }
	            if (pst != null) {
	                try {pst.close();} catch (SQLException e) {e.printStackTrace();}
	            }
	            if (conn != null) {
	            	try {conn.close();} catch (SQLException e) {e.printStackTrace();}
	            }
	        }
		return false;
	}
}