package com.citymaps.userphotodisplay.model;

import java.util.ArrayList;
import java.util.Collection;

import android.content.Context;

public class UserPhotoList {
	private static final String TAG = "UserPhotoList";
	
	private ArrayList<UserPhoto> mPhotoList;
	
	private static UserPhotoList sPhotoList;
	private Context mAppContext;
	
	private UserPhotoList(Context appContext) {
		mAppContext = appContext;
		mPhotoList = new ArrayList<UserPhoto>();
	}
	
	public static UserPhotoList get(Context c) {
		if(sPhotoList == null) {
			sPhotoList = new UserPhotoList(c.getApplicationContext());
		}
		return sPhotoList;
	}
	
	public void addUserPhoto(UserPhoto userPhoto) {
		mPhotoList.add(userPhoto);
	}
	
	public void addUserPhotoList(Collection<UserPhoto> userPhotoList) {
		mPhotoList.addAll(userPhotoList);
	}
	
	public void deleteCrime(UserPhoto userPhoto) {
		mPhotoList.remove(userPhoto);
	}
	
	public ArrayList<UserPhoto> getUserPhotoList() {
		return mPhotoList;
	}
}
