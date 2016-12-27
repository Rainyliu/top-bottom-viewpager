package com.rainy.topbottomviewpager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Author: Rainy <br>
 * Description: top-bottom-viewpager <br>
 * Since: 2016/12/6 0006 下午 5:27 <br>
 */

public class MoveImageView extends ImageView {

    private int lastX = 0;
    private int lastY = 0;

    //屏幕宽度
    private static final int screenWidth = 720;
    //屏幕高度
    private static final int screenHeight  = 1280;

    public MoveImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN://按下
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE://移动
                int dx = (int) event.getRawX()-lastX;
                int dy = (int) event.getRawY()-lastY;

                //可以从坐标图看出图片移动后四条边的坐标
                int left = getLeft() + dx;
                int top = getTop() + dy;
                int right = getRight() + dx;
                int bottom = getBottom() + dy;
                //这时表示图片的左边已经移出了屏幕左侧
                if(left < 0){
                    left = 0;
                    right = left + getWidth();
                }
                //这时表示图片右侧已经移出了右侧的屏幕
                if(right > screenWidth){
                    right = screenWidth;
                    left = right - getWidth();
                }
                //这时表示图片上面已经移出了上面的屏幕
                if(top < 0){
                    top = 0;
                    bottom = top + getHeight();
                }
                //这时表示图片底部已经移出了底边的屏幕
                if(bottom > screenHeight){
                    bottom = screenHeight;
                    top = bottom - getHeight();
                }

                layout(left,top,right,bottom);
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP://抬起
                break;
        }
        return true;
    }
}
