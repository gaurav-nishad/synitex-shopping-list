package com.synitex.mn.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddNoteCanceledEvent extends GwtEvent<AddNoteCanceledHandler> {

	public static Type<AddNoteCanceledHandler> TYPE = new Type<AddNoteCanceledHandler>();

	@Override
	protected void dispatch(AddNoteCanceledHandler handler) {
		handler.onAddNoteCanceled(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AddNoteCanceledHandler> getAssociatedType() {
		return TYPE;
	}

}
