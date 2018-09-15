package com.ibm.cs.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class representing a single category, can have zero or more products
 * registered under it. For example: Under 'toothpaste' category, you can have
 * 'Colgate White 50ml', 'Colgate Red 50ml', 'Close Up White 10ml' products
 * 
 * @author Dan Alejandro A. Vargas
 * @see Product
 */
@SuppressWarnings("serial")
public class Category implements Serializable {
	private int categoryId;
	private String categoryName;
	private String categoryType;
	private boolean isPerishable;
	private Boolean isRecyclable;
	ArrayList<Product> products;

	public Category() {
	}

	public Category(int category_id, String categoryName, String categoryType, boolean isPerishable,
			Boolean isRecyclable) {
		super();
		this.categoryId = category_id;
		this.categoryName = categoryName;
		this.categoryType = categoryType;
		this.isPerishable = isPerishable;
		this.isRecyclable = isRecyclable;
		products = new ArrayList<>();
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int category_id) {
		this.categoryId = category_id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String name) {
		this.categoryName = name;
	}

	/**
	 * Categories are further grouped into types, example:<br>
	 * Category Type | category samples<br>
	 * ----------------------------------------------<br>
	 * Beverages | coffee/tea, juice, soda<br>
	 * Bread/Bakery | sandwich loaves, dinner rolls, tortillas, bagels<br>
	 * Canned/Jarred Goods | vegetables, spaghetti sauce, ketchup<br>
	 * Dairy | cheeses, eggs, milk, yogurt, butter<br>
	 * Dry/Baking Goods | cereals, flour, sugar, pasta, mixes<br>
	 * Frozen Foods | waffles, vegetables, individual meals, ice cream<br>
	 * Meat | lunch meat, poultry, beef, pork<br>
	 * Produce | fruits, vegetables<br>
	 * Cleaners | laundry detergent, dishwashing liquid/detergent<br>
	 * Paper Goods | paper towels, toilet paper, aluminum foil, sandwich bags<br>
	 * Personal Care | shampoo, soap, hand soap, shaving cream<br>
	 * Other | baby items, pet items, batteries, greeting cards<br>
	 * 
	 * @return category type
	 */
	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String product_type) {
		this.categoryType = product_type;
	}

	public boolean getIsPerishable() {
		return isPerishable;
	}

	public void setIsPerishable(boolean isPerishable) {
		this.isPerishable = isPerishable;
	}

	public Boolean getIsRecyclable() {
		return isRecyclable;
	}

	public void setIsRecyclable(Boolean isRecyclable) {
		this.isRecyclable = isRecyclable;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
}
