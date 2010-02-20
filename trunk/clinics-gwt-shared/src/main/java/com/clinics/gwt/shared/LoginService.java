package com.clinics.gwt.shared;

import com.clinics.gwt.shared.model.UserModel;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("springGwtServices/public/login")
public interface LoginService extends RemoteService {

    UserModel login(String name, String pass);

    UserModel isLogged();

    void logout();
}
