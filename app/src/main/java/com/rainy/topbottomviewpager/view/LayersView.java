package com.rainy.topbottomviewpager.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author: Rainy <br>
 * Description: top-bottom-viewpager <br>
 * Since: 2016/12/15 0015 上午 11:44 <br>
 */

public class LayersView extends View {
    private static final int LAYER_FLAGS = Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG
                    | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG | Canvas.FULL_COLOR_LAYER_SAVE_FLAG
                   | Canvas.CLIP_TO_LAYER_SAVE_FLAG;
    private Paint mPaint;
    public LayersView(Context context) {
        super(context);
        setFocusable(true);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

    }

    public LayersView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public LayersView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.translate(10,10);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(75,75,75,mPaint);
        canvas.saveLayerAlpha(0,0,200,200,0x88,LAYER_FLAGS);//将一个layer推入栈中

        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(125,125,75,mPaint);
        canvas.restore();
    }
}
