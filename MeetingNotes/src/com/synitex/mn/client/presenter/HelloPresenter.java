package com.synitex.mn.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class HelloPresenter implements Presenter {

	public interface Display {
		Widget asWidget();
	}

	private Display display;
	private HandlerManager eventBus;

	public HelloPresenter(Display display, HandlerManager eventBus) {
		this.display = display;
		this.eventBus = eventBus;
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}
}
