package com.clinics.gwt.client.gchart;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.googlecode.gchart.client.GChart;

public class GChartExample02 extends GChart {
	final String[] groupLabels = { "<html>01.07.98", "<html>01.07.05",
			"<html>01.07.08", "<html>01.02.09" };
	final int[] values = new int[] { 68, 80, 100, 95 };

	final int MAX_REVENUE = 130;
	final int WIDTH = 300;
	final int HEIGHT = 200;
	Button updateButton = new Button(
			"<b><big>Generate New Simulated Revenues</big></b>");

	public GChartExample02() {

		setChartSize(WIDTH, HEIGHT);
		setChartTitle("<b><big><big>" + "Blood test: Glucose"
				+ "</big></big><br>&nbsp;</b>");
		updateButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				for (int iCurve = 0; iCurve < getNCurves(); iCurve++) {
					for (int iPoint = 0; iPoint < getCurve(iCurve).getNPoints(); iPoint++) {
						getCurve(iCurve).getPoint(iPoint).setY(
								Math.random() * MAX_REVENUE);
					}
				}
				update();
				updateButton.setFocus(true);
			}
		});
		setChartFootnotes(updateButton);
		for (int i = 0; i < 4; i++) {

			addCurve(); // one curve per quarter
			getCurve().getSymbol().setSymbolType(SymbolType.VBAR_SOUTHWEST);
			getCurve().getSymbol().setBackgroundColor("green");
			// getCurve().setLegendLabel(barLabels[iCurve]);
			// getCurve().getSymbol().setHovertextTemplate(
			// GChart.formatAsHovertext(barLabels[iCurve]
			// + " revenue=${y}"));
			getCurve().getSymbol().setModelWidth(1.0);
			getCurve().getSymbol().setBorderColor("black");
			getCurve().getSymbol().setBorderWidth(1);

			getCurve().addPoint(1 + i * (groupLabels.length + 1), values[i]);

			getXAxis().addTick(5 * i, groupLabels[i]);

			// for (int jGroup = 0; jGroup < groupLabels.length; jGroup++) {
			// the '+1' creates a bar-sized gap between groups
			// getCurve().addPoint(
			// 1 + iCurve + jGroup * (barLabels.length + 1),
			// Math.random() * MAX_REVENUE);
			// getCurve().getPoint().setAnnotationText(barLabels[iCurve]);
			// getCurve().getPoint().setAnnotationLocation(
			// AnnotationLocation.NORTH);
			// }
		}

		addCurve();
		// solid, 2px thick, 1px resolution, connecting lines:
		getCurve().getSymbol().setSymbolType(SymbolType.LINE);
		getCurve().getSymbol().setFillThickness(2);
		getCurve().getSymbol().setFillSpacing(1);
		// Make center-fill of square point markers same color as line:
		getCurve().getSymbol().setBackgroundColor("red");
		getCurve().getSymbol().setBorderColor("red");
		getCurve().addPoint(-2, 72);
		getCurve().addPoint(20, 72);

		addCurve();
		// solid, 2px thick, 1px resolution, connecting lines:
		getCurve().getSymbol().setSymbolType(SymbolType.LINE);
		getCurve().getSymbol().setFillThickness(2);
		getCurve().getSymbol().setFillSpacing(1);
		// Make center-fill of square point markers same color as line:
		getCurve().getSymbol().setBackgroundColor("red");
		getCurve().getSymbol().setBorderColor("red");
		getCurve().addPoint(-2, 122);
		getCurve().addPoint(20, 122);

		// for (int i = 0; i < groupLabels.length; i++) {
		// formula centers tick-label horizontally on each group
		// getXAxis().addTick(
		// barLabels.length / 2. + i * (barLabels.length + 1),
		// groupLabels[i]);
		// }
		getXAxis().setTickLabelFontSize(20);
		getXAxis().setTickLabelThickness(40);
		getXAxis().setTickLength(6); // small tick-like gap...
		getXAxis().setTickThickness(0); // but with invisible ticks
		getXAxis().setAxisMin(-2); // keeps first bar on chart

		getYAxis().setAxisMin(55); // Based on sim revenue range
		getYAxis().setAxisMax(MAX_REVENUE); // of 0 to MAX_REVENUE.
		getYAxis().setTickCount(6);
		getYAxis().setHasGridlines(true);
		getYAxis().setTickLabelFormat("###");
	}
}
