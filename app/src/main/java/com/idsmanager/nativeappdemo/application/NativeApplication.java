package com.idsmanager.nativeappdemo.application;

import android.app.Application;
import android.content.Context;

import com.avos.avoscloud.AVOSCloud;
import com.idsmanager.idp2nativeapplibrary.util.IDP2NativeApp;
import com.idsmanager.idp2nativeapplibrary.util.LogUtils;
import com.idsmanager.nativeappdemo.activity.SplashActivity;

/**
 * Created by hui on 2016/11/28.
 */
public class NativeApplication extends Application {
    private static Context mContext;
    private static final String TAG = "NativeApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        AVOSCloud.initialize(this, "4ttO8fCifHlaOUEGO1J81I2x-gzGzoHsz", "f3ya12Slu4fuxyoS2m0QiVO9");
        IDP2NativeApp.init(mContext);
        LogUtils.d(TAG, "id-->" + IDP2NativeApp.getFacetID(mContext));
    }

    public static Context getmContext() {
        return mContext;
    }

}
