package com.citymaps.userphotodisplay.ui;

import com.citymaps.userphotodisplay.R;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

	EditText loginField;
	SharedPreferences prefs;
	boolean authtoken;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);

		loginField = (EditText) findViewById(R.id.login_name);
		prefs = this.getSharedPreferences("com.citymaps.userphotodisplay", 0);
		authtoken = prefs.getBoolean("authtoken", false);
		
		if(authtoken == true) {
			//if already logged in, then start the Main activity
			Intent i = new Intent(this, PhotoDisplayActivity.class);
			startActivity(i);
		}
	}

	public void toPhotoDisplay(View view) {
		String loginName = loginField.getText().toString();
		Editor editor = prefs.edit();
		editor.putBoolean("authtoken", true);
		editor.putString("firstName", loginName);
		editor.commit();
		if (loginName.equals("Aaron Rudenstine")) {
			Intent intent = new Intent(this, PhotoDisplayActivity.class);
			startActivity(intent);
		} else {
			Toast toast =Toast.makeText(this, "Sure you aren't Aaron Rudenstine??", Toast.LENGTH_SHORT);
			toast.show();
		}
	}
	
	public void onResume() {
		super.onResume();
		if(authtoken == true) {
			finish();
		}
	}
}
