package com.synitex.sample.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.synitex.sample.shared.UserModel;

public interface LoginServiceAsync {

	/**
	 * Try to login user.
	 * 
	 * @param name
	 *            Name
	 * @param pass
	 *            Password
	 * @param callback
	 *            UserModel is login was OK, or null
	 */
	void login(String name, String pass, AsyncCallback<UserModel> callback);

	/**
	 * Checks if user is logged on the server.
	 * 
	 * @param callback
	 */
	void isLogged(AsyncCallback<UserModel> callback);

	/**
	 * Performs server logout.
	 * 
	 * @param callback
	 */
	void logout(AsyncCallback<Void> callback);

}
