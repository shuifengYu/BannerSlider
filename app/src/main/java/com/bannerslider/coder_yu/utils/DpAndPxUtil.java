package com.bannerslider.coder_yu.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by yushi on 16/1/14.
 */
public class DpAndPxUtil {
    public static float dp2px(Context ctx, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, ctx.getResources().getDisplayMetrics());
    }

    public static float px2dp(Context ctx, int px) {

        float density = ctx.getResources().getDisplayMetrics().density;

        float ret = px / density;

        return ret;
    }
}
