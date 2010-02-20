package com.clinics.gwt.client.login;

import com.clinics.gwt.client.widgets.ErrorDialog;
import com.clinics.gwt.shared.LoginStatuses;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.StatusCodeException;

public abstract class SecuredAsyncCallback<T> implements AsyncCallback<T> {
    public abstract void doSuccess(T result);

    public void doFailure(Throwable caught) {
        ErrorDialog.show(caught);
    }

    public void onFailure(Throwable caught) {

        if (caught instanceof StatusCodeException) {

            StatusCodeException casted = (StatusCodeException) caught;
            String msg = casted.getMessage();

            if (msg != null && msg.startsWith(LoginStatuses.FAILED)) {

                Window.Location.reload();

            } else {

                doFailure(caught);

            }

        } else {
            doFailure(caught);
        }
    }

    public void onSuccess(T result) {
        doSuccess(result);
    };

}
