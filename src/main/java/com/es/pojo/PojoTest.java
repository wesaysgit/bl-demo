package com.es.pojo;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;

public class PojoTest {

    public static void main(String[] args) {
        UnionOrderUnpayTb unpay = new UnionOrderUnpayTb();
//                unpay.setId(unpayOrder.getId());
        unpay.setOutChannelId("9");
        unpay.setUnionId(200128L);
        unpay.setRecordTime(1669496871L);
        unpay.setTradeNo("1");
        String s = JSONObject.toJSONString(unpay);
        System.out.println("s = " + s);

    }
}
