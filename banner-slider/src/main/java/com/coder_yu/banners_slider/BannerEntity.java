package com.coder_yu.banners_slider;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by coder_yu on 17/4/12.
 */

public class BannerEntity implements Parcelable {
    public BannerEntity(String url, Uri imageUrl) {
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

    public Uri imageUrl;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeParcelable(this.imageUrl, flags);
    }

    protected BannerEntity(Parcel in) {
        this.url = in.readString();
        this.imageUrl = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<BannerEntity> CREATOR = new Creator<BannerEntity>() {
        @Override
        public BannerEntity createFromParcel(Parcel source) {
            return new BannerEntity(source);
        }

        @Override
        public BannerEntity[] newArray(int size) {
            return new BannerEntity[size];
        }
    };
}
