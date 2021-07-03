package com.idsmanager.idp2nativeapplibrary.response;

/**
 * Created by hui on 2016/11/16.
 */
public class InfoResponse {
    private String authKey;
    private String errorCode;
    private String errorMessage;
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getErrorMessage() {

        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {

        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getAuthKey() {

        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }
}
