package com.synitex.mn.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class EditableLabel extends Composite implements BlurHandler,
		KeyDownHandler {

	private static EditableLabelUiBinder uiBinder = GWT
			.create(EditableLabelUiBinder.class);

	interface EditableLabelUiBinder extends UiBinder<Widget, EditableLabel> {
	}

	@UiField
	Label lbl;

	@UiField
	TextBox txt;

	public EditableLabel() {
		initWidget(uiBinder.createAndBindUi(this));
		txt.setVisible(false);
		txt.addBlurHandler(this);
		txt.addKeyDownHandler(this);
		txt.setWidth("100%");
	}

	@UiHandler("lbl")
	public void onLblClick(ClickEvent ev) {
		String curText = lbl.getText();
		txt.setText(curText);
		lbl.setVisible(false);
		txt.setVisible(true);
		txt.selectAll();
		txt.setFocus(true);
	}

	public void setText(String initalCaption) {
		if (lbl.isVisible()) {
			lbl.setText(initalCaption);
		} else {
			txt.setText(initalCaption);
		}
	}

	public String getText() {
		if (lbl.isVisible()) {
			return lbl.getText();
		} else {
			return txt.getText();
		}
	}

	protected void showLabel() {
		String curText = txt.getText();
		lbl.setText(curText);
		txt.setVisible(false);
		lbl.setVisible(true);
	}

	public void onBlur(BlurEvent event) {
		if (event.getSource() == txt) {
			showLabel();
		}
	}

	public void onKeyDown(KeyDownEvent event) {
		if (event.getSource() == txt) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				showLabel();
			}
		}
	}

}
