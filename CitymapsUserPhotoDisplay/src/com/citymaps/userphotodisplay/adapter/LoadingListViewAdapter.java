package com.citymaps.userphotodisplay.adapter;

import java.util.ArrayList;

import com.android.volley.toolbox.ImageLoader;
import com.citymaps.userphotodisplay.R;
import com.citymaps.userphotodisplay.model.UserPhoto;
import com.citymaps.userphotodisplay.view.AnimateInNetworkImageView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class LoadingListViewAdapter extends ArrayAdapter<UserPhoto> {

	Context mContext;
	ArrayList<UserPhoto> mItems;
	ImageLoader mImageLoader;

	public LoadingListViewAdapter(Context context, int resource, ArrayList<UserPhoto> items, ImageLoader imageLoader) {
		super(context, resource);
		mItems = items;
		mContext = context;
		mImageLoader = imageLoader;
	}

	public class ViewHolder {
		AnimateInNetworkImageView networkImageView;
		TextView nameTextView;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater infalInflater = (LayoutInflater) this.mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_item_photo, null);
			holder.networkImageView = (AnimateInNetworkImageView) convertView
					.findViewById(R.id.networkImageView);
			holder.nameTextView = (TextView) convertView.findViewById(R.id.name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder)convertView.getTag();
		}

        final UserPhoto userphoto = mItems.get(position);
		
        holder.networkImageView.setDefaultImageResId(R.drawable.custom_progress_bar);
        holder.networkImageView.setErrorImageResId(R.drawable.error);
        holder.networkImageView.setAdjustViewBounds(true);
        holder.networkImageView.setImageUrl(userphoto.getUrl(), mImageLoader);
        holder.nameTextView.setText(userphoto.getCaption());

		Log.d("ADAPTER", "position is " + position);
		return convertView;
	}

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public UserPhoto getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
