package com.ibm.cs.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Category implements Serializable {
	private int categoryId;
	private String categoryName;
	private String categoryType; // (Beverages, Bread/Bakery, Canned/Jarred GOods, Dairy, Dry/Baking Goods, Frozen Goods, Meat, Produce, Cleaners, Paper Goods, Personal Care, Other)
	private boolean isPerishable;
	private Boolean isRecyclable;
	
	public Category() {
		// TODO Auto-generated constructor stub
	}
	
	public Category(int category_id, String categoryName, String categoryType, boolean isPerishable, Boolean isRecyclable) {
		super();
		this.categoryId = category_id;
		this.categoryName = categoryName;
		this.categoryType = categoryType;
		this.isPerishable = isPerishable;
		this.isRecyclable = isRecyclable;
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
	
}
