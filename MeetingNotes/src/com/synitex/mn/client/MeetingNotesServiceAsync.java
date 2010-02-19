package com.synitex.mn.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.synitex.mn.shared.NoteModel;
import com.synitex.mn.shared.UserModel;

public interface MeetingNotesServiceAsync {

	void getNickname(AsyncCallback<UserModel> callback);

	void saveNote(NoteModel n, AsyncCallback<NoteModel> callback);

	void commitDraft(NoteModel n, AsyncCallback<NoteModel> callback);

	void removeNote(Long noteId, AsyncCallback<Void> callback);

	void getNote(Long noteId, AsyncCallback<NoteModel> callback);

	void getDrafts(AsyncCallback<ArrayList<NoteModel>> callback);

	void getNotes(AsyncCallback<ArrayList<NoteModel>> callback);

}
