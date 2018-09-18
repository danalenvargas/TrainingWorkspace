package com.ibm.cs.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter which filters all in-bound requests
 * 
 * @author Dan Alejandro A. Vargas
 */
public class RequestFilter implements Filter {
	/**
	 * Default constructor.
	 */
	public RequestFilter() {
		
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * Filters in-bound requests for purposes of authentication and proper
	 * processing of page. <br>
	 * - Checks if user is logged in before proceeding, if not logged-in, request is
	 * redirected to login page. <br>
	 * - Redirects direct JSP page request to their corresponding servlets for proper
	 * processing.
	 * 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		HttpSession session = req.getSession();
		boolean isLoggedIn = (session.getAttribute("user") != null);

		if (!isLoggedIn && !uri.endsWith("login.jsp") && !uri.endsWith("Login")) {
			res.sendRedirect("Login");
		} else if (uri.contains("usermanagement.jsp")) {
			res.sendRedirect("UserManagement");
		} else if (uri.contains("productmanagement.jsp")) {
			res.sendRedirect("ProductManagement");
		} else if (uri.contains("userprofile.jsp")) {
			res.sendRedirect("Profile");
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
