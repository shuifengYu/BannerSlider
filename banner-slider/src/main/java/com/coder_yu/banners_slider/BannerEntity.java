package com.coder_yu.banners_slider;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by coder_yu on 17/4/12.
 */

public class BannerEntity implements Parcelable {
    public Uri imageUri;
    public String url;
    public int type;

    public BannerEntity(String url, Uri imageUri) {
        this(url, imageUri, 0);
    }

    public BannerEntity(String url, Uri imageUri, int type) {
        this.url = url;
        this.imageUri = imageUri;
        this.type = type;
    }


    @Override
    public String toString() {
        return "BannerEntity{" +
                "url='" + url + '\'' +
                ", imageUri='" + imageUri + '\'' +
                '}';
    }

    protected BannerEntity(Parcel in) {
        this.url = in.readString();
        this.imageUri = in.readParcelable(Uri.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeParcelable(this.imageUri, flags);
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
