package com.citymaps.userphotodisplay.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.*;
import android.util.AttributeSet;

import com.android.volley.toolbox.NetworkImageView;

/**
 * Custom Imageview extending networkImageView that animates when image drawable set
 * @author drice
 *
 */
public class AnimateInNetworkImageView extends NetworkImageView {

	private static final int FADE_IN_TIME_MS = 250;

	public AnimateInNetworkImageView(Context context) {
		super(context);
	}

	public AnimateInNetworkImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AnimateInNetworkImageView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void setImageBitmap(Bitmap bm) {
		TransitionDrawable td = new TransitionDrawable(new Drawable[] {
				new ColorDrawable(android.R.color.transparent),
				new BitmapDrawable(getContext().getResources(), bm) });

		setImageDrawable(td);
		td.startTransition(FADE_IN_TIME_MS);
	}
}