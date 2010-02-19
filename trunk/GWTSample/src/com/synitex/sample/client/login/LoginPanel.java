package com.synitex.sample.client.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.synitex.sample.client.UserManager;
import com.synitex.sample.client.ui.ErrorDialog;
import com.synitex.sample.client.ui.InfoDialog;
import com.synitex.sample.shared.UserModel;

public class LoginPanel extends Composite implements ClickHandler,
		AsyncCallback<UserModel> {

	private LoginConstants constants = GWT.create(LoginConstants.class);
	private LoginMessages messages = GWT.create(LoginMessages.class);
	private TextBox fldName;
	private PasswordTextBox fldPass;
	private Button btnLogin;

	public LoginPanel() {

		fldName = new TextBox();
		fldPass = new PasswordTextBox();
		btnLogin = new Button(constants.login());
		btnLogin.addClickHandler(this);

		FlexTable table = new FlexTable();
		table.setCellSpacing(5);
		FlexCellFormatter cf = table.getFlexCellFormatter();

		// title
		table.setHTML(0, 0, constants.caption());
		cf.setColSpan(0, 0, 2);
		cf.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		// name
		table.setHTML(1, 0, constants.name());
		table.setWidget(1, 1, fldName);

		// password
		table.setHTML(2, 0, constants.password());
		table.setWidget(2, 1, fldPass);

		// login button
		table.setWidget(3, 0, btnLogin);
		cf.setColSpan(3, 0, 2);
		cf.setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_RIGHT);

		DecoratorPanel dp = new DecoratorPanel();
		dp.setWidget(table);
		initWidget(dp);
	}

	public void onClick(ClickEvent event) {
		if (event.getSource() == btnLogin) {
			String name = fldName.getText();
			String pass = fldPass.getText();
			UserManager.getInstance().login(name, pass, this);
		}
	}

	public void onFailure(Throwable caught) {
		ErrorDialog.show(caught);
	}

	public void onSuccess(UserModel result) {
		if (result != null) {
			Window.Location.reload();
		} else {
			InfoDialog.show(messages.notLogged(fldName.getText()));
		}
	}
}
