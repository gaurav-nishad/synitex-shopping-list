package com.synitex.mn.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.synitex.mn.shared.NoteModel;
import com.synitex.mn.shared.UserModel;

@RemoteServiceRelativePath("notes")
public interface MeetingNotesService extends RemoteService {

	UserModel getNickname();

	// ----

	NoteModel saveNote(NoteModel n);

	NoteModel commitDraft(NoteModel n);

	void removeNote(Long noteId);

	NoteModel getNote(Long noteId);

	ArrayList<NoteModel> getDrafts();

	ArrayList<NoteModel> getNotes();

}
