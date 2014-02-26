package com.teamkitKAT.bigdatadigger;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ResultActivity extends Activity {
	private Context context;
	private static final String VTYPE = "vehicleType";
	private static final String VCOLOR = "vehicleColor";
	private static final String FUEL = "fuel";
	private static final String TREAD = "treadType";

	ArrayList<HashMap<String, String>> jsonlist = new ArrayList<HashMap<String, String>>();
	ListView lv;

	// get URL from web team
	String url_string = "http://docs.blackberry.com/sampledata.json";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		String searchKey = getIntent().getStringExtra("searchKey");
		// incorporate searchKey with URL string
		// url_string += searchKey;

		new ProgressTask(ResultActivity.this).execute();
	}

	private class ProgressTask extends AsyncTask<String, Void, Boolean> {
		private ProgressDialog dialog;

		private Activity activity;

		// private List<Message> messages;
		public ProgressTask(Activity activity) {
			this.activity = activity;
			context = activity;
			dialog = new ProgressDialog(context);
		}

		protected void onPreExecute() {
			this.dialog.setMessage("Progress start");
			this.dialog.show();
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			if (dialog.isShowing()) {
				dialog.dismiss();
			}

			lv = (ListView) findViewById(R.id.list);

			String[] from = { VTYPE, VCOLOR, FUEL, TREAD };

			int[] to = { R.id.item1, R.id.item2, R.id.item3, R.id.item4 };

			ListAdapter adapter = new SimpleAdapter(context, jsonlist,
					R.layout.grid_item, from, to);

			lv.setAdapter(adapter);
		}

		@Override
		protected Boolean doInBackground(String... params) {
			JSONParser jParser = new JSONParser();

			// get JSON data from URL
			JSONArray json = jParser.getJSONFromUrl(url_string);

			for (int i = 0; i < json.length(); i++) {

				try {
					JSONObject c = json.getJSONObject(i);
					String vtype = c.getString(VTYPE);

					String vcolor = c.getString(VCOLOR);
					String vfuel = c.getString(FUEL);
					String vtread = c.getString(TREAD);

					Log.e("$$$DEBUG$$$", vtype);
					Log.e("$$$DEBUG$$$", vcolor);
					Log.e("$$$DEBUG$$$", vfuel);
					Log.e("$$$DEBUG$$$", vtread);

					HashMap<String, String> map = new HashMap<String, String>();

					// Add child node to HashMap key & value
					map.put(VTYPE, vtype);
					map.put(VCOLOR, vcolor);
					map.put(FUEL, vfuel);
					map.put(TREAD, vtread);
					jsonlist.add(map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return null;
		}

	}

}
