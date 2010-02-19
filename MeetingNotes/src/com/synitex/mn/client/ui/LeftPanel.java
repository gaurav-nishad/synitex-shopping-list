package com.synitex.mn.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class LeftPanel extends Composite {

	private static LeftPanelUiBinder uiBinder = GWT
			.create(LeftPanelUiBinder.class);

	interface LeftPanelUiBinder extends UiBinder<Widget, LeftPanel> {
	}

	@UiField
	NotesPanel notesPanel;

	public LeftPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public NotesPanel getNotesPanel() {
		return notesPanel;
	}

}
