package com.synitex.mn.client.utils;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.StatusCodeException;
import com.synitex.mn.client.AppController;
import com.synitex.mn.client.ui.dialogs.ErrorDialog;

public abstract class SecuredAsyncCallback<T> implements AsyncCallback<T> {

	public abstract void doSuccess(T result);

	public void doFailure(Throwable caught) {
		ErrorDialog.show(caught);
	}

	public void onFailure(Throwable caught) {

		AppController.hideLoading();

		if (caught instanceof StatusCodeException) {
			StatusCodeException casted = (StatusCodeException) caught;
			String msg = casted.getMessage();
			if (msg != null && msg.startsWith("access:")) {
				String logingUrl = msg.substring("access:".length());
				Window.open(logingUrl, "_self", "");
			} else {
				doFailure(caught);
			}
		} else {
			doFailure(caught);
		}
	}

	public void onSuccess(T result) {
		AppController.hideLoading();
		doSuccess(result);
	};

}
