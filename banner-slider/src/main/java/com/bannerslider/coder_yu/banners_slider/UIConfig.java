package com.bannerslider.coder_yu.banners_slider;

import java.io.Serializable;

/**
 * Created by yushuifeng on 2017/7/14.
 */

public class UIConfig implements Serializable {

    public int imageLoadingRes;
    public int imageLoadFailed;
    public boolean isRecycled;
    public int indicateUnSelected;
    public int indicateSelectedRes;

    private UIConfig(Builder builder) {
        this.imageLoadFailed = builder.imageLoadFailedRes;
        this.imageLoadingRes = builder.imageLoadingRes;
        this.isRecycled = builder.isRecycled;
        this.indicateSelectedRes = builder.indicateSelectedRes;
        this.indicateUnSelected = builder.indicateUnSelectedRes;
    }

    static class Builder {
        private int imageLoadingRes;
        private int imageLoadFailedRes;
        private boolean isRecycled;
        private int indicateUnSelectedRes;
        private int indicateSelectedRes;

        public Builder() {
            this.imageLoadFailedRes = R.drawable.load_failed;
            this.imageLoadingRes = R.drawable.loading;
            this.indicateSelectedRes = R.drawable.aide_shape_indicate_selected;
            this.indicateUnSelectedRes = R.drawable.aide_shape_indicate_unselected;
            this.isRecycled = true;
        }

        public Builder imageLoadingRes(int imageLoadingRes) {
            this.imageLoadingRes = imageLoadingRes;
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
