package com.clinics.gwt.server.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class SpringGwtRemoteServiceServlet extends RemoteServiceServlet {

    @Override
    public void init() {

    }

    @Override
    public String processCall(String payload) throws SerializationException {
        try {
            Object handler = getBean(getThreadLocalRequest());
            RPCRequest rpcRequest = RPC.decodeRequest(payload, handler
                    .getClass(), this);
            onAfterRequestDeserialized(rpcRequest);

            return RPC.invokeAndEncodeResponse(handler, rpcRequest.getMethod(),
                    rpcRequest.getParameters(), rpcRequest
                            .getSerializationPolicy());
        } catch (IncompatibleRemoteServiceException ex) {
            log(
                    "An IncompatibleRemoteServiceException was thrown while processing this call.",
                    ex);
            return RPC.encodeResponseForFailure(null, ex);
        }
    }

    /**
     * Determine Spring bean to handle request based on request URL, e.g. a
     * request ending in /myService will be handled by bean with name
     * "myService".
     * 
     * @param request
     * @return handler bean
     */
    protected Object getBean(HttpServletRequest request) {
        String service = getService(request);
        Object bean = getBean(service);
        if (!(bean instanceof RemoteService)) {
            throw new IllegalArgumentException(
                    "Spring bean is not a GWT RemoteService: " + service + " ("
                            + bean + ")");
        }

        return bean;
    }

    /**
     * Parse the service name from the request URL.
     * 
     * @param request
     * @return bean name
     */
    protected String getService(HttpServletRequest request) {
        String url = request.getRequestURI();

        String publicService = "public/";
        String secureService = "secured/";
        int idx = -1;
        String bean = null;
        if ((idx = url.lastIndexOf(secureService)) > -1) {
            bean = url.substring(idx + secureService.length());
        } else if ((idx = url.lastIndexOf(publicService)) > -1) {
            bean = url.substring(idx + publicService.length());
        }

        if (bean == null) {
            return "";
        }

        return bean;
    }

    /**
     * Look up a spring bean with the specified name in the current web
     * application context.
     * 
     * @param name
     *            bean name
     * @return the bean
     */
    protected Object getBean(String name) {
        WebApplicationContext applicationContext = WebApplicationContextUtils
                .getWebApplicationContext(getServletContext());
        if (applicationContext == null) {
            throw new IllegalStateException(
                    "No Spring web application context found");
        }
        if (!applicationContext.containsBean(name)) {
            {
                throw new IllegalArgumentException("Spring bean not found: "
                        + name);
            }
        }
        return applicationContext.getBean(name);
    }
}