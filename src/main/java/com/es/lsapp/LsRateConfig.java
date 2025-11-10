package com.es.lsapp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.Base64Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LsRateConfig {

    public static void main(String[] args) throws Exception {
        rateConfig();
    }

    public static void rateConfig() throws Exception {
        JSONObject payMap = new JSONObject();
        payMap.put("merchantId", "7239111077");//商户号 M

        payMap.put("effectiveType", 2);

        Integer rateVal = 60;

        JSONObject weixin = new JSONObject();
        JSONObject rate = new JSONObject();
        rate.put("rate", rateVal);
        weixin.put("insurance", rate);
        JSONObject rate2 = new JSONObject();
        rate2.put("rate", 61);
        weixin.put("online", rate2);
        payMap.put("weixin", weixin);

        JSONObject ali = new JSONObject();
        rate = new JSONObject();
        rate.put("rate", rateVal);
        ali.put("t1", rate);
        payMap.put("alipay", ali);

        JSONObject unionScan = new JSONObject();
        unionScan.put("t1Credit", rate);
        unionScan.put("t1Debit", rate);
        unionScan.put("t1OverSee", rate);
        if (rateVal >= 60) {
            payMap.put("unionScan", unionScan);
            payMap.put("unionScan2", unionScan);
        }
        Map requestMap = new HashMap();
        requestMap.put("agentId", "5919932");
        requestMap.put("version", "1.0");
        requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
        requestMap.put("data", JSONObject.toJSONString(payMap).toString());
        String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + JSON.toJSONString(payMap), "utf-8").toLowerCase().getBytes());

        requestMap.put("sign", sign);
        System.out.println("配置费率开通支付" + requestMap);
        String result = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/merchant/open", requestMap);
        System.out.println("配置结果" + result);
    }
}
