package com.coder_yu.banners_slider;

import android.view.Gravity;

import com.bannerslider.coder_yu.banners_slider.R;
import com.coder_yu.banners_slider.loader.ImageLoaderInterface;

import java.io.Serializable;

/**
 * Created by yushuifeng on 2017/7/14.
 */

public class UIConfig implements Serializable {

    public static int DURATION = 5000;
    public static int SLIDING_TIME = 300;
    public boolean isRecycled;
    public int indicateUnSelected;
    public int indicateSelectedRes;


    /**
     * the marginbottom value of the indicate line
     * unit (px)
     */
    public int indicatesMarginBottomDP;

    /**
     * the marginRight value of the indicate line
     * unit (px)
     */
    public int indicatesMarginRightDP;

    /**
     * the marginLeft value of the indicate line
     * unit (px)
     */
    public int indicatesMarginLeftDP;

    /**
     * time the banner stays
     * unit：millisecond
     */
    public int duration;

    /**
     * 滑动到下一页花费的时间
     */
    public int slidingTime;


    /**
     * 导航条的位置
     * {@link Gravity}
     */
    public int indicatesGravity;

    public ImageLoaderInterface imageLoader;

    private UIConfig(Builder builder) {
        this.isRecycled = builder.isRecycled;
        this.indicateSelectedRes = builder.indicateSelectedRes;
        this.indicateUnSelected = builder.indicateUnSelectedRes;
        this.duration = builder.duration;
        this.slidingTime = builder.slidingTime;
        this.indicatesMarginBottomDP = builder.indicatesMarginBottomDP;
        this.indicatesMarginLeftDP = builder.indicatesMarginLeftDP;
        this.indicatesMarginRightDP = builder.indicatesMarginRightDP;
        this.indicatesGravity = builder.indicatesGravity;
        this.imageLoader = builder.imageLoader;
    }

    public static class Builder {
//        private int imageLoadingRes;
//        private int imageLoadFailedRes;
        private boolean isRecycled;
        private int indicateUnSelectedRes;
        private int indicateSelectedRes;
        private int duration;
        private int indicatesMarginBottomDP;
        private int indicatesMarginRightDP;
        private int indicatesMarginLeftDP;
        private int indicatesGravity;
        private int slidingTime;
        private ImageLoaderInterface imageLoader;

        public Builder() {
            this.indicateSelectedRes = R.drawable.aide_shape_indicate_selected;
            this.indicateUnSelectedRes = R.drawable.aide_shape_indicate_unselected;
            this.isRecycled = true;
            this.duration = DURATION;
            this.indicatesMarginBottomDP = 10;
            this.slidingTime = SLIDING_TIME;
            this.indicatesMarginLeftDP =10;
            this.indicatesMarginRightDP = 10;
            this.indicatesGravity = Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL;
            this.imageLoader = null;
        }

        public Builder imageLoader(ImageLoaderInterface imageLoader){
            this.imageLoader = imageLoader;
            return this;
        }

        public Builder indicatesMarginLeftDP(int indicatesMarginLeftDP) {
            this.indicatesMarginLeftDP = indicatesMarginLeftDP;
            return this;
        }


        public Builder indicatesMarginRightDP(int indicatesMarginRightDP) {
            this.indicatesMarginRightDP = indicatesMarginRightDP;
            return this;
        }


        public Builder indicatesGravity(int indicatesGravity) {
            this.indicatesGravity = indicatesGravity;
            return this;
        }


        public Builder indicatesMarginBottomDP(int imageLoadingRes) {
            this.indicatesMarginBottomDP = imageLoadingRes;
            return this;
        }

        public Builder duration(int duration) {
            this.duration = duration;
            return this;
        }

        public Builder slidingTime(int slidingTime) {
            this.slidingTime = slidingTime;
            return this;
        }

        public Builder recycled(boolean recycled) {
            isRecycled = recycled;
            return this;
        }


        public Builder indicateUnSelectedRes(int indicateUnSelectedRes) {
            this.indicateUnSelectedRes = indicateUnSelectedRes;
            return this;
        }


        public Builder indicateSelectedRes(int indicateSelectedRes) {
            this.indicateSelectedRes = indicateSelectedRes;
            return this;
        }

        public UIConfig build() {
            if(this.imageLoader == null){
                throw new RuntimeException("you must give a imageloader to display the image!");
            }
            return new UIConfig(this);
        }
    }
}
