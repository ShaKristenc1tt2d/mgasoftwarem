package com.idsmanager.nativeappdemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.idsmanager.idp2nativeapplibrary.response.UserInfo;
import com.idsmanager.idp2nativeapplibrary.util.IDP2NativeApp;
import com.idsmanager.nativeappdemo.R;
import com.idsmanager.nativeappdemo.receive.UserReceiver;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splsh);

//        UserInfo info = IDP2NativeApp.getUser(this);
//        if (info != null) {
//            Intent intent = new Intent(SplashActivity.this, UserInfoActivity.class);
//            intent.putExtra("username", info.getAccount());
//            startActivity(intent);
//            Log.d(TAG,"info!");
//            finish();
//        } else {
//            startActivity(new Intent(SplashActivity.this, MainActivity.class));
//            Log.d(TAG, "info");
//            finish();
//        }
        if (TextUtils.isEmpty(UserReceiver.mUsername)){
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            Log.d(TAG, "info");
            finish();
        }

    }
}
