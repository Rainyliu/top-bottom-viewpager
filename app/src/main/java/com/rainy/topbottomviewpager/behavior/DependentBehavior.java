package com.rainy.topbottomviewpager.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Author: Rainy <br>
 * Description: top-bottom-viewpager <br>
 * Since: 2016/12/21 0021 上午 11:24 <br>
 * 现在我们就来根据第一种情况尝试自定义一个Behavior，这里我们实现一个简单的效果，让一个View根据另一个View上下移动。
   首先我们来自定义一个Behavior，起名为DependentBehavior
 */

public class DependentBehavior  extends CoordinatorLayout.Behavior<View>{

    public DependentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof TextView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        int offset = dependency.getTop() - child.getTop();
        ViewCompat.offsetTopAndBottom(child,offset);
        return true;
    }
}
