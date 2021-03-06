package com.ibm.cs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.ibm.cs.model.Category;
/**
 * Data Access Object for Category
 * 
 * @author Dan Alejandro A. Vargas
 * @see Category
 */
public class CategoryDAO extends MasterDAO {
	int count;

	public CategoryDAO() throws Exception {
		super();
	}
	
	/**
	 * Gets hash map of all categories from the database
	 * 
	 * @return HashMap(key = category id, value = Category object) containing all
	 *         categories in the database
	 * @see Category
	 */
	public HashMap<Integer, Category> getCategoryMap() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		HashMap<Integer, Category> categoryMap = new HashMap<>();
		int categoryId;
		String categoryName, categoryType;
		boolean isPerishable; Boolean isRecyclable; // (isPerishable does not accept null, isRecyclable may be null)
		
		try {
			pst = conn.prepareStatement("SELECT * FROM tbl_category");
			rs = pst.executeQuery();
			
			while(rs.next()) {
				categoryId = rs.getInt("category_id");
				categoryName = rs.getString("category_name");
				categoryType = rs.getString("category_type");
				isPerishable = rs.getBoolean("is_perishable");
				isRecyclable = null;
				if(!isPerishable) {
					isRecyclable = rs.getBoolean("is_recyclable");
				}
				
				categoryMap.put(categoryId, new Category(categoryId, categoryName, categoryType, isPerishable, isRecyclable));
			}
		} catch (SQLException e) {
			throw e;
		} finally {
            closeResources(rs, pst);
        }
		return categoryMap;
	}
	
	/**
	 * Checks if the the category name is unique
	 * 
	 * @param categoryName
	 *            String name of a category
	 * @return true if the category's name is unique
	 */
	public boolean validateCategoryName(String categoryName) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement("SELECT * FROM tbl_category WHERE category_name=? LIMIT 1");
			pst.setString(1, categoryName);
			
			rs = pst.executeQuery();
			if(rs.next()) {
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
	
	// v======= CATEGORY CRUD ===========v
	
	public boolean addCategory(String categoryName, String categoryType, boolean isPerishable, Boolean isRecyclable) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement("INSERT INTO tbl_category(category_name, category_type, is_perishable, is_recyclable) VALUES(?,?,?,?)");
			pst.setString(1, categoryName);
			pst.setString(2, categoryType);
			pst.setBoolean(3, isPerishable);
			if (isRecyclable != null) pst.setBoolean(4, isRecyclable);
			else pst.setNull(4, java.sql.Types.BOOLEAN);
			
			count = pst.executeUpdate();
			if(count>0) {
				return true;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
            closeResources(rs, pst);
        }
		return false;
	}
	
	public boolean editCategory(int categoryId, String categoryName, String categoryType, boolean isPerishable, Boolean isRecyclable) throws SQLException {
		PreparedStatement pst = null;
		
		try{
			pst = conn.prepareStatement("UPDATE tbl_category SET category_name=?, category_type=?, is_perishable=?, is_recyclable=? "
					+ "WHERE category_id=?");
			pst.setString(1, categoryName);
			pst.setString(2, categoryType);
			pst.setBoolean(3, isPerishable);
			if (isRecyclable != null) pst.setBoolean(4, isRecyclable);
			else pst.setNull(4, java.sql.Types.BOOLEAN);
			pst.setInt(5, categoryId);
			
			count = pst.executeUpdate();
			if(count>0) {
				return true;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
            closeResources(pst);
        }
		return false;
	}
	
	public boolean deleteCategory(int categoryId) throws SQLException {
		PreparedStatement pst = null;
		
		try {
			pst = conn.prepareStatement("DELETE FROM tbl_category WHERE category_id=?");
			pst.setInt(1, categoryId);
			
			count = pst.executeUpdate();
			if(count>0) {
				return true;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
            closeResources(pst);
        }
		return false;
	}
	
	public Category getCategory(int categoryId) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String categoryName, categoryType;
		boolean isPerishable; Boolean isRecyclable;
		Category category = null;
		
		try {
			pst = conn.prepareStatement("SELECT * FROM tbl_category WHERE category_id=?;");
			pst.setInt(1, categoryId);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				categoryName = rs.getString("category_name");
				categoryType = rs.getString("category_type");
				isPerishable = rs.getBoolean("is_perishable");
				isRecyclable = null;
				if(!isPerishable) {
					isRecyclable = rs.getBoolean("is_recyclable");
				}
				
				category = new Category(categoryId, categoryName, categoryType, isPerishable, isRecyclable);
            }
		} catch (SQLException e) {
			throw e;
		} finally {
            closeResources(rs, pst);
        }
		return category;
	}
}
