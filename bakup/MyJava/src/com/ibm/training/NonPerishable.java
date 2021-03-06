package com.ibm.training;

public class NonPerishable extends Product {
	private boolean isRecyclable;

	public NonPerishable(String name, boolean isRecyclable) {
		super(name);
		this.isRecyclable = isRecyclable;
	}

	public boolean isRecyclable() {
		return isRecyclable;
	}

	public void setRecyclable(boolean isRecyclable) {
		this.isRecyclable = isRecyclable;
	}
}
