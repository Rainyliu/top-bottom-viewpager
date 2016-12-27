package com.rainy.topbottomviewpager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rainy.topbottomviewpager.R;

/**
 * Author: Rainy <br>
 *     实现原理：
 1：实现自定义一个ViewGroup类型,设置好它的背景,
 2：在onTouch  down的时候,获取相对于view的按下的x,y坐标,然后遍历所有的子view判断是否重叠因为每个view其实都是一个矩形,Rect类方法中可以判断该点是否在view上,
 *  仿nice打标签
 * Description: top-bottom-viewpager <br>
 * Since: 2016/12/19 0019 上午 10:36 <br>
 */

public class TagView extends RelativeLayout implements TextView.OnEditorActionListener {
    private TextView tvPictureTagLabel;
    private EditText etPictureTagLabel;
    private View loTag;

    private InputMethodManager inputMethodManager;
    public enum Status{//标签是编辑状态还是正常状态
        Normal,Edit
    }

    public enum Direction{//标签是左边还是右边
        Left,Right
    }

    private Direction direction = Direction.Left;//标签是左边还是右边
    private Context mContext;

    private static final int ViewWidth = 80;//单位像素
    private static final int ViewHeight = 50;

    public TagView(Context context,Direction direction) {
        super(context);
        this.direction = direction;
        this.mContext = context;

        initViews();
        init();
        initEvents();
    }

    /**
     * 初始化事件
     */
    private void initEvents() {
        etPictureTagLabel.setOnEditorActionListener(this);
    }

    private void init() {
        inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        directionChange();
    }

    /**
     * 方向改变
     */
    private void directionChange() {
        switch (direction){
            case Left:
                loTag.setBackgroundResource(R.drawable.bg_picturetagview_tagview_left);
                break;
            case Right:
                loTag.setBackgroundResource(R.drawable.bg_picturetagview_tagview_right);
                break;
        }
    }

    /**
     * 初始化view
     */
    private void initViews() {
        LayoutInflater.from(mContext).inflate(R.layout.activity_tag_view, this,true);
        tvPictureTagLabel = (TextView) findViewById(R.id.tvPictureTagLabel);
        etPictureTagLabel = (EditText) findViewById(R.id.etPictureTagLabel);
        loTag = findViewById(R.id.loTag);
    }

    public TagView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TagView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return true;
    }

    public void setStatus(Status status){
        switch(status){
            case Normal://正常状态
                tvPictureTagLabel.setVisibility(View.VISIBLE);
                etPictureTagLabel.clearFocus();
                tvPictureTagLabel.setText(etPictureTagLabel.getText());
                etPictureTagLabel.setVisibility(View.GONE);

                //隐藏键盘
                inputMethodManager.hideSoftInputFromWindow(etPictureTagLabel.getWindowToken(),0);
                break;
            case Edit://编辑状态
                tvPictureTagLabel.setVisibility(View.GONE);
                etPictureTagLabel.setVisibility(View.VISIBLE);
                etPictureTagLabel.requestFocus();

                //弹出键盘
                inputMethodManager.toggleSoftInput(0,InputMethodManager.SHOW_FORCED);
                break;
        }
    }

    public static int getViewWidth(){
        return ViewWidth;
    }
    public static int getViewHeight(){
        return ViewHeight;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        View parent = (View) getParent();//父view就是PictureTagLayout
        int halfParentW = (int) (parent.getWidth()*0.5);//获取父view宽度的一半
        int center = (int) (l + (this.getWidth()*0.5));
        if(center<=halfParentW){
            direction = Direction.Left;
        }
        else{
            direction = Direction.Right;
        }

        directionChange();
    }
}
