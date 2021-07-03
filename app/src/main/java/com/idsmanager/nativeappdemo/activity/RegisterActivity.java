package com.idsmanager.nativeappdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.idsmanager.idp2nativeapplibrary.util.LogUtils;
import com.idsmanager.idp2nativeapplibrary.util.ToastUtil;
import com.idsmanager.nativeappdemo.R;
import com.idsmanager.nativeappdemo.util.NetUtils;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "RegisterActivity";
    private TextView tvCancel;
    private Button btnAchieve;
    private EditText etUserName;
    private EditText etUserPsw;
    private EditText etUserPsw2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUserName = (EditText) findViewById(R.id.et_user_name);
        etUserPsw = (EditText) findViewById(R.id.et_user_psw);
        etUserPsw2 = (EditText) findViewById(R.id.et_user_psw_re);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        btnAchieve = (Button) findViewById(R.id.btn_achieve);
        tvCancel.setOnClickListener(this);
        btnAchieve.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.btn_achieve:
                if (NetUtils.isNetworkAvailable(RegisterActivity.this)) {
                    registerUserName();
                } else {
                    Toast.makeText(RegisterActivity.this, R.string.no_net, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

    }

    private void registerUserName() {
        String username = etUserName.getText().toString().trim();
        String userPsw = etUserPsw.getText().toString().trim();
        String userPsw2 = etUserPsw2.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(RegisterActivity.this, "请输入用户名！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPsw)) {
            Toast.makeText(RegisterActivity.this, "请输入密码！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPsw2)) {
            Toast.makeText(RegisterActivity.this, "请再次输入密码！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!userPsw.equals(userPsw2)) {
            Toast.makeText(RegisterActivity.this, "密码不正确！", Toast.LENGTH_SHORT).show();
            return;
        }
        AVUser.logOut();
        final AVUser user = new AVUser();
        user.setUsername(username);
        user.setPassword(userPsw);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                etUserName.setText("");
                etUserPsw.setText("");
                etUserPsw2.setText("");
                ToastUtil.showToast(RegisterActivity.this, "注册成功！请记住用户名和密码");
                LogUtils.d(TAG, "注册成功 uesr:" + user);
            }
        });
    }


}
