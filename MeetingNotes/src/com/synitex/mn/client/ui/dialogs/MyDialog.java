package com.synitex.mn.client.ui.dialogs;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.ui.DialogBox;

public class MyDialog extends DialogBox {

	@Override
	protected void onPreviewNativeEvent(NativePreviewEvent preview) {
		super.onPreviewNativeEvent(preview);

		NativeEvent evt = preview.getNativeEvent();
		if (evt.getType().equals("keydown")) {
			// Use the popup's key preview hooks to close the dialog when either
			// escape is pressed.
			switch (evt.getKeyCode()) {
			case KeyCodes.KEY_ESCAPE:
				hide();
				break;
			}
		}
	}

}
