package com.clinics.gwt.server.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clinics.gwt.shared.LoginStatuses;
import com.clinics.gwt.shared.model.UserModel;

public class LoginFilter implements Filter {

    public static final String USER_MODEL_ID_IN_SESSION = "user-model-id";

    public void doFilter(ServletRequest req, ServletResponse resp,
            FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        UserModel user = (UserModel) request.getSession().getAttribute(
                USER_MODEL_ID_IN_SESSION);

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
