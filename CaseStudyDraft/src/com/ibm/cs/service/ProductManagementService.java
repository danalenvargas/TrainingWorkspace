package com.ibm.cs.service;

import java.util.ArrayList;

import com.ibm.cs.dao.CategoryDAO;
import com.ibm.cs.dao.ProductDAO;
import com.ibm.cs.model.Category;
import com.ibm.cs.model.Product;

public class ProductManagementService {
	boolean isSuccessful;
	CategoryDAO dao;

	public ProductManagementService() {
		// TODO Auto-generated constructor stub
	}

	public boolean addCategory(String name, String productType, boolean isPerishable, Boolean isRecyclable) {
		dao = new CategoryDAO();
		isSuccessful = dao.addCategory(name, productType, isPerishable, isRecyclable);
		dao.closeConnection();
		return isSuccessful;
	}
	
	public boolean editCategory(int categoryId, String name, String productType, boolean isPerishable, Boolean isRecyclable) {
		dao = new CategoryDAO();
		isSuccessful = dao.editCategory(categoryId, name, productType, isPerishable, isRecyclable);
		dao.closeConnection();
		return isSuccessful;
	}
	
	public boolean deleteCategory(int categoryId) {
		dao = new CategoryDAO();
		isSuccessful = dao.deleteCategory(categoryId);
		dao.closeConnection();
		return isSuccessful;
	}
	
	public ArrayList<Category> getCategoryList(){
		dao = new CategoryDAO();
		ArrayList<Category> categoryList = dao.getCategoryList();
		dao.closeConnection();
		return categoryList;
	}
	
	public Category getCategory(int categoryId) {
		dao = new CategoryDAO();
		Category category = dao.getCategory(categoryId);
		dao.closeConnection();
		return category;
	}
	
	public ArrayList<Product> getProductList(){
		ProductDAO dao = new ProductDAO();
		ArrayList<Product> productList = dao.getProductList();
		dao.closeConnection();
		return productList;
	}
	
	public boolean addProduct(int categoryId, String brand, String variant, String size, String measurementUnit, String description, String specialHandling, double sellPrice, String SKU) {
		ProductDAO dao = new ProductDAO();
		isSuccessful = dao.addProduct(categoryId, brand, variant, size, measurementUnit, description, specialHandling, sellPrice, SKU);
		dao.closeConnection();
		return isSuccessful;
	}
}
