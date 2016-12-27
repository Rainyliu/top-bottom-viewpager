package com.rainy.topbottomviewpager.utils;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.rainy.topbottomviewpager.App;
import com.rainy.topbottomviewpager.constants.AppConstants;
import com.rainy.topbottomviewpager.view.LabelView;
import com.rainy.topbottomviewpager.view.MyHighlightView;
import com.rainy.topbottomviewpager.view.MyImageViewDrawableOverlay;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Author: Rainy <br>
 * Description: top-bottom-viewpager <br>
 * Since: 2016/12/8 0008 下午 2:42 <br>
 */

public class EffectUtil {
    private static List<MyHighlightView> hightlistViews = new CopyOnWriteArrayList<MyHighlightView>();
    public static int getStandDis(float realDis, float baseWidth) {
        float imageWidth = baseWidth <= 0 ? App.getApp().getScreenWidth() : baseWidth;
        float radio = AppConstants.DEFAULT_PIXEL / imageWidth;
        return (int) (radio * realDis);
    }

    public static void clear() {
        hightlistViews.clear();
    }

    //----添加标签-----
    public static void addLabelEditable(MyImageViewDrawableOverlay overlay, ViewGroup container,
                                        LabelView label, int left, int top) {
        addLabel(container, label, left, top);
        addLabel2Overlay(overlay, label);
    }

    private static void addLabel(ViewGroup container, LabelView label, int left, int top) {
        label.addTo(container, left, top);
    }

    public static void removeLabelEditable(MyImageViewDrawableOverlay overlay, ViewGroup container,
                                           LabelView label) {
        container.removeView(label);
        overlay.removeLabel(label);
    }

    /**
     * 使标签在Overlay上可以移动
     * @param overlay
     * @param label
     */
    private static void addLabel2Overlay(final MyImageViewDrawableOverlay overlay,
                                         final LabelView label) {
        //添加事件，触摸生效
        overlay.addLabel(label);
        label.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:// 手指按下时
                        overlay.setCurrentLabel(label, event.getRawX(), event.getRawY());
                        return false;
                    default:
                        return false;
                }
            }
        });
    }

}
