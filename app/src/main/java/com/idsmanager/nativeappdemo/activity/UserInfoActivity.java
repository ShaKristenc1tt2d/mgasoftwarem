package com.idsmanager.nativeappdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.avos.avoscloud.AVUser;
import com.idsmanager.nativeappdemo.R;
import com.idsmanager.nativeappdemo.util.AppActivities;

public class UserInfoActivity extends AppCompatActivity {
    private static final String TAG = "UserInfoActivity";

    private TextView tvCancel;
    private TextView tvUserName;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        AppActivities.finishAll();
        AppActivities.addActivity(this);
        username = getIntent().getStringExtra("username");
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvUserName = (TextView) findViewById(R.id.tv_user_name);
        tvUserName.setText("-用户 " + username + " -");
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AVUser.logOut();
                startActivity(new Intent(UserInfoActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
