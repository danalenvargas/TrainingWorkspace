package com.ibm.cs.service;

import java.util.ArrayList;
import java.util.HashMap;
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
	
// 	builds the Inventory
//	Iventory has following structure:
//		- list of categories, e.g. (toothpaste)
//		- each category has list of products, e.g. (Colgate-White variant-150ml under toothpaste category)
//		- each product has list of batches, e.g. (1 shipment of Colgate-White-150ml toothpaste containing 100 items)
//		- each batch has list of items, e.g. (item of type Colgate-White-150ml toothpaste with own expiry and manufacture date)
	public ArrayList<Category> getInventory(){
		Product parentProduct;
		Batch parentBatch;
		ArrayList<Item> itemList = getItemList();
		// HashMaps used for constant-time O(1) search/get
		HashMap<Integer, Batch> batchMap = getBatchMap(); // key = batch id, value = Batch object
		HashMap<Integer, Product> productMap = getProductMap(); // key = product id, value = Product object
		HashMap<Integer, Category> categoryMap = getCategoryMap(); // key = category id, value = Category object
		
		// put each item into the items-arraylist of their 'Parent Batch'
		for(Item item : itemList) {
			parentBatch = batchMap.get(item.getFkBatchId());
			parentBatch.getItems().add(item);
			parentBatch.incrementRemainingAmount(1); // increment amount of remaining items under this batch
		}
		
		// put each batch into the batches-arraylist of their 'Parent Product'
		for(Batch batch : batchMap.values()) {
			parentProduct = productMap.get(batch.getFkProductId());
			parentProduct.getBatches().add(batch);
			parentProduct.incrementStockAmount(batch.getRemainingAmount()); // increment amount of remaining items under this product
		}
		
		// put each product into the products-arraylist of their 'Parent Category'
		for(Product product : productMap.values()) {
			categoryMap.get(product.getFkCategoryId()).getProducts().add(product);
		}
		
		ArrayList<Category> categoryList = new ArrayList<>(categoryMap.values());
		return categoryList;
	}
	
	public ArrayList<Item> getItemList(){
		itemDao = new ItemDAO();
		ArrayList<Item> itemList = itemDao.getItemList();
		itemDao.closeConnection();
		return itemList;
	}
	
	public HashMap<Integer, Batch> getBatchMap(){
		batchDao = new BatchDAO();
		HashMap<Integer, Batch> batchMap = batchDao.getBatchMap();
		batchDao.closeConnection();
		return batchMap;
	}
	
	public HashMap<Integer, Product> getProductMap(){
		productDao = new ProductDAO();
		HashMap<Integer, Product> productMap = productDao.getProductMap();
		productDao.closeConnection();
		return productMap;
	}
	
	public HashMap<Integer, Category> getCategoryMap(){
		categoryDao = new CategoryDAO();
		HashMap<Integer, Category> categoryMap = categoryDao.getCategoryMap();
		categoryDao.closeConnection();
		return categoryMap;
	}
	
	// ==================================== v TO REPLACE v  ===========================
	// get list of categories
//	public ArrayList<Category> getCategoryList(){
//		categoryDao = new CategoryDAO();
//		ArrayList<Category> categoryList = categoryDao.getCategoryList();
//		categoryDao.closeConnection();
//		return categoryList;
//	}
	
//	// fill the productList of every category
//	public void fillProductList(ArrayList<Category> categoryList){
//		productDao = new ProductDAO();
//		for (Category category : categoryList) {
//			ArrayList<Product> products = productDao.getProductList(category.getCategoryId());
//			category.setProducts(products);
//			fillBatchList(products);
//		}
//		productDao.closeConnection();
//	}
	
//	// fill the batchList of every product
//	public void fillBatchList(ArrayList<Product> productList) {
//		batchDao = new BatchDAO();
//		for (Product product : productList) {
//			ArrayList<Batch> batches = batchDao.getBatchList(product.getProductId());
//			product.setBatches(batches);
//			fillItemList(batches);
//		}
//		batchDao.closeConnection();
//	}
	
//	// fill the itemList of every batch
//	public void fillItemList(ArrayList<Batch> batchList) {
//		itemDao = new ItemDAO();
//		for (Batch batch : batchList) {
//			ArrayList<Item> items = itemDao.getItemList(batch.getBatchId());
//			batch.setItems(items);
//		}
//		itemDao.closeConnection();
//	}
	
	// ==================================== ^ TO REPLACE ^  ===========================

	public boolean addCategory(String categoryName, String categoryType, boolean isPerishable, Boolean isRecyclable) {
		categoryDao = new CategoryDAO();
		isSuccessful = categoryDao.addCategory(categoryName, categoryType, isPerishable, isRecyclable);
		categoryDao.closeConnection();
		return isSuccessful;
	}
	
	public Category getCategory(int categoryId) {
		categoryDao = new CategoryDAO();
		Category category = categoryDao.getCategory(categoryId);
		categoryDao.closeConnection();
		return category;
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
