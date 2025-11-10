package com.bolink;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class YnnxTest {


    public static void main(String[] args) throws Exception {
        Map<String, String> headMap = new HashMap<>();
        headMap.put("ynrcc-sign", "sign");
        headMap.put("ynrcc-cert", "2");
        String url = "https://ebank.ynrcc.com/acqu/";
        String data = "{\"request\":{\"tradeNo\":\"94202510151403044227046\",\"seqNo\":\"202510151403042707324\",\"temId\":\"99001037\",\"termIP\":\"119.23.234.83\",\"onlineFlag\":\"1\",\"apiVersion\":\"1.1.0\",\"totalAmt\":\"10\",\"totalNum\":\"1\",\"service\":\"GenDynaQRCode\",\"ccy\":\"156\",\"txnTime\":\"20251015140304\",\"merId\":\"909731059450288\",\"tranCode\":\"120101\",\"businessType\":\"001\",\"orderDesc\":\"停车费\",\"tradeChannel\":\"01\",\"eventFlag\":\"1\"}}";
        JSONObject jsonObject = HttpClientUtil.postForGetHeader(url, data, "application/json", "utf-8", 5000, 5000, headMap, "ynrcc-sign");
        System.out.println(jsonObject);


    }
}
