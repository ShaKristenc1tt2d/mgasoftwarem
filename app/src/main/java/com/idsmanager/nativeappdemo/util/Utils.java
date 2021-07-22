package com.idsmanager.nativeappdemo.util;

import android.content.Context;
import android.content.pm.PackageManager;


/**
 * Created by Administrator on 2017/1/5.
 */

public class Utils {
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return verName;
    }
}
