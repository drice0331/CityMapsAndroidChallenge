package com.citymaps.userphotodisplay.ui;

import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.citymaps.userphotodisplay.R;
import com.citymaps.userphotodisplay.util.MyVolleySingleton;

/**
 * Single activity for image of clicked on listview item
 * @author drice
 *
 */
public class ViewImageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);

        String url = getIntent().getStringExtra("url");

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        ImageLoader mImageLoader = MyVolleySingleton.getInstance(this).getImageLoader();
        mImageLoader.get(url,
                ImageLoader.getImageListener(imageView,
                        R.drawable.custom_progress_bar,
                        R.drawable.error),
                        600,
                        600
        );
    }
}
