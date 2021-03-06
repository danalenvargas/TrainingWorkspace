package com.ibm.training;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Perishable extends Product {
	private Date expiryDate;
	private int expiresIn;

	public Perishable(String name, Date expiryDate) {
		super(name);
		this.expiryDate = expiryDate;
		
		Date curDate = new Date();
		long diffInMillies = Math.abs(expiryDate.getTime() - curDate.getTime());
		this.expiresIn = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
	
	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}
