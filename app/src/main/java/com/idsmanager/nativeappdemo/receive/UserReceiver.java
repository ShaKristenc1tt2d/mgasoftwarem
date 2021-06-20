package com.idsmanager.nativeappdemo.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.idsmanager.idp2nativeapplibrary.util.LogUtils;
import com.idsmanager.nativeappdemo.activity.MainActivity;
import com.idsmanager.nativeappdemo.activity.UserInfoActivity;

/**
 * Created by hui on 2016/12/13.
 */
public class UserReceiver extends BroadcastReceiver {
    private static final String TAG = "UserReceiver";
    public static String mUsername;

    @Override
    public void onReceive(Context context, Intent intent) {
        //  判断是否为sendbroadcast发送的广播
        if ("com.idsmanager.nativeappdemo.summer.userinfo".equals(intent.getAction())) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                String username = bundle.getString("name");
                String psw = bundle.getString("psw");
                LogUtils.d(TAG, "name->" + username + ";psw->" + psw);
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(psw)) {
                    mUsername = username;
                    Intent intent1 = new Intent(context, UserInfoActivity.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent1.putExtra("username", username);
                    context.startActivity(intent1);
                    Log.d(TAG, "info!");
                } else {
                    Intent in = new Intent(context, MainActivity.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(in);
                    Log.d(TAG, "info");
                }
            }
        }
    }
}
