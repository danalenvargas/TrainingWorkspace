package com.ibm.cs.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Category implements Serializable {
	private int categoryId;
	private String name;
	private String productType; // (Beverages, Bread/Bakery, Canned/Jarred GOods, Dairy, Dry/Baking Goods, Frozen Goods, Meat, Produce, Cleaners, Paper Goods, Personal Care, Other)
	private boolean isPerishable;
	private Boolean isRecyclable;
	
	public Category() {
		// TODO Auto-generated constructor stub
	}
	
	public Category(int category_id, String name, String product_type, boolean isPerishable, Boolean isRecyclable) {
		super();
		this.categoryId = category_id;
		this.name = name;
		this.productType = product_type;
		this.isPerishable = isPerishable;
		this.isRecyclable = isRecyclable;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int category_id) {
		this.categoryId = category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String product_type) {
		this.productType = product_type;
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
