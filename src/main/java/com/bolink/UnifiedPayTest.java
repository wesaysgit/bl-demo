package com.bolink;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bolink.pojo.ParkingLotProfitDetail;
import com.es.lsapp.TradeNoUtil;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

public class UnifiedPayTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
//        String payUrl = getUnifiedPayUrl("https://beta.bolink.club/unionapi/bolinkunified");
//        String payUrl = queryorder("https://beta.bolink.club/unionapi/queryorder");
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
        data.put("outTradeNo", "241020260201150018256709999");
        data.put("appId", "wxe551bcb8271420f0");
        data.put("delayAccountDay", 1);
        data.put("payTime", 1769938124);

        List<JSONObject> profits = new ArrayList<>();
        JSONObject profit1 = new JSONObject();
        profit1.put("amount", 8);
        profit1.put("feeSubject", 1);
        profit1.put("parkId", "56443");
        profit1.put("platformType", 4);
        profit1.put("unionId", 200712);
        profit1.put("wxWallet", 0);
        JSONObject profit2 = new JSONObject();
        profit2.put("amount", 2);
        profit2.put("feeSubject", 0);
        profit2.put("parkId", "56442");
        profit2.put("platformType", 4);
        profit2.put("unionId", 200712);
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
        String uKey = "C4597BFC7B76089A";
        JSONObject data = new JSONObject();
        data.put("unionId", 201035);
        data.put("parkId", "1514901088864313346");
        String sign = DigestUtil.md5Hex(JSON.toJSONString(data, SerializerFeature.MapSortField) + "key=" + uKey).toUpperCase();

        JSONObject request = new JSONObject();
        request.put("data", data);
        request.put("unionId", 201035);
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

    public static String queryorder(String url) {
        JSONObject data = new JSONObject();
        data.put("trade_no", "2026052515311501");
        data.put("park_id", "56545");
        //厂商密钥
        String uKey = "3A37E69B1E4C614E"; //200712
        String sign = DigestUtil.md5Hex(data + "key=" + uKey, "utf-8").toUpperCase();

        JSONObject request = new JSONObject();
        request.put("data", data);
        request.put("sign", sign);
        request.put("union_id", 201121);

        if (StrUtil.isEmpty(url)) {
            url = "https://beta.bolink.club/unionapi/bolinkunified";
        }
        String params = request.toJSONString();
        System.out.println(params);
        String post = HttpUtil.post(url, params);
        System.out.println(post);
        return "";
    }

    public static String getUnifiedPayUrl(String url) {
        JSONObject data = new JSONObject();
        data.put("title", "停车费用支付");
        data.put("park_id", "56583");//车场编号
        data.put("amount", "0.03");//支付金额（单位：元。2位小数）
        data.put("car_number", "贵AG7X65");//车牌
        data.put("pay_type", 0);//支付类型(必填, 0-扫码支付, 1-微信公众号支付, 2-付款码被扫支付)
        data.put("trade_no", TradeNoUtil.getTradeNo(17));
        data.put("channel", "weixin");//支付渠道(pay_type=0时必填: weixin-微信/alipay-支付宝/applets-小程序/unionpay-银联云闪付)
        data.put("biz_type", "5");//业务类型: 0-停车缴费(def); 1-机场预约; 2-PAAS追缴订单; 3-三方追缴(分润); 4-车位优选; 5-商业咨询服务;
        data.put("time_temp", System.currentTimeMillis() / 1000);
        //分账详情
        ParkingLotProfitDetail detail1 = new ParkingLotProfitDetail();
        detail1.setAmount(2L);//子项分账接收方金额（单份：分）
        detail1.setFeeSubject(1);//子项手续费承担主体
        detail1.setUnionId(201121L);//厂商编号
        detail1.setParkId("56583");//车场编号
        detail1.setPlatformType(4);//平台类型4-车场（固定4）
        ParkingLotProfitDetail detail2 = new ParkingLotProfitDetail();
        detail2.setUnionId(201121L);
        detail2.setParkId("56665");
        detail2.setPlatformType(4);
        detail2.setAmount(1L);
        //分账接收方详情（可为当前业务发生车场）
        data.put("share_details", JSON.toJSONString(Arrays.asList(detail1, detail2)));
        //厂商密钥
        String uKey = "3A37E69B1E4C614E"; //200712
        String sign = DigestUtil.md5Hex(data + "key=" + uKey, "utf-8").toUpperCase();

        JSONObject request = new JSONObject();
        request.put("data", data);
        request.put("sign", sign);
        request.put("union_id", 201121);
//        request.put("request_source", "applets_plugin_yima_direct");
//        request.put("code", "0a3qb8ml2VG2yg4nbvnl2eVkYR1qb8mu");

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
