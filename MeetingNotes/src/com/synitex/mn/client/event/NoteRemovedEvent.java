package com.synitex.mn.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.synitex.mn.shared.NoteModel;

public class NoteRemovedEvent extends GwtEvent<NoteRemovedHandler> {

	public static final Type<NoteRemovedHandler> TYPE = new Type<NoteRemovedHandler>();
	private NoteModel noteModel;

	public NoteRemovedEvent(NoteModel noteModel) {
		this.noteModel = noteModel;
	}

	@Override
	protected void dispatch(NoteRemovedHandler handler) {
		handler.onNoteRemoved(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<NoteRemovedHandler> getAssociatedType() {
		return TYPE;
	}

	public NoteModel getNoteModel() {
		return noteModel;
	}

}
