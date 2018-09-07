package com.ibm.cs.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ibm.cs.model.Category;
import com.ibm.cs.model.Product;
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
		if(action==null) {
			action = "showpage";
		}
		
		switch(action) {
		case "showpage":
			ArrayList<Category> categoryList = service.getCategoryList();
			request.setAttribute("categoryList", categoryList);
			ArrayList<Product> productList = service.getProductList();
			request.setAttribute("productList", productList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("productmanagement.jsp");
			dispatcher.forward(request, response);
			break;
		case "getCategoryDetails":
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			Category category = service.getCategory(categoryId);
			String jsonString = new Gson().toJson(category);
			System.out.println("GOT CATEGORY DETAILS:");
			System.out.println(category);
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
		int categoryId;
		String name, productType;
		boolean isPerishable;
		Boolean isRecyclable;
		String action = request.getParameter("action");
		
		switch(action) {
		case "addCategory":
			name = request.getParameter("name");
			productType = request.getParameter("productType");
			isPerishable = Boolean.valueOf(request.getParameter("perishable"));
			isRecyclable = null;
			if(!isPerishable) {
				isRecyclable = Boolean.valueOf(request.getParameter("recyclable"));
			}
			isSuccessful = service.addCategory(name, productType, isPerishable, isRecyclable);
			System.out.println("ADD CATEGORY SUCCESS: " + isSuccessful);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("productmanagement.jsp");
//			dispatcher.forward(request, response);
			response.sendRedirect("productmanagement.jsp");
			break;
		case "editCategory":
			categoryId = Integer.parseInt(request.getParameter("categoryId"));
			name = request.getParameter("name");
			productType = request.getParameter("productType");
			isPerishable = Boolean.valueOf(request.getParameter("perishable"));
			isRecyclable = null;
			if(!isPerishable) {
				isRecyclable = Boolean.valueOf(request.getParameter("recyclable"));
			}
			isSuccessful = service.editCategory(categoryId, name, productType, isPerishable, isRecyclable);
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
			String brand = request.getParameter("brand");
			String variant = request.getParameter("variant");
			String size = request.getParameter("size");
			String measurementUnit = request.getParameter("measurementUnit");
			String description = request.getParameter("description");
			String specialHandling = request.getParameter("specialHandling");
			double sellPrice = Double.parseDouble(request.getParameter("sellPrice"));
			String SKU = request.getParameter("SKU");
			isSuccessful = service.addProduct(categoryId, brand, variant, size, measurementUnit, description, specialHandling, sellPrice, SKU);
			System.out.println("ADD PRODUCT SUCCESS: " + isSuccessful);
			response.sendRedirect("productmanagement.jsp");
		}
	}

}
