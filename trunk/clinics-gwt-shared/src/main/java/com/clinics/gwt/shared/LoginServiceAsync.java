package com.clinics.gwt.shared;

import com.clinics.gwt.shared.model.UserModel;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {

    void login(String name, String pass, AsyncCallback<UserModel> callback);

    void isLogged(AsyncCallback<UserModel> callback);

    void logout(AsyncCallback<Object> callback);

}
