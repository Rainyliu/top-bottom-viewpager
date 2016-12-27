package com.rainy.topbottomviewpager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.rainy.topbottomviewpager.R;

/**
 * Author: Rainy <br>
 * Description: top-bottom-viewpager <br>
 * Since: 2016/12/8 0008 上午 9:36 <br>
 */

public class LabelSelector extends LinearLayout {
    private ImageView txtLabelBtn;
    private ImageView addrLabelBtn;

    private float mLastTouchX = -1;
    private float mLastTouchY = -1;
    public LabelSelector(Context context) {
        this(context,null);
    }

    public LabelSelector(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_label_layout, this);
        txtLabelBtn = (ImageView) findViewById(R.id.iv_tag_tip);//添加心情
        addrLabelBtn = (ImageView) findViewById(R.id.iv_tag_address);//添加地点
    }

    /**
     * 给添加心情按钮设置监听
     * @param listener
     */
    public void setTxtClicked(OnClickListener listener){
        txtLabelBtn.setOnClickListener(listener);
    }

    /**
     * 给添加地点按钮设置监听
     * @param listener
     */
    public void setAddrClicked(OnClickListener listener) {
        addrLabelBtn.setOnClickListener(listener);
    }

    /**
     * 获得上次标签的X坐标
     * @return
     */
    public float getmLastTouchX(){
        return mLastTouchX;
    }

    /**
     * 获得上次标签的Y坐标
     * @return
     */
    public float getmLastTouchY() {
        return mLastTouchY;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_UP://手指抬起(手指离开时 )
            case MotionEvent.ACTION_CANCEL:
                mLastTouchX = event.getX();
                mLastTouchY = event.getY();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    public void showToTop(){
        setVisibility(View.VISIBLE);
        bringToFront();
    }

    public void hide(){
        setVisibility(View.GONE);
    }
}
