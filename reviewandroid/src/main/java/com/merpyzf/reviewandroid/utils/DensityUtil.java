package com.merpyzf.reviewandroid.utils;

import android.content.Context;

/**
 * 像素单位转换的工具类
 *@author wangke
 */

public class DensityUtil {

    /**
     * 根据手机分辨率从dp转成px
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context,float dpValue){

        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);

    }

    /**
     * 根据手机分辨率从px转换成dp
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dp(Context context,float pxValue){

        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale+0.5);

    }


}
