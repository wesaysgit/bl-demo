package com.es.etc.yunnan;

import com.alibaba.fastjson.JSONObject;
import com.es.lsapp.HttpClientUtil;
import com.es.lsapp.MD5Util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

public class YunNanEtcDemo {
    private static final SecureRandom random = new SecureRandom();
    private static final String DATA_URL = "http://sandbox.ynhim.com/middlewareApp/clound";
    private static final String APPID = "444e62b9e89c4c6fac1ff292776fd5f4";
    private static final String PARKING_NO = "ETC123456";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    public static void main(String[] args) {
        String orderId = "A1_2C"+System.currentTimeMillis()/1000;
        String carNumber = "云A627AA";
        String enterRes = enterInfo(orderId, carNumber);

//        JSONObject enterJson = JSON.parseObject(enterRes);
//        String himOrderNo = enterJson.getJSONObject("data").getString("him_order_no");
//        String applyRes = apply(orderId, carNumber, himOrderNo);
//        JSONObject applyJson = JSON.parseObject(applyRes);
//        String payOrderNo = applyJson.getJSONObject("data").getString("pay_order_no");
//        exitInfo(orderId, carNumber, himOrderNo, payOrderNo);
    }

    /** 支付 */
    public static String apply(String orderId, String carNumber, String himOrderNo) {
        try {
            String name = "enter_"+PARKING_NO+"_"+orderId+".jpg";
            String upload = upload(name, orderId, carNumber);
            System.out.println("upload = " + upload);
            JSONObject data = new JSONObject();
            data.put("parking_no", PARKING_NO);
            data.put("enter_order_no", orderId);
            data.put("exit_order_no", orderId);
            data.put("him_order_no", himOrderNo);
            data.put("trans_place_type", 1);
            data.put("plate_no", carNumber);
            data.put("export_no", "4");
            data.put("export_name", "出口");
            data.put("exit_time", sdf.format(new Date()));
            data.put("parking_type", 1);
            data.put("parking_time", 3600);
            data.put("receivable_fee", 1);
            data.put("discount_fee", 0);
            data.put("discount_type", 0);
            data.put("actual_fee", 1);
            data.put("exit_images", "exit_"+PARKING_NO+"_"+orderId+".jpg");
            data.put("pay_time_out", 4000);
            JSONObject request = commonRequest(data, "etc.parking.payinfo.apply");
            request.put("parking_no", PARKING_NO);
            request.put("lane_no", data.getString("export_no"));
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("车辆扣费请求>>>"+request);
            String response = HttpClientUtil.postParameters(DATA_URL, request);
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("车辆扣费结果>>>"+response);
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 出场  */
    public static String exitInfo(String orderId, String carNumber, String himOrderNo, String payOrderNo) {
        try {
            JSONObject data = new JSONObject();
            data.put("parking_no", PARKING_NO);
            data.put("enter_order_no", orderId);
            data.put("exit_order_no", orderId);
            data.put("him_order_no", himOrderNo);
            data.put("trans_place_type", 1);
            data.put("plate_no", carNumber);
            data.put("entrance_no", "3");
            data.put("entrance_name", "入口");
            data.put("enter_time", sdf.format(new Date()));
//            data.put("enter_time", "20230419154855");
            data.put("export_no", "4");
            data.put("export_name", "出口");
            data.put("exit_time", sdf.format(new Date()));
            data.put("parking_type", 1);
            data.put("parking_time", 3600);
            data.put("receivable_fee", 1);
            data.put("discount_fee", 0);
            data.put("actual_fee", 1);
            data.put("exit_pay_method", 1);
            data.put("pay_order_no", payOrderNo);

            JSONObject request = commonRequest(data, "saas.parking.exitinfo.sync");
            request.put("parking_no", PARKING_NO);
            request.put("lane_no", data.getString("export_no"));
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("车辆出场信息传输请求>>>"+request);
            String response = HttpClientUtil.postParameters(DATA_URL, request);
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("车辆出场信息传输结果>>>"+response);
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 入场 */
    public static String enterInfo(String orderId, String carNumber) {
        try {
            String name = "enter_"+PARKING_NO+"_"+orderId+".jpg";
            String upload = upload(name, orderId, carNumber);
            System.out.println("upload = " + upload);
            JSONObject data = new JSONObject();
            data.put("parking_no", PARKING_NO);
            data.put("enter_order_no", orderId);
            data.put("trans_place_type", 1);//交易场景类别（1：路外停车场；2：路内泊位）,固定传1
            data.put("plate_no", carNumber);
            data.put("entrance_no", "3178");//入口编号
            data.put("entrance_name", "入口");
            data.put("enter_time", sdf.format(new Date()));
            data.put("parking_type", 1);
            data.put("enter_images", name);
            data.put("read_time_out", 4000);

            JSONObject request = commonRequest(data, "etc.parking.enterinfo.sync");
            request.put("parking_no", data.getString("parking_no"));
            request.put("lane_no", data.getString("entrance_no"));
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("车辆入场信息传输请求>>>"+request);
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
            String response = HttpClientUtil.postParameters(DATA_URL, request);
            System.out.println("车辆入场信息传输结果>>>"+response);
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String upload(String name, String orderId, String carNumber) {
        try {
//            String base64 = NetImageToBase64(IMAGE_URL_EX);
            String base64 = "";
            List<JSONObject> car_images = new ArrayList<>();
            JSONObject image = new JSONObject();
            image.put("name", name);
            image.put("data", base64);
            car_images.add(image);

            JSONObject data = new JSONObject();
            data.put("parking_no", PARKING_NO);
            data.put("order_no", orderId);
            data.put("plate_no", carNumber);
            data.put("order_time", sdf.format(new Date()));
            data.put("car_images", car_images);

            JSONObject request = commonRequest(data, "saas.parking.upload.file");
            request.put("parking_no", PARKING_NO);
            request.put("lane_no", "3");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
//            System.out.println("车辆出入场信息照片上传输请求>>>"+request);
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
            String response = HttpClientUtil.postParameters(DATA_URL, request);
            System.out.println("车辆出入场信息照片上传输结果>>>"+response);
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public static JSONObject commonRequest(JSONObject data, String keyword) {
        String sign = MD5Util.MD5Encode(data + APPID, "utf-8");
        JSONObject request = new JSONObject();
        request.put("appid", APPID);
        request.put("versions", "1.0");
        request.put("interface_keyword", keyword);
        request.put("request_sn", UUID.randomUUID().toString().replace("-", ""));
        request.put("data", data.toString());
        request.put("sign", sign);
        return  request;
    }

    public static String NetImageToBase64(String imgUrl) {
        final ByteArrayOutputStream data = new ByteArrayOutputStream();
        String base64 = "";
        try {
            URL url = new URL(imgUrl);
            final byte[] by = new byte[1024];
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            InputStream is = conn.getInputStream();
            int len;
            while ((len = is.read(by)) != -1) {
                data.write(by, 0, len);
            }
            base64 = Base64.getEncoder().encodeToString(data.toByteArray());
            is.close();
        } catch (IOException e) {
            System.out.println("图片转换异常" + imgUrl);
            e.printStackTrace();
        }
        return base64;
    }

    public static String getTradeNo(Integer payCompanyNo){
        SimpleDateFormat sdf_no = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String pre = "";
        if(payCompanyNo != null){
            pre = payCompanyNo+"";
        }
        Calendar calendar = Calendar.getInstance();
        return pre+sdf_no.format(calendar.getTime()) + (int)(random.nextDouble() * 10000000);
    }
}
