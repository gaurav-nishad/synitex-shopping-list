package com.synitex.mn.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface NoteRemoveRequestHandler extends EventHandler {

	void onNoteRemoveRequest(NoteRemoveRequestEvent ev);

}
