package com.synitex.mn.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.UIObject;
import com.synitex.mn.client.event.NoteRemoveRequestEvent;
import com.synitex.mn.client.event.NoteRemoveRequestHandler;
import com.synitex.mn.client.event.NoteSelectedEvent;
import com.synitex.mn.client.event.NoteSelectedHandler;
import com.synitex.mn.shared.NoteModel;

public class NoteItem extends UIObject {

	private static NoteItemUiBinder uiBinder = GWT
			.create(NoteItemUiBinder.class);

	interface NoteItemUiBinder extends UiBinder<DivElement, NoteItem> {
	}

	private NoteModel noteModel;

	@UiField
	SpanElement lbl;
	@UiField
	DivElement btn;

	private DecoratedPopupPanel simplePopup;

	private HandlerManager handlerManager;

	public NoteItem() {

		simplePopup = new DecoratedPopupPanel(true);
		simplePopup.setWidth("150px");
		simplePopup.setWidget(new HTML("on remove button click"));

		setElement(uiBinder.createAndBindUi(this));

		Event.setEventListener(btn, new BtnEventListener());
		Event.sinkEvents(btn, Event.ONCLICK);

		Event.setEventListener(lbl, new LblEventListener());
		Event.sinkEvents(lbl, Event.ONCLICK);
	}

	public NoteItem(NoteModel m) {
		this();
		setNoteModel(m);
	}

	public void setNoteModel(NoteModel m) {
		this.noteModel = m;
		lbl.setInnerHTML(noteModel.getCaption());
	}

	public void addNoteSelectedHandler(NoteSelectedHandler handler) {
		ensureHandlers().addHandler(NoteSelectedEvent.TYPE, handler);
	}

	public void addNoteRemoveRequestHandler(NoteRemoveRequestHandler handler) {
		ensureHandlers().addHandler(NoteRemoveRequestEvent.TYPE, handler);
	}

	protected void onRemButtonClick(Event event) {
		// int left = event.getClientX() + 10;
		// int top = event.getClientY() + 10;
		// simplePopup.setPopupPosition(left, top);

		// Show the popup
		// simplePopup.show();

		fireEvent(new NoteRemoveRequestEvent(noteModel));

	}

	protected void onLblClick(Event event) {
		fireEvent(new NoteSelectedEvent(noteModel));
	}

	private class BtnEventListener implements EventListener {
		public void onBrowserEvent(Event event) {
			if (event.getTypeInt() == Event.ONCLICK) {
				onRemButtonClick(event);
			}
		}
	}

	private class LblEventListener implements EventListener {
		public void onBrowserEvent(Event event) {
			if (event.getTypeInt() == Event.ONCLICK) {
				onLblClick(event);
			}
		}
	}

	/**
	 * Ensures the existence of the handler manager.
	 * 
	 * @return the handler manager
	 * */
	private HandlerManager ensureHandlers() {
		return handlerManager == null ? handlerManager = new HandlerManager(
				this) : handlerManager;
	}

	protected void fireEvent(GwtEvent<?> event) {
		if (handlerManager != null) {
			handlerManager.fireEvent(event);
		}
	}

}
