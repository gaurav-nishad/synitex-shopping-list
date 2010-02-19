package com.synitex.mn.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.Widget;
import com.synitex.mn.client.event.AddNoteCanceledEvent;
import com.synitex.mn.client.event.AddNoteCanceledHandler;
import com.synitex.mn.client.event.CreateNoteEvent;
import com.synitex.mn.client.event.CreateNoteHandler;
import com.synitex.mn.client.event.NoteRemovedEvent;
import com.synitex.mn.client.event.NoteRemovedHandler;
import com.synitex.mn.client.event.NoteSelectedEvent;
import com.synitex.mn.client.event.NoteSelectedHandler;
import com.synitex.mn.client.view.HelloView;
import com.synitex.mn.shared.NoteModel;

public class CenterPanel extends Composite implements AddNoteCanceledHandler,
		NoteSelectedHandler, CreateNoteHandler, NoteRemovedHandler {

	private static CenterPanelUiBinder uiBinder = GWT
			.create(CenterPanelUiBinder.class);

	interface CenterPanelUiBinder extends UiBinder<Widget, CenterPanel> {
	}

	private AddNotePanel addNotePanel;
	private ViewNotePanel viewNotePanel;
	private HelloView helloPanel;

	@UiField
	DeckPanel container;

	public CenterPanel() {
		Widget w = uiBinder.createAndBindUi(this);
		addNotePanel = new AddNotePanel();
		addNotePanel.addAddNoteCanceledHandler(this);

		viewNotePanel = new ViewNotePanel();

		helloPanel = new HelloView();

		container.add(addNotePanel);
		container.add(viewNotePanel);
		container.add(helloPanel);

		initWidget(w);
	}

	@Override
	protected void onLoad() {
		showHelloPanel();
	}

	public void showHelloPanel() {
		int idx = container.getWidgetIndex(helloPanel);
		container.showWidget(idx);
	}

	public void showNote(NoteModel noteModel) {
		viewNotePanel.setNoteModel(noteModel);
		int idx = container.getWidgetIndex(viewNotePanel);
		container.showWidget(idx);
	}

	public void createNote() {
		addNotePanel.init();
		int idx = container.getWidgetIndex(addNotePanel);
		container.showWidget(idx);
	}

	public void onAddNoteCanceled(AddNoteCanceledEvent event) {
		showHelloPanel();
	}

	/**
	 * @see NoteSelectedHandler
	 */
	public void onNoteSelected(NoteSelectedEvent ev) {
		showNote(ev.getNoteModel());
	}

	/**
	 * @see CreateNoteHandler
	 */
	public void onCreateNote(CreateNoteEvent ev) {
		createNote();
	}

	/**
	 * @see NoteRemovedHandler
	 */
	public void onNoteRemoved(NoteRemovedEvent event) {
		NoteModel removedNote = event.getNoteModel();
		int idx = container.getVisibleWidget();
		Widget currentPanel = container.getWidget(idx);
		if (currentPanel == addNotePanel) {
			NoteModel m = addNotePanel.getCurrentNote();
			if (m != null && removedNote.getId().equals(m.getId())) {
				showHelloPanel();
			}
		} else if (currentPanel == viewNotePanel) {
			NoteModel m = viewNotePanel.getCurrentNoteModel();
			if (m != null && removedNote.getId().equals(m.getId())) {
				showHelloPanel();
			}
		}
	}
}
