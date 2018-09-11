package com.ibm.cs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("serial")
public class Batch implements Serializable {
	int batchId, fkProductId, amount, remainingAmount;
	String comments, supplier;
	Date entryTimestamp;
	ArrayList<Item> items = new ArrayList<>();

	public Batch() {
		// TODO Auto-generated constructor stub
	}

	public Batch(int batchId, int fkProductId, int amount, int remainingAmount, String comments, String supplier, Date entryTimestamp) {
		super();
		this.batchId = batchId;
		this.fkProductId = fkProductId;
		this.amount = amount;
		this.remainingAmount = remainingAmount;
		this.comments = comments;
		this.supplier = supplier;
		this.entryTimestamp = entryTimestamp;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getFkProductId() {
		return fkProductId;
	}

	public void setFkProductId(int fkProductId) {
		this.fkProductId = fkProductId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getEntryTimestamp() {
		return entryTimestamp;
	}

	public void setEntryTimestamp(Date entryTimestamp) {
		this.entryTimestamp = entryTimestamp;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public int getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(int remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	
}
