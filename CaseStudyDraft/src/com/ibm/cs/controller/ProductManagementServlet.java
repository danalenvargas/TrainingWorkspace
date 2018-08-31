package com.ibm.cs.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String action = request.getParameter("action");
		if(action==null) {
			action = "showpage";
		}
		
		switch(action) {
		case "showpage":
			RequestDispatcher dispatcher = request.getRequestDispatcher("productmanagement.jsp");
			dispatcher.forward(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductManagementService service = new ProductManagementService();
		boolean isSuccessful;
		String action = request.getParameter("action");
		switch(action) {
		case "addCategory":
			String name = request.getParameter("name");
			String productType = request.getParameter("productType");
			boolean isPerishable = Boolean.valueOf(request.getParameter("perishable"));
			Boolean isRecyclable = null;
			if(!isPerishable) {
				isRecyclable = Boolean.valueOf(request.getParameter("perishable"));
			}
			isSuccessful = service.addCategory(name, productType, isPerishable, isRecyclable);
			System.out.println("SUCCESS: " + isSuccessful);
			RequestDispatcher dispatcher = request.getRequestDispatcher("productmanagement.jsp");
			dispatcher.forward(request, response);
			break;
		}
	}

}
