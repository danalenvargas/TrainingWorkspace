package com.ibm.training;

import java.text.SimpleDateFormat;

public class Service {
	Product[] productArray = new Product[20];
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	public Product[] getProductArray() {
		return productArray;
	}

	public void setProductArray(Product[] productArray) {
		this.productArray = productArray;
	}
	

	public void addProduct(Product newProduct) {
		for(int i=0; i<productArray.length; i++) {
			if(!(productArray[i] instanceof Product)) {
				productArray[i] = newProduct;
				break;
			}
		}
	}
	// returns info of the product at a particular index
	public String getProduct(int index) {
		StringBuffer output = new StringBuffer("Product at index " + index + " is:\n");
		output.append(getProperties(productArray[index]) + "\n");
		return output.toString();
	}
	
	// returns listing of all products
	public String reviewProducts() {
		StringBuffer listing = new StringBuffer("List of all products:\n");
		for(int i=0; i<productArray.length; i++) {
			if(productArray[i] != null) {
				listing.append(getProperties(productArray[i]) + "\n");
			}
		}
		return listing.toString();
	}
	
	// returns listing of products filtered by type (Perishable or NonPerishable)
	public String reviewProducts(EnumProdType type) {
		StringBuffer listing = new StringBuffer("List of all " + type + " products:\n");
		for(int i=0; i<productArray.length; i++) {
			if(productArray[i] instanceof Perishable && type == EnumProdType.PERISHABLE) {
				listing.append(getProperties(productArray[i]) + "\n");
			}else if(productArray[i] instanceof NonPerishable && type == EnumProdType.NONPERISHABLE) {
				listing.append(getProperties(productArray[i]) + "\n");
			}
		}
		return listing.toString();
	}
	
	public String getExpiryCountdown() {
		StringBuffer listing = new StringBuffer("List of all perishable products:\n");
		for(int i=0; i<productArray.length; i++) {
			if(productArray[i] instanceof Perishable) {
				Perishable peri = (Perishable) productArray[i];
				listing.append(getProperties(productArray[i]) + 
						"\t|   " + "Days until Expiry: " +
						((peri.getExpiresIn() > 0) ? peri.getExpiresIn():"Expired!") + "\n");
			}
		}
		return listing.toString();
	}
	
	// private utility method to get String value of a product's properties (name, expiryDate, isRecyclable)
	private String getProperties(Product prod) {
		if(prod instanceof Perishable) {
			Perishable peri = (Perishable) prod; //downcast from Product to Perishable
			return peri.getName() + "\t|   " + "Perishable\t\tExpiry Date: " + dateFormat.format(peri.getExpiryDate());
		} else {
			NonPerishable nonPeri = (NonPerishable) prod; //downcast from Product to NonPerishable
			return nonPeri.getName() + "\t|   " + "Non-Perishable\t" + (nonPeri.isRecyclable() ? "Recyclabe":"Not Recyclable");
		}
	}
}
