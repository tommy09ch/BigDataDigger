package com.teamkitKAT.bigdatadigger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.*;
import android.app.Activity;
import android.content.Intent;
import android.view.*;

public class SplashScreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent i = checkForFirstLoad();
				startActivity(i);
				finish();
			}
		}, 2000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}

	public Intent checkForFirstLoad() {
		String sdPath = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		File cache = new File(sdPath + "/Innutrac/vals/cache");

		if (cache.exists()) {
			return new Intent(this, MainActivity.class);
		} else {
			File dir = new File(sdPath + "/Innutrac/vals");
			dir.mkdirs();
			cache = new File(dir, "cache");
			try {
				FileOutputStream f = new FileOutputStream(cache);
				f.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return new Intent(this, WelcomeMessageActivity.class);
		}
	}
}
