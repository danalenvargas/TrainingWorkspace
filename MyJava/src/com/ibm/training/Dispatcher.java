package com.ibm.training;

import java.util.ArrayList;

public class Dispatcher implements Runnable {
	private Service service;
	
	public Dispatcher() {
		// TODO Auto-generated constructor stub
	}
	
	public Dispatcher(Service service) {
		this.service = service;
	}

	@Override
	public void run(){
		while(true) {
			try {
				Thread.sleep(2000);
				ArrayList<Product> productArray = service.reviewProducts(EnumProdType.PERISHABLE);
				boolean foundExpired = false;
				String expiredProductsString = "";
				
				for(Product prod : productArray) {
					Perishable peri = (Perishable) prod;
					if(peri.getExpiresIn() < 1) {
						if(!foundExpired) {
							foundExpired = true;
						} else {
							expiredProductsString += ", ";
						}
						expiredProductsString += peri.getName();
					}
				}
				
				if(foundExpired) throw new ExpiredProductException("Dispatcher found an expired product: " + expiredProductsString);
				
			} catch (ExpiredProductException e) {
				System.out.println(e.getMessage());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
}
