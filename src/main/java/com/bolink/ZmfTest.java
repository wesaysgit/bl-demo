package com.bolink;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bolink.pojo.LsZhimaScoreRequestDto;
import com.es.encrypt.StringUtils;
import com.es.lsapp.MD5Util;
import com.es.lsapp.WxPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class ZmfTest {

    public static final String SUCCESSCODE = "000000";

    public static void main(String[] args) {

//        closeOrder(null);

        //获取外部协议号
        LsZhimaScoreRequestDto dto2 = LsZhimaScoreRequestDto.builder()
                .outAgreementNo("202606111100147133245")
                .build();
        JSONObject agreementIdList = getAgreementIdList(dto2);
//        JSONObject agreementIdList = null;
        Integer state1 = agreementIdList.getInteger("state");
        if (state1 == 1) {
            String data1 = agreementIdList.getString("data");
            LsZhimaScoreRequestDto dto3 = LsZhimaScoreRequestDto.builder()
                    .tradeComponentOrderId("2026061101502300000006410071409740")
                    .tcInstallmentOrderId("2026061101502301900006410049445945")
                    .agreementNo(data1)
                    .merchantId("1830015938")
                    .outTradeNo("a7d843ef18694625bb307c415c2da406")
                    .amount("3777")
                    .title("充电桩充电")
                    .build();
            withHoldingAgain(dto3);
        }

    }

    public static JSONObject withHoldingAgain(LsZhimaScoreRequestDto req) {
        JSONObject ret = new JSONObject();
        ret.put("state", 0);
        ret.put("msg", "代扣失败");
        try {
            //String url = "https://paygate.leshuazf.com/cgi-bin/lepos_pay_gateway.cgi";
            //String backUrl =  Defind.getProperty("DOMAIN") +"lsZhimaScore/notify/paySuccess";
            String backUrl =  "https://s.bolink.club/unionapi/lsZhimaScore/notify/paySuccess";
            Map agreement_params = new HashMap();
            agreement_params.put("agreement_no", req.getAgreementNo());
            Map extend_business_params = new HashMap();
            Map extend_params = new HashMap();
            extend_params.put("tradeComponentOrderId", req.getTradeComponentOrderId());//交易组件业务主单号
            extend_params.put("tcInstallmentOrderId", req.getTcInstallmentOrderId());//交易组件分期履约单号
            extend_business_params.put("extend_params", extend_params);
            Map payMap = new HashMap();
            payMap.put("product_code", "GENERAL_WITHHOLDING");
            payMap.put("agreement_params", JSON.toJSONString(agreement_params));
            payMap.put("extend_business_params", JSON.toJSONString(extend_business_params));
            payMap.put("service", "alipay_zms_credit_redebit");
            payMap.put("pay_way", "ZFBZF");
            payMap.put("auth_code", "137385866377690103");//付款码,无实际意义
            payMap.put("merchant_id", req.getMerchantId());
            payMap.put("third_order_id", req.getOutTradeNo());
            payMap.put("amount", req.getAmount());
            payMap.put("notify_url", backUrl);
            payMap.put("body", req.getTitle());
            payMap.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32).toUpperCase());//随机字符串  M
            payMap.put("sign", getSign(payMap,1));//签名 M
            log.info("【芝麻分】重新代扣请求:"+JSON.toJSONString(payMap));
            String result = HttpClientUtil.postParameter("https://paygate.leshuazf.com/cgi-bin/lepos_pay_gateway.cgi", payMap);
            log.info("【芝麻分】重新代扣返回:"+JSON.toJSONString(result));
            Map<String, String> resMap = WxPayUtil.xmlToMap(result);
            log.info("【芝麻分】重新代扣结果:"+JSON.toJSONString(resMap));
            if (Objects.equals("0", resMap.get("result_code")) && Objects.equals("0", resMap.get("resp_code"))) {
                ret.put("state", 1);
                ret.put("data", resMap.get("data"));
                ret.put("msg", "重新代扣成功");
            } else {
                ret.put("msg", resMap.get("error_msg"));
            }
            return ret;
        } catch (Exception e) {
            ret.put("state", 0);
            ret.put("msg", "代扣异常");
            log.info("乐刷【芝麻分】代扣异常", e);
        }
        return ret;
    }

    public static JSONObject getAgreementIdList(LsZhimaScoreRequestDto req) {
        JSONObject ret = new JSONObject();
        ret.put("state", 0);
        ret.put("msg", "获取失败");
        try {
            String url = "https://saas-mch.leshuazf.com/apiv2/zhimaScoreOpen/getAgreementIdList";
            Map data = new HashMap();
            data.put("outAgreementNo", req.getOutAgreementNo());//外部商户协议号

            String dataStr = JSON.toJSONString(data);

            Map requestMap = new HashMap();
            requestMap.put("agentId", "5919932");
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", dataStr);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos5D5DC123FD9DE1B4984C9FF07E19EFCA" + dataStr, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            log.info("【芝麻分】代扣协议号获取请求:" + JSON.toJSONString(requestMap));
            String result = HttpClientUtil.postParameters(url, requestMap);
            log.info("【芝麻分】代扣协议号获取返回:" + JSON.toJSONString(result));

            JSONObject restObj = JSON.parseObject(result);
            if (Objects.equals(SUCCESSCODE, restObj.get("respCode"))) {
                ret.put("state", 1);
                ret.put("data", restObj.getJSONObject("data").getString("generalWithHoldingP"));
                ret.put("msg", "获取成功");
            } else {
                ret.put("msg", restObj.get("respMsg"));
            }
            return ret;
        } catch (Exception e) {
            ret.put("state", 0);
            ret.put("msg", "代扣协议号获取异常");
            log.info("乐刷【芝麻分】代扣协议号获取异常", e);
        }
        return ret;
    }

    public static JSONObject closeOrder(LsZhimaScoreRequestDto req) {
        JSONObject ret = new JSONObject();
        ret.put("state", 0);
        ret.put("msg", "获取失败");
        try {
            String url = "https://saas-mch.leshuazf.com/apiv2/zhimaScoreOpen/closeOrder";
            Map data = new HashMap();
            data.put("userId", "2088132779769641");//
            data.put("outOrderId", "20265411282619335664");//
            data.put("orderId", "2026061101502300000006410071409740");//

            String dataStr = JSON.toJSONString(data);

            Map requestMap = new HashMap();
            requestMap.put("agentId", "5919932");
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", dataStr);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos5D5DC123FD9DE1B4984C9FF07E19EFCA" + dataStr, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            log.info("【芝麻分】代扣协议号获取请求:" + JSON.toJSONString(requestMap));
            String result = HttpClientUtil.postParameters(url, requestMap);
            log.info("【芝麻分】代扣协议号获取返回:" + JSON.toJSONString(result));

            JSONObject restObj = JSON.parseObject(result);
            if (Objects.equals(SUCCESSCODE, restObj.get("respCode"))) {
                ret.put("state", 1);
                ret.put("data", restObj.getJSONObject("data").getString("generalWithHoldingP"));
                ret.put("msg", "获取成功");
            } else {
                ret.put("msg", restObj.get("respMsg"));
            }
            return ret;
        } catch (Exception e) {
            ret.put("state", 0);
            ret.put("msg", "代扣协议号获取异常");
            log.info("乐刷【芝麻分】代扣协议号获取异常", e);
        }
        return ret;
    }

    public static String getSign(Map<String, String> source, int type) {//1 交易报文 2 通知报文 3营销补贴
        if (type == 1 || type == 2) {
            Map resultMap = new TreeMap<>(String::compareTo);
            resultMap.putAll(source);
            Iterator<String> it = resultMap.keySet().iterator();
            StringBuilder paramStr = new StringBuilder();
            while (it.hasNext()) {
                String key = it.next();
                if (!StringUtils.isNotNull(String.valueOf(resultMap.get(key)))) {
                    continue;
                }
                paramStr.append("&").append(key).append("=").append(resultMap.get(key));
            }
            String result = MD5Util.MD5Encode(paramStr.substring(1) + "&key=5D5DC123FD9DE1B4984C9FF07E19EFCA", "utf-8");
            return type == 1 ? result.toUpperCase() : result.toLowerCase();
        } else if (type == 3) {
            return MD5Util.MD5Encode(net.sf.json.JSONObject.fromObject(source).toString() + "5D5DC123FD9DE1B4984C9FF07E19EFCA", "utf-8").toLowerCase();
        }
        return null;
    }

}
