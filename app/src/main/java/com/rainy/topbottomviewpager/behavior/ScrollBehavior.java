package com.rainy.topbottomviewpager.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author: Rainy <br>
 * Description: top-bottom-viewpager <br>
 * Since: 2016/12/21 0021 上午 11:56 <br>
 *
 * Behavior这个类默认调用的是两个参数的构造方法
 * 嵌套滑动默认重写下面两个方法：
 * onStartNestedScroll和onNestedPreScroll方法
 *
 * 是处理fling动作的，你想想，我们在滑动松开手的时候，
 * ScrollView是不是还继续滑动一会，那我们也需要让跟随的那个ScrollView也要继续滑动一会，
 * 这种效果，onNestedPreFling就派上用场了
 */

public class ScrollBehavior extends CoordinatorLayout.Behavior<View> {

    public ScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 这里的返回值表明这次滑动我们要不要关心，我们要关心什么样的滑动？当然是y轴方向上的。
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes
     * @return
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    /**
     * 滑动事件，那如何让它滑动起来呢？还是要看onNestedPreScroll的实现
     * 让child的scrollY的值等于目标的scrollY的值就ok啦
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dx
     * @param dy
     * @param consumed
     */
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        int leftScrolled = target.getScrollY();
        child.setScrollY(leftScrolled);
    }

    /**
     * 直接将现在的y轴上的速度传递传递给child，让他fling起来就ok了。
     * @param coordinatorLayout
     * @param child 被动
     * @param target 主动
     * @param velocityX
     * @param velocityY
     * @return
     */
    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY) {
        ((NestedScrollView) child).fling((int) velocityY);
        return true;
    }
}
