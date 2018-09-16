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

/**
 * Service class to handle all inventory-related logic. <br>
 * Handles CRUD operations for Category, Product, Batch, and Item <br>
 * 
 * @author Dan Alejandro A. Vargas
 * @see Category
 * @see Product
 * @see Batch
 * @see Item
 */
public class ProductManagementService {
	boolean isSuccessful;
	CategoryDAO categoryDao;
	ProductDAO productDao;
	BatchDAO batchDao;
	ItemDAO itemDao;

	public ProductManagementService() {
	}

	/**
	 * builds the Inventory. Inventory has following structure:<br>
	 * - root is a list of categories, e.g. (toothpaste)<br>
	 * - each category has list of products, e.g. (Colgate-White variant-150ml under toothpaste category)<br>
	 * - each product has list of batches, e.g. (1 shipment of Colgate-White-150ml toothpaste containing 100 items)<br>
	 * - each batch has list of items, e.g. (item of type Colgate-White-150ml toothpaste with own expiry and manufacture date)<br>
	 * <br>
	 * 4 separate database tables are involved in the inventory (tables for
	 * category, product, batch, item). I wanted to avoid a 4-table join or
	 * repetitive queries. Instead, each table is queried once, and then organized
	 * into the hierarchical structure using HashMaps. I chose HashMaps for
	 * constant-time O(1) insert and search/get.
	 * 
	 * @return ArrayList of Categories which represents the whole inventory
	 */
	public ArrayList<Category> getInventory() {
		Product parentProduct;
		Batch parentBatch;
		ArrayList<Item> itemList = getItemList();
		HashMap<Integer, Batch> batchMap = getBatchMap();
		HashMap<Integer, Product> productMap = getProductMap();
		HashMap<Integer, Category> categoryMap = getCategoryMap();

		// put each item into the items-arraylist of their 'Parent Batch'
		for (Item item : itemList) {
			parentBatch = batchMap.get(item.getFkBatchId());
			parentBatch.getItems().add(item);
			parentBatch.incrementRemainingAmount(1);
		}

		// put each batch into the batches-arraylist of their 'Parent Product'
		for (Batch batch : batchMap.values()) {
			parentProduct = productMap.get(batch.getFkProductId());
			parentProduct.getBatches().add(batch);
			parentProduct.incrementStockAmount(batch.getRemainingAmount());
		}

		// put each product into the products-arraylist of their 'Parent Category'
		for (Product product : productMap.values()) {
			categoryMap.get(product.getFkCategoryId()).getProducts().add(product);
		}

		ArrayList<Category> categoryList = new ArrayList<>(categoryMap.values());
		return categoryList;
	}

	/**
	 * Gets arraylist of all items from the item DAO
	 * 
	 * @return ArrayList containing all items in the database
	 * @see Item
	 * @see ItemDAO
	 */
	public ArrayList<Item> getItemList() {
		itemDao = new ItemDAO();
		ArrayList<Item> itemList = itemDao.getItemList();
		itemDao.closeConnection();
		return itemList;
	}

	/**
	 * Gets hash map of all batches from the batch DAO
	 * 
	 * @return HashMap(key = batch id, value = Batch object) containing all batches
	 *         in the database
	 * @see Batch
	 * @see BatchDAO
	 */
	public HashMap<Integer, Batch> getBatchMap() {
		batchDao = new BatchDAO();
		HashMap<Integer, Batch> batchMap = batchDao.getBatchMap();
		batchDao.closeConnection();
		return batchMap;
	}

	/**
	 * Gets hash map of all products from the product DAO
	 * 
	 * @return HashMap(key = product id, value = Product object) containing all
	 *         products in the database
	 * @see Product
	 * @see ProductDAO
	 */
	public HashMap<Integer, Product> getProductMap() {
		productDao = new ProductDAO();
		HashMap<Integer, Product> productMap = productDao.getProductMap();
		productDao.closeConnection();
		return productMap;
	}

	/**
	 * Gets hash map of all categories from the category DAO
	 * 
	 * @return HashMap(key = category id, value = Category object) containing all
	 *         categories in the database
	 * @see Category
	 * @see CategoryDAO
	 */
	public HashMap<Integer, Category> getCategoryMap() {
		categoryDao = new CategoryDAO();
		HashMap<Integer, Category> categoryMap = categoryDao.getCategoryMap();
		categoryDao.closeConnection();
		return categoryMap;
	}

	/**
	 * Checks if the the category name is unique
	 * 
	 * @param categoryName
	 *            String name of a category
	 * @return true if the category's name is unique
	 */
	public boolean validateCategoryName(String categoryName) {
		categoryDao = new CategoryDAO();
		boolean isUnique = categoryDao.validateCategoryName(categoryName);
		categoryDao.closeConnection();
		return isUnique;
	}

	/**
	 * Checks if the the product SKU is unique
	 * 
	 * @param SKU
	 *            String Stock Keeping Unit (SKU) of a product
	 * @return true if the product's SKU is unique
	 */
	public boolean validateProductSKU(String SKU) {
		productDao = new ProductDAO();
		boolean isUnique = productDao.validateProductSKU(SKU);
		productDao.closeConnection();
		return isUnique;
	}

	// v======= CATEGORY CRUD ===========v

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

	public boolean editCategory(int categoryId, String categoryName, String categoryType, boolean isPerishable,
			Boolean isRecyclable) {
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

	// v======= PRODUCT CRUD ===========v

	public boolean addProduct(int categoryId, String brand, String variant, String size, String measurementUnit,
			String description, String specialHandling, double sellPrice, String SKU) {
		productDao = new ProductDAO();
		isSuccessful = productDao.addProduct(categoryId, brand, variant, size, measurementUnit, description,
				specialHandling, sellPrice, SKU);
		productDao.closeConnection();
		return isSuccessful;
	}

	public Product getProduct(int productId) {
		productDao = new ProductDAO();
		Product product = productDao.getProduct(productId);
		productDao.closeConnection();
		return product;
	}

	public boolean editProduct(int categoryId, int productId, String brand, String variant, String size,
			String measurementUnit, String description, String specialHandling, double sellPrice, String SKU) {
		productDao = new ProductDAO();
		isSuccessful = productDao.editProduct(categoryId, productId, brand, variant, size, measurementUnit, description,
				specialHandling, sellPrice, SKU);
		productDao.closeConnection();
		return isSuccessful;
	}

	public boolean deleteProduct(int productId) {
		productDao = new ProductDAO();
		isSuccessful = productDao.deleteProduct(productId);
		productDao.closeConnection();
		return isSuccessful;
	}

	// v======= BATCH CRUD ===========v

	public boolean inputBatch(int productId, int amount, String comments, String supplier, Date manufactureDate,
			Date expirationDate, double purchasePrice) {
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

	public boolean editBatch(int batchId, int productId, int amount, String comments, String supplier) {
		batchDao = new BatchDAO();
		isSuccessful = batchDao.editBatch(batchId, productId, amount, comments, supplier);
		batchDao.closeConnection();
		return isSuccessful;
	}

	public boolean deleteBatch(int batchId) {
		batchDao = new BatchDAO();
		isSuccessful = batchDao.deleteBatch(batchId);
		batchDao.closeConnection();
		return isSuccessful;
	}

	// v======= ITEM CRUD ===========v

	public ArrayList<Item> getItems(int[] itemIds) {
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

	public boolean deleteItems(int[] itemIds) {
		itemDao = new ItemDAO();
		isSuccessful = itemDao.deleteItems(itemIds);
		itemDao.closeConnection();
		return isSuccessful;
	}
}
