package com.rainy.topbottomviewpager.newdemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.rainy.topbottomviewpager.R;

public class PreViewActivity extends AppCompatActivity {

    private String TAG = "PreViewActivity";
    private Uri mImageUri;			//目标图片的Uri
    private String mImagePath;		//目标图片的路径

    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_view);

        mImagePath = getIntent().getStringExtra("tag_image_path");
        mImageUri = Uri.parse(mImagePath);
        Logger.d(TAG, "imagePath:" + mImagePath);
        Logger.d(TAG, "mImageUri:" + mImageUri);

        initView();
    }

    private void initView() {
        mImageView = (ImageView)findViewById(R.id.pv_image);
        if(mImageUri != null){
            mImageView.setImageURI(mImageUri);
        }
    }
}
