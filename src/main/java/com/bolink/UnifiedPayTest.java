package com.bolink;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bolink.pojo.ParkingLotProfitDetail;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

public class UnifiedPayTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
//        String payUrl = getUnifiedPayUrl("https://beta.bolink.club/unionapi/bolinkunified");
//        String payUrl = getPayUrlV2("https://s.bolink.club/unionapi/bolinkparkpay");
//        String payUrl = getUnifiedPayUrl("http://127.0.0.1:8080/unionapi/bolinkunified");
//        String result = visitorReq();
//        String appletAppInfo = getAppletAppInfo("https://s.bolink.club/unionapi/applet/appInfo");
//        String appletAppInfo = parkingLotProfit("https://beta.bolink.club/unionapi/miniprogram/parkingLotProfit");
//        String appletAppInfo = parkingLotProfit("http://127.0.0.1:8080/unionapi/miniprogram/parkingLotProfit");
//        queryCustAcctId();
    }

    private static String parkingLotProfit(String url) {
        String uKey = "4F80BF35DF83858D";
        JSONObject data = new JSONObject();
        data.put("profitType", 3);
        data.put("outTradeNo", "241020250722185542607378999");
        data.put("appId", "wxe551bcb8271420f0");
        data.put("delayAccountDay", 3);
        data.put("payTime", 1753180211);

        List<JSONObject> profits = new ArrayList<>();
        JSONObject profit1 = new JSONObject();
        profit1.put("amount", 3);
        profit1.put("feeSubject", 1);
        profit1.put("parkId", "56399");
        profit1.put("platformType", 4);
        profit1.put("unionId", 200712);
        profit1.put("wxWallet", 0);
        JSONObject profit2 = new JSONObject();
        profit2.put("amount", 3);
        profit2.put("feeSubject", 0);
        profit2.put("parkId", "jichangtest");
        profit2.put("platformType", 4);
        profit2.put("unionId", 201018);
        profit2.put("wxWallet", 0);
        profits.add(profit1);
        profits.add(profit2);

        data.put("profitDetails", profits);

//        String sign = StringUtilBl.MD5(JSON.toJSONString(data, SerializerFeature.MapSortField) + "key=" + uKey).toUpperCase();
        String sign = DigestUtil.md5Hex(data + "key=" + uKey, "utf-8").toUpperCase();

        JSONObject request = new JSONObject();
        request.put("data", data);
        request.put("unionId", 200712);
        request.put("sign", sign);
        System.out.println(request.toJSONString());
        String post = HttpUtil.post(url, request.toJSONString());
        System.out.println(post);
        return null;
    }

    private static String getAppletAppInfo(String url) {
        String uKey = "B4279FD34C634941";
        JSONObject data = new JSONObject();
        data.put("unionId", 202570);
        data.put("parkId", "230776");
        String sign = StringUtilBl.MD5(JSON.toJSONString(data, SerializerFeature.MapSortField) + "key=" + uKey).toUpperCase();


        JSONObject request = new JSONObject();
        request.put("data", data);
        request.put("unionId", 202570);
        request.put("sign", sign);
        String post = HttpUtil.post(url, request.toJSONString());
        System.out.println(post);
        return null;
    }


    public static String visitorReq() {
        String url = "https://beta.bolink.club/unionapi/neworder/visitorCodePrepayHandle";
        JSONObject data = new JSONObject();
        data.put("unionId", 201018);
        data.put("parkId", "223344");
        data.put("orderId", "A1_2C1724740277");
        data.put("outOrderId", "AU8LE1723527262041");//预约充值订单id
        data.put("money", BigDecimal.valueOf(0.01));
        data.put("platId", "10000002");
        data.put("plateNumber", "粤A77777");

        String uKey = "06BE6FCB92A64961";
        String signStr = JSON.toJSONString(data) + "key=" + uKey;
        String sign = DigestUtil.md5Hex(signStr, "utf-8").toUpperCase();

        JSONObject request = new JSONObject();
        request.put("data", data);
        request.put("unionId", 201018);
        request.put("sign", sign);
        String post = HttpUtil.post(url, request.toJSONString());
        System.out.println(post);
        return post;
    }

    public static String getPayUrlV2(String url) {
        JSONObject data = new JSONObject();
        data.put("callback_url", "https://one.yunparking.club/yunbo/#/pages/success?money=3.0");
        data.put("amount", "0.02");
        data.put("car_number", "wx4e1d0d53645b8f11");
        data.put("in_time", "1731396137371");
        data.put("wx_app_id", "wx0870f79235e513f7");
//        data.put("wx_open_id", "ofUl45Oe7znyy0WSjIwN_2cKSWL8");
        data.put("park_id", "161270");
        data.put("channel", "applets");
        data.put("trade_no", "202411085133697506496" + new Random().nextInt(1000));
        data.put("time_temp", System.currentTimeMillis());
        data.put("pay_type", 0);
        data.put("title", "停车");
        data.put("order_id", "100001305794438160711680");
        data.put("description", "商户自助续费");

        String uKey = "F71E99F251301CC5"; //200712

        String linkString = StringUtilBl.createLinkString(data);
        String sign = DigestUtil.md5Hex(linkString + "&key=" + uKey, "utf-8").toUpperCase();
        JSONObject request = new JSONObject();
        request.put("data", data);
        request.put("sign", sign);
        request.put("union_id", 201246);
        request.put("request_source", "applets_yyxgj_wx");

        String post = HttpUtil.post(url, request.toJSONString());
        System.out.println(post);
        JSONObject resObj = JSON.parseObject(post);
        String payUrl = resObj.getString("payurl");
        return payUrl;
    }

    public static String getUnifiedPayUrl(String url) {
        JSONObject data = new JSONObject();
//        data.put("callback_url", "https://one.yunparking.club/yunbo/#/pages/success?money=3.0");
        data.put("title", "停车费用支付");
        data.put("park_id", "cs2025102301");
        data.put("amount", "0.01");
        data.put("car_number", "贵AG7X65");
        data.put("pay_type", 0);
        data.put("trade_no", "175583463250082123417");
        data.put("channel", "applets");
//        data.put("douyin_pay", 1);
//        data.put("douyin_params", "{\"limitPayWayList\":[],\"orderEntrySchema\":{\"path\":\"subpackages/airport-parking/order-detail/index\"},\"payExpireSeconds\":300,\"skuList\":[{\"imageList\":[\"https://image.bolink.com/yima/parking-icon.png\"],\"price\":2,\"quantity\":1,\"skuId\":\"1\",\"tagGroupId\":\"tag_group_7443548955339669558\",\"title\":\"曾天劭自动化进件1753340831\",\"type\":703}],\"totalAmount\":2}");
//        data.put("airport_pre_paid", 1);
        data.put("time_temp", System.currentTimeMillis() / 1000);
//        data.put("wx_app_id", "wxe551bcb8271420f0");
//        data.put("wx_open_id", "0d3qJmHa1tfzxK0y9oHa1yBwWv0qJmHw");
//        data.put("paas_hermes_paid", 1);
        ParkingLotProfitDetail detail1 = new ParkingLotProfitDetail();
        detail1.setAmount(80L);
        detail1.setShopId("5933000000091007");
        detail1.setFeeSubject(1);
        detail1.setPlatformId(2L);
        ParkingLotProfitDetail detail2 = new ParkingLotProfitDetail();
        detail2.setAmount(20L);
        detail2.setShopId("5933000000091007");
        detail2.setFeeSubject(1);
        detail2.setPlatformId(3L);
//        data.put("share_details", JSON.toJSONString(Arrays.asList(detail1, detail2)));
        data.put("wx_app_id", "wx0870f79235e513f7");
//        data.put("wx_open_id", "ofUl45B5OLX0AYX1dcArstEc9Z5w");
//        data.put("order_id", "1806574373341462503");
//        data.put("description", "停车费用支付");

//        String uKey = "1C263F8299C535C6"; //201026 223355 gaodeng
//        String uKey = "06BE6FCB92A64961"; //201018 223355 315357
//        String uKey = "6D1B9AC3968F877E"; //201033 fenruntest 315577
//        String uKey = "4F80BF35DF83858D"; //200712
        String uKey = "17AC830AB299EBFD"; //200712
//        String signStr = data.toJSONString() + "key=" + uKey;
//        String sign = DigestUtil.md5Hex(signStr, "utf-8").toUpperCase();

        String linkString = StringUtilBl.createLinkString(data);
        String sign = DigestUtil.md5Hex(linkString + "&key=" + uKey, "utf-8").toUpperCase();
        JSONObject request = new JSONObject();
        request.put("data", data);
        request.put("sign", sign);
        request.put("union_id", 200688);
//        request.put("request_source", "applets_plugin_dy_pingan");
        request.put("request_source", "applets_plugin_yima_direct");
        request.put("code", "0a3qb8ml2VG2yg4nbvnl2eVkYR1qb8mu");

        if (StrUtil.isEmpty(url)) {
            url = "https://beta.bolink.club/unionapi/bolinkunified";
        }
        String params = request.toJSONString();
        System.out.println(params);
        String post = HttpUtil.post(url, params);
        System.out.println(post);
        return "";
    }

    private static void queryCustAcctId() {
        JSONObject request = new JSONObject();
        request.put("txnCode", "6010");

        JSONObject body = new JSONObject();
        body.put("QueryFlag", "2");
        body.put("PageNum", "1");
        body.put("SubAcctNo", "5933000000000003");
        body.put("FundSummaryAcctNo", "15000114484826");

        request.put("req", body);
        String url = "https://s.bolink.club/sett/binternal/pingAnApi";

        System.out.println(JSONObject.toJSONString(request));
//        String post = HttpUtil.post(url, JSONObject.toJSONString(request));
//        System.out.println(post);
    }

}
