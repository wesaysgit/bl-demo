package com.es;

import cn.hutool.core.util.XmlUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class LsParse {

    public static void main(String[] args) {
        String payUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        String xmlStr = "<xml><appid>wx962fe9d5c0e2a2c7</appid><attach><![CDATA[{\"paysence\":\"1\",\"costtype\":1,\"appKey\":\"bolink20180603101600weixipay0000\"}]]></attach><body><![CDATA[北京大学第一医院大兴院区停车场-停车费-京LKP978]]></body><mch_id>1493651012</mch_id><nonce_str>8eJtTIyYhoce6nEQ</nonce_str><notify_url>https://s.bolink.club/unionapi/wxnotify/codepay</notify_url><openid>ouc2o03_GFjDip4jPBBiqim9bARA</openid><out_trade_no>0202405231130489806940823</out_trade_no><scene_info>{\"store_info\":{\"id\":\"492847\",\"name\":\"北京大学第一医院大兴院区停车场\"}}</scene_info><sign><![CDATA[6B37C8ACB2B84549961737568E7D2679]]></sign><spbill_create_ip>172.16.0.41</spbill_create_ip><sub_mch_id>1666785981</sub_mch_id><total_fee>1000</total_fee><trade_type>JSAPI</trade_type></xml>";
        String post = HttpUtil.post(payUrl, xmlStr);
        System.out.println(post);
        Map reMap = XmlUtil.xmlToMap(post);
        System.out.println(JSON.toJSONString(reMap));
    }

}