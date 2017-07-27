package com.coder_yu.banners_slider.utils;

import android.support.v4.view.ViewPager;
import android.view.animation.AccelerateInterpolator;

import com.coder_yu.banners_slider.widget.FixedSpeedScroller;

import java.lang.reflect.Field;


/**
 * Created by yushuifeng on 2017/7/27.
 */

public class ViewPagerUtil {

    private static FixedSpeedScroller mScroller = null;
    /**
     * 设置ViewPager的滑动时间
     * @param viewpager ViewPager控件
     * @param DurationSwitch 滑动延时
     */
    public static void controlViewPagerSpeed(ViewPager viewpager, int DurationSwitch) {
        try {
            Field mField;

            mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);

            mScroller = new FixedSpeedScroller(viewpager.getContext(),
                    new AccelerateInterpolator());
            mScroller.setmDuration(DurationSwitch);
            mField.set(viewpager, mScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
