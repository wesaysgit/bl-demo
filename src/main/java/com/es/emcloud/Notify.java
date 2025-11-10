package com.es.emcloud;

import com.alibaba.fastjson.JSONObject;

public class Notify {

    public static void main(String[] args) {

        String payNotifyJ = "{\"channelTradeNo\":\"4200002298202406139846793366\",\"openId\":\"\\\"ouc2o08ls6JmSy9Hwmr-7qNUL4uk\\\"\",\"outTradeNo\":\"21202406131554117198813613\",\"payCallParamDTO\":{\"mchId\":\"2444118828\",\"mchOrderId\":\"9000406982824165\",\"outTradeNo\":\"21202406131554117198813613\",\"payWay\":\"WXZF\",\"subMchId\":\"569635592\",\"subMchOrderId\":\"4200002298202406139846793366\",\"subMchTradeNo\":\"4200002298202406139846793366\"},\"payPlateTradeNo\":\"9000406982824165\",\"payTime\":1718265304}";
        PayNotifyDTO payNotify = JSONObject.parseObject(payNotifyJ, PayNotifyDTO.class);
        System.out.println(payNotify);

        System.out.println("沪AEB8502".length());

    }
}
