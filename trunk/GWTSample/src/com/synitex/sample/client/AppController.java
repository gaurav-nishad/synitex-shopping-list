package com.synitex.sample.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.synitex.sample.client.i18n.LocaleChanger;
import com.synitex.sample.client.login.LoginPanel;
import com.synitex.sample.client.main.MainPanel;

public class AppController implements Presenter {

	public void go(HasWidgets container) {

		DockLayoutPanel mainPanel = new DockLayoutPanel(Unit.PX);

		if (UserManager.getInstance().getCurrentUser() == null) {
			LocaleChanger localeChanger = new LocaleChanger();
			LoginPanel lp = new LoginPanel();

			mainPanel.addNorth(localeChanger, 15);
			mainPanel.add(lp);

		} else {
			MainPanel mp = new MainPanel();
			mainPanel.add(mp);
		}

		// container.add(mainPanel);
		com.synitex.sample.client.main.MainPanel mp = new MainPanel();
		container.add(mp);

	}

}
