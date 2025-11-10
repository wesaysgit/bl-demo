package com.es.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping()
public class CallBackController {

    private final Log log = LogFactory.getLog(CallBackController.class);

    @RequestMapping("/callback/js_china")
    public void jsChinaCallback(HttpServletRequest request, HttpServletResponse response) {
        try {
            log.error(">>>>>>>>>>>>>>>>>>>>>jsChina callback into...");
            Map<String, String[]> map = request.getParameterMap();
            JSONObject object = new JSONObject();
            map.keySet().forEach(key -> object.put(key, map.get(key)[0]));
            System.out.println(JSONObject.toJSONString(object, true));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    @RequestMapping("/callback/nj")
    public JSONObject queryPrice(@RequestBody JSONObject dataReq) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>"+dataReq);
        JSONObject data = dataReq.getJSONObject("data");
        String licence = data.getString("licence");
        String park_id = data.getString("park_id");
        String query_order_no = data.getString("query_order_no");
        String out_channel_id = data.getString("out_channel_id");
        String pay_user_id = data.getString("pay_user_id");
        Integer pay_scene = data.getInteger("pay_scene");
        String service_name = data.getString("service_name");
        JSONObject resData = new JSONObject();
        switch (service_name) {
            case "query_price":
                resData.put("service_name", "query_price");
                resData.put("park_id", park_id);
                resData.put("errmsg", "查询成功");
                resData.put("data_target", "cloud");
                resData.put("query_order_no", query_order_no);
                resData.put("multiple_prepay", 0);
                resData.put("duration", 2);
                resData.put("derate_duration", 0);
                resData.put("total", "3.0");
                resData.put("in_time", System.currentTimeMillis()/1000-3400);
                resData.put("price", "3.0");
                resData.put("car_number", "川EF52N2");
                resData.put("free_out_time", 15);
                resData.put("state", 1);
                resData.put("order_id", System.currentTimeMillis()+999);
                resData.put("query_time", System.currentTimeMillis()/1000);
            default:

        }
        return resData;
    }

    public static JSONObject toJson(String content) {
        JSONObject object = new JSONObject();
        String[] split = content.split("&");
        Arrays.stream(split).forEach(it -> {
            String[] strings = it.split("=");
            if (strings.length > 1) object.put(strings[0], strings[1]);
            else object.put(strings[0], "");
        });
        return object;
    }

}
