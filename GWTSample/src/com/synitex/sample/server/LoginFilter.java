package com.synitex.sample.server;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.synitex.sample.shared.LoginStatuses;
import com.synitex.sample.shared.UserModel;

public class LoginFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		UserModel user = (UserModel) request.getSession().getAttribute(
				LoginServiceImpl.CURRENT_USER);

		if (user == null) {

			try {
				response.setContentType("text/plain");
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				String errorMsg = LoginStatuses.FAILED;
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

	}

	public void destroy() {

	}

}
