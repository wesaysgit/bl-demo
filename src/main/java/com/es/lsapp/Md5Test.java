package com.es.lsapp;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.Base64Utils;

import java.util.Locale;

public class Md5Test {

    public static void main(String[] args) throws Exception {
        String data = "{\"parking_no\":\"ETC123456\",\"order_no\":\"E93AA8F83B4E4328JB27B56085219A98\",\"plate_no\":\"云" +
                "AJ03J5\",\"order_time\":\"20220101103015\",\"car_images\":[{\"name\":\"exit_5300020001_" +
                "E93AA8F83B4E4328JB27B56085219A98.jpg\",\"data\":\"basedsdsdsadsd64\"}]}";
        String appid = "444e62b9e89c4c6fac1ff292776fd5f4";
        JSONObject object = JSONObject.parseObject(data);
        String s = MD5Util.MD5Encode(object+appid, "utf-8");
        System.out.println((data + appid));
        String sign = Base64Utils.encodeToString(MD5Util.MD5Encode(data+appid,"utf-8").toLowerCase().getBytes());
        System.out.println("sign = " + sign);
        String s1 = MD5Util.MD5Encode(data + appid, "utf-8").toUpperCase(Locale.ROOT);
        System.out.println("s1 = " + s1);

    }


}
