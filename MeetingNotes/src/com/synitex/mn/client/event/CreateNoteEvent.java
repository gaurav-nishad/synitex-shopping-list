package com.synitex.mn.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class CreateNoteEvent extends GwtEvent<CreateNoteHandler> {

	public static final Type<CreateNoteHandler> TYPE = new Type<CreateNoteHandler>();

	@Override
	protected void dispatch(CreateNoteHandler handler) {
		handler.onCreateNote(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<CreateNoteHandler> getAssociatedType() {
		return TYPE;
	}

}
