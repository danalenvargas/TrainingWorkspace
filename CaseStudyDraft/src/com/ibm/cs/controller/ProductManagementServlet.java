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
import com.google.gson.GsonBuilder;
import com.ibm.cs.model.Batch;
import com.ibm.cs.model.Category;
import com.ibm.cs.model.Item;
import com.ibm.cs.model.Product;
import com.ibm.cs.service.ProductManagementService;

/**
 * Servlet controller for all inventory-related requests. <br>
 * Handles validation and CRUD requests for Category, Product, Batch, and Item
 * <br>
 * 
 * @author Dan Alejandro A. Vargas
 * @see ProductManagementService
 * @see Category
 * @see Product
 * @see Batch
 * @see Item
 */
public class ProductManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductManagementService service;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductManagementServlet() {
		super();
		service = new ProductManagementService();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Handles GET requests, purpose of request are determined by the request's
	 * 'action' parameter. <br>
	 * Actions handled: <br>
	 * - showing productmanagement.jsp page <br>
	 * - getting information of category, product, batch, item <br>
	 * - validating uniqueness of category name and product SKU
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String jsonString;
		boolean isUnique;

		if (action == null) {
			action = "showpage";
		}

		switch (action) {
		case "showpage":
			ArrayList<Category> categoryList = service.getInventory();
			request.setAttribute("categoryList", categoryList);
			if (!request.getParameterMap().containsKey("selectedTab")) {
				request.setAttribute("selectedTab", "item");
			} else {
				request.setAttribute("selectedTab", request.getParameter("selectedTab"));
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("productmanagement.jsp");
			dispatcher.forward(request, response);
			break;

		case "getCategoryDetails":
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			Category category = service.getCategory(categoryId);
			jsonString = new Gson().toJson(category);
			response.setContentType("application/json");
			response.getWriter().write(jsonString);
			break;

		case "getProductDetails":
			int productId = Integer.parseInt(request.getParameter("productId"));
			Product product = service.getProduct(productId);
			jsonString = new Gson().toJson(product);
			response.setContentType("application/json");
			response.getWriter().write(jsonString);
			break;

		case "getBatchDetails":
			int batchId = Integer.parseInt(request.getParameter("batchId"));
			Batch batch = service.getBatch(batchId);
			jsonString = new Gson().toJson(batch);
			response.getWriter().write(jsonString);
			break;

		case "getItemDetails":
			int[] itemIds = new Gson().fromJson(request.getParameter("itemIds"), int[].class);
			ArrayList<Item> itemList = service.getItems(itemIds);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			jsonString = gson.toJson(itemList);
			response.getWriter().write(jsonString);
			break;

		case "validateCategoryName":
			String categoryName = request.getParameter("categoryName");
			isUnique = service.validateCategoryName(categoryName);
			jsonString = new Gson().toJson(isUnique);
			response.setContentType("application/json");
			response.getWriter().write(jsonString);
			break;

		case "validateProductSKU":
			String SKU = request.getParameter("SKU");
			isUnique = service.validateProductSKU(SKU);
			jsonString = new Gson().toJson(isUnique);
			response.setContentType("application/json");
			response.getWriter().write(jsonString);
			break;
		}
	}

	/**
	 * Handles POST requests, purpose of request are determined by the request's
	 * 'action' parameter. <br>
	 * Actions handled: <br>
	 * - add, edit, delete operations for category, product, batch, item
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// RequestDispatcher dispatcher;
		boolean isSuccessful;
		int[] itemIds;
		int categoryId, productId, batchId, amount;
		double sellPrice, purchasePrice;
		boolean isPerishable;
		String categoryName, categoryType, brand, variant, size, measurementUnit, specialHandling, description, SKU,
				comments, supplier, selectedTab = "";
		Boolean isRecyclable;
		Date manufactureDate, expirationDate;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		String action = request.getParameter("action");

		switch (action) {
		case "addCategory":
			categoryName = request.getParameter("categoryName");
			categoryType = request.getParameter("categoryType");
			isPerishable = Boolean.valueOf(request.getParameter("perishable"));
			isRecyclable = null;
			if (!isPerishable) {
				isRecyclable = Boolean.valueOf(request.getParameter("recyclable"));
			}
			isSuccessful = service.addCategory(categoryName, categoryType, isPerishable, isRecyclable);
			selectedTab = "category";
			break;

		case "editCategory":
			categoryId = Integer.parseInt(request.getParameter("categoryId"));
			categoryName = request.getParameter("categoryName");
			categoryType = request.getParameter("categoryType");
			isPerishable = Boolean.valueOf(request.getParameter("perishable"));
			isRecyclable = null;
			if (!isPerishable) {
				isRecyclable = Boolean.valueOf(request.getParameter("recyclable"));
			}
			isSuccessful = service.editCategory(categoryId, categoryName, categoryType, isPerishable, isRecyclable);
			selectedTab = "category";
			break;

		case "deleteCategory":
			categoryId = Integer.parseInt(request.getParameter("categoryId"));
			isSuccessful = service.deleteCategory(categoryId);
			selectedTab = "category";
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
			isSuccessful = service.addProduct(categoryId, brand, variant, size, measurementUnit, description,
					specialHandling, sellPrice, SKU);
			selectedTab = "product";
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
			isSuccessful = service.editProduct(categoryId, productId, brand, variant, size, measurementUnit,
					description, specialHandling, sellPrice, SKU);
			selectedTab = "product";
			break;

		case "deleteProduct":
			productId = Integer.parseInt(request.getParameter("productId"));
			isSuccessful = service.deleteProduct(productId);
			selectedTab = "product";
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
				isSuccessful = service.inputBatch(productId, amount, comments, supplier, manufactureDate,
						expirationDate, purchasePrice);
				selectedTab = "batch";
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
			selectedTab = "batch";
			break;

		case "deleteBatch":
			batchId = Integer.parseInt(request.getParameter("batchId"));
			isSuccessful = service.deleteBatch(batchId);
			selectedTab = "batch";
			break;

		case "editItems":
			try {
				itemIds = new Gson().fromJson(request.getParameter("itemIds"), int[].class);
				manufactureDate = format.parse(request.getParameter("manufactureDate"));
				expirationDate = format.parse(request.getParameter("expirationDate"));
				purchasePrice = Double.parseDouble(request.getParameter("purchasePrice"));
				isSuccessful = service.editItems(itemIds, manufactureDate, expirationDate, purchasePrice);
				selectedTab = "item";
			} catch (ParseException e) {
				System.out.println("Error parsing date");
				e.printStackTrace();
			}
			break;

		case "deleteItems":
			itemIds = new Gson().fromJson(request.getParameter("itemIds"), int[].class);
			isSuccessful = service.deleteItems(itemIds);
			selectedTab = "item";
			break;
		}

		// POST-REDIRECT-GET pattern to avoid form re-submission
		response.sendRedirect("ProductManagement?action=showpage&selectedTab=" + selectedTab);
		// dispatcher=request.getRequestDispatcher("ProductManagement?action=showpage?selectedTab="+selectedTab);
		// dispatcher.forward(request, response);
	}

}
