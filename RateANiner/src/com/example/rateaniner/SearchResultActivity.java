package com.example.rateaniner;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SearchResultActivity extends Activity {

	final private String[] instructorArray = { "", "" };

	private ListView searchResultListView;
	private ArrayAdapter arrayAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);
		
		searchResultListView = (ListView) findViewById(R.id.listView1);

		// this-The current activity context.
		// Second param is the resource Id for list layout row item
		// Third param is input array 
		arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, instructorArray);
		searchResultListView.setAdapter(arrayAdapter);
		
	}
	
}
