package com.synitex.mn.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ShowHelloEvent extends GwtEvent<ShowHelloEventHandler> {

	public static Type<ShowHelloEventHandler> TYPE = new Type<ShowHelloEventHandler>();

	@Override
	protected void dispatch(ShowHelloEventHandler handler) {
		handler.onShowHello(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ShowHelloEventHandler> getAssociatedType() {
		return TYPE;
	}

}
