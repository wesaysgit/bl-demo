/*
 * 文 件 名:  XlapiSignature.java
 * 版    权:  SDHS XinLian Payment Copyright 2013-2018,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  cuiwei
 * 修改时间:  2018年11月24日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.es.etc.xinlianyun;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.es.lsapp.HttpClientUtil;
import okhttp3.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;


/**
 * SHA256WithRSA工具类
 */
public class XlapiSignature {
    private static final String SIGN_TYPE_RSA = "RSA";

    private static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";

    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    public static boolean rsaCheckContent(String content, String sign, String publicKey, String charset)
            throws SignatureException {
        try {
            PublicKey pubKey = getPublicKeyFromX509(SIGN_TYPE_RSA, new ByteArrayInputStream(publicKey.getBytes()));
            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
            signature.initVerify(pubKey);
            if (StringUtils.isEmpty(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }
            return signature.verify(Base64.decodeBase64(sign.getBytes()));
        } catch (Exception e) {
            throw new SignatureException("RSAcontent = " + content + ",sign=" + sign + ",charset = " + charset, e);
        }
    }

    public static boolean rsa256CheckContent(String content, String sign, String publicKey, String charset)
            throws SignatureException, Exception {
        try {
            PublicKey pubKey = getPublicKeyFromX509(SIGN_TYPE_RSA, new ByteArrayInputStream(publicKey.getBytes()));
            java.security.Signature signature = java.security.Signature.getInstance(SIGN_SHA256RSA_ALGORITHMS);
            signature.initVerify(pubKey);
            if (StringUtils.isEmpty(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }
            return signature.verify(Base64.decodeBase64(sign));
        } catch (SignatureException e) {
            throw new SignatureException("验签失败，RSAcontent = " + content + ",sign=" + sign + ",charset = " + charset, e);
        }
    }

    public static PublicKey getPublicKeyFromX509(String algorithm, InputStream ins)
            throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        StringWriter writer = new StringWriter();
        StreamUtil.io(new InputStreamReader(ins), writer);
        byte[] encodedKey = writer.toString().getBytes();
        encodedKey = Base64.decodeBase64(encodedKey);
        return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
    }

    public static String rsaSign(String content, String privateKey, String charset)
            throws Exception {
        PrivateKey priKey = getPrivateKeyFromPKCS8(SIGN_TYPE_RSA, new ByteArrayInputStream(privateKey.getBytes()));
        java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
        signature.initSign(priKey);
        if (StringUtils.isEmpty(charset)) {
            signature.update(content.getBytes());
        } else {
            signature.update(content.getBytes(charset));
        }
        byte[] signed = signature.sign();
        return new String(Base64.encodeBase64(signed));
    }

    public static String rsa256Sign(String content, String privateKey, String charset)
            throws Exception {
        try {
            PrivateKey priKey = getPrivateKeyFromPKCS8(SIGN_TYPE_RSA, new ByteArrayInputStream(privateKey.getBytes()));
            java.security.Signature signature = java.security.Signature.getInstance(SIGN_SHA256RSA_ALGORITHMS);
            signature.initSign(priKey);
            if (StringUtils.isEmpty(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }
            byte[] signed = signature.sign();
            return new String(Base64.encodeBase64(signed));
        } catch (Exception e) {
            throw new Exception("RSAcontent = " + content + "; charset = " + charset, e);
        }
    }

    public static PrivateKey getPrivateKeyFromPKCS8(String algorithm, InputStream ins)
            throws Exception {
        if (ins == null || StringUtils.isEmpty(algorithm)) {
            return null;
        }
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        byte[] encodedKey = StreamUtil.readText(ins).getBytes();
        encodedKey = Base64.decodeBase64(encodedKey);
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
    }

    /**
     * 献给不会敲代码的大哥们，请直接调这个
     *
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public static String sdhsSign(String data, String privateKey)
            throws Exception {
        return rsa256Sign(new String(java.util.Base64.getEncoder().encode(data.getBytes("utf-8")), "utf-8"), privateKey, "utf-8");
    }

    public static final String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMbSERmDFY4sQ8TQK04T8kLxvMFYE6HgaYiEpmYuo6YKME9AZYr3PU/FdHG1kiFuG2+4DVzfcKnR56y7dYn2PkzhNJjkfPnp/z/VHcyjc/Afb1hsKbl2T41zOY/blOx09p2kwIb8I1X4xeELpK9HnqiU4I4jvXc67SInlGU6QV9vAgMBAAECgYEAmNdyd8dSscoLiuhw8gPZcGc/sO6RoRbQajd3aAQgoAsjGjmqFgt84a8sliF8JiDsDJVUCHpotNP9osc1U4quVraoMpmO82NkOPOJ6hva9I1NPQUlKbOdDctDjztFAeGo/Jhv36oDVcqGzYFSvtvMM5qlRLx4AJUKX9borxKKmOECQQDnoRtHsQqgJ5jQJgw50O3SAAte+loU+0sSAvtH1kEUK7sMcWaOS1dZbOQ9unLZ1yfo0mAkhiDRTfhKKxdD6JPRAkEA271DODruNFM80mT8Vlz2Ivdr+WcW8ACo9YIyjhVCP4LD5ZPmu1Fprj98Aiy39lAhklWn9tk/SZbtaVT4J0/PPwJBAI7NEFh+maJrRxbsnhT27efOBzSi++57jqioFesP+EdLPqJJN4T6hfun2n8D99K9hulO6d8aohjoDO8M6eb/+EECQQCsnvXbio/ukyVIswR4Y/cIaQ5tksq2KA6TL4C/lXvBoNx70cCEjuRSepTUsISj/zcgWiHlDa1mBibjhFidqVjJAkB7jkjQxl5A2tLZfNJ8hB7Hj1kPHNGfDveIILDepV/yza9AKaTes88qN6I3siDqh1C/BjStAXcsKNUhccdQxbLR";

    public static final String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDG0hEZgxWOLEPE0CtOE/JC8bzBWBOh4GmIhKZmLqOmCjBPQGWK9z1PxXRxtZIhbhtvuA1c33Cp0eesu3WJ9j5M4TSY5Hz56f8/1R3Mo3PwH29YbCm5dk+NczmP25TsdPadpMCG/CNV+MXhC6SvR56olOCOI713Ou0iJ5RlOkFfbwIDAQAB";

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        String testUrl = "https://xlapi-etc-dev.xlkeji.net/xl-api/common/gateway";
        String testUrl = "https://xlapi.etcsd.cn:8093/xl-api/common/gateway";

        JSONObject data = new JSONObject();
        data.put("biz_id", "etc.parking.cloud.fee");
        data.put("waste_sn", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
        JSONObject params = new JSONObject();
        params.put("lane_id", "2024010216562545");
        params.put("lane_name", "2024010216562545出口");
        params.put("pay_serial_no", System.currentTimeMillis() + 1212);
        params.put("trans_amount", 1);
        params.put("vehplate_color", 0);
        params.put("vehplate_no", "陕A87LR6");
        params.put("park_time", parseParkTime(3530/60));
        params.put("enter_time", sdf.format(new Date(System.currentTimeMillis() - 3530 * 1000)));
        params.put("leave_time", sdf.format(new Date()));
        params.put("plate_type_code", "0");
        params.put("parking_type_code", "11");

        data.put("params", params);
        String dataStr = JSON.toJSONString(data);

        System.out.println(JSON.toJSONString(data));
        String s = new String(java.util.Base64.getEncoder().encode(dataStr.getBytes(String.valueOf("utf-8"))));
        String sign = rsa256Sign(s, privateKey, String.valueOf("utf-8"));
//        System.out.println(sign);

        Map<String, String> request = new HashMap<>();
        request.put("versions", "1.0");
        request.put("data", dataStr);
//        request.put("appid", "PK23121437");
        request.put("appid", "PK23123038");
        request.put("sign", sign);

//        testUrl = "http://127.0.0.1:8090/test/xly";
        String test = null;
        try {
            System.out.println("start ......."+System.currentTimeMillis());
            test = post(testUrl, request);
            System.out.println("end ...."+System.currentTimeMillis());
        } catch (Exception e) {
            System.out.println("exception ..."+System.currentTimeMillis());
            e.printStackTrace();
        }
        System.out.println(test);

//        boolean utf8 = rsa256CheckContent(s, sign, pubKey, String.valueOf("utf-8"));
//        System.out.println(utf8);

    }

    public static String post(String reqUrl, Map<String, String> map) throws Exception {
        PostMethod postMethod = new PostMethod(reqUrl) ;
        postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8") ;
        NameValuePair[] data = new NameValuePair[map.size()];
        Object[] objects = map.keySet().toArray();
        for (int i = 0; i < objects.length; i++) {
            String k = (String) objects[i];
            String v = map.get(k);
            data[i] = new NameValuePair(k, v);
        }
        postMethod.setRequestBody(data);
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setSoTimeout(6000);
        client.executeMethod(postMethod);

        InputStream inputStream = postMethod.getResponseBodyAsStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        return sb.toString();
    }

    private static String parseParkTime(Integer parkingTime) {
        String parkTimeStr;
        int day = parkingTime / (24 * 60);
        if (day > 0) {
            parkTimeStr = day + "天";
            int minutes = parkingTime - day * 24 * 60;
            int hour = minutes / 60;
            if (hour > 0) {
                int min = minutes % 60;
                parkTimeStr = parkTimeStr + hour + "时" + min + "分0秒";
            } else {
                parkTimeStr = parkTimeStr + "0时" + minutes + "分0秒";
            }
        } else {
            int hour = parkingTime / 60;
            if (hour > 0) {
                int min = parkingTime % 60;
                parkTimeStr = hour + "时" + min + "分0秒";
            } else {
                parkTimeStr = parkingTime + "分0秒";
            }
        }
        return parkTimeStr;
    }

}
