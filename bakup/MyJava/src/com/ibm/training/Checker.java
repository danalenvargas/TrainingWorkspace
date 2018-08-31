package com.ibm.training;

public class Checker extends Thread {
	Service service;

	public Checker() {
		// TODO Auto-generated constructor stub
	}
	
	public Checker(Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Product[] productArray = service.getProductArray();
				for(int i=0; i<productArray.length; i++) {
					if(productArray[i] != null && productArray[i] instanceof Perishable) {
						Perishable peri = (Perishable) productArray[i];
						peri.setExpiresIn(peri.getExpiresIn()-1);
					}
				}
				System.out.println("\n\n...One day has passed. Days until expiry gets closer.");
				System.out.println(service.getExpiryCountdown());
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
