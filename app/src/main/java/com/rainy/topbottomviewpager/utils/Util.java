package com.rainy.topbottomviewpager.utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.rainy.topbottomviewpager.bean.TagItem;
import com.rainy.topbottomviewpager.view.LabelSelector;
import com.rainy.topbottomviewpager.view.LabelView;
import com.rainy.topbottomviewpager.view.MyImageViewDrawableOverlay;

import java.util.List;

/**
 * Author: Rainy <br>
 * Description: top-bottom-viewpager <br>
 * Since: 2016/12/8 0008 上午 9:28 <br>
 */

public class Util {
    //小白点标签
//    private LabelView emptyLabelView;
    //添加标签
    public static void addLable(Context context,TagItem tagItem, LabelSelector labelSelector, LabelView emptyLabelView, List<LabelView> labels,MyImageViewDrawableOverlay mImageView){
        labelSelector.hide();
        emptyLabelView.setVisibility(View.INVISIBLE);
        if(labels.size() >= 5){
            Toast.makeText(context,"您只能添加5个标签！",Toast.LENGTH_SHORT ).show();
        }else {
            int left = emptyLabelView.getLeft();
            int top = emptyLabelView.getTop();
            if(labels.size() == 0 && left == 0 && top == 0){

            }
        }
    }
}
