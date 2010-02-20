package com.clinics.gwt.server.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestUtils {

    private RequestUtils() {
    }

    public static HttpServletRequest getRequest() {

        ServletRequestAttributes servletRequest = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = servletRequest.getRequest();
        return request;
    }

    public static HttpSession getSession() {
        HttpServletRequest request = getRequest();
        return request.getSession();
    }
}
