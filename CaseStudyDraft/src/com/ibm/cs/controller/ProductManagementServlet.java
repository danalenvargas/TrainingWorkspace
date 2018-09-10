package com.ibm.cs.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ibm.cs.model.Batch;
import com.ibm.cs.model.Category;
import com.ibm.cs.model.Product;
//import com.ibm.cs.model.Product;
import com.ibm.cs.service.ProductManagementService;

/**
 * Servlet implementation class ProductManagementServlet
 */
public class ProductManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductManagementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductManagementService service = new ProductManagementService();
		String action = request.getParameter("action");
		String jsonString;
		if(action==null) {
			action = "showpage";
		}
		
		switch(action) {
		case "showpage":
			ArrayList<Category> categoryList = service.getInventory();
			request.setAttribute("categoryList", categoryList);
//			ArrayList<Product> productList = service.getProductList();
//			request.setAttribute("productList", productList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("productmanagement.jsp");
			dispatcher.forward(request, response);
			break;
		case "getCategoryDetails":
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			Category category = service.getCategory(categoryId);
			jsonString = new Gson().toJson(category);
			System.out.println("GOT CATEGORY DETAILS:");
			System.out.println(category);
			System.out.println(jsonString);
			response.setContentType("application/json");
            response.getWriter().write(jsonString);
			break;
		case "getProductDetails":
			int productId = Integer.parseInt(request.getParameter("productId"));
			Product product = service.getProduct(productId);
			jsonString = new Gson().toJson(product);
			System.out.println("GOT PRODUCT DETAILS:");
			System.out.println(product);
			System.out.println(jsonString);
			response.setContentType("application/json");
            response.getWriter().write(jsonString);
			break;
		case "getBatchDetails":
			int batchId = Integer.parseInt(request.getParameter("batchId"));
			Batch batch = service.getBatch(batchId);
			jsonString = new Gson().toJson(batch);
			System.out.println("GOT BATCH DETAILS:");
			System.out.println(batch);
			System.out.println(jsonString);
			response.setContentType("application/json");
            response.getWriter().write(jsonString);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductManagementService service = new ProductManagementService();
		boolean isSuccessful;
		int categoryId, productId, batchId, amount;
		String categoryName, categoryType, brand, variant, size, measurementUnit, specialHandling, description, SKU, comments, supplier;
		double sellPrice, purchasePrice;
		Date manufactureDate, expirationDate;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		boolean isPerishable;
		Boolean isRecyclable;
		
		String action = request.getParameter("action");
		
		switch(action) {
		case "addCategory":
			categoryName = request.getParameter("categoryName");
			categoryType = request.getParameter("categoryType");
			isPerishable = Boolean.valueOf(request.getParameter("perishable"));
			isRecyclable = null;
			if(!isPerishable) {
				isRecyclable = Boolean.valueOf(request.getParameter("recyclable"));
			}
			isSuccessful = service.addCategory(categoryName, categoryType, isPerishable, isRecyclable);
			System.out.println("ADD CATEGORY SUCCESS: " + isSuccessful);
			response.sendRedirect("productmanagement.jsp");
			break;
		case "editCategory":
			categoryId = Integer.parseInt(request.getParameter("categoryId"));
			categoryName = request.getParameter("categoryName");
			categoryType = request.getParameter("categoryType");
			isPerishable = Boolean.valueOf(request.getParameter("perishable"));
			isRecyclable = null;
			if(!isPerishable) {
				isRecyclable = Boolean.valueOf(request.getParameter("recyclable"));
			}
			isSuccessful = service.editCategory(categoryId, categoryName, categoryType, isPerishable, isRecyclable);
			System.out.println("EDIT CATEGORY SUCCESS: " + isSuccessful);
			response.sendRedirect("productmanagement.jsp");
			break;
		case "deleteCategory":
			categoryId = Integer.parseInt(request.getParameter("categoryId"));
			isSuccessful = service.deleteCategory(categoryId);
			System.out.println("DELETE CATEGORY SUCCESS: " + isSuccessful);
			response.sendRedirect("productmanagement.jsp");
			break;
		case "addProduct":
			categoryId = Integer.parseInt(request.getParameter("categoryId"));
			brand = request.getParameter("brand");
			variant = request.getParameter("variant");
			size = request.getParameter("size");
			measurementUnit = request.getParameter("measurementUnit");
			description = request.getParameter("description");
			specialHandling = request.getParameter("specialHandling");
			sellPrice = Double.parseDouble(request.getParameter("sellPrice"));
			SKU = request.getParameter("SKU");
			isSuccessful = service.addProduct(categoryId, brand, variant, size, measurementUnit, description, specialHandling, sellPrice, SKU);
			System.out.println("ADD PRODUCT SUCCESS: " + isSuccessful);
			response.sendRedirect("productmanagement.jsp");
			break;
		case "editProduct":
			productId = Integer.parseInt(request.getParameter("productId"));
			categoryId = Integer.parseInt(request.getParameter("categoryId"));
			brand = request.getParameter("brand");
			variant = request.getParameter("variant");
			size = request.getParameter("size");
			measurementUnit = request.getParameter("measurementUnit");
			description = request.getParameter("description");
			specialHandling = request.getParameter("specialHandling");
			sellPrice = Double.parseDouble(request.getParameter("sellPrice"));
			SKU = request.getParameter("SKU");
			isSuccessful = service.editProduct(categoryId, productId, brand, variant, size, measurementUnit, description, specialHandling, sellPrice, SKU);
			System.out.println("EDIT PRODUCT SUCCESS: " + isSuccessful);
			response.sendRedirect("productmanagement.jsp");
			break;
		case "deleteProduct":
			productId = Integer.parseInt(request.getParameter("productId"));
			isSuccessful = service.deleteProduct(productId);
			System.out.println("DELETE PRODUCT SUCCESS: " + isSuccessful);
			response.sendRedirect("productmanagement.jsp");
			break;
		case "inputBatch":
			try {
				productId = Integer.parseInt(request.getParameter("productId"));
				amount = Integer.parseInt(request.getParameter("amount"));
				comments = request.getParameter("comments");
				supplier = request.getParameter("supplier");
				manufactureDate = format.parse(request.getParameter("manufactureDate"));
				expirationDate = format.parse(request.getParameter("expirationDate"));
				purchasePrice = Double.parseDouble(request.getParameter("purchasePrice"));
				isSuccessful = service.inputBatch(productId, amount, comments, supplier, manufactureDate, expirationDate, purchasePrice);
				System.out.println("INPUT BATCH SUCCESS: " + isSuccessful);
				response.sendRedirect("productmanagement.jsp");
			} catch (ParseException e) {
				System.out.println("Error parsing date");
				e.printStackTrace();
			}
			break;
		case "editBatch":
			batchId = Integer.parseInt(request.getParameter("batchId"));
			productId = Integer.parseInt(request.getParameter("productId"));
			amount = Integer.parseInt(request.getParameter("amount"));
			comments = request.getParameter("comments");
			supplier = request.getParameter("supplier");
			isSuccessful = service.editBatch(batchId, productId, amount, comments, supplier);
			System.out.println("EDIT BATCH SUCCESS: " + isSuccessful);
			response.sendRedirect("productmanagement.jsp");
			break;
		case "deleteBatch":
			batchId = Integer.parseInt(request.getParameter("batchId"));
			isSuccessful = service.deleteBatch(batchId);
			System.out.println("DELETE BATCH SUCCESS: " + isSuccessful);
			response.sendRedirect("productmanagement.jsp");
			break;
		}
	}

}
