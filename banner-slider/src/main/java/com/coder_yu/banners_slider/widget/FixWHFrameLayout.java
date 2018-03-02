package com.coder_yu.banners_slider.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.bannerslider.coder_yu.banners_slider.R;

/**
 * Created by yushuifeng on 2017/7/28.
 *
 * 宽高比固定的FragmLayout，以宽度为基准
 */

public class FixWHFrameLayout extends FrameLayout {
    private static final String TAG = "FixWHFrameLayout";
    private float radio_WH = 0;

    public void setRadio (float radio){
        this.radio_WH = radio;
    }
    public FixWHFrameLayout(@NonNull Context context) {
        this(context,null);
    }

    public FixWHFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FixWHFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.fixaction_layout);
        radio_WH = a.getFloat(R.styleable.fixaction_layout_ratio_wh, 0);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(radio_WH > 0){
            int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
            int height = (int) (width / radio_WH + 0.5f);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
