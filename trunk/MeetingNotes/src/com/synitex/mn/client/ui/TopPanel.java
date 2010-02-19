package com.synitex.mn.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.synitex.mn.shared.UserModel;

public class TopPanel extends Composite {

	private static TopPanelUiBinder uiBinder = GWT
			.create(TopPanelUiBinder.class);

	interface TopPanelUiBinder extends UiBinder<Widget, TopPanel> {
	}

	@UiField
	Label lblUser;

	@UiField
	Anchor signOutLink;

	private UserModel currentUserModel;

	public TopPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setCurrentUser(UserModel userModel) {
		this.currentUserModel = userModel;
		lblUser.setText(currentUserModel.getNick());
	}

	@UiHandler("signOutLink")
	public void onSignOutLinkClicked(ClickEvent clickEvent) {
		String logOutUrl = currentUserModel.getLogOutUrl();
		Window.open(logOutUrl, "_self", "");
	}
}
