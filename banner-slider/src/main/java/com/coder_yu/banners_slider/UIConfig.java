package com.coder_yu.banners_slider;

import com.bannerslider.coder_yu.banners_slider.R;

import java.io.Serializable;

/**
 * Created by yushuifeng on 2017/7/14.
 */

public class UIConfig implements Serializable {

    public static int DURATION = 5000;
    public static int SLIDING_TIME = 300;
    public int imageLoadingRes;
    public int imageLoadFailed;
    public boolean isRecycled;
    public int indicateUnSelected;
    public int indicateSelectedRes;

    /**
     * the marginbottom value of the indicate line
     * unit (px)
     */
    public int indicatesMarginBottomDP;

    /**
     * time the banner stays
     * unit：millisecond
     */
    public int duration;

    /**
     * 滑动到下一页花费的时间
     */
    public int slidingTime ;

    private UIConfig(Builder builder) {
        this.imageLoadFailed = builder.imageLoadFailedRes;
        this.imageLoadingRes = builder.imageLoadingRes;
        this.isRecycled = builder.isRecycled;
        this.indicateSelectedRes = builder.indicateSelectedRes;
        this.indicateUnSelected = builder.indicateUnSelectedRes;
        this.duration = builder.duration;
        this.slidingTime = builder.slidingTime;
        this.indicatesMarginBottomDP =builder.indicatesMarginBottomDP;
    }

    public static class Builder {
        private int imageLoadingRes;
        private int imageLoadFailedRes;
        private boolean isRecycled;
        private int indicateUnSelectedRes;
        private int indicateSelectedRes;
        private int duration;
        private int indicatesMarginBottomDP;
        private int slidingTime;

        public Builder() {
            this.imageLoadFailedRes = R.drawable.img_load_failed;
            this.imageLoadingRes = R.drawable.img_loading;
            this.indicateSelectedRes = R.drawable.aide_shape_indicate_selected;
            this.indicateUnSelectedRes = R.drawable.aide_shape_indicate_unselected;
            this.isRecycled = true;
            this.duration = DURATION;
            this.indicatesMarginBottomDP = 10;
            this.slidingTime = SLIDING_TIME;
        }



        public Builder indicatesMarginBottomDP(int imageLoadingRes) {
            this.indicatesMarginBottomDP = imageLoadingRes;
            return this;
        }

        public Builder imageLoadingRes(int imageLoadingRes) {
            this.imageLoadingRes = imageLoadingRes;
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

        public Builder imageLoadFailedRes(int imageLoadFailedRes) {
            this.imageLoadFailedRes = imageLoadFailedRes;
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
            return new UIConfig(this);
        }
    }
}
