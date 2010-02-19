package com.synitex.mn.server.converters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.users.User;
import com.synitex.mn.server.db.Note;
import com.synitex.mn.shared.NoteModel;

public class NoteConverter {

	private NoteConverter() {
	};

	public static ArrayList<NoteModel> convert(List<Note> notes) {
		ArrayList<NoteModel> res = new ArrayList<NoteModel>();
		if (notes != null && notes.size() > 0) {
			for (Note n : notes) {
				res.add(convert(n));
			}
		}
		return res;
	}

	public static NoteModel convert(Note note) {

		User owner = note.getOwner();
		Text noteText = note.getNoteText();

		NoteModel m = new NoteModel();
		m.setCaption(note.getCaption());
		m.setId(note.getId());
		m.setLastSavedAt(note.getLastUpdatedAt());
		m.setOwnerEmail(note.getOwnerEmail());
		m.setText(noteText.getValue());
		m.setIsDraft(note.getIsDraft());

		return m;
	}

	public static Note createFrom(NoteModel m, User owner) {
		Note n = new Note();

		n.setCaption(m.getCaption());
		n.setNoteText(new Text(m.getText()));
		n.setOwner(owner);
		n.setOwnerEmail(owner.getEmail());
		Date createdAt = new Date();
		n.setCreatedAt(createdAt);
		n.setLastUpdatedAt(createdAt);

		n.setIsDraft(true);

		return n;
	}

	public static Note updateWith(Note n, NoteModel m) {

		n.setCaption(m.getCaption());
		n.setNoteText(new Text(m.getText()));
		n.setLastUpdatedAt(new Date());

		return n;
	}

}
