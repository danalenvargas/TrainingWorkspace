package com.ibm.cs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ibm.cs.model.Category;

public class CategoryDAO extends MasterDAO {
//	private PreparedStatement pst = null;
//	private ResultSet rs = null;
//	private Connection conn;
	int count;

	public CategoryDAO() {
		// TODO Auto-generated constructor stub
	}
	
	// constructor used when ProductDAO needs to use a CategoryDAO, so that they can share a single connection
	public CategoryDAO(Connection conn) { 
		this.conn = conn;
	}
	
	public boolean addCategory(String name, String productType, boolean isPerishable, Boolean isRecyclable) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement("INSERT INTO tbl_category(name, product_type, is_perishable, is_recyclable) VALUES(?,?,?,?)");
			pst.setString(1, name);
			pst.setString(2, productType);
			pst.setBoolean(3, isPerishable);
			if (isRecyclable != null) pst.setBoolean(4, isRecyclable);
			else pst.setNull(4, java.sql.Types.BOOLEAN);
			
			count = pst.executeUpdate();
			if(count>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            closeResources(rs, pst);
        }
		return false;
	}
	
	public boolean editCategory(int categoryId, String name, String productType, boolean isPerishable, Boolean isRecyclable) {
		PreparedStatement pst = null;
		
		try{
			pst = conn.prepareStatement("UPDATE tbl_category SET name=?, product_type=?, is_perishable=?, is_recyclable=? "
					+ "WHERE category_id=?");
			pst.setString(1, name);
			pst.setString(2, productType);
			pst.setBoolean(3, isPerishable);
			if (isRecyclable != null) pst.setBoolean(4, isRecyclable);
			else pst.setNull(4, java.sql.Types.BOOLEAN);
			pst.setInt(5, categoryId);
			
			count = pst.executeUpdate();
			if(count>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            closeResources(pst);
        }
		return false;
	}
	
	public boolean deleteCategory(int categoryId) {
		PreparedStatement pst = null;
		
		try {
			pst = conn.prepareStatement("UPDATE tbl_category SET is_active=0 WHERE category_id=?");
			pst.setInt(1, categoryId);
			
			count = pst.executeUpdate();
			if(count>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            closeResources(pst);
        }
		return false;
	}
	
	public ArrayList<Category> getCategoryList(){
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		ArrayList<Category> categoryList = new ArrayList<>();
		int categoryId;
		String name, productType;
		boolean isPerishable; Boolean isRecyclable; // (isPerishable does not accept null, isRecyclable may be null)
		
		try {
			pst = conn.prepareStatement("SELECT * FROM tbl_category WHERE is_active = 1");
			rs = pst.executeQuery();
			
			while(rs.next()) {
				categoryId = rs.getInt("category_id");
				name = rs.getString("name");
				productType = rs.getString("product_type");
				isPerishable = rs.getBoolean("is_perishable");
				isRecyclable = null;
				if(!isPerishable) {
					isRecyclable = rs.getBoolean("is_recyclable");
				}
				categoryList.add(new Category(categoryId, name, productType, isPerishable, isRecyclable));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            closeResources(rs, pst);
        }
		return categoryList;
	}
	
	public Category getCategory(int categoryId) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String name, productType;
		boolean isPerishable; Boolean isRecyclable;
		Category category = null;
		
		try {
			pst = conn.prepareStatement("SELECT * FROM tbl_category WHERE category_id=?;");
			pst.setInt(1, categoryId);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				name = rs.getString("name");
				productType = rs.getString("product_type");
				isPerishable = rs.getBoolean("is_perishable");
				isRecyclable = null;
				if(!isPerishable) {
					isRecyclable = rs.getBoolean("is_recyclable");
				}
				
				category = new Category(categoryId, name, productType, isPerishable, isRecyclable);
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            closeResources(rs, pst);
        }
		return category;
	}
}