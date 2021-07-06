package com.idsmanager.nativeappdemo.activity;

import android.content.Intent;
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
import com.idsmanager.nativeappdemo.util.NetUtils;
import com.idsmanager.nativeappdemo.util.Utils;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private EditText etUserName;
    private EditText etUserPsw;
    private Button btnLogin;
    private Button btnRegister;

    private TextView tvVersion;
//    private boolean where;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AVAnalytics.trackAppOpened(getIntent());
        etUserName = (EditText) findViewById(R.id.et_user_name);
        etUserPsw = (EditText) findViewById(R.id.et_user_psw);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvVersion = (TextView) findViewById(R.id.tv_version);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

//        where = getIntent().getBooleanExtra("where", false);
//        if (where) {
//            if (NetUtils.isNetworkAvailable(MainActivity.this)) {
//                String username = getIntent().getStringExtra("username");
//                String userPsw = getIntent().getStringExtra("psw");
//                login(username, userPsw);
//            } else {
//                Toast.makeText(MainActivity.this, R.string.no_net, Toast.LENGTH_SHORT).show();
//            }
//        } else {
//
//        }
        //        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            UserInfo info = (UserInfo) bundle.getSerializable("user");
//            if (info != null) {
//                login(info.getAccount(), info.getPassword());
//            }
//        }
        tvVersion.setText(Utils.getVerName(MainActivity.this));

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
