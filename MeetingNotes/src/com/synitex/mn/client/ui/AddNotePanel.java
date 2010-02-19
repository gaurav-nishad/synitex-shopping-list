package com.synitex.mn.client.ui;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.Widget;
import com.synitex.mn.client.AppController;
import com.synitex.mn.client.event.AddNoteCanceledEvent;
import com.synitex.mn.client.event.AddNoteCanceledHandler;
import com.synitex.mn.client.ui.dialogs.ConfirmDialog;
import com.synitex.mn.client.ui.dialogs.ConfirmDialog.Listener;
import com.synitex.mn.client.ui.richtext.RichTextToolbar;
import com.synitex.mn.client.utils.SecuredAsyncCallback;
import com.synitex.mn.shared.NoteModel;

public class AddNotePanel extends Composite {

	private static AddNotePanelUiBinder uiBinder = GWT
			.create(AddNotePanelUiBinder.class);

	interface AddNotePanelUiBinder extends UiBinder<Widget, AddNotePanel> {
	}

	@UiField
	EditableLabel caption;

	@UiField
	Button btnCancel;

	RichTextArea noteTxt;

	@UiField
	Label lblStatus;

	@UiField
	Button btnSave;

	@UiField
	DockLayoutPanel textAreaContainer;

	private SaveTimer saveTimer = new SaveTimer();

	private NoteModel noteModel;

	public AddNotePanel() {
		Widget w = uiBinder.createAndBindUi(this);

		noteTxt = new RichTextArea();
		noteTxt.setWidth("100%");
		noteTxt.setHeight("100%");
		RichTextToolbar toolbar = new RichTextToolbar(noteTxt);
		toolbar.setWidth("100%");

		textAreaContainer.addNorth(toolbar, 60);
		textAreaContainer.add(noteTxt);

		initWidget(w);
	}

	public void addAddNoteCanceledHandler(AddNoteCanceledHandler handler) {
		addHandler(handler, AddNoteCanceledEvent.TYPE);
	}

	public void init() {
		noteModel = null;
		Date date = new Date();
		String initalCaption = DateTimeFormat.getFormat("dd.MM.yyyy HH.mm")
				.format(date);
		caption.setText("Note - " + initalCaption);
		noteTxt.setHTML("");
		lblStatus.setText("");
		saveTimer.scheduleRepeating(10000); // each 10 sec
	}

	public NoteModel getCurrentNote() {
		if (noteModel == null) {
			noteModel = new NoteModel();
		}
		noteModel.setCaption(caption.getText());
		noteModel.setText(noteTxt.getHTML());

		return noteModel;
	}

	public void updateCurrentNote(NoteModel noteModel) {
		this.noteModel = noteModel;
	}

	@UiHandler("btnCancel")
	public void onBtnCancelClick(ClickEvent ev) {
		String msg = "The note will be deleted! Do you really want to cancel editing the note?";
		ConfirmDialog.show(msg, new CancelConfirmDialogListener());
	}

	@UiHandler("btnSave")
	public void onBtnSaveCicked(ClickEvent ev) {
		NoteModel nm = getCurrentNote();
		AppController.showLoading();
		AppController.notesSerice.commitDraft(nm, new SaveCallback());
	}

	protected boolean isNoteChanged(NoteModel current, NoteModel last) {

		String cCaption = current.getCaption();
		String lCaption = last.getCaption();
		String cText = current.getText();
		String lText = last.getText();

		if (!(cCaption.equals(lCaption))) {
			return true;
		}

		if (!(cText.equals(lText))) {
			return true;
		}

		return false;

	}

	// ------------------------------------------

	private class SaveTimer extends Timer {

		private NoteModel lastNoteModel;

		@Override
		public void run() {

			if (!isVisible()) {
				return;
			}

			NoteModel noteModel = getCurrentNote();
			if (lastNoteModel != null
					&& !isNoteChanged(noteModel, lastNoteModel)) {
				return;
			}
			lastNoteModel = noteModel;

			AppController.showLoading();
			AppController.notesSerice.saveNote(noteModel, new SaveCallback());
		}
	}

	private class SaveCallback extends SecuredAsyncCallback<NoteModel> {

		public void doSuccess(NoteModel result) {
			Date lastSavedAt = result.getLastSavedAt();
			String savedAt = DateTimeFormat.getFormat("HH:mm:ss").format(
					lastSavedAt);
			String status = "Last saved at: " + savedAt;
			if (result.getIsDraft()) {
				status = "Draft saved at: " + savedAt;
			}
			lblStatus.setText(status);
			updateCurrentNote(result);
		}

	}

	private class CancelCallback extends SecuredAsyncCallback<Void> {

		@Override
		public void doSuccess(Void result) {
			fireEvent(new AddNoteCanceledEvent());
		}

	}

	private class CancelConfirmDialogListener implements Listener {

		public void onOk() {
			saveTimer.cancel();
			NoteModel nm = getCurrentNote();
			if (nm.getId() != null) {
				AppController.showLoading();
				AppController.notesSerice.removeNote(nm.getId(),
						new CancelCallback());
			} else {
				fireEvent(new AddNoteCanceledEvent());
			}
		}

	}

}
