package com.ibm.training;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

	public static void main(String[] args) {
		Main prog = new Main();
		prog.execute();
	}
	
	public void execute() {
		Service service = new Service();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		try {
			Perishable veggies = new Perishable("veggies", dateFormat.parse("11/17/2018"));
			Perishable meats = new Perishable("meats", dateFormat.parse("09/02/2018"));
			Perishable milk = new Perishable("milk", dateFormat.parse("09/03/2018"));
			NonPerishable toys = new NonPerishable("toys", true);
			NonPerishable hammer = new NonPerishable("hammer", true);
			NonPerishable diaper = new NonPerishable("diaper", false);
			NonPerishable mugs = new NonPerishable("mugs", false);

			service.addProduct(veggies);
			service.addProduct(milk);
			service.addProduct(toys);
			service.addProduct(hammer);
			service.addProduct(mugs);
			service.addProduct(meats);
			service.addProduct(diaper);

			System.out.println("Product at index " + 1 + " is:\n " + service.getProduct(1));
			System.out.println("List of all products:\n" + service.arrayToString(service.reviewProducts()));
			System.out.println("List of all Perishable products:\n" + service.arrayToString(service.reviewProducts(EnumProdType.PERISHABLE)));
			System.out.println("List of all NonPerishable products:\n" + service.arrayToString(service.reviewProducts(EnumProdType.NONPERISHABLE)));
			
			Thread checker = new Thread(new Checker(service));
			checker.start();
			
			Thread dispatcher = new Thread(new Dispatcher(service));
			dispatcher.start();
		} catch (ParseException e) {
			System.out.println("Error in Parsing Date");
			e.printStackTrace();
		}
	}
}
