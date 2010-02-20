package com.clinics.gwt.client.main;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.TabPanel;

public class MainPanel extends DockLayoutPanel {

	private HeaderPanel headerPanel;
	private TabPanel tabPanel;

	private GChartExamplePanel gchartPanel;
	private TablePanel tablePanel;

	public MainPanel() {
		super(Unit.PX);
		initUI();
	}

	private void initUI() {

		headerPanel = new HeaderPanel();
		tabPanel = new TabPanel();
		tabPanel.setWidth("100%");

		gchartPanel = new GChartExamplePanel();
		tablePanel = new TablePanel();

		tabPanel.add(gchartPanel, "GChart");
		tabPanel.add(tablePanel, "Table");
		tabPanel.add(new SecondLevelMenuPanel(), "Second Level Menu");

		addNorth(headerPanel, 100);
		add(tabPanel);
	}
}
