package com.es.bolink.qrcode;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Base64Test {

    public static void main(String[] args) {
        String secretKey = "ERlaAZ5q";

        JSONObject param = new JSONObject(true);
        param.put("qrCodeId", "71446821");
        param.put("operate", "query");
        param.put("timestemp", System.currentTimeMillis());
        String sign = getSign(param, secretKey);
        // 整合请求参数
        JSONObject reqParm = new JSONObject();
        reqParm.put("sign", sign);
        reqParm.put("data", param);

        String result = HttpRequest.post("https://s.bolink.club/unionapi/microcode").timeout(5000).body(reqParm.toJSONString()).execute().body();

        System.out.println(reqParm);
        System.out.println(result);
    }

    public static String getSign(JSONObject data, String key) {
        String _signStr = data.toString() + "key=" + key;
        String _sign = MD5(_signStr).toUpperCase();
        return _sign;
    }

    public static String MD5(String s) {
        //System.err.println(s);
        try {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.reset();
            byte abyte0[] = messagedigest.digest(s.getBytes("utf-8"));
            return byteToString1(abyte0);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String byteToString1(byte abyte0[]) {
        int i = abyte0.length;
        char[] ac = new char[i * 2];
        int j = 0;
        for (int k = 0; k < i; k++) {
            byte byte0 = abyte0[k];
            ac[j++] = hexDigits[byte0 >>> 4 & 0xf];
            ac[j++] = hexDigits[byte0 & 0xf];
        }

        return new String(ac);
    }


    private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

}
