package com.clinics.gwt.client.gchart;

import com.googlecode.gchart.client.GChartCanvasFactory;
import com.googlecode.gchart.client.GChartCanvasLite;

public class GWTCanvasBasedCanvasFactory implements GChartCanvasFactory {

	public GChartCanvasLite create() {
		GChartCanvasLite result = new GWTCanvasBasedCanvasLite();
		return result;
	}

}
