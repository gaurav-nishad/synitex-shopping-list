package com.synitex.sample.client.main;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;

public class RadioPanel extends FlowPanel implements ClickHandler {

	public RadioPanel() {
		setWidth("100%");
		setHeight("25px");
		setStyleName("ui-second-level-menu-panel");
	}

	public void addHyperlink(Anchor hyper) {
		hyper.setStyleName("ui-2nd-level-menu-hyperlink");
		add(hyper);

		hyper.addClickHandler(this);
	}

	/**
	 * @see ClickHandler
	 */
	public void onClick(ClickEvent event) {
		Anchor anchor = (Anchor) event.getSource();
		anchor.setStyleName("ui-2nd-level-menu-hyperlink-selected");
	}

}
