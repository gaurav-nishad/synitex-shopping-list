package com.synitex.sample.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

public class InfoDialog extends MyDialog {
	private static InfoDialogUiBinder uiBinder = GWT
			.create(InfoDialogUiBinder.class);

	interface InfoDialogUiBinder extends UiBinder<Widget, InfoDialog> {
	}

	@UiField
	DivElement divTxt;

	@UiField
	Button btnOk;

	public static void show(String msg) {
		InfoDialog d = new InfoDialog(msg);
		d.show();
		d.center();
	}

	public InfoDialog(String msg) {
		setText("Info!");
		setWidget(uiBinder.createAndBindUi(this));
		setAnimationEnabled(false);
		setGlassEnabled(true);
		divTxt.setInnerHTML(msg);
	}

	@UiHandler("btnOk")
	public void onOkClick(ClickEvent ev) {
		hide();
	}
}
