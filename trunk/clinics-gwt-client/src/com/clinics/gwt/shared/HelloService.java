package com.clinics.gwt.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("springGwtService/hello")
public interface HelloService extends RemoteService {

    String sayHello();

}
