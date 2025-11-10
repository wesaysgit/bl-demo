package com.es.encrypt;

//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class PaasLogin {

    private static String PKCS5PADDINGALGORITHM = "AES/CBC/PKCS5Padding";
    public static final String KEY="zldboink20170613";

    public static void main(String[] args) {
        String dbpwd = StringUtils.MD5("wuye123456" + KEY);
        System.out.println("dbpwd = " + dbpwd);
        boolean flag = dbpwd.equals("15d691ada4933f06778d72228937ee86");
        System.out.println("flag = " + flag);
    }

    public static void main1(String[] args) {
//        String dbPass = "15d691ada4933f06778d72228937ee86";
//        String password = "193360392";
//        boolean password1 = isPassword(password, dbPass);
//        System.out.println("password1 = " + password1);
//        System.out.println("-----------------------------------------------------------------");
//        boolean password2 = isPassword(encryptToAESPKCS5(password, KEY), dbPass);
//        System.out.println("password2 = " + password2);
    }

    private static boolean isPassword(String reqPass, String dbPass) {
        String dbpwd = StringUtils.MD5(reqPass + KEY);
        System.out.println("dbpwd = " + dbpwd);
        if (dbPass.equals(dbpwd) || dbPass.equals(reqPass)) {
            return true;
        }
        return false;
    }

//    public static String encryptToAESPKCS5(String content, String key) {
//        byte[] encryptResult = null;
//        try {
//            // 密钥
//            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
//            // 算法/模式/填充
//            Cipher cipher = Cipher.getInstance(PKCS5PADDINGALGORITHM);
//            byte[] byteContent = content.getBytes("utf-8");
//            // 初始化向量,在密钥相同的前提下，加上初始化向量，相同内容加密后相同
//            IvParameterSpec zeroIv = new IvParameterSpec(key.getBytes());
//            cipher.init(Cipher.ENCRYPT_MODE, secretKey, zeroIv);
//            encryptResult = cipher.doFinal(byteContent);
//
//        }
//        catch(Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//
//        return Base64.encode(encryptResult);
//    }
}
