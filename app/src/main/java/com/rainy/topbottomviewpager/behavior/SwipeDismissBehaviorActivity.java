package com.rainy.topbottomviewpager.behavior;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.rainy.topbottomviewpager.R;

/**
 * SwipeDismissBehavior可以替代RecyclerView的ItemTouchHelper或者其他列表滑动删除库吗？
 * 
 * 因为CoordinatorLayout遍历子View的时候，只遍历了第一层view，
 * 而列表的滑动删除对象是在RecyclerView的里面，
 * 不是CoordinatorLayout的直接子view。
 * 所以SwipeDismissBehavior只可以用在CoordinatorLayout的子View上。
 */
public class SwipeDismissBehaviorActivity extends AppCompatActivity {
    public static final String TAG = "SwipeDismissActivity";//tag最多23个字符
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_dismiss_behavior);

        textView = (TextView) findViewById(R.id.text);
        SwipeDismissBehavior swipeDismissBehavior = new SwipeDismissBehavior();

        // 告诉如果一个视图设置阈值一直拖到足以被关闭。
//        swipeDismissBehavior.setDragDismissDistance(0.5F);
//
//        // 最小滑动距离时view的透明度。
//        swipeDismissBehavior.setStartAlphaSwipeDistance(0F);
//
//        // 最大滑动距离时view的透明度。
//        swipeDismissBehavior.setEndAlphaSwipeDistance(0.5F);
//
//        // 阀值，也就是敏感度。
//        swipeDismissBehavior.setSensitivity(0);

        // 滑动方向。
//        swipeDismissBehavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END);
        swipeDismissBehavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.setBehavior(swipeDismissBehavior);
        swipeDismissBehavior.setListener(onDismissListener);
    }

    private SwipeDismissBehavior.OnDismissListener onDismissListener = new SwipeDismissBehavior.OnDismissListener() {
        @Override
        public void onDismiss(View view) {
            Log.d(TAG, "onDismiss: ");//然后调这个方法
        }

        @Override
        public void onDragStateChanged(int state) {
            Log.d(TAG, "onDragStateChanged: ");//先调这个方法
        }
    };
}
