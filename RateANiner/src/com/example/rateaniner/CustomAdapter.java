package com.example.rateaniner;

import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Professor> {

	ArrayList<Professor> proflist = new ArrayList<Professor>();
	ArrayList<Professor> arraylist = new ArrayList<Professor>();
	int resource;
	Context context;

	public CustomAdapter(Context context, int resource,
			ArrayList<Professor> profList) {
		super(context, resource);
		this.resource = resource;
		this.proflist = profList;
		this.context = context;

		this.arraylist.addAll(profList);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return proflist.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi;
			vi = LayoutInflater.from(getContext());
			v = vi.inflate(R.layout.custom_view, null);
		}

		TextView tv1 = (TextView) v.findViewById(R.id.textViewName);
		TextView tv2 = (TextView) v.findViewById(R.id.textViewDept);

		Professor p = proflist.get(position);
		tv1.setText(p.getName());
		tv2.setText(p.getDept());
		return v;
	}

	public void filter(String charText) {
		charText = charText.toLowerCase(Locale.getDefault());
		proflist.clear();
		if (charText.length() == 0) {
			proflist.addAll(arraylist);
		} else {
			for (Professor wp : arraylist) {
				if (wp.getName().toLowerCase(Locale.getDefault())
						.contains(charText)) {
					proflist.add(wp);
				}
			}
		}
		notifyDataSetChanged();
	}
}
