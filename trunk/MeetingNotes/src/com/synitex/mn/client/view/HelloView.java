package com.synitex.mn.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.synitex.mn.client.presenter.HelloPresenter.Display;

public class HelloView extends Composite implements Display {

	private static HelloPanelUiBinder uiBinder = GWT
			.create(HelloPanelUiBinder.class);

	interface HelloPanelUiBinder extends UiBinder<Widget, HelloView> {
	}

	public HelloView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Widget asWidget() {
		return this;
	}

}
