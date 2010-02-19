package com.synitex.mn.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.synitex.mn.client.event.ShowHelloEvent;
import com.synitex.mn.client.event.ShowHelloEventHandler;
import com.synitex.mn.client.presenter.HelloPresenter;
import com.synitex.mn.client.presenter.Presenter;
import com.synitex.mn.client.ui.LeftPanel;
import com.synitex.mn.client.ui.TopPanel;
import com.synitex.mn.client.view.HelloView;

public class AppController implements Presenter {

	public static MeetingNotesServiceAsync notesSerice;

	static {
		notesSerice = GWT.create(MeetingNotesService.class);
	}

	private HandlerManager eventBus;
	private ScrollPanel centerPanel;
	private TopPanel topPanel;
	private LeftPanel leftPanel;
	private static PopupPanel loading;

	public AppController(HandlerManager eventBus) {
		this.eventBus = eventBus;

		loading = new PopupPanel(false);
		loading.setWidget(new HTML("Loading..."));
		loading.setWidth("100px");

		bind();
	}

	public void go(HasWidgets container) {
		// DockLayoutPanel mainPanel = uiBinder.createAndBindUi(this);
		// leftPanel.getNotesPanel().addNoteSelectedHandler(centerPanel);
		// leftPanel.getNotesPanel().addCreateNoteHandler(centerPanel);
		// leftPanel.getNotesPanel().addNoteRemovedHandler(centerPanel);
		// RootLayoutPanel.get().add(mainPanel);
		// showLoading();
		// notesSerice.getNickname(new InitCallback());

		centerPanel = new ScrollPanel();
		topPanel = new TopPanel();
		leftPanel = new LeftPanel();

		DockLayoutPanel mainPanel = new DockLayoutPanel(Unit.EM);
		SplitLayoutPanel lp = new SplitLayoutPanel();
		lp.addWest(leftPanel, 250);
		lp.add(centerPanel);
		mainPanel.addNorth(topPanel, 5);
		mainPanel.add(lp);

		container.add(mainPanel);
	}

	protected void bind() {
		eventBus.addHandler(ShowHelloEvent.TYPE, new ShowHelloEventHandler() {
			public void onShowHello(ShowHelloEvent ev) {
				doShowHello();
			}
		});
	}

	public static void showLoading() {
		if (!loading.isShowing()) {
			loading.setPopupPosition(300, 0);
			loading.show();
		}
	}

	public static void hideLoading() {
		if (loading.isShowing()) {
			loading.hide();
		}
	}

	protected void doShowHello() {
		Presenter pr = new HelloPresenter(new HelloView(), eventBus);
		pr.go(centerPanel);
	}

	// ----------------------

	// private class InitCallback extends SecuredAsyncCallback<UserModel> {

	// public void doSuccess(UserModel result) {
	// topPanel.setCurrentUser(result);
	// }

	// }

}
