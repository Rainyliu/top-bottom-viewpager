package com.rainy.topbottomviewpager.listener;

/**
 * Author: Rainy <br>
 * Description: top-bottom-viewpager <br>
 * Since: 2016/12/9 0009 上午 9:57 <br>
 */

import android.view.View;

/**
 * 全局拒绝频繁点击代理Listener
 *
 * @author tongqian.ni
 */
public class GlobalLimitClickOnClickListener implements View.OnClickListener {

    // 全局防频繁点击
    private static long     lastClick;

    private View.OnClickListener listener;

    private long            intervalClick;

    public GlobalLimitClickOnClickListener(View.OnClickListener listener, long intervalClick) {
        this.intervalClick = intervalClick;
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (System.currentTimeMillis() > lastClick
                && System.currentTimeMillis() - lastClick <= intervalClick) {
            return;
        }
        listener.onClick(v);
        lastClick = System.currentTimeMillis();
    }
}
