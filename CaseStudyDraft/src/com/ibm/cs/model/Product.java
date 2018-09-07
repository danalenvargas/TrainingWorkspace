package com.ibm.cs.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Product implements Serializable {
	int productId;
	String SKU, brand, variant, size, measurementUnit, description, specialHandling;
	double sellPrice, stockAmount;
	Category category;

	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	public Product(int productId, String sKU, String brand, String variant, String size, String measurementUnit,
			String description, String specialHandling, double sellPrice, double stockAmount, Category category) {
		super();
		this.productId = productId;
		SKU = sKU;
		this.brand = brand;
		this.variant = variant;
		this.size = size;
		this.measurementUnit = measurementUnit;
		this.description = description;
		this.specialHandling = specialHandling;
		this.sellPrice = sellPrice;
		this.stockAmount = stockAmount;
		this.category = category;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getSKU() {
		return SKU;
	}

	public void setSKU(String sKU) {
		SKU = sKU;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getVariant() {
		return variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSpecialHandling() {
		return specialHandling;
	}

	public void setSpecialHandling(String specialHandling) {
		this.specialHandling = specialHandling;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public double getStockAmount() {
		return stockAmount;
	}

	public void setStockAmount(double stockAmount) {
		this.stockAmount = stockAmount;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
