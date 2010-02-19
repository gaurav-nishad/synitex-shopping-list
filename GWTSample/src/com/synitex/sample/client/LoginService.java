package com.synitex.sample.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.synitex.sample.shared.UserModel;

@RemoteServiceRelativePath("unsecured/login")
public interface LoginService extends RemoteService {

	UserModel login(String name, String pass);

	UserModel isLogged();

	/**
	 * Performs server logout.
	 */
	void logout();

}
