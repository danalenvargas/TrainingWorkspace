package com.ibm.cs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ibm.cs.entity.Category;
import com.ibm.cs.entity.Product;

public class ProductManagementDAO extends MasterDAO {
//	private PreparedStatement pst = null;
//	private ResultSet rs = null;
//	private Connection conn;
	int count;

	public ProductManagementDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean addCategory(String name, String productType, boolean isPerishable, Boolean isRecyclable) {
		conn = getConnection();
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
	
	public boolean editCategory(int categoryId, String name, String productType, boolean isPerishable, Boolean isRecyclable) {
		conn = getConnection();
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
            closeConnection();
        }
		return false;
	}
	
	public boolean deleteCategory(int categoryId) {
		conn = getConnection();
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
	
	public ArrayList<Category> getCategoryList(){
		conn = getConnection();
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
		return categoryList;
	}
	
	public Category getCategory(int categoryId) {
		conn = getConnection();
		
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
		return category;
	}
	
	public ArrayList<Product> getProductList(){
		Connection conn = getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		ArrayList<Product> productList = new ArrayList<>();
		int productId, fkCategoryId;
		String SKU, brand, variant, size, measurementUnit, description, specialHandling;
		double sellPrice, stockAmount;
		Category category;
		
		try {
			pst = conn.prepareStatement("SELECT * FROM tbl_product WHERE is_active = 1");
			rs = pst.executeQuery();
			
			while(rs.next()) {
				System.out.println("FOUND PRODUCTS");
				productId = rs.getInt("product_id");
				fkCategoryId = rs.getInt("fk_category_id");
				SKU = rs.getString("SKU");
				brand = rs.getString("brand");
				variant = rs.getString("variant");
				size = rs.getString("size");
				measurementUnit = rs.getString("measurement_unit");
				description = rs.getString("description");
				specialHandling = rs.getString("special_handling");
				sellPrice = rs.getDouble("sell_price");
				stockAmount = calculateStockAmount(productId);
				category = getCategory(fkCategoryId);

				System.out.println("ADDING TO LIST");
				productList.add(new Product(productId, SKU, brand, variant, size, measurementUnit, description, specialHandling, sellPrice, stockAmount, category));
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
		return productList;
	}
	
	private int calculateStockAmount(int productId) {
		Connection innerConn = getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		int stockAmount = 0;
		try {
			pst = innerConn.prepareStatement("SELECT COUNT(*) AS amount FROM tbl_item "
					+ "INNER JOIN tbl_batch ON tbl_item.fk_batch_id = tbl_batch.batch_id "
					+ "WHERE tbl_batch.fk_product_id = ?");
			pst.setInt(1, productId);
			
			rs = pst.executeQuery();
			if(rs.next()) {
				stockAmount = rs.getInt("amount");
			}
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
            if (rs != null) {
                try {rs.close();} catch (SQLException e) { e.printStackTrace();}
            }
            if (pst != null) {
                try {pst.close();} catch (SQLException e) {e.printStackTrace();}
            }
            if (innerConn != null) {
            	try {innerConn.close();} catch (SQLException e) {e.printStackTrace();}
            }
        }
		return stockAmount;
	}
	
	public boolean addProduct(int categoryId, String brand, String variant, String size, String measurementUnit, String description, String specialHandling, double sellPrice, String SKU) {
		conn = getConnection();
		try {
			pst = conn.prepareStatement("INSERT INTO tbl_product(fk_category_id, SKU, brand, variant, size, measurement_unit, description, special_handling, sell_price) "
					+ "VALUES(?,?,?,?,?,?,?,?,?)");
			pst.setInt(1, categoryId);
			pst.setString(2, SKU);
			pst.setString(3, brand);
			pst.setString(4, variant);
			pst.setString(5, size);
			pst.setString(6, measurementUnit);
			pst.setString(7, description);
			pst.setString(8, specialHandling);
			pst.setDouble(9, sellPrice);
			
			count = pst.executeUpdate();
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
