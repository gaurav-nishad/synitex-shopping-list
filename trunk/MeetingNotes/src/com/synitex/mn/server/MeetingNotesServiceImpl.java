package com.synitex.mn.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.synitex.mn.server.converters.NoteConverter;
import com.synitex.mn.server.db.Note;
import com.synitex.mn.server.db.PMF;
import com.synitex.mn.shared.NoteModel;
import com.synitex.mn.shared.UserModel;

public class MeetingNotesServiceImpl extends RemoteServiceServlet implements
		com.synitex.mn.client.MeetingNotesService {

	private static final long serialVersionUID = 1L;

	public UserModel getNickname() {

		User user = getCurrentUser();

		if (user != null) {
			UserService userService = UserServiceFactory.getUserService();
			String logOutUrl = userService
					.createLogoutURL("/MeetingNotes.html");
			return new UserModel(user.getNickname(), logOutUrl);
		} else {
			return null;
		}

	}

	// ----------------------------------

	public NoteModel commitDraft(NoteModel n) {

		n = saveNote(n);

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Note note = pm.getObjectById(Note.class, n.getId());
			note.setIsDraft(Boolean.valueOf(false));

			return NoteConverter.convert(note);
		} finally {
			pm.close();
		}

	}

	public ArrayList<NoteModel> getDrafts() {
		return getNotesImpl(Boolean.valueOf(true));
	}

	public NoteModel getNote(Long noteId) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Note note = pm.getObjectById(Note.class, noteId);
			return NoteConverter.convert(note);
		} finally {
			pm.close();
		}
	}

	public void removeNote(Long noteId) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Note note = pm.getObjectById(Note.class, noteId);
			pm.deletePersistent(note);
		} finally {
			pm.close();
		}
	}

	public NoteModel saveNote(NoteModel n) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		Note note = null;
		try {

			Long curId = n.getId();
			if (curId == null) {
				// create new note
				note = NoteConverter.createFrom(n, getCurrentUser());
				note = pm.makePersistent(note);
			} else {
				note = pm.getObjectById(Note.class, curId);
				note = NoteConverter.updateWith(note, n);
			}

		} finally {
			pm.close();
		}

		NoteModel m = NoteConverter.convert(note);
		return m;
	}

	public ArrayList<NoteModel> getNotes() {
		return getNotesImpl(Boolean.valueOf(false));
	}

	// --------------------------------

	@SuppressWarnings("unchecked")
	protected ArrayList<NoteModel> getNotesImpl(boolean isDraft) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {

			User user = getCurrentUser();
			Query query = pm.newQuery(Note.class);
			query.setFilter("ownerEmail == email && isDraft == is_draft");
			query.setOrdering("createdAt desc");
			query.declareParameters("String email, Boolean is_draft");
			List<Note> notes = (List<Note>) query.execute(user.getEmail(),
					isDraft);

			ArrayList<NoteModel> res = NoteConverter.convert(notes);
			return res;

		} finally {
			pm.close();
		}

	}

	protected User getCurrentUser() {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		return user;
	}

}
