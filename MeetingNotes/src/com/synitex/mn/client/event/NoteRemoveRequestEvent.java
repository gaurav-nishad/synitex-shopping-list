package com.synitex.mn.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.synitex.mn.shared.NoteModel;

public class NoteRemoveRequestEvent extends GwtEvent<NoteRemoveRequestHandler> {

	public static final Type<NoteRemoveRequestHandler> TYPE = new Type<NoteRemoveRequestHandler>();
	private NoteModel noteModel;

	public NoteRemoveRequestEvent(NoteModel noteModel) {
		this.noteModel = noteModel;
	}

	@Override
	protected void dispatch(NoteRemoveRequestHandler handler) {
		handler.onNoteRemoveRequest(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<NoteRemoveRequestHandler> getAssociatedType() {
		return TYPE;
	}

	public NoteModel getNoteModel() {
		return noteModel;
	}

}
