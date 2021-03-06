package com.ibm.training;

import java.util.ArrayList;

public class Checker implements Runnable {
	private Service service;

	public Checker() {
		// TODO Auto-generated constructor stub
	}
	
	public Checker(Service service) {
		this.service = service;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				// get all perishable products
				ArrayList<Product> productArray = service.reviewProducts(EnumProdType.PERISHABLE);
				// Decrement expiresIn property
				for(Product prod : productArray) {
					if(prod != null) {
						Perishable peri = (Perishable) prod;
						peri.setExpiresIn(peri.getExpiresIn()-1);
					}
				}
				System.out.println("\n\n...One day has passed. Days until expiry gets closer.\n"
				+ service.arrayToString(productArray));
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
