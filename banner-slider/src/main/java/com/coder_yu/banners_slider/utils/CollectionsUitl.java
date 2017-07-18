package com.coder_yu.banners_slider.utils;

import java.util.Collection;

/**
 * Created by coder_yu on 17/4/10.
 */

public class CollectionsUitl {
    public static boolean isEmpty(Collection collection){
        return getSize(collection) == 0;
    }

    public static int getSize(Collection collection){
        return collection == null?0:collection.size();
    }

}
