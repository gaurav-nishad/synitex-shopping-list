package com.synitex.android.buy;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ViewLastList extends Activity implements OnClickListener {

	private Button btn;
	private List<String> items = new ArrayList<String>();
	private ListView itemsList;
	private ArrayAdapter<String> adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		btn = (Button) findViewById(R.id.AddItemButton);
		btn.setOnClickListener(this);

		itemsList = (ListView) findViewById(R.id.ItemsList);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_checked, items);
		itemsList.setAdapter(adapter);

	}

	public void onClick(View v) {
		if (v == btn) {
			// items.add("New item");
			adapter.add("New Item");
		}
	}

}