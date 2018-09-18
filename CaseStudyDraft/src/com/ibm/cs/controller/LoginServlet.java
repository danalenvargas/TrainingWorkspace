package com.ibm.cs.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.cs.model.User;
import com.ibm.cs.service.LoginService;

/**
 * Servlet controller for logging in and out of Users from the web application.
 * 
 * @author Dan Alejandro A. Vargas
 * @see User
 * @see LoginService
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Handles GET requests, purpose of request are determined by the request's
	 * 'action' parameter. <br>
	 * Actions handled: logout
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			action = "";
		}
		
		try {
			switch (action) {
			case "logout":
				HttpSession session = request.getSession(false);
				session.invalidate();
				response.sendRedirect("login.jsp");
				break;
			default:
				response.sendRedirect("login.jsp");
				break;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			response.sendRedirect("errorpage.jsp");
		}
	}

	/**
	 * Handles POST requests, purpose of request are determined by the request's
	 * 'action' parameter. <br>
	 * Actions handled: login
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * @see User
	 * @see LoginService
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username;
		String password;
		HttpSession session;

		String action = request.getParameter("action");

		if (action == null) {
			action = "";
		}

		try {
			LoginService loginService = new LoginService();
			switch (action) {
			case "login":
				username = request.getParameter("username");
				password = request.getParameter("password");
				session = request.getSession();
				if (loginService.isValidUser(username, password)) {
					User user = loginService.getUser();
					session.setAttribute("user", user);
	
					RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
					dispatcher.forward(request, response);
				} else {
					response.sendRedirect("login.jsp");
				}
				break;
			default:
				response.sendRedirect("login.jsp");
				break;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			response.sendRedirect("errorpage.jsp");
		}
	}
}
