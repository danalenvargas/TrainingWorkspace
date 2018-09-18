package com.ibm.cs.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ibm.cs.model.User;
import com.ibm.cs.service.UserManagementService;

/**
 * Servlet controller for all user account-related requests. <br>
 * Handles validation and CRUD requests for User accounts<br>
 * 
 * @author Dan Alejandro A. Vargas
 * @see UserManagementService
 * @see User
 */
public class UserManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManagementService userService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManagementServlet() {
        super();
        userService = new UserManagementService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Handles GET requests, purpose of request are determined by the request's
	 * 'action' parameter. <br>
	 * Actions handled: <br>
	 * - showing usermanagement.jsp page <br>
	 * - getting User information <br>
	 * - validating uniqueness of username when adding / editing user accounts
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsonString;
		String action = request.getParameter("action");
		
		if(action==null) {
			action = "showpage";
		}
		
		switch(action){
		case "showpage":
			ArrayList<User> userList = userService.getStandardUsers();
			request.setAttribute("userList", userList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("usermanagement.jsp");
			dispatcher.forward(request, response);
			break;
			
		case "getUser":
			int userId = Integer.parseInt(request.getParameter("userId"));
			User user = userService.getUser(userId);
			jsonString = new Gson().toJson(user);
			response.setContentType("application/json");
            response.getWriter().write(jsonString);
            break;
            
		case "validateUsername":
			String username = request.getParameter("username");
			boolean isUnique = userService.validateUsername(username);
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
	 * - adding, editing, deleting user accounts <br>
	 * - changing user passwords 
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId;
		String username, password, userType;
		boolean canCreate, canUpdate, canDelete;
		
		String action = request.getParameter("action");
		
		switch(action){
		case "addUser":
			username = request.getParameter("username");
			password = request.getParameter("password");
			userType = "user";
			canCreate = request.getParameterMap().containsKey("canCreate");
			canUpdate = request.getParameterMap().containsKey("canUpdate");
			canDelete = request.getParameterMap().containsKey("canDelete");
			userService.addUser(username, password, userType, canCreate, canUpdate, canDelete);
			break;
			
		case "editUser":
			userId = Integer.parseInt(request.getParameter("userId"));
			username = request.getParameter("username");
			canCreate = request.getParameterMap().containsKey("canCreate");
			canUpdate = request.getParameterMap().containsKey("canUpdate");
			canDelete = request.getParameterMap().containsKey("canDelete");
			userService.editUser(userId, username, canCreate, canUpdate, canDelete);
			break;
			
		case "deleteUser":
			userId = Integer.parseInt(request.getParameter("userId"));
			userService.deleteUser(userId);
			break;
			
		case "changePassword":
			userId = Integer.parseInt(request.getParameter("userId"));
			password = request.getParameter("password");
			userService.changePassword(userId, password);
			break;
		}
		
		response.sendRedirect("UserManagement?action=showpage");
	}
}
