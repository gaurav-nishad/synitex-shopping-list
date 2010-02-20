package com.clinics.gwt.client.main;

import com.clinics.gwt.client.gchart.GChartExample02;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.gchart.client.GChart;

public class GChartExamplePanel extends Composite {

    private DockLayoutPanel panel;

    public GChartExamplePanel() {

        VerticalPanel vp = new VerticalPanel();

        GChart gchart = new GChartExample02();
        vp.add(gchart);
        gchart.update();

        initWidget(vp);
    }

}
