package com.teamkitKAT.bigdatadigger;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Spinner;

public class AdvanceSearchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advance_search);
		
		final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView1);
		final LayoutInflater inf = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		scrollView.addView(inf.inflate(R.layout.tb_searchorder, null));
		
		Spinner categSpinner = (Spinner) findViewById(R.id.spinner_selectCategories);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category_array, android.R.layout.simple_spinner_dropdown_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		categSpinner.setAdapter(adapter);
		
		categSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				scrollView.removeAllViews();
				switch(arg2) {
				case 0:
					scrollView.addView(inf.inflate(R.layout.tb_searchorder, null));
					break;
				case 1:
					scrollView.addView(inf.inflate(R.layout.tb_searchaddress, null));
					break;
				case 2:
				case 3:
				case 4:
				case 5:
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.advance_search, menu);
		return true;
	}

}
