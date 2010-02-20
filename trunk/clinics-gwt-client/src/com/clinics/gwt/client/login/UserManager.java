package com.clinics.gwt.client.login;

import com.clinics.gwt.shared.LoginService;
import com.clinics.gwt.shared.LoginServiceAsync;
import com.clinics.gwt.shared.model.UserModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

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

    public void logout(AsyncCallback<Object> callback) {
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

    private class LogoutCallback implements AsyncCallback<Object> {

        private AsyncCallback<Object> parentCallback;

        public LogoutCallback(AsyncCallback<Object> parentCallback) {
            this.parentCallback = parentCallback;
        }

        public void onFailure(Throwable caught) {
            parentCallback.onFailure(caught);
        }

        public void onSuccess(Object result) {
            UserManager.getInstance().setCurrentUser(null);
            parentCallback.onSuccess(result);
        }

    }
}
