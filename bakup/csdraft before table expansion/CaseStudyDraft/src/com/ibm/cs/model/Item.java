package com.ibm.cs.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Class representing a single item, entered under a particular batch of a
 * particular product type.
 * 
 * @author Dan Alejandro A. Vargas
 * @see Batch
 * @see Product
 */
@SuppressWarnings("serial")
public class Item implements Serializable {
	private int itemId, fkBatchId;
	Date manufactureDate, expirationDate;
	double purchasePrice;

	public Item() {
		// TODO Auto-generated constructor stub
	}

	public Item(int itemId, int fkBatchId, Date manufactureDate, Date expirationDate, double purchasePrice) {
		super();
		this.itemId = itemId;
		this.fkBatchId = fkBatchId;
		this.manufactureDate = manufactureDate;
		this.expirationDate = expirationDate;
		this.purchasePrice = purchasePrice;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getFkBatchId() {
		return fkBatchId;
	}

	public void setFkBatchId(int fkBatchId) {
		this.fkBatchId = fkBatchId;
	}

	public Date getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
}
