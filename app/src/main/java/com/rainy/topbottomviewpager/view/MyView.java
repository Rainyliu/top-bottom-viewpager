package com.rainy.topbottomviewpager.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * 自定义view小demo
 * Author: Rainy <br>
 * Description: top-bottom-viewpager <br>
 * Since: 2016/12/15 0015 上午 10:10 <br>
 */

public class MyView extends View {

    public final static String TAG = "Example";
    private Paint mPaint = null;
    private Bitmap bitmap;
    private int px;
    private int py;

    public MyView(Context context) {
        super(context);
        mPaint = new Paint();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        px = windowManager.getDefaultDisplay().getWidth();
        py = windowManager.getDefaultDisplay().getHeight();

    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint background = new Paint();
        Paint line = new Paint();
        line.setStrokeWidth(4);
        line.setColor(Color.RED);
        background.setColor(Color.BLUE);
        Log.d(TAG,"px=============="+px);
        Log.d(TAG,"py=============="+py);
//        canvas.drawBitmap(bitmap,10,10,mPaint);
        canvas.drawRect(0, 0, px, py, background);
//        canvas.save();
        canvas.rotate(90,px/2,py/2);
//        // 画一个向上的箭头
        canvas.drawLine(px / 2, 0, 0, py / 2, line); // 左边的斜杠
        canvas.drawLine(px / 2, 0, px, py / 2, line);// 右边的斜杠
        canvas.drawLine(px / 2, 0, px / 2, py, line);// 垂直的竖杠
//        canvas.drawBitmap(bitmap,100,200,mPaint);
//        canvas.restore();
        canvas.drawCircle(px - 100, py - 100, 50, line);
    }
}
