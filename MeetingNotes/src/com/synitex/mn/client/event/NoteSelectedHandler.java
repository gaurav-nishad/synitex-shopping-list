package com.synitex.mn.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface NoteSelectedHandler extends EventHandler {

	void onNoteSelected(NoteSelectedEvent ev);
}
