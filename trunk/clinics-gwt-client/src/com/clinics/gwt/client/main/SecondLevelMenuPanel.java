package com.clinics.gwt.client.main;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SecondLevelMenuPanel extends Composite {

	private DockPanel dockPanel;

	public SecondLevelMenuPanel() {
		dockPanel = new DockPanel();

		RadioPanel hp = new RadioPanel();
		hp.addHyperlink(new Anchor("Level1"));
		hp.addHyperlink(new Anchor("Level2"));
		hp.addHyperlink(new Anchor("Level3"));

		VerticalPanel vp = new VerticalPanel();
		vp.add(new Label("Hello in 2 level menu!"));

		dockPanel.add(hp, DockPanel.NORTH);
		dockPanel.add(vp, DockPanel.CENTER);

		initWidget(dockPanel);
	}
}
