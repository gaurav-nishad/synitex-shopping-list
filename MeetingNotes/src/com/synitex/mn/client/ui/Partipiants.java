package com.synitex.mn.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class Partipiants extends Composite {

	private static PartipiantsUiBinder uiBinder = GWT
			.create(PartipiantsUiBinder.class);

	interface PartipiantsUiBinder extends UiBinder<Widget, Partipiants> {
	}

	public Partipiants() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
