package com.synitex.mn.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.synitex.mn.shared.NoteModel;

public class NoteSelectedEvent extends GwtEvent<NoteSelectedHandler> {

	public static final Type<NoteSelectedHandler> TYPE = new Type<NoteSelectedHandler>();
	private NoteModel noteModel;

	public NoteSelectedEvent(NoteModel noteModel) {
		this.noteModel = noteModel;
	}

	@Override
	protected void dispatch(NoteSelectedHandler handler) {
		handler.onNoteSelected(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<NoteSelectedHandler> getAssociatedType() {
		return TYPE;
	}

	public NoteModel getNoteModel() {
		return noteModel;
	}

}
