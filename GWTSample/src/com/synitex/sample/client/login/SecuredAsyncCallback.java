package com.synitex.sample.client.login;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.StatusCodeException;
import com.synitex.sample.client.ui.ErrorDialog;
import com.synitex.sample.shared.LoginStatuses;

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
