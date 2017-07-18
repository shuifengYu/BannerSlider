package com.coder_yu.banners_slider;

import java.io.Serializable;

/**
 * Created by coder_yu on 17/4/12.
 */

public class BannerEntity implements Serializable {
    public BannerEntity(String url, String imageUrl) {
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public String url;

    @Override
    public String toString() {
        return "BannerEntity{" +
                "url='" + url + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public String imageUrl;
}
