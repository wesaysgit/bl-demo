package com.es.etc.yunnan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class YunNanEtcTest {
    public static void main(String[] args) {
        JSONObject object = new JSONObject();
        object.put("fee", 0.01);
        Integer fee = object.getInteger("fee");
        System.out.println(fee);

    }
}
