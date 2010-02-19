package com.synitex.sample.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.synitex.sample.shared.UserModel;

public class UserManager implements AsyncCallback<UserModel> {

	private static UserManager instance = new UserManager();
	private LoginServiceAsync loginService = GWT.create(LoginService.class);

	private UserManager() {
	}

	private UserModel currentUser;
	private AsyncCallback<UserModel> parentCallback;

	public static UserManager getInstance() {
		return instance;
	}

	public UserModel getCurrentUser() {
		return currentUser;
	}

	private void setCurrentUser(UserModel newUser) {
		this.currentUser = newUser;
	}

	public void login(String name, String pass,
			AsyncCallback<UserModel> callback) {
		this.parentCallback = callback;
		loginService.login(name, pass, this);
	}

	public void logout(AsyncCallback<Void> callback) {
		loginService.logout(new LogoutCallback(callback));
	}

	public void checkIsLogged(AsyncCallback<UserModel> callback) {
		this.parentCallback = callback;
		loginService.isLogged(this);
	}

	public void onFailure(Throwable caught) {
		parentCallback.onFailure(caught);
	}

	public void onSuccess(UserModel result) {
		setCurrentUser(result);
		parentCallback.onSuccess(result);
	}

	private class LogoutCallback implements AsyncCallback<Void> {

		private AsyncCallback<Void> parentCallback;

		public LogoutCallback(AsyncCallback<Void> parentCallback) {
			this.parentCallback = parentCallback;
		}

		public void onFailure(Throwable caught) {
			parentCallback.onFailure(caught);
		}

		public void onSuccess(Void result) {
			UserManager.getInstance().setCurrentUser(null);
			parentCallback.onSuccess(result);
		}

	}
}
