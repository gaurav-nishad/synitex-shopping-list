package com.clinics.gwt.server;

import com.clinics.gwt.server.util.LoginFilter;
import com.clinics.gwt.server.util.RequestUtils;
import com.clinics.gwt.shared.LoginService;
import com.clinics.gwt.shared.model.UserModel;

public class LoginServiceImpl implements LoginService {

    @Override
    public UserModel login(String name, String pass) {

        if (name.startsWith("a")) {
            UserModel user = new UserModel(name);
            RequestUtils.getSession().setAttribute(
                    LoginFilter.USER_MODEL_ID_IN_SESSION, user);
            return user;
        } else {
            RequestUtils.getSession().setAttribute(
                    LoginFilter.USER_MODEL_ID_IN_SESSION, null);
            return null;
        }
    }

    public UserModel isLogged() {
        return (UserModel) RequestUtils.getSession().getAttribute(
                LoginFilter.USER_MODEL_ID_IN_SESSION);
    }

    public void logout() {
        RequestUtils.getSession().setAttribute(
                LoginFilter.USER_MODEL_ID_IN_SESSION, null);
    }

}
