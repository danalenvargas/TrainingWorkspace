package com.ibm.cs.service;

import java.util.ArrayList;

import com.ibm.cs.dao.ProductManagementDAO;
import com.ibm.cs.entity.Category;
import com.ibm.cs.entity.Product;

public class ProductManagementService {
	boolean isSuccessful;
	ProductManagementDAO dao = new ProductManagementDAO();

	public ProductManagementService() {
		// TODO Auto-generated constructor stub
	}

	public boolean addCategory(String name, String productType, boolean isPerishable, Boolean isRecyclable) {
		isSuccessful = dao.addCategory(name, productType, isPerishable, isRecyclable);
		return isSuccessful;
	}
	
	public boolean editCategory(int categoryId, String name, String productType, boolean isPerishable, Boolean isRecyclable) {
		isSuccessful = dao.editCategory(categoryId, name, productType, isPerishable, isRecyclable);
		return isSuccessful;
	}
	
	public boolean deleteCategory(int categoryId) {
		isSuccessful = dao.deleteCategory(categoryId);
		return isSuccessful;
	}
	
	public ArrayList<Category> getCategoryList(){
		ArrayList<Category> categoryList = dao.getCategoryList();
		return categoryList;
	}
	
	public Category getCategory(int categoryId) {
		Category category = dao.getCategory(categoryId);
		return category;
	}
	
	public ArrayList<Product> getProductList(){
		ArrayList<Product> productList = dao.getProductList();
		return productList;
	}
	
	public boolean addProduct(int categoryId, String brand, String variant, String size, String measurementUnit, String description, String specialHandling, double sellPrice, String SKU) {
		isSuccessful = dao.addProduct(categoryId, brand, variant, size, measurementUnit, description, specialHandling, sellPrice, SKU);
		return isSuccessful;
	}
}
