package com.clinics.gwt.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Widget;

public class ErrorDialog extends DialogBox {

	private static ErrorDialogUiBinder uiBinder = GWT
			.create(ErrorDialogUiBinder.class);

	interface ErrorDialogUiBinder extends UiBinder<Widget, ErrorDialog> {
	}

	@UiField
	DivElement divTxt;

	@UiField
	Button btnOk;

	public static void show(Throwable ex) {
		ErrorDialog d = new ErrorDialog(ex);
		d.show();
		d.center();
	}

	public ErrorDialog(Throwable ex) {
		setText("Error!");
		setWidget(uiBinder.createAndBindUi(this));
		setAnimationEnabled(false);
		setGlassEnabled(true);
		divTxt.setInnerHTML(ex.getMessage());
	}

	@UiHandler("btnOk")
	public void onOkClick(ClickEvent ev) {
		hide();
	}
}
