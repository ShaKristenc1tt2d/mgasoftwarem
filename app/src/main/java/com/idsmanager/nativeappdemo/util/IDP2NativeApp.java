//package com.idsmanager.nativeappdemo.util;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.ApplicationInfo;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Base64;
//
//import com.google.gson.Gson;
//import com.idsmanager.nativeappdemo.response.InfoResponse;
//import com.idsmanager.nativeappdemo.response.UserInfo;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.security.cert.CertificateException;
//import java.security.cert.CertificateFactory;
//import java.security.cert.X509Certificate;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class IDP2NativeApp {
//    private static final String TAG = "IDP2NativeApp";
//    private static String SEPARATOR = "-";
//    private static String data;
//    private static Class<?> mActivity;
//    private static Context mContext;
//
//    public static String getFacetID(Context context) {
//        String apkSign = getApkSign(context);
//        String pack = context.getPackageName();
//        String feature = pack + SEPARATOR + apkSign;
//        String facetId = Base64.encodeToString(feature.getBytes(), Base64.URL_SAFE);
//        return facetId;
//    }
//
//    private static String getApkSign(Context context) {
//        PackageManager pm = context.getPackageManager();
//        ApplicationInfo ai = null;
//        try {
//            ai = pm.getApplicationInfo(context.getApplicationContext().getPackageName(), PackageManager.GET_ACTIVITIES);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        String packageNames[] = context.getPackageManager().getPackagesForUid(ai.uid);
//        if (packageNames == null) {
//            return null;
//        }
//
//        try {
//            PackageInfo info = context.getPackageManager().getPackageInfo(packageNames[0], PackageManager.GET_SIGNATURES);
//            byte[] cert = info.signatures[0].toByteArray();
//            InputStream input = new ByteArrayInputStream(cert);
//            CertificateFactory cf = CertificateFactory.getInstance("X509");
//            X509Certificate c = (X509Certificate) cf.generateCertificate(input);
//            MessageDigest md = MessageDigest.getInstance("SHA1");
//
//            return Base64.encodeToString(md.digest(c.getEncoded()), Base64.DEFAULT | Base64.NO_WRAP | Base64.NO_PADDING);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        } catch (CertificateException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static void getInfo(String head, String applicationUuid, String nativeToken) {
//        if (!TextUtils.isEmpty(head) && !TextUtils.isEmpty(applicationUuid) && !TextUtils.isEmpty(nativeToken)) {
//            String url = head + "/public/api/application/native_app/callback_" + applicationUuid + "?nativeToken=" + nativeToken;
//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder().url(url).build();
//            Call call = client.newCall(request);
//            call.enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    LogUtils.d(TAG, "onFailure-->" + e.getMessage());
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    LogUtils.d(TAG, "onResponse-->");
//                    Gson gson = new Gson();
//                    InfoResponse response1 = gson.fromJson(response.body().string(), InfoResponse.class);
//                    data = SecretUtil.decrypt(response1.getInfo(), response1.getAuthKey());
//                    LogUtils.d(TAG, data);
//                    getUserInfo();
//                    startActivity();
//                }
//            });
////            OkHttpUtils
////                    .get()
////                    .url(url)
////                    .build()
////                    .execute(new StringCallback() {
////                        @Override
////                        public void onError(Call call, Exception e, int id) {
////                            LogUtils.d(TAG, "==id==" + id);
////                        }
////
////                        @Override
////                        public void onResponse(String response, int id) {
////                            LogUtils.d(TAG, "response-->" + response + "==id==" + id);
////                            Gson gson = new Gson();
////                            InfoResponse response1 = gson.fromJson(response, InfoResponse.class);
////                            data = SecretUtil.decrypt(response1.getInfo(), response1.getAuthKey());
////                            LogUtils.d(TAG, data);
////                            getUserInfo();
////                            startActivity();
////                        }
////                    });
//        } else {
//            LogUtils.d(TAG, "参数为空");
//        }
//
//    }
//
//    private static UserInfo getUserInfo() {
//        if (TextUtils.isEmpty(data)) {
//            return null;
//        } else {
//            Gson gson = new Gson();
//            UserInfo userInfo = gson.fromJson(data, UserInfo.class);
//            return userInfo;
//        }
//    }
//
//    public static void startActivity() {
//        if (mActivity != null && mContext != null) {
//            Intent intent1 = new Intent(mContext, mActivity);
//            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            Bundle bundle1 = new Bundle();
//            if (getUserInfo() != null) {
//                bundle1.putSerializable("user", getUserInfo());
//                intent1.putExtras(bundle1);
//                mContext.startActivity(intent1);
//            }
//        }
//    }
//
//    public static void init(Context context, Class<?> ac) {
//        mContext = context;
//        mActivity = ac;
//    }
//
//    public static UserInfo getUser(Activity mainActivity) {
//        Bundle bundle = mainActivity.getIntent().getExtras();
//        if (bundle != null) {
//            UserInfo info = (UserInfo) bundle.getSerializable("user");
//            if (info != null) {
//                return info;
//            }
//        }
//        return null;
//    }
//}
