package com.idsmanager.idp2nativeapplibrary.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    private static Toast sToast;

    public static void showToast(Context context, String message) {
        if (context == null) {
            return;
        }

        if (sToast != null) {
            sToast.cancel();
        }
        sToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        sToast.show();
    }
    public static void showLongToast(Context context, String message) {
        if (context == null) {
            return;
        }

        if (sToast != null) {
            sToast.cancel();
        }
        sToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        sToast.show();
    }

    public static void showToast(Context context, int res) {
        if (context == null) {
            return;
        }

        if (sToast != null) {
            sToast.cancel();
        }
        sToast = Toast.makeText(context, context.getString(res), Toast.LENGTH_SHORT);
        sToast.show();
    }
}
