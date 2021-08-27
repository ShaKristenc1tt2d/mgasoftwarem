package com.idsmanager.nativeappdemo.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.google.gson.Gson;
import com.idsmanager.idp2nativeapplibrary.util.LogUtils;
import com.idsmanager.idp2nativeapplibrary.util.ToastUtil;
import com.idsmanager.nativeappdemo.activity.MainActivity;
import com.idsmanager.nativeappdemo.activity.UserInfoActivity;
import com.idsmanager.nativeappdemo.response.LoginBean;

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
                int errorCode = bundle.getInt("errorCode");
                Log.d(TAG, "errorCode-" + errorCode);
                if (errorCode == 0) {
                    if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(psw)) {
                        //判断用户是否存在
                        login(username, psw, context);
                    } else {
                        Intent in = new Intent(context, MainActivity.class);
                        in.putExtra("where", false);
                        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(in);
                        Log.d(TAG, "info:idp2中的用户名和密码没有收到！");
                    }
                } else {
                }
            }
        }
    }

    private void login(final String username, String userPsw, final Context context) {

        AVUser.logOut();
        AVUser.logInInBackground(username, userPsw, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (e != null) {
                    Gson gson = new Gson();
                    LoginBean result = gson.fromJson(e.getMessage(), LoginBean.class);
                    if (result.getCode() == 210) {
                        ToastUtil.showToast(context, "用户名或密码错误！");
                    } else if (result.getCode() == 211) {
                        ToastUtil.showToast(context, "登录失败！用户名还未被注册！");
                    }
                } else {
                    LogUtils.d(TAG, "登录成功 user：" + avUser.toString());
                    ToastUtil.showToast(context, "登录成功！");
                    Intent intent = new Intent(context, UserInfoActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("username", username);
                    context.startActivity(intent);
                }
            }
        });
    }
}
