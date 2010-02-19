package com.synitex.sample.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

public class ConfirmDialog extends MyDialog {

	private static ConfirmDialogUiBinder uiBinder = GWT
			.create(ConfirmDialogUiBinder.class);

	interface ConfirmDialogUiBinder extends UiBinder<Widget, ConfirmDialog> {
	}

	public interface Listener {

		void onOk();

	}

	private Listener listener;

	@UiField
	DivElement divTxt;

	@UiField
	Button btnCancel;

	@UiField
	Button btnOk;

	public static void show(String msg, Listener listener) {
		ConfirmDialog d = new ConfirmDialog(msg, listener);
		d.show();
		d.center();
	}

	public ConfirmDialog(String msg, Listener listener) {
		this.listener = listener;
		setText("Confirm?");
		setWidget(uiBinder.createAndBindUi(this));
		setAnimationEnabled(false);
		setGlassEnabled(true);
		divTxt.setInnerHTML(msg);
	}

	@UiHandler("btnCancel")
	public void onCancelClick(ClickEvent ev) {
		hide();
	}

	@UiHandler("btnOk")
	public void onOkClick(ClickEvent ev) {
		hide();
		if (listener != null) {
			listener.onOk();
		}
	}

}
