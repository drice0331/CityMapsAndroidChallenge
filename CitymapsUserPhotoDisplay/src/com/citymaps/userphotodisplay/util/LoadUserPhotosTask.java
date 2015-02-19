package com.citymaps.userphotodisplay.util;

import java.util.ArrayList;

import com.citymaps.userphotodisplay.model.UserPhoto;
import com.citymaps.userphotodisplay.model.UserPhotoList;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Async task for loading photos into listview
 * @author drice
 *
 */
public class LoadUserPhotosTask extends AsyncTask<Void, Void, Void> {

	LoadUserPhotosTaskListener listener;
	Context mContext;

	public LoadUserPhotosTask(Context listener) {
		mContext = listener;
		this.listener = (LoadUserPhotosTaskListener) listener;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		listener.onTaskPreExecute();
	}

	@Override
	protected Void doInBackground(Void... params) {
		// listener.onTaskBackgroundWork();
		if (isCancelled()) {
			return null;
		}
		// Simulates a background task
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		UserPhotoList userPhotoList = UserPhotoList.get(mContext);
		int offset = userPhotoList.getUserPhotoList().size();
		ArrayList<UserPhoto> newList = new UserJsonParser().fetchItems(offset);
		userPhotoList.addUserPhotoList(newList);

		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		listener.onTaskPostExecute();
		super.onPostExecute(result);

	}

	@Override
	protected void onCancelled() {
		// Notify the loading more operation has finished
		listener.onTaskCancel();
	}

	// Callbacks for interacting with activity on UI Thread
	public interface LoadUserPhotosTaskListener {
		void onTaskPreExecute();

		void onTaskPostExecute();

		void onTaskCancel();
	}

}
