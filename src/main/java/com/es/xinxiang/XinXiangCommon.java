package com.es.xinxiang;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;

public class XinXiangCommon {
    public static String url = "https://beta.bolink.club/unionapi/xinxiang/common";
    public static String account = "10027";
    public static String signKey = "uool2wnjrf2hkcwuvd368efm26tl6lgh";
    public static String dataKey = "22y5LmHK84qpRRrn";
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    public static void main(String[] args) throws Exception {
//        String str = "7f8YyNC4Wo6ARS1Kr0JzG7HayUF+gT2Qy8bnve6XmySic7iFUU0qCIWCflHWmXyroGjTsu5UaVByHyi0H5hVQgv9rY5tlsWSLm0XyPsbo2o+zpZ19gSGBd2ef06YgJe7Tu4WyFEDpancHnpIm8gVFzSZLphghiD1YNuw6ajZcE0=";
//        String decrypt = AesUtil.decrypt(str, dataKey);
//        JSONObject object = JSON.parseObject(decrypt);
//        System.out.println("object = " + object);
        parkingDelPlate();
    }

    /** 查询停车费-车牌号码(下行) */
    public static void queryParkingFeePlate() throws Exception {
        GregorianCalendar gCalendar = new GregorianCalendar();
        String timestamp = sdf.format(gCalendar.getTime());

        Map<String, Object> params1 = new HashMap<>();
        params1.put("parklotCode", "D005");
        params1.put("plateNo", "临A14859");
        params1.put("laneCode", "C1");

        Map<String, Object> params2 = new HashMap<>();
        params2.put("parklotCode", "D005");
        params2.put("plateNo", "临A14856");
        params2.put("laneCode", "C1");

        Map<String, Object> params3 = new HashMap<>();
        params3.put("parklotCode", "D005");
        params3.put("plateNo", "临A14857");
        params3.put("laneCode", "C1");

        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        maps.add(params1);
        maps.add(params2);
        maps.add(params3);

        Map<String, String> contentMap = new HashMap<>();
        contentMap.put("method", "queryParkingFeePlate");
        contentMap.put("params", JSONObject.toJSONString(maps));
        String content = JSONObject.toJSONString(contentMap);

        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("account", account);
        reqMap.put("timestamp", timestamp);
        reqMap.put("sign", SignUtil.createSign(account, timestamp, signKey));
        reqMap.put("content",AesUtil.encrypt(content, dataKey));
        System.out.println(JSONObject.toJSONString(reqMap));
        System.out.println("====================================");

        String response = HttpUtil.post(url, JSON.toJSONString(reqMap));
        System.out.println(response);
    }

    public static void main1(String[] args) throws Exception {

        String decrypt = AesUtil.decrypt("7f8YyNC4Wo6ARS1Kr0JzG7HayUF+gT2Qy8bnve6XmyRaSLkGEC8hE9LZXtUD4IEu/oyHigUuAedb+P0hUWqVZp+TbAxa6PX4AJQz0isjUQbGLUHGFcZaLQ3NZ6ZuQwRM", dataKey);
        JSONObject contentMap  = JSONObject.parseObject(decrypt);
        String method = contentMap.getString("method");
        JSONObject params = contentMap.getJSONObject("params");
        JSONArray params1 = contentMap.getJSONArray("params");
        String parklotCode = params.getString("parklotCode");
        System.out.println("parklotCode = " + parklotCode);
    }

    /** 查询会员车(下行) */
    public static void queryVehicle() throws Exception {
        GregorianCalendar gCalendar = new GregorianCalendar();
        String timestamp = sdf.format(gCalendar.getTime());
        Map<String, Object> params = new HashMap<>();
        params.put("parklotCode", "12321412");
        params.put("plate", "湘ADD441");

        Map<String, String> contentMap = new HashMap<>();
        contentMap.put("method", "queryVehicle");
        contentMap.put("params", JSONObject.toJSONString(params));
        String content = JSONObject.toJSONString(contentMap);
        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("account", account);
        reqMap.put("timestamp", timestamp);
        reqMap.put("sign", SignUtil.createSign(account, timestamp, signKey));
        reqMap.put("content",AesUtil.encrypt(content, dataKey));
        System.out.println(JSONObject.toJSONString(reqMap));
        System.out.println("====================================");

        String response = HttpUtil.post(url, JSON.toJSONString(reqMap));
        System.out.println("response = " + response);
    }

    public static void notifyParkingFee() throws Exception {
        GregorianCalendar gCalendar = new GregorianCalendar();
        String timestamp = sdf.format(gCalendar.getTime());
        Map<String, Object> params = new HashMap<>();
        params.put("parklotCode", "12321412");
        params.put("plate", "苏E00007");
        params.put("preBillUuid", "20076812321412A1_2C16729704209811");
        params.put("inUniqueNo", "A1_2C16729704209811in");
        params.put("laneCode", "A1");
        params.put("payFee", "1");
        params.put("payTime", sdf.format(new Date()));
        params.put("payModel", 1);
        params.put("payType", "1");

        Map<String, String> contentMap = new HashMap<>();
        contentMap.put("method", "notifyParkingFee");
        contentMap.put("params", JSONObject.toJSONString(params));
        String content = JSONObject.toJSONString(contentMap);
        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("account", account);
        reqMap.put("timestamp", timestamp);
        reqMap.put("sign", SignUtil.createSign(account, timestamp, signKey));
        reqMap.put("content",AesUtil.encrypt(content, dataKey));
        System.out.println(JSONObject.toJSONString(reqMap));
        System.out.println("====================================");

        String response = HttpUtil.post(url, JSON.toJSONString(reqMap));
        System.out.println("response = " + response);
    }

    /** 删除场内车(下行) */
    public static void parkingDelPlate() throws Exception {
        GregorianCalendar gCalendar = new GregorianCalendar();
        String timestamp = sdf.format(gCalendar.getTime());
        Map<String, Object> params = new HashMap<>();
        params.put("parklotCode", "D005");
        params.put("inUniqueNo", "4b6ee7c3b9ae4d59acd1651919b6c507");

        Map<String, String> contentMap = new HashMap<>();
        contentMap.put("method", "parkingDelPlate");
        contentMap.put("params", JSONObject.toJSONString(params));
        String content = JSONObject.toJSONString(contentMap);
        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("account", account);
        reqMap.put("timestamp", timestamp);
        reqMap.put("sign", SignUtil.createSign(account, timestamp, signKey));
        reqMap.put("content",AesUtil.encrypt(content, dataKey));
        System.out.println(JSONObject.toJSONString(reqMap));
        System.out.println("====================================");

        String response = HttpUtil.post(url, JSON.toJSONString(reqMap));
        System.out.println("response = " + response);
    }

    /** 停车场心跳(下行) */
    public static void parklotHeart() throws Exception {
        GregorianCalendar gCalendar = new GregorianCalendar();
        String timestamp = sdf.format(gCalendar.getTime());
        Map<String, Object> params = new HashMap<>();
        params.put("parklotCode", "12321412");
        params.put("uploadDataUrl", "https://test.xxparking.com/openapi/v3/dclot");

        Map<String, String> contentMap = new HashMap<>();
        contentMap.put("method", "parklotHeart");
        contentMap.put("params", JSONObject.toJSONString(params));
        String content = JSONObject.toJSONString(contentMap);
        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("account", account);
        reqMap.put("timestamp", timestamp);
        reqMap.put("sign", SignUtil.createSign(account, timestamp, signKey));
        reqMap.put("content",AesUtil.encrypt(content, dataKey));
        System.out.println(JSONObject.toJSONString(reqMap));
        System.out.println("====================================");

        String response = HttpUtil.post(url, JSON.toJSONString(reqMap));
        System.out.println("response = " + response);
    }

    /** 解锁(下行) */
    public static void unlockLaneDevice() throws Exception {
        GregorianCalendar gCalendar = new GregorianCalendar();
        String timestamp = sdf.format(gCalendar.getTime());
        Map<String, Object> params = new HashMap<>();
        params.put("parklotCode", "12321412");
        params.put("laneCode", "A1");

        Map<String, String> contentMap = new HashMap<>();
        contentMap.put("method", "unlockLaneDevice");
        contentMap.put("params", JSONObject.toJSONString(params));
        String content = JSONObject.toJSONString(contentMap);
        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("account", account);
        reqMap.put("timestamp", timestamp);
        reqMap.put("sign", SignUtil.createSign(account, timestamp, signKey));
        reqMap.put("content",AesUtil.encrypt(content, dataKey));
        System.out.println(JSONObject.toJSONString(reqMap));
        System.out.println("====================================");

        String response = HttpUtil.post(url, JSON.toJSONString(reqMap));
        System.out.println("response = " + response);
    }

    /** 锁闸(下行) */
    public static void lockLaneDevice() throws Exception {
        GregorianCalendar gCalendar = new GregorianCalendar();
        String timestamp = sdf.format(gCalendar.getTime());
        Map<String, Object> params = new HashMap<>();
        params.put("parklotCode", "12321412");
        params.put("laneCode", "A1");

        Map<String, String> contentMap = new HashMap<>();
        contentMap.put("method", "lockLaneDevice");
        contentMap.put("params", JSONObject.toJSONString(params));
        String content = JSONObject.toJSONString(contentMap);
        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("account", account);
        reqMap.put("timestamp", timestamp);
        reqMap.put("sign", SignUtil.createSign(account, timestamp, signKey));
        reqMap.put("content",AesUtil.encrypt(content, dataKey));
        System.out.println(JSONObject.toJSONString(reqMap));
        System.out.println("====================================");

        String response = HttpUtil.post(url, JSON.toJSONString(reqMap));
        System.out.println("response = " + response);
    }

    /** 开闸(下行) */
    public static void openLaneDevice() throws Exception {
        GregorianCalendar gCalendar = new GregorianCalendar();
        String timestamp = sdf.format(gCalendar.getTime());
        Map<String, Object> params = new HashMap<>();
        params.put("parklotCode", "12321412");
        params.put("laneCode", "A1");

        Map<String, String> contentMap = new HashMap<>();
        contentMap.put("method", "openLaneDevice");
        contentMap.put("params", JSONObject.toJSONString(params));
        String content = JSONObject.toJSONString(contentMap);
        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("account", account);
        reqMap.put("timestamp", timestamp);
        reqMap.put("sign", SignUtil.createSign(account, timestamp, signKey));
        reqMap.put("content",AesUtil.encrypt(content, dataKey));
        System.out.println(JSONObject.toJSONString(reqMap));
        System.out.println("====================================");

        String response = HttpUtil.post(url, JSON.toJSONString(reqMap));
        System.out.println("response = " + response);
    }

    /** 删除黑名单(下行) */
    public static void delBlackVehicle() throws Exception {
        GregorianCalendar gCalendar = new GregorianCalendar();
        String timestamp = sdf.format(gCalendar.getTime());
        Map<String, Object> params = new HashMap<>();
        params.put("parklotCode", "12321412");
        params.put("plateColor", 0);
        params.put("plate", "鄂A12345");

        Map<String, String> contentMap = new HashMap<>();
        contentMap.put("method", "delBlackVehicle");
        contentMap.put("params", JSONObject.toJSONString(params));
        String content = JSONObject.toJSONString(contentMap);

        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("account", account);
        reqMap.put("timestamp", timestamp);
        reqMap.put("sign", SignUtil.createSign(account, timestamp, signKey));
        reqMap.put("content",AesUtil.encrypt(content, dataKey));
        System.out.println(JSONObject.toJSONString(reqMap));
        System.out.println("====================================");

        String response = HttpUtil.post(url, JSON.toJSONString(reqMap));
        System.out.println("response = " + response);
    }

    /** 添加/修改黑名单(下行) */
    public static void addBlackVehicle() throws Exception {
        GregorianCalendar gCalendar = new GregorianCalendar();
        String timestamp = sdf.format(gCalendar.getTime());
        Map<String, Object> params = new HashMap<>();
        params.put("parklotCode", "12321412");
        params.put("plateColor", 0);
        params.put("plate", "鄂A12345");
        params.put("createTime", sdf.format(new Date()));
        params.put("startTime", "20230105144201");
        params.put("endTime", "20240605144201");
        params.put("limitPassIn", 0);
        params.put("vehContact", "张伟");
        params.put("vehContactNumber", "13705468888");
        params.put("reason", "拉黑");

        Map<String, String> contentMap = new HashMap<>();
        contentMap.put("method", "addBlackVehicle");
        contentMap.put("params", JSONObject.toJSONString(params));
        String content = JSONObject.toJSONString(contentMap);

        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("account", account);
        reqMap.put("timestamp", timestamp);
        reqMap.put("sign", SignUtil.createSign(account, timestamp, signKey));
        reqMap.put("content",AesUtil.encrypt(content, dataKey));
        System.out.println(JSONObject.toJSONString(reqMap));
        System.out.println("====================================");

        String response = HttpUtil.post(url, JSON.toJSONString(reqMap));
        System.out.println("response = " + response);
    }

    /** 添加/修改会员车(下行) */
    public static void addVehicle() throws Exception {
        GregorianCalendar gCalendar = new GregorianCalendar();
        String timestamp = sdf.format(gCalendar.getTime());
        Map<String, Object> params = new HashMap<>();
        params.put("parklotCode", "12321412");
        params.put("plate", "鄂A12345");
        params.put("plateColor", 0);
        params.put("createTime", sdf.format(new Date()));
        params.put("startDate", "20230105");
        params.put("endDate", "20240605");
        params.put("userName", "张伟");
        params.put("userPhone", "13705468888");

        Map<String, String> contentMap = new HashMap<>();
        contentMap.put("method", "addVehicle");
        contentMap.put("params", JSONObject.toJSONString(params));
        String content = JSONObject.toJSONString(contentMap);

        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("account", account);
        reqMap.put("timestamp", timestamp);
        reqMap.put("sign", SignUtil.createSign(account, timestamp, signKey));
        reqMap.put("content",AesUtil.encrypt(content, dataKey));
        System.out.println(JSONObject.toJSONString(reqMap));
        System.out.println("====================================");

        String response = HttpUtil.post(url, JSON.toJSONString(reqMap));
        System.out.println("response = " + response);
    }

    /** 删除会员车(下行) */
    public static void delVehicle() throws Exception {
        GregorianCalendar gCalendar = new GregorianCalendar();
        String timestamp = sdf.format(gCalendar.getTime());
        Map<String, Object> params = new HashMap<>();
        params.put("parklotCode", "12321412");
        params.put("plate", "鄂A12345");
        params.put("plateColor", 0);

        Map<String, String> contentMap = new HashMap<>();
        contentMap.put("method", "delVehicle");
        contentMap.put("params", JSONObject.toJSONString(params));
        String content = JSONObject.toJSONString(contentMap);

        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("account", account);
        reqMap.put("timestamp", timestamp);
        reqMap.put("sign", SignUtil.createSign(account, timestamp, signKey));
        reqMap.put("content",AesUtil.encrypt(content, dataKey));
        System.out.println(JSONObject.toJSONString(reqMap));
        System.out.println("====================================");

        String response = HttpUtil.post(url, JSON.toJSONString(reqMap));
        System.out.println("response = " + response);
    }



    /** 重写无牌车车牌(下行) */
    public static void rewriteNoPlateForWaitPass() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        GregorianCalendar gCalendar = new GregorianCalendar();
        String timestamp = sdf.format(gCalendar.getTime());
        Map<String, Object> params = new HashMap<>();
        params.put("parklotCode", "12321412");
        params.put("plate", "7777777");
        params.put("passTime", timestamp);
        params.put("inUniqueNo", "1673432124772in");
        params.put("laneCode", "A1");
        params.put("direction", 1);
        Map<String, String> contentMap = new HashMap<>();
        contentMap.put("method", "rewriteNoPlateForWaitPass");
        contentMap.put("params", JSONObject.toJSONString(params));
        String content = JSONObject.toJSONString(contentMap);

        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("account", account);
        reqMap.put("timestamp", timestamp);
        reqMap.put("sign", SignUtil.createSign(account, timestamp, signKey));
        reqMap.put("content",AesUtil.encrypt(content, dataKey));
        System.out.println(JSONObject.toJSONString(reqMap));
        System.out.println("====================================");

        String response = HttpUtil.post(url, JSON.toJSONString(reqMap));
        System.out.println("response = " + response);
    }

}
