package com.es.bolink;

import com.alibaba.fastjson.JSONObject;

public class ClientTest {

    public static void main(String[] args) {
        storedCouponRefund();
    }

    public static void storedCouponRefund() {
        try {
            JSONObject request = new JSONObject();
            request.put("union_id", 200279);
            request.put("sign", "ssss");
            JSONObject data = new JSONObject();
            data.put("trade_no", "21202309111510373212591308");
            data.put("refund_money", 1.00);
            data.put("union_id", 200279);
            data.put("end_time", 1695180439);
            data.put("park_id", "leshuatest2020102602");
            data.put("refund_reason", "21312");
            data.put("source", "wx");
            request.put("data", data);
            System.out.println(request);
            String result = HttpClientUtil.post("https://beta.bolink.club/unionapi/miniprogram/storedCouponRefund", JSONObject.toJSONString(request),"application/json","utf-8",10000,10000);
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
