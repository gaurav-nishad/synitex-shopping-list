package com.clinics.gwt.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface HelloServiceAsync {

    void sayHello(AsyncCallback<String> callback);

}
