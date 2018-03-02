package com.coder_yu.banners_slider.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

/**
 * Created by yushuifeng on 2017/7/28.
 */

public class UriUtil {
    public static final String TAG = "UriUtil";

    public static Uri getUri(Context context, int resID) {
        if (context == null || resID == 0) {
            Log.e(TAG, "getUri findEmpty params: context = " + context + ",resID=" + resID);
            return null;
        }
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + context.getResources().getResourcePackageName(resID) + "/"
                + context.getResources().getResourceTypeName(resID) + "/"
                + context.getResources().getResourceEntryName(resID));
        return uri;
    }
}
