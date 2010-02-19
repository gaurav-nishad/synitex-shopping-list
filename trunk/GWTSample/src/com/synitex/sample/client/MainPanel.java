package com.synitex.sample.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.gchart.client.GChart;
import com.synitex.sample.client.gchart.GChartExample02;
import com.synitex.sample.client.login.SecuredAsyncCallback;
import com.synitex.sample.client.ui.ErrorDialog;
import com.synitex.sample.client.ui.InfoDialog;
import com.synitex.sample.client.ui.PrintChartDialog;
import com.synitex.sample.shared.UserModel;

public class MainPanel extends Composite implements ClickHandler {

	private static GreetingServiceAsync s = GWT.create(GreetingService.class);

	private Anchor hrefLogout;
	private Anchor hrefSayHello;

	private Button btnPrint;

	public MainPanel() {

		VerticalPanel vp = new VerticalPanel();
		UserModel user = UserManager.getInstance().getCurrentUser();
		String name = user.getName();
		Label lbl = new Label("Hello, " + name + "!");
		vp.add(lbl);

		hrefLogout = new Anchor("Logout");
		hrefLogout.addClickHandler(this);
		vp.add(hrefLogout);

		hrefSayHello = new Anchor("Say hello");
		hrefSayHello.addClickHandler(this);
		vp.add(hrefSayHello);

		btnPrint = new Button("Open test print dialog");
		btnPrint.addClickHandler(this);
		vp.add(btnPrint);

		GChart gchart = new GChartExample02();
		// vp.add(gchart);
		// gchart.update();

		initWidget(vp);

	}

	public void onClick(ClickEvent event) {
		if (event.getSource() == hrefLogout) {

			UserManager.getInstance().logout(new AsyncCallback<Void>() {

				public void onSuccess(Void result) {
					Window.Location.reload();
				}

				public void onFailure(Throwable caught) {
					ErrorDialog.show(caught);
				}

			});

		} else if (event.getSource() == hrefSayHello) {

			s.greetServer("aaa", new SecuredAsyncCallback<String>() {
				@Override
				public void doSuccess(String result) {
					InfoDialog.show(result);
				}
			});

		} else if (event.getSource() == btnPrint) {
			PrintChartDialog.showDialog();
		}
	}
}
