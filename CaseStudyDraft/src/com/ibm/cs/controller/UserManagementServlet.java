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
 * Servlet implementation class UserManagementServlet
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			String jsonString = new Gson().toJson(user);
			response.setContentType("application/json");
            response.getWriter().write(jsonString);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		int userId;
		String username, password, userType;
		boolean canCreate, canUpdate, canDelete;
		
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
		
		// POST-REDIRECT-GET pattern to avoid form resubmission
		response.sendRedirect("UserManagement?action=showpage");
	}
}
