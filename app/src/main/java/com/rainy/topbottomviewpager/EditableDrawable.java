package com.rainy.topbottomviewpager;

import android.graphics.Paint;

/**
 * Author: Rainy <br>
 * Description: top-bottom-viewpager <br>
 * Since: 2016/12/9 0009 上午 11:38 <br>
 */

public interface EditableDrawable {
    public static final int CURSOR_BLINK_TIME = 400;

    public void setOnSizeChangeListener(OnSizeChange paramOnSizeChange);

    public void beginEdit();

    public void endEdit();

    public boolean isEditing();

    public CharSequence getText();

    public void setText(CharSequence paramCharSequence);

    public void setText(String paramString);

    public void setTextHint(CharSequence paramCharSequence);

    public void setTextHint(String paramString);

    public boolean isTextHint();

    public void setBounds(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);

    public void setTextColor(int paramInt);

    public int getTextColor();

    public float getTextSize();

    public float getFontMetrics(Paint.FontMetrics paramFontMetrics);

    public void setTextStrokeColor(int paramInt);

    public int getTextStrokeColor();

    public void setStrokeEnabled(boolean paramBoolean);

    public boolean getStrokeEnabled();

    public int getNumLines();

    public static interface OnSizeChange {
        public void onSizeChanged(EditableDrawable paramEditableDrawable, float paramFloat1,
                                  float paramFloat2, float paramFloat3, float paramFloat4);
    }
}
