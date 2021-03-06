package com.ibm.training;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Service {
	private ArrayList<Product> productArray = new ArrayList<Product>();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	public ArrayList<Product> getProductArray() {
		return productArray;
	}

	public void setProductArray(ArrayList<Product> productArray) {
		this.productArray = productArray;
	}
	
	public void addProduct(Product newProduct) {
		productArray.add(newProduct);
	}

	// return product at particular index
	public Product getProduct(int index) {
		return productArray.get(index);
	}
	
	// returns array of all products
	public ArrayList<Product> reviewProducts() {
		return productArray;
	}
	
	// returns array of products filtered by type (Perishable or NonPerishable)
	public ArrayList<Product> reviewProducts(EnumProdType type) {
		ArrayList<Product> newArr = new ArrayList<Product>();
		for(Product prod : productArray) {
			if(prod instanceof Perishable && type == EnumProdType.PERISHABLE) {
				newArr.add(prod);
			}else if(prod instanceof NonPerishable && type == EnumProdType.NONPERISHABLE) {
				newArr.add(prod);
			}
		}
		return newArr;
	}
	
	// utility method to return string representation of any arraylist of products
	public String arrayToString(ArrayList<Product> productArray) {
		StringBuffer listing = new StringBuffer("");
		for(Product prod : productArray) {
			if(prod instanceof Product) {
				listing.append(prod);
			}
		}
		return listing.toString();
	}
}
