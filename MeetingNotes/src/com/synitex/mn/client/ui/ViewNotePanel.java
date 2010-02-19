package com.synitex.mn.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.synitex.mn.shared.NoteModel;

public class ViewNotePanel extends Composite {

	private static ViewNotePanelUiBinder uiBinder = GWT
			.create(ViewNotePanelUiBinder.class);

	interface ViewNotePanelUiBinder extends UiBinder<Widget, ViewNotePanel> {
	}

	private NoteModel noteModel;

	@UiField
	HTML html;

	@UiField
	Label lblStatus;

	@UiField
	Label lblCaption;

	public ViewNotePanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setNoteModel(NoteModel noteModel) {
		this.noteModel = noteModel;
		html.setHTML(noteModel.getText());
		lblCaption.setText(noteModel.getCaption());

		String lastSaved = DateTimeFormat.getFormat("dd.MM.yyyy HH:mm").format(
				noteModel.getLastSavedAt());

		StringBuffer status = new StringBuffer();
		status.append("owner: ");
		status.append(noteModel.getOwnerEmail());
		status.append("; last updated at: ");
		status.append(lastSaved);

		lblStatus.setText(status.toString());

	}

	public NoteModel getCurrentNoteModel() {
		return noteModel;
	}

}
