package com.clinics.gwt.client;

import com.clinics.gwt.client.i18n.LocaleChanger;
import com.clinics.gwt.client.login.LoginPanel;
import com.clinics.gwt.client.login.UserManager;
import com.clinics.gwt.client.main.MainPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController {

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

        container.add(mainPanel);

    }

}
