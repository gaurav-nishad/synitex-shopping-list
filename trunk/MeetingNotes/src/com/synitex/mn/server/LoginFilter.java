package com.synitex.mn.server;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain filterChain) throws IOException, ServletException {

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user == null) {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;
			String loginUrl = userService.createLoginURL("/MeetingNotes.html");

			try {
				response.setContentType("text/plain");
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				String errorMsg = "access:" + loginUrl;
				try {
					response.getOutputStream().write(errorMsg.getBytes("UTF8"));
				} catch (IllegalStateException e) {
					// Handle the (unexpected) case where getWriter() was
					// previously used
					response.getWriter().write(errorMsg);
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		} else {

			filterChain.doFilter(req, resp);
		}

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}
