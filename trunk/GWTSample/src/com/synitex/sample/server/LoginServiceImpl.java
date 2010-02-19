package com.synitex.sample.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.synitex.sample.client.LoginService;
import com.synitex.sample.shared.UserModel;

public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {
	private static final long serialVersionUID = 1L;

	public static final String CURRENT_USER = "currentUser";

	public UserModel login(String name, String pass) {

		if (name.startsWith("a")) {
			UserModel user = new UserModel(name);
			getThreadLocalRequest().getSession().setAttribute(CURRENT_USER,
					user);
			return user;
		} else {
			getThreadLocalRequest().getSession().setAttribute(CURRENT_USER,
					null);
			return null;
		}

	}

	public UserModel isLogged() {
		return (UserModel) getThreadLocalRequest().getSession().getAttribute(
				CURRENT_USER);
	}

	public void logout() {
		getThreadLocalRequest().getSession().setAttribute(CURRENT_USER, null);
	}
}
