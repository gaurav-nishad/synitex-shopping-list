package com.synitex.mn.client.ui;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.synitex.mn.client.AppController;
import com.synitex.mn.client.event.CreateNoteEvent;
import com.synitex.mn.client.event.CreateNoteHandler;
import com.synitex.mn.client.event.NoteRemoveRequestEvent;
import com.synitex.mn.client.event.NoteRemoveRequestHandler;
import com.synitex.mn.client.event.NoteRemovedEvent;
import com.synitex.mn.client.event.NoteRemovedHandler;
import com.synitex.mn.client.event.NoteSelectedHandler;
import com.synitex.mn.client.ui.dialogs.ConfirmDialog;
import com.synitex.mn.client.ui.dialogs.ConfirmDialog.Listener;
import com.synitex.mn.client.utils.SecuredAsyncCallback;
import com.synitex.mn.shared.NoteModel;

public class NotesPanel extends Composite implements NoteRemoveRequestHandler {

	private static NotesPanelUiBinder uiBinder = GWT
			.create(NotesPanelUiBinder.class);

	interface NotesPanelUiBinder extends UiBinder<Widget, NotesPanel> {
	}

	@UiField
	Button btnRefresh;
	@UiField
	Button addNoteBtn;
	@UiField
	FlowPanel notesContainer;

	private RefreshCallback refreshCallback = new RefreshCallback();
	private ArrayList<NoteItem> notes = new ArrayList<NoteItem>();
	private ArrayList<NoteSelectedHandler> noteSelectedHandlers = new ArrayList<NoteSelectedHandler>();

	public NotesPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void refresh() {
		AppController.showLoading();
		AppController.notesSerice.getNotes(refreshCallback);
	}

	@UiHandler("btnRefresh")
	public void onBtnRefreshClick(ClickEvent ev) {
		refresh();
	}

	@Override
	protected void onLoad() {
		refresh();
	}

	public void onNoteRemoveRequest(NoteRemoveRequestEvent ev) {
		NoteModel noteModel = ev.getNoteModel();
		String noteName = noteModel.getCaption();
		String msg = "Do you really want to delete note \"" + noteName + "\"?";
		ConfirmDialog.show(msg, new RemoveListener(noteModel));
	}

	public void addNoteSelectedHandler(NoteSelectedHandler handler) {
		noteSelectedHandlers.add(handler);
	}

	public void addCreateNoteHandler(CreateNoteHandler handler) {
		addHandler(handler, CreateNoteEvent.TYPE);
	}

	public void addNoteRemovedHandler(NoteRemovedHandler handler) {
		addHandler(handler, NoteRemovedEvent.TYPE);
	}

	@UiHandler("addNoteBtn")
	public void onAddNoteBtnClick(ClickEvent ev) {
		fireEvent(new CreateNoteEvent());
	}

	private class RefreshCallback extends
			SecuredAsyncCallback<ArrayList<NoteModel>> {

		public void doSuccess(ArrayList<NoteModel> result) {

			AppController.hideLoading();

			for (NoteItem l : notes) {
				notesContainer.getElement().removeChild(l.getElement());
			}
			notes.clear();

			for (NoteModel n : result) {
				NoteItem noteItem = new NoteItem(n);
				for (NoteSelectedHandler h : noteSelectedHandlers) {
					noteItem.addNoteSelectedHandler(h);
				}
				noteItem.addNoteRemoveRequestHandler(NotesPanel.this);
				notes.add(noteItem);
				notesContainer.getElement().appendChild(noteItem.getElement());
			}

		}
	}

	private class RemoveListener implements Listener {

		private NoteModel noteModel;

		public RemoveListener(NoteModel noteModel) {
			this.noteModel = noteModel;
		}

		public void onOk() {
			AppController.showLoading();
			AppController.notesSerice.removeNote(noteModel.getId(),
					new RemoveCallback());
			fireEvent(new NoteRemovedEvent(noteModel));
		}

	}

	private class RemoveCallback extends SecuredAsyncCallback<Void> {

		@Override
		public void doSuccess(Void result) {
			AppController.showLoading();
			AppController.notesSerice.getNotes(refreshCallback);
		}

	}

}
