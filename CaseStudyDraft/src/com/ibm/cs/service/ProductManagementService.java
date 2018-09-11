package com.ibm.cs.service;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.Date;

import com.ibm.cs.dao.BatchDAO;
import com.ibm.cs.dao.CategoryDAO;
import com.ibm.cs.dao.ItemDAO;
import com.ibm.cs.dao.ProductDAO;
import com.ibm.cs.model.Batch;
import com.ibm.cs.model.Category;
import com.ibm.cs.model.Item;
import com.ibm.cs.model.Product;

public class ProductManagementService {
	boolean isSuccessful;
	CategoryDAO categoryDao;
	ProductDAO productDao;
	BatchDAO batchDao;
	ItemDAO itemDao;

	public ProductManagementService() {
		// TODO Auto-generated constructor stub
	}

	public boolean addCategory(String categoryName, String categoryType, boolean isPerishable, Boolean isRecyclable) {
		categoryDao = new CategoryDAO();
		isSuccessful = categoryDao.addCategory(categoryName, categoryType, isPerishable, isRecyclable);
		categoryDao.closeConnection();
		return isSuccessful;
	}
	
	public boolean editCategory(int categoryId, String categoryName, String categoryType, boolean isPerishable, Boolean isRecyclable) {
		categoryDao = new CategoryDAO();
		isSuccessful = categoryDao.editCategory(categoryId, categoryName, categoryType, isPerishable, isRecyclable);
		categoryDao.closeConnection();
		return isSuccessful;
	}
	
	public boolean deleteCategory(int categoryId) {
		categoryDao = new CategoryDAO();
		isSuccessful = categoryDao.deleteCategory(categoryId);
		categoryDao.closeConnection();
		return isSuccessful;
	}
	
	public ArrayList<Category> getCategoryList(){
		categoryDao = new CategoryDAO();
		ArrayList<Category> categoryList = categoryDao.getCategoryList();
		categoryDao.closeConnection();
		return categoryList;
	}
	
	public Category getCategory(int categoryId) {
		categoryDao = new CategoryDAO();
		Category category = categoryDao.getCategory(categoryId);
		categoryDao.closeConnection();
		return category;
	}
	
	public ArrayList<Category> getInventory(){
		ArrayList<Category> categoryList = getCategoryList();
		fillProductList(categoryList);
		return categoryList;
	}
	
	public void fillProductList(ArrayList<Category> categoryList){
		productDao = new ProductDAO();
		for (Category category : categoryList) {
			ArrayList<Product> products = productDao.getProductList(category.getCategoryId());
			category.setProducts(products);
			fillBatchList(products);
		}
		productDao.closeConnection();
	}
	
	public void fillBatchList(ArrayList<Product> productList) {
		batchDao = new BatchDAO();
		for (Product product : productList) {
			ArrayList<Batch> batches = batchDao.getBatchList(product.getProductId());
			product.setBatches(batches);
			fillItemList(batches);
		}
		batchDao.closeConnection();
	}
	
	public void fillItemList(ArrayList<Batch> batchList) {
		itemDao = new ItemDAO();
		for (Batch batch : batchList) {
			ArrayList<Item> items = itemDao.getItemList(batch.getBatchId());
			batch.setItems(items);
		}
		itemDao.closeConnection();
	}
	
//	public ArrayList<Product> getProductList(int categoryId){
//		productDao = new ProductDAO();
//		ArrayList<Product> products = productDao.getProductList(categoryId);
//		productDao.closeConnection();
//		return products;
//	}
//	public ArrayList<Product> getProductList(){
//		ProductDAO dao = new ProductDAO();
//		ArrayList<Product> productList = dao.getProductList();
//		dao.closeConnection();
//		return productList;
//	}
	
//	public HashMap<Integer, Category> getProductMap(){
//		HashMap<Integer, Category> categoryMap = categoryDao.getCategoryMap();
//		for(Integer categoryId : categoryMap.keySet()) {
//			
//		}
//	}
	
	public boolean addProduct(int categoryId, String brand, String variant, String size, String measurementUnit, String description, String specialHandling, double sellPrice, String SKU) {
		productDao = new ProductDAO();
		isSuccessful = productDao.addProduct(categoryId, brand, variant, size, measurementUnit, description, specialHandling, sellPrice, SKU);
		productDao.closeConnection();
		return isSuccessful;
	}
	
	public Product getProduct(int productId) {
		productDao = new ProductDAO();
		Product product = productDao.getProduct(productId);
		productDao.closeConnection();
		return product;
	}
	
	public boolean editProduct(int categoryId, int productId, String brand, String variant, String size, String measurementUnit, String description, String specialHandling, double sellPrice, String SKU) {
		productDao = new ProductDAO();
		isSuccessful = productDao.editProduct(categoryId, productId, brand, variant, size, measurementUnit, description, specialHandling, sellPrice, SKU);
		productDao.closeConnection();
		return isSuccessful;
	}
	
	public boolean deleteProduct(int productId) {
		productDao = new ProductDAO();
		isSuccessful = productDao.deleteProduct(productId);
		productDao.closeConnection();
		return isSuccessful;
	}
	
	public boolean inputBatch(int productId, int amount, String comments, String supplier, Date manufactureDate, Date expirationDate, double purchasePrice) {
		batchDao = new BatchDAO();
		int newBatchId = batchDao.addBatch(productId, amount, comments, supplier);
		batchDao.closeConnection();
		
		itemDao = new ItemDAO();
		isSuccessful = itemDao.addItems(newBatchId, amount, manufactureDate, expirationDate, purchasePrice);
		itemDao.closeConnection();
		return isSuccessful;
	}
	
	public Batch getBatch(int batchId) {
		batchDao = new BatchDAO();
		Batch batch = batchDao.getBatch(batchId);
		batchDao.closeConnection();
		return batch;
	}
	
	public boolean editBatch(int batchId, int productId, int amount, String comments, String supplier){
		batchDao = new BatchDAO();
		isSuccessful = batchDao.editBatch(batchId, productId, amount, comments, supplier);
		batchDao.closeConnection();
		return isSuccessful;
	}
	
	public boolean deleteBatch(int batchId){
		batchDao = new BatchDAO();
		isSuccessful = batchDao.deleteBatch(batchId);
		batchDao.closeConnection();
		return isSuccessful;
	}
	
	public ArrayList<Item> getItems(int[] itemIds){
		itemDao = new ItemDAO();
		ArrayList<Item> itemList = itemDao.getItemList(itemIds);
		itemDao.closeConnection();
		return itemList;
	}
	
	public boolean editItems(int[] itemIds, Date manufactureDate, Date expirationDate, double purchasePrice) {
		itemDao = new ItemDAO();
		isSuccessful = itemDao.editItems(itemIds, manufactureDate, expirationDate, purchasePrice);
		itemDao.closeConnection();
		return isSuccessful;
	}
	
	public boolean deleteItems(int[] itemIds){
		itemDao = new ItemDAO();
		isSuccessful = itemDao.deleteItems(itemIds);
		itemDao.closeConnection();
		return isSuccessful;
	}
}
