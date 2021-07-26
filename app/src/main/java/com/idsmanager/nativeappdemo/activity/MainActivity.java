package com.idsmanager.nativeappdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.google.gson.Gson;
import com.idsmanager.idp2nativeapplibrary.response.UserInfo;
import com.idsmanager.idp2nativeapplibrary.util.IDP2NativeApp;
import com.idsmanager.idp2nativeapplibrary.util.LogUtils;
import com.idsmanager.idp2nativeapplibrary.util.ToastUtil;
import com.idsmanager.nativeappdemo.R;
import com.idsmanager.nativeappdemo.response.LoginBean;
import com.idsmanager.nativeappdemo.util.AppActivities;
import com.idsmanager.nativeappdemo.util.NetUtils;
import com.idsmanager.nativeappdemo.util.Utils;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private EditText etUserName;
    private EditText etUserPsw;
    private Button btnLogin;
    private Button btnRegister;

    private TextView tvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppActivities.finishAll();
        AppActivities.addActivity(this);
        AVAnalytics.trackAppOpened(getIntent());
        etUserName = (EditText) findViewById(R.id.et_user_name);
        etUserPsw = (EditText) findViewById(R.id.et_user_psw);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvVersion = (TextView) findViewById(R.id.tv_version);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        tvVersion.setText(Utils.getVerName(MainActivity.this));
        getData(getIntent());
    }

    private void getData(Intent intent) {
        if (intent !=null) {
            Uri uri = intent.getData();
            if (uri != null) {
                String applicationUuid = uri.getQueryParameter("applicationUuid");
                String nativeToken = uri.getQueryParameter("nativeToken");
                String head = uri.getQueryParameter("head");
                if(!TextUtils.isEmpty(applicationUuid)||!TextUtils.isEmpty(nativeToken)||!TextUtils.isEmpty(head)){
                    //接收数据，进行获取账号密码
                    IDP2NativeApp.getInfo(head, applicationUuid, nativeToken);
                }
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        getData(intent);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_login:
                final String username = etUserName.getText().toString().trim();
                String userPsw = etUserPsw.getText().toString().trim();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(userPsw)) {
                    ToastUtil.showToast(MainActivity.this, "用户名密码不能为空！");
                    return;
                }
                if (NetUtils.isNetworkAvailable(MainActivity.this)) {
                    login(username, userPsw);
                } else {
                    Toast.makeText(MainActivity.this, R.string.no_net, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            default:
                break;
        }
    }

    private void login(final String username, String userPsw) {

        AVUser.logOut();
        AVUser.logInInBackground(username, userPsw, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (e != null) {
                    Gson gson = new Gson();
                    LoginBean result = gson.fromJson(e.getMessage(), LoginBean.class);
                    if (result.getCode() == 210) {
                        ToastUtil.showToast(MainActivity.this, "用户名或密码错误！");
                    } else if (result.getCode() == 211) {
                        ToastUtil.showToast(MainActivity.this, "登录失败！用户名还未被注册！");
                    }
                } else {
                    LogUtils.d(TAG, "登录成功 user：" + avUser.toString());
                    ToastUtil.showToast(MainActivity.this, "登录成功！");
                    Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    etUserName.setText("");
                    etUserPsw.setText("");
                }
            }
        });
    }
}
