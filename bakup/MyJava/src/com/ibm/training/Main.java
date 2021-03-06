package com.ibm.training;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

	public static void main(String[] args) {
		Service productService = new Service();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		try {
			Perishable veggies = new Perishable("veggies", dateFormat.parse("11/17/2018"));
			Perishable meats = new Perishable("meats", dateFormat.parse("09/02/2018"));
			Perishable milk = new Perishable("milk", dateFormat.parse("10/15/2018"));
			NonPerishable toys = new NonPerishable("toys", true);
			NonPerishable hammer = new NonPerishable("hammer", true);
			NonPerishable diaper = new NonPerishable("diaper", false);
			NonPerishable mugs = new NonPerishable("mugs", false);

			productService.addProduct(veggies);
			productService.addProduct(milk);
			productService.addProduct(toys);
			productService.addProduct(hammer);
			productService.addProduct(mugs);
			productService.addProduct(meats);
			productService.addProduct(diaper);

			System.out.println(productService.getProduct(1));
			System.out.println(productService.reviewProducts());
			System.out.println(productService.reviewProducts(EnumProdType.PERISHABLE));
			System.out.println(productService.reviewProducts(EnumProdType.NONPERISHABLE));
			
			Checker checker = new Checker(productService);
			checker.start();
		} catch (ParseException e) {
			System.out.println("Error in Parsing Date");
			e.printStackTrace();
		}
	}
}
