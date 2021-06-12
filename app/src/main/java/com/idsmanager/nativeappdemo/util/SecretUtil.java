//package com.idsmanager.nativeappdemo.util;
//
//import android.util.Base64;
//
//import java.security.NoSuchAlgorithmException;
//
//import javax.crypto.Cipher;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.spec.SecretKeySpec;
//
///**
// * Created by hui on 2016/11/16.
// */
//public class SecretUtil {
//    public static String decrypt(String sSrc, String key) {
//        if (sSrc != null && key != null) {
//            try {
//                byte[] ex = key.getBytes("UTF-8");
//                SecretKeySpec skeySpec = getSecretKeySpec(ex);
//                Cipher cipher = getCipher();
//                cipher.init(2, skeySpec);
//                byte[] encrypted1 = Base64.decode(sSrc, Base64.NO_WRAP);
//                byte[] original = cipher.doFinal(encrypted1);
//                return new String(original, "UTF-8");
//            } catch (Exception var7) {
//                throw new IllegalStateException(var7);
//            }
//        } else {
//            return null;
//        }
//    }
//
//    private static SecretKeySpec getSecretKeySpec(byte[] raw) {
//        return new SecretKeySpec(raw, "AES");
//    }
//
//    private static Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
//        return Cipher.getInstance("AES/ECB/PKCS5Padding");
//    }
//}
