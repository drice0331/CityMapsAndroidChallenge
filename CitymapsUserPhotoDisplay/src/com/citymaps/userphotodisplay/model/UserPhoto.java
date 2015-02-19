package com.citymaps.userphotodisplay.model;

import android.os.Parcel;
import android.os.Parcelable;

//NOTE - implementing parcelable in case rotation is a desired support
public class UserPhoto implements Parcelable {

	public UserPhoto() {
		mId = "";
		mCaption = "";
		mUrl = "";
	}

	public UserPhoto(String id, String caption, String url) {
		mId = id;
		mCaption = caption;
		mUrl = url;
	}
	
	private String mCaption;
	private String mId;
	private String mUrl;

	public String getCaption() {
		return mCaption;
	}

	public void setCaption(String caption) {
		this.mCaption = caption;
	}

	public String getId() {
		return mId;
	}

	public void setId(String id) {
		this.mId = id;
	}

	public String getUrl() {
		return mUrl;
	}

	public void setUrl(String url) {
		this.mUrl = url;
	}

	public String toString() {
		return mId + " " + mCaption + " " + mUrl;
	}
	
	// Parcelable implentation
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mUrl);
		dest.writeString(mId);
		dest.writeString(mCaption);

	}

	public static final Parcelable.Creator<UserPhoto> CREATOR = new Parcelable.Creator<UserPhoto>() {
		public UserPhoto createFromParcel(Parcel in) {
			return new UserPhoto(in);
			
		}

		public UserPhoto[] newArray(int size) {
			return new UserPhoto[size];
		}
	};
	
	private UserPhoto(Parcel in) {
		mUrl = in.readString();
		mId = in.readString();
		mCaption = in.readString();
	}
}
