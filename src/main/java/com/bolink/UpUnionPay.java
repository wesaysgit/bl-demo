package com.bolink;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.XmlUtil;
import com.alibaba.fastjson.JSONObject;
import com.es.lsapp.HttpClientUtil;
import com.es.lsapp.MD5Util;
import com.es.lsapp.TradeNoUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

public class UpUnionPay {

    private static SecureRandom random = new SecureRandom();

    public static void main(String[] args) throws Exception {
        //89058487523993C,8905848752301B5
//        queryMchId("6157211815");
        String redirectUrl = getCode("6157211815", null);
        String source = "02";
        String weAppId = "wx3cbe919f36710d1c";
        String entryAppid = "wx46cbaf3845af50f1";
        String isLocationAuthRequird = "02";
        String host = "html";
        String isNeedWeApp = "1";
        String serviceStr = "/pagesPay/payDetails/index"
                + "?certificate=" + redirectUrl
                + "&source=" + source
                + "&entryAppId=" + entryAppid
                + "&isLocationAuthRequird=" + isLocationAuthRequird;
        String weAppPath = "/pages/CQPApplet/index?service=" +
                URLEncoder.encode(serviceStr, StandardCharsets.UTF_8.name()) + "&type=applet";
        String weAppEnvVersion = "release";

        JSONObject data = new JSONObject();
        data.put("dest", redirectUrl.replace("https://", ""));
        data.put("host", host);
        data.put("isNeedWeApp", isNeedWeApp);
        data.put("weAppId", weAppId);
        data.put("weAppPath", weAppPath);
        data.put("weAppEnvVersion", weAppEnvVersion);

        String base64 = Base64.encodeBase64String(data.toJSONString().getBytes(StandardCharsets.UTF_8));
        String params = URLEncoder.encode(base64, String.valueOf(StandardCharsets.UTF_8));
        String payUrl = "https://base.cup.com.cn/s/wl/WebAPP/helpAgree/page/sharePay/" + RandomUtil.randomString(8) + "?params=" + params;
        System.out.println(payUrl);
    }

    public static String getCode(String merchantId, String ass_merchant_id) throws Exception {
        String tradeNo = getTradeNo(21);
        Map payMap = new HashMap();
        payMap.put("amount", "1");//订单金额 M
        payMap.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32).toUpperCase());//随机字符串  M
        payMap.put("pnrins_id_cd", "C1004248");
        payMap.put("jspay_flag", "1");
        payMap.put("merchant_id", "6157211815");//商户号 M
        payMap.put("notify_url", encodeUTF8("https://beta.bolink.club/unionapi/walletNotify/rechargeCallback"));//通知地址 O
        payMap.put("body", "1");//商品描述  O
        payMap.put("pay_way", "UPSMZF");//支付类型 M
//        payMap.put("sub_openid", "LKEg2qAVz27Oor1JsiUAPdqm2VukxmDm/8V0nT8ldESg3ksHsitmmnEb2CpuzFOH");//支付类型 M
        payMap.put("third_order_id", TradeNoUtil.getTradeNo(21));//订单号 M
        payMap.put("app_up_identifier", "MicroMessenger");//订单号 M
        payMap.put("appid", "wx46cbaf3845af50f1");
        payMap.put("sub_openid", "432434");
        payMap.put("qrcode_url", "https://beta.bolink.club/nw/unionapi/t?p=151539");//订单号 M

        payMap.put("service", "get_tdcode");//接口名 M

        //支付成功跳转页
        String defaultPaySuccessUrl = "http://beta.bolink.club/unionapi/payComplete?money=1" + "&tradeno=" + tradeNo + "&merchant_id=" + merchantId;
        payMap.put("jump_url", encodeUTF8(defaultPaySuccessUrl));//支付成功跳转页面 需要编码 C
        payMap.put("sign", getSign(payMap, 1));//签名 M
        System.out.println("下单:" + JSONObject.toJSONString(payMap));
        String lsResultStr = HttpClientUtil.postParameters("https://paygate.leshuazf.com/cgi-bin/lepos_pay_gateway.cgi", payMap);
        Map<String, Object> map = XmlUtil.xmlToMap(lsResultStr);
        System.out.println("返回:" + JSONObject.toJSONString(map));
        return (String) map.get("jspay_info");
    }

    private static String queryMchId(String lsMchId) throws Exception {
        String unionMchId = "";

        JSONObject reqJson = new JSONObject();
        reqJson.put("merchantId", lsMchId);
        reqJson.put("subMchType", 4);

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("agentId", 5919932);
        requestMap.put("version", "2.0");
        requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
        requestMap.put("data", reqJson.toString());

        String sign = Base64Utils.encodeToString(
                MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + reqJson.toString(), "utf-8")
                        .toLowerCase().getBytes()
        );
        requestMap.put("sign", sign);

        String result = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/submch/query", requestMap, 3000, 3000);
        System.out.println(result);
        return unionMchId;
    }


    public static String encodeUTF8(String someStr) {
        String newStr = null;
        if (someStr != null && someStr.equals("")) {
            return "";
        }
        if (someStr != null) {
            try {
                newStr = URLEncoder.encode(someStr, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return newStr;
    }

    public static String getTradeNo(Integer payCompanyNo) {
        SimpleDateFormat sdf_no = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String pre = "";
        if (payCompanyNo != null) {
            pre = payCompanyNo + "";
        }
        Calendar calendar = Calendar.getInstance();
        return pre + sdf_no.format(calendar.getTime()) + (int) (random.nextDouble() * 10000000);
    }

    public static String getSign(Map<String, String> source, int type) {//1 交易报文 2 通知报文 3营销补贴
        if (type == 1 || type == 2) {
            Map resultMap = new TreeMap<>(String::compareTo);
            resultMap.putAll(source);
            Iterator<String> it = resultMap.keySet().iterator();
            StringBuilder paramStr = new StringBuilder();
            while (it.hasNext()) {
                String key = it.next();
                if (!StringUtils.isNotBlank(String.valueOf(resultMap.get(key)))) {
                    continue;
                }
                paramStr.append("&").append(key).append("=").append(resultMap.get(key));
            }
            String result = MD5Util.MD5Encode(paramStr.substring(1) + "&key=5D5DC123FD9DE1B4984C9FF07E19EFCA", "utf-8");
            return type == 1 ? result.toUpperCase() : result.toLowerCase();
        } else if (type == 3) {
            return MD5Util.MD5Encode(JSONObject.toJSONString(source) + "5D5DC123FD9DE1B4984C9FF07E19EFCA", "utf-8").toLowerCase();
        }
        return null;
    }
}
