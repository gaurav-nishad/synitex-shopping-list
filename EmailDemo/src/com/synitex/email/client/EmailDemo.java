package com.synitex.email.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class EmailDemo implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		DockLayoutPanel mainPanel = new DockLayoutPanel(Unit.PX);
		EmailPanel panel = new EmailPanel();
		ScrollPanel scrollPanel = new ScrollPanel(panel);
		mainPanel.add(scrollPanel);
		RootLayoutPanel.get().add(mainPanel);
		// RootPanel.get().add(mainPanel);
	}
}
