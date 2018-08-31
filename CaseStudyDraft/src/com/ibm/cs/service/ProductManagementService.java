package com.ibm.cs.service;

import com.ibm.cs.dao.ProductManagementDAO;

public class ProductManagementService {
	boolean isSuccessful;

	public ProductManagementService() {
		// TODO Auto-generated constructor stub
	}

	public boolean addCategory(String name, String productType, boolean isPerishable, Boolean isRecyclable) {
		ProductManagementDAO dao = new ProductManagementDAO();
		isSuccessful = dao.addCategory(name, productType, isPerishable, isRecyclable);
		return isSuccessful;
	}
}
