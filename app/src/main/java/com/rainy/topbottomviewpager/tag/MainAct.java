package com.rainy.topbottomviewpager.tag;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rainy.topbottomviewpager.view.CenterImageView;
import com.rainy.topbottomviewpager.view.LayersView;
import com.rainy.topbottomviewpager.view.MyView;
import com.rainy.topbottomviewpager.view.TagView;

public class MainAct extends AppCompatActivity {
    private MyView myView;
    private LayersView layersView;
    private CenterImageView centerImageView;
    private boolean flag = false;
    private TagView tagView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tagView = new TagView(this, TagView.Direction.Left);
        setContentView(tagView);
//        setContentView(R.layout.activity_main3);
//        setContentView(R.layout.activity_main2);
//        myView = new MyView(this);
//        setContentView(myView);
//        centerImageView =(CenterImageView)findViewById(R.id.goodsImage);
//        centerImageView.setCenterImgShow(flag);

//        layersView = new LayersView(this);
//        setContentView(layersView);
//        myView.setBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.bg_test));
    }

//    public void click(View view){
//        if(!flag){
//            flag = true;
//        }else {
//            flag = false;
//        }
//        Log.d("mtag","flag=============="+flag);
//        centerImageView.setCenterImgShow(flag);
//    }
}
