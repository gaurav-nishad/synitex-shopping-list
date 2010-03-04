package com.synitex.email.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

public class LoadingBar extends PopupPanel {

	public static LoadingBar instance = new LoadingBar();

	private LoadingBar() {
		setAnimationEnabled(false);
		setGlassEnabled(false);
		setWidget(new Label("Loading..."));
		setPopupPosition(15, 5);
	}

	public void showLoading() {
		if (!isShowing()) {
			show();
		}
	}

	public void hideLoading() {
		if (isShowing()) {
			hide();
		}
	}

}
