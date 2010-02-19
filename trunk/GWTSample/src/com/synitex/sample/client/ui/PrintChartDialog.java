package com.synitex.sample.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.synitex.sample.client.gchart.GChartExample02;
import com.synitex.sample.client.ui.util.Print;

public class PrintChartDialog extends MyDialog {

	private static PrintChartDialogUiBinder uiBinder = GWT
			.create(PrintChartDialogUiBinder.class);

	interface PrintChartDialogUiBinder extends
			UiBinder<Widget, PrintChartDialog> {
	}

	@UiField
	DivElement mainDiv;

	@UiField
	Button btnOk;

	public static void showDialog() {
		PrintChartDialog d = new PrintChartDialog();
		d.show();
		d.center();
	}

	public PrintChartDialog() {
		setText("Test printing");
		setWidget(uiBinder.createAndBindUi(this));
		setAnimationEnabled(false);
		setGlassEnabled(true);
		// setSize("400px", "400px");

		GChartExample02 gchart = new GChartExample02();
		mainDiv.appendChild(gchart.getElement());
		gchart.update();
	}

	@UiHandler("btnOk")
	public void onOkClick(ClickEvent ev) {
		hide();
	}

	@UiHandler("btnPrint")
	public void onPrintClick(ClickEvent ev) {
		Print.it(this);
	}

}
