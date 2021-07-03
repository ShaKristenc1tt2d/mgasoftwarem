package com.idsmanager.idp2nativeapplibrary.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.idsmanager.idp2nativeapplibrary.util.IDP2NativeApp;
import com.idsmanager.idp2nativeapplibrary.util.LogUtils;


/**
 * Created by hui on 2016/11/16.
 */
public final class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        {
            //  判断是否为sendbroadcast发送的广播
            if ("com.idsmanager.enterprisetwo.summer.receiver".equals(intent.getAction())) {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    String faceId = IDP2NativeApp.getFacetID(context);
                    String s = bundle.getString("androidAppId");
                    if (s != null) {
                        if (faceId.trim().contains(s.trim())) {
                            String applicationUuid = bundle.getString("applicationUuid");
                            String nativeToken = bundle.getString("nativeToken");
                            String head = bundle.getString("head");
                            IDP2NativeApp.getInfo(head, applicationUuid, nativeToken);
                            LogUtils.d(TAG, "applicationUuid->" + applicationUuid + ";nativeToken->" + nativeToken+";head->"+head);
                        } else {
                            LogUtils.d(TAG, "接收不到");
                        }
                    }
                }
            }
        }
    }


}
