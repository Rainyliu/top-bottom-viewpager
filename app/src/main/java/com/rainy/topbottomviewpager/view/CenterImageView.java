package com.rainy.topbottomviewpager.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.rainy.topbottomviewpager.R;

/**
 * Author: Rainy <br>
 * Description: top-bottom-viewpager <br>
 * Since: 2016/12/15 0015 下午 3:09 <br>
 */

public class CenterImageView extends ImageView{
    private Paint paint;
    private boolean isCenterImgShow;
    private Bitmap bitmap;

    public void setCenterImgShow(boolean centerImgShow) {
        isCenterImgShow = centerImgShow;
        if(isCenterImgShow){
            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bg_test);
            invalidate();
        }
    }

    public CenterImageView(Context context) {
        super(context);
        init();
    }

    public CenterImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CenterImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(isCenterImgShow && bitmap != null){
            canvas.drawBitmap(bitmap, getMeasuredWidth() / 2 - bitmap.getWidth() / 2, getMeasuredHeight() / 2 - bitmap.getHeight() / 2, paint);
        }
    }
}
