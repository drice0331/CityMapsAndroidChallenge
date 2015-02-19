package com.citymaps.userphotodisplay.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.citymaps.userphotodisplay.R;

public class LaunchActivity extends BaseActivity {
	
	SharedPreferences prefs;
	boolean authtoken;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);

		prefs = this.getSharedPreferences("com.citymaps.userphotodisplay", 0);
		authtoken = prefs.getBoolean("authtoken", false);
		
		if(authtoken == true) {
			//if already logged in, then start the Main activity
			Intent i = new Intent(this, PhotoDisplayActivity.class);
			startActivity(i);
		} else {
			//else start login activity
			Intent i = new Intent(this, LoginActivity.class);
			startActivity(i);
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
}
