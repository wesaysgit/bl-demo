package com.es.bolink;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class SendDataToPay {

    public static void main(String[] args) {
        int i = 0;
        try {
            while (i < 1) {
                String res = HttpClientUtil.get("https://s.bolink.club/unionapi/wallet/updDateToPayment");
                System.out.println(res);
                JSONObject obj = JSON.parseObject(res);
                Boolean state = obj.getBoolean("state");
                if (state) {
                    System.out.println(i+"成功");
                    Thread.sleep(15000);
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
