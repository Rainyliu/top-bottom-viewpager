package com.rainy.topbottomviewpager.newdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rainy.topbottomviewpager.R;

/**
 * Author: Rainy <br>
 * Description: top-bottom-viewpager <br>
 * Since: 2016/12/19 0019 下午 5:46 <br>
 */

public class TagViewRight extends TagView {
    public TagViewRight(Context paramContext) {
        this(paramContext, null);
    }

    public TagViewRight(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        LayoutInflater.from(paramContext).inflate(R.layout.tag_view_right, this);
        this.textview = ((TextView) findViewById(R.id.text));
        this.textview.getBackground().setAlpha(178);
        this.textview.setVisibility(View.VISIBLE);
        this.blackIcon1 = ((ImageView) findViewById(R.id.blackIcon1));
        this.blackIcon2 = ((ImageView) findViewById(R.id.blackIcon2));
        this.brandIcon = ((ImageView) findViewById(R.id.brandIcon));
        this.geoIcon = ((ImageView) findViewById(R.id.geoIcon));
//		this.brandIcon.setVisibility(View.GONE);
        this.viewPointer = brandIcon;
        setVisible();
    }
}