package com.synitex.mn.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface NoteRemovedHandler extends EventHandler {

	void onNoteRemoved(NoteRemovedEvent event);

}
