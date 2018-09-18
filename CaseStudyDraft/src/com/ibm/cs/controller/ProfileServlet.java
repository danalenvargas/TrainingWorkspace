package com.ibm.cs.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.ibm.cs.model.User;
import com.ibm.cs.service.UserManagementService;

/**
 * Servlet controller for all profile-related requests. <br>
 * Handles requests for editing own user profile <br>
 * 
 * @author Dan Alejandro A. Vargas
 * @see UserManagementService
 * @see User
 */
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManagementService userService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileServlet() {
		super();
		userService = new UserManagementService();
	}

	/**
	 * Handles GET requests, purpose of request are determined by the request's
	 * 'action' parameter. <br>
	 * Actions handled: <br>
	 * - showing userprofile.jsp page <br>
	 * - validating uniqueness of username when user is trying to edit his/her
	 * profile
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String jsonString;
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");

		if (action == null) {
			action = "showpage";
		}

		try {
			switch (action) {
			case "showpage":
				RequestDispatcher dispatcher = request.getRequestDispatcher("userprofile.jsp");
				dispatcher.forward(request, response);
				break;
				
			case "validateUsername":
				String username = request.getParameter("username");
				boolean isUnique;
				if (user.getUsername().equals(username)) {
					isUnique = true;
				} else {
					isUnique = userService.validateUsername(username);
				}
				jsonString = new Gson().toJson(isUnique);
				response.setContentType("application/json");
				response.getWriter().write(jsonString);
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
	 * Actions handled: <br>
	 * - editing own profile <br>
	 * - changing own password
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		int userId;
		String username, password;
		boolean isSuccessful;
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");

		try {
			switch (action) {
			case "editUser":
				userId = user.getUserId();
				username = request.getParameter("username");
				isSuccessful = userService.editProfile(userId, username);
				if (isSuccessful) {
					user.setUsername(username);
					session.setAttribute("user", user);
				}
				break;
				
			case "changePassword":
				userId = user.getUserId();
				password = request.getParameter("password");
				userService.changePassword(userId, password);
				break;
			}
			
			response.sendRedirect("Profile?action=showpage");
			// RequestDispatcher dispatcher=request.getRequestDispatcher("userprofile.jsp");
			// dispatcher.forward(request, response);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			response.sendRedirect("errorpage.jsp");
		}
	}

}
