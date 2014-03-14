package com.teamkitKAT.bigdatadigger;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	final String[] CATEGORY = { "Order", "Address", "Customer", "Payment",
			"Item" };
	String selectedCateg = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final EditText searchKey = (EditText) findViewById(R.id.et_searchKey);
		final Button searchCateg = (Button) findViewById(R.id.searchCateg);
		final Button searchAll = (Button) findViewById(R.id.searchAll);
		final Button searchGo = (Button) findViewById(R.id.searchGo);
		final TextView searchDes = (TextView) findViewById(R.id.search_description);
		searchAll.setSelected(true);

		searchCateg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						MainActivity.this);
				alert.setTitle("Select a Category:");
				alert.setItems(CATEGORY, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						selectedCateg = CATEGORY[which];
						searchDes.setText("Searching by: " + CATEGORY[which]);
						searchKey.setHint("Enter in an ID");
						searchCateg.setSelected(true);
						searchAll.setSelected(false);
					}

				});
				alert.show();
			}
		});

		searchAll.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				searchDes.setText("Searching All");
				searchKey.setHint("Enter a query");
				searchAll.setSelected(true);
				searchCateg.setSelected(false);
			}

		});

		searchGo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (searchKey.getText().toString().isEmpty()) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							MainActivity.this);
					builder.setMessage("Search field is empty!")
							.setCancelable(false)
							.setPositiveButton("OK",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											// do things
										}
									});
					AlertDialog alert = builder.create();
					alert.show();
				} else {
					Intent i = new Intent(MainActivity.this,
							ResultActivity.class);
					if (searchCateg.isSelected()) {
						i.putExtra("categ", selectedCateg).putExtra("categkey",
								searchKey.getText().toString());
					} else if (searchAll.isSelected()) {
						i.putExtra("allkey", searchKey.getText().toString());
					}
					startActivity(i);
				}

			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
