package com.example.rateaniner;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class HomeActivity extends Activity {

	ImageView browseSubjects, searchBtn;
	ArrayList<Professor> result;
	CustomAdapter adapter;
	ListView mainList;
	EditText searchET;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		searchET = (EditText) findViewById(R.id.editText1);
		result = new ArrayList<Professor>();
		browseSubjects = (ImageView) findViewById(R.id.imageView5);
		mainList = (ListView) findViewById(R.id.mainList);

		ParseQuery<ParseObject> q = ParseQuery.getQuery("Staff");
		q.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> arg0, ParseException arg1) {
				if (arg1 == null) {
					result = new ArrayList<Professor>();
					for (ParseObject o : arg0) {
						Professor p = new Professor();
						p.setDept(o.getString("dept"));
						p.setName(o.getString("name"));
						p.setPoistion(o.getString("position"));
						result.add(p);
					}
					adapter = new CustomAdapter(HomeActivity.this,
							R.layout.custom_view, result);
					mainList.setAdapter(adapter);

				} else {
					Toast.makeText(HomeActivity.this, "No results found",
							Toast.LENGTH_LONG).show();
				}
			}
		});

		searchET.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				String text = searchET.getText().toString()
						.toLowerCase(Locale.getDefault());
				adapter.filter(text);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void showCheckbox() {

		AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
		helpBuilder.setTitle("Subjects");

		LayoutInflater inflater = getLayoutInflater();
		View checkboxLayout = inflater.inflate(R.layout.popuplayout, null);
		helpBuilder.setView(checkboxLayout);

		helpBuilder.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// Do nothing but close the dialog
					}
				});

		// Remember, create doesn't show the dialog
		AlertDialog helpDialog = helpBuilder.create();
		helpDialog.show();
	}
}
