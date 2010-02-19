package com.synitex.mn.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.synitex.mn.client.event.ShowHelloEvent;

public class MeetingNotes implements EntryPoint {

	interface GlobalResources extends ClientBundle {
		@NotStrict
		@Source("global.css")
		CssResource css();
	}

	public void onModuleLoad() {

		// Inject global styles.
		GWT.<GlobalResources> create(GlobalResources.class).css()
				.ensureInjected();

		HandlerManager eventBus = new HandlerManager(null);
		AppController appViewer = new AppController(eventBus);
		appViewer.go(RootLayoutPanel.get());

		eventBus.fireEvent(new ShowHelloEvent());
	}

}
