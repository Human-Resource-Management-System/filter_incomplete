
package com.nkxgen.spring.orm.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.nkxgen.spring.orm.model.login;
import com.nkxgen.spring.orm.service.UserService;

@Component
@Order(1)
public class AuthenticationFilter extends GenericFilterBean {

	@Autowired
	private UserService userService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		// Check if the request is for the login page or already authenticated
		String requestURI = httpRequest.getRequestURI();
		if (requestURI.equals(httpRequest.getContextPath() + "/login") || isAuthenticated(httpRequest)) {
			// Allow the request to proceed to the next filter or controller
			chain.doFilter(request, response);
		} else {
			// Redirect to the login page if not authenticated
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
		}
	}

	private boolean isAuthenticated(HttpServletRequest request) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// Check if the email and password combination is valid by querying the database through ORM
		login user = userService.authenticate(email, password);
		if (user != null) {
			// Store the authenticated user in the session
			request.getSession().setAttribute("user", user);
			return true;
		}
		return false;
	}

	@Override
	public void destroy() {
		// Cleanup resources, if any
	}

}
