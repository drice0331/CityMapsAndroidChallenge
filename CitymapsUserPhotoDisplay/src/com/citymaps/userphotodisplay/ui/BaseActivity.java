package com.citymaps.userphotodisplay.ui;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

/**
 * BaseActivity for locking the starting orientation of the device
 * @author drice
 *
 */
public class BaseActivity extends Activity {

	protected static final int PORTRAIT = 1;
	protected static final int LANDSCAPE = 2;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		lockOrientation();
	}

	protected void lockOrientation() {
		int orientation = this.getResources().getConfiguration().orientation;
		if (orientation == PORTRAIT) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		} else {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}
	}
}
