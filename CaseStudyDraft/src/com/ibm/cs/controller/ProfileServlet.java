package com.ibm.cs.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.cs.model.User;
import com.ibm.cs.service.UserManagementService;


/**
 * Servlet implementation class ProfileServlet
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("userprofile.jsp");
			dispatcher.forward(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		int userId;
		String username, password;
		boolean isSuccessful;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		switch(action){
		case "editUser":
			userId = user.getUserId();
			username = request.getParameter("username");
			isSuccessful = userService.editProfile(userId, username);
			if(isSuccessful) {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("userprofile.jsp");
		dispatcher.forward(request, response);
	}

}
