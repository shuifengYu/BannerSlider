package com.coder_yu.banners_slider.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by yushuifeng on 2017/7/26.
 */

public class AutoSlidingViewPager extends ViewPager {

    public ActionListener listener;

    public interface ActionListener {
        void onActionDown();

        void onActionUp();
    }

    public void setActionListener(ActionListener actionListener) {
        this.listener = actionListener;
    }

    public AutoSlidingViewPager(Context context) {
        super(context);
    }

    public AutoSlidingViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                actionDown();
                break;
            case MotionEvent.ACTION_UP:
                actionUp();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    protected void actionDown() {
        if (listener == null) {
            return;
        }
        listener.onActionDown();
    }


    protected void actionUp() {
        if (listener == null) {
            return;
        }
        listener.onActionUp();
    }

}
