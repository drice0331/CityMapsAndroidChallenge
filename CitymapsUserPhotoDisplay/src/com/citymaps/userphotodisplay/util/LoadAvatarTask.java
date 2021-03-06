package com.citymaps.userphotodisplay.util;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Retrieves Avatar Url for header in Main page
 * @author drice
 *
 */
public class LoadAvatarTask extends AsyncTask<Void, Void, String> {

	LoadAvatarTaskListener listener;
	Context mContext;

	public LoadAvatarTask(Context listener) {
		mContext = listener;
		this.listener = (LoadAvatarTaskListener) listener;
	}


	@Override
	protected String doInBackground(Void... params) {
		// listener.onTaskBackgroundWork();
		if (isCancelled()) {
			return null;
		}

		return new UserJsonParser().fetchAvatarUrl();
	}

	@Override
	protected void onPostExecute(String result) {
		listener.onAvatarTaskPostExecute(result);
		super.onPostExecute(result);
	}


	// Callbacks for interacting with activity on UI Thread
	public interface LoadAvatarTaskListener {
		void onAvatarTaskPostExecute(String result);
	}
}
