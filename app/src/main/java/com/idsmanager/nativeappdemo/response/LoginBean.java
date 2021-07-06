package com.idsmanager.nativeappdemo.response;

/**
 * describe
 * Created by hui on 2016/12/23.
 */
//com.avos.avoscloud.AVException: {"code":210,"error":"The username and password mismatch."}
//com.avos.avoscloud.AVException: {"code":211,"error":"Could not find user"}
public class LoginBean {
    private int code;
    private String error;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
