package com.bolink;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 厂商编号：201109
 * 密钥：0C672A7A2141875E
 * 车场comId：530707
 * 车场编号：233174
 */
public class AgreementSignTest {

//    private static final String uKey = "5541BE4DCB5E8374";
//    private static final String unionId = "200835";
//    private static final String url_prefix = "https://beta.bolink.club";
//    private static final String url_prefix = "http://127.0.0.1:8080";
//
    private static final String uKey = "0C672A7A2141875E";
    private static final String unionId = "201109";
    private static final String url_prefix = "https://s.bolink.club";

    public static void main(String[] args) {
//        agreementSign();
        agreementSignQuery("1352592283635023875");
//        agreementUnSign("1356377910759391303");
//        agreementPay();
//        payQuery("1354960906559160458");
//        payQuery("1361576779692640322");
//        payRefund("", "29.90");
    }

    /**
     * 签约发起
     *
     * @return
     */
    public static String agreementSign() {
        String url = url_prefix + "/unionapi/aliAgreement/sign";
        JSONObject data = new JSONObject();
        data.put("executeTime", "2024-12-26");
        data.put("period", 1);
        data.put("comId", 314646);
        data.put("periodType", "MONTH");
        data.put("singleAmount", "0.01");
        data.put("totalAmount", "1");
        data.put("totalPayments", 3L);
        data.put("externalAgreementNo", "202412261882768865510001");
        data.put("effectTime", 300);
        data.put("returnUrl", "https://www.baidu.com");
        data.put("notifyUrl", "https://beta.bolink.club/unionapi/aliAgreement/signNotify");

        String signStr = JSON.toJSONString(data) + "key=" + uKey;
        String sign = DigestUtil.md5Hex(signStr, "utf-8").toUpperCase();

        JSONObject request = new JSONObject();
        request.put("data", data);
        request.put("unionId", unionId);
        request.put("sign", sign);
        String post = HttpUtil.post(url, request.toJSONString());
        System.out.println(post);
        return post;
    }

    /**
     * 签约查询
     *
     * @return
     */

    public static String agreementSignQuery(String externalAgreementNo) {
        String url = url_prefix + "/unionapi/aliAgreement/signQuery";
        JSONObject data = new JSONObject();

        data.put("externalAgreementNo", externalAgreementNo);

        String signStr = JSON.toJSONString(data) + "key=" + uKey;
        String sign = DigestUtil.md5Hex(signStr, "utf-8").toUpperCase();

        JSONObject request = new JSONObject();
        request.put("data", data);
//        request.put("unionId", 200835);
        request.put("unionId", unionId);
        request.put("sign", sign);
        System.out.println(request.toJSONString());
        String post = HttpUtil.post(url, request.toJSONString());
        System.out.println(post);
        return post;
    }

    /**
     * 解约发起
     *
     * @return
     */
    public static String agreementUnSign(String externalAgreementNo) {
        String url = url_prefix + "/unionapi/aliAgreement/unSign";
        JSONObject data = new JSONObject();

        data.put("externalAgreementNo", externalAgreementNo);

        String signStr = JSON.toJSONString(data) + "key=" + uKey;
        String sign = DigestUtil.md5Hex(signStr, "utf-8").toUpperCase();

        JSONObject request = new JSONObject();
        request.put("data", data);
        request.put("unionId", unionId);
        request.put("sign", sign);
        String post = HttpUtil.post(url, request.toJSONString());
        System.out.println(post);
        return post;
    }

    /**
     * 签约扣费
     *
     * @return
     */
    public static String agreementPay() {
        String url = url_prefix + "/unionapi/aliAgreement/pay";
        JSONObject data = new JSONObject();

        String agreementNo = "20246025233677714996";
        data.put("outTradeNo", agreementNo + "001");
        data.put("totalAmount", "0.01");
        data.put("subject", "扣款测试19001");
        data.put("agreementNo", agreementNo);

        String signStr = JSON.toJSONString(data) + "key=" + uKey;
        String sign = DigestUtil.md5Hex(signStr, "utf-8").toUpperCase();

        JSONObject request = new JSONObject();
        request.put("data", data);
        request.put("unionId", unionId);
        request.put("sign", sign);
        String post = HttpUtil.post(url, request.toJSONString());
        System.out.println(post);
        return post;
    }

    /**
     * 签约扣费查询
     *
     * @return
     */
    public static String payQuery(String outTradeNo) {
        String url = url_prefix + "/unionapi/aliAgreement/payQuery";
        JSONObject data = new JSONObject();

        data.put("outTradeNo", outTradeNo);

        String signStr = JSON.toJSONString(data) + "key=" + uKey;
        String sign = DigestUtil.md5Hex(signStr, "utf-8").toUpperCase();

        JSONObject request = new JSONObject();
        request.put("data", data);
        request.put("unionId", unionId);
        request.put("sign", sign);
        System.out.println(request.toJSONString());
        String post = HttpUtil.post(url, request.toJSONString());
        System.out.println(post);
        return post;
    }

    /**
     * 签约扣费退款
     *
     * @return
     */
    public static String payRefund(String outTradeNo, String refundAmount) {
        String url = url_prefix + "/unionapi/aliAgreement/payRefund";
        JSONObject data = new JSONObject();

        data.put("outTradeNo", outTradeNo);
        data.put("refundAmount", refundAmount);

        String signStr = JSON.toJSONString(data) + "key=" + uKey;
        String sign = DigestUtil.md5Hex(signStr, "utf-8").toUpperCase();

        JSONObject request = new JSONObject();
        request.put("data", data);
        request.put("unionId", unionId);
        request.put("sign", sign);
        System.out.println(request.toJSONString());
        String post = HttpUtil.post(url, request.toJSONString());
        System.out.println(post);
        return post;
    }


}








































































































































