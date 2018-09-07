package com.ibm.cs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ibm.cs.model.Category;
import com.ibm.cs.model.Product;

public class ProductDAO extends MasterDAO {
	int count;

	public ProductDAO() {
		// TODO Auto-generated constructor stub
	}
	public ArrayList<Product> getProductList(){
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		ArrayList<Product> productList = new ArrayList<>();
		int productId, fkCategoryId;
		String SKU, brand, variant, size, measurementUnit, description, specialHandling;
		double sellPrice, stockAmount;
		Category category;
		
		CategoryDAO catDAO = new CategoryDAO(conn);
		
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
				category = catDAO.getCategory(fkCategoryId);

				System.out.println("ADDING TO LIST");
				productList.add(new Product(productId, SKU, brand, variant, size, measurementUnit, description, specialHandling, sellPrice, stockAmount, category));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            closeResources(rs, pst);
        }
		return productList;
	}
	
	private int calculateStockAmount(int productId) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		int stockAmount = 0;
		try {
			pst = conn.prepareStatement("SELECT COUNT(*) AS amount FROM tbl_item "
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
            closeResources(rs, pst);
        }
		return stockAmount;
	}
	
	public boolean addProduct(int categoryId, String brand, String variant, String size, String measurementUnit, String description, 
			String specialHandling, double sellPrice, String SKU) {
		PreparedStatement pst = null;
		
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
            closeResources(pst);
        }
		return false;
	}
}