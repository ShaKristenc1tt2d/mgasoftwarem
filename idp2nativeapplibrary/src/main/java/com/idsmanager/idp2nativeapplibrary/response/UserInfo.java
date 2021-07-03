package com.idsmanager.idp2nativeapplibrary.response;

import java.io.Serializable;

/**
 * Created by hui on 2016/11/17.
 */
public class UserInfo implements Serializable {
    private String account;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {

        return account;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
