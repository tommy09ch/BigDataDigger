package com.teamkitKAT.bigdatadigger;

import java.util.ArrayList;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	private Context context;
	String url_string = "http://www.albaspectrum.com:8210/CS480WebService/CS480WebService.svc/UserServices/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		Button loginB = (Button) findViewById(R.id.loginBut);
		final EditText user = (EditText) findViewById(R.id.user);
		final EditText pass = (EditText) findViewById(R.id.pass);

		loginB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (user.getText().toString().isEmpty()
						|| pass.getText().toString().isEmpty()) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							LoginActivity.this);
					builder.setMessage(
							"Login required both user name and password.")
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
					url_string += user.getText().toString() + "/"
							+ pass.getText().toString();
					url_string = url_string.replaceAll("\\s+", "%20");
					Log.e("Debug", url_string);

					new ProgressTask(LoginActivity.this).execute();
				}
			}
		});
	}

	private class ProgressTask extends AsyncTask<String, Void, Boolean> {
		private ProgressDialog dialog;
		private String result;

		// private List<Message> messages;
		public ProgressTask(Activity activity) {
			context = activity;
			dialog = new ProgressDialog(context);
		}

		protected void onPreExecute() {
			this.dialog.setMessage("Logging in...");
			this.dialog.show();
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			if (dialog.isShowing()) {
				dialog.dismiss();
			}

			if (result.equalsIgnoreCase("true")) {
				startActivity(new Intent(LoginActivity.this, MainActivity.class));
				finish();
			} else {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						LoginActivity.this);
				builder.setMessage("Login Fail!")
						.setCancelable(false)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										// do things
									}
								});
				AlertDialog alert = builder.create();
				alert.show();
			}
		}

		@Override
		protected Boolean doInBackground(String... params) {
			JSONParser jParser = new JSONParser();
			// get JSON data from URL
			result = jParser.getBoolFromUrl(url_string);
			return null;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
