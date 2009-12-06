package com.synitex.android.buy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class CreateItemActivity extends Activity {

	private DbHelper dbHelper;
	private EditText fldName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbHelper = new DbHelper(this);
		dbHelper.open();
		setContentView(R.layout.createitem);
		fldName = (EditText) findViewById(R.id.FldName);
	}

	protected void onCreateItemClick() {
		String name = fldName.getText().toString();
		Long id = dbHelper.createItem(name);
	}
}
