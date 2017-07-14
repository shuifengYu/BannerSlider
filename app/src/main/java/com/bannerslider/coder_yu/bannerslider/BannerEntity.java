package com.bannerslider.coder_yu.bannerslider;

import java.io.Serializable;

/**
 * Created by coder_yu on 17/4/12.
 */

public class BannerEntity implements Serializable {
    public BannerEntity(String url, String imageUrl) {
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public BannerEntity() {
    }

    public String url;
    public String imageUrl;
}
