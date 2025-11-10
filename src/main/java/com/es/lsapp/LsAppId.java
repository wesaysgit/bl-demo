package com.es.lsapp;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.es.wesays.TimeTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Base64Utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 6967215495
 * 2355111163
 * 7447411803 -- app商户号
 */
@Slf4j
public class LsAppId {
    public static void main(String[] args) {
//        int orderAmount = Integer.parseInt(String.format("%.1f", 2.5));
//        System.out.println(orderAmount);
//        List<String> list = Arrays.asList("5919932031100563881520250311", "5919932031100563889020250311", "5919932031100563903220250311", "5919932031100563927920250311", "5919932031100564225220250311", "5919932031100570299220250311", "5919932031100570599920250311", "5919932031100570672420250311", "5919932031100570695920250311", "5919932031100570749320250311", "5919932031100570880320250311", "5919932031100571422920250311", "5919932031100571437520250311", "5919932031100571576920250311", "5919932031101193586020250311", "5919932031101193592320250311", "5919932031101193607020250311", "5919932031101195770720250311", "5919932031101195788220250311", "5919932031101195915920250311");
//        for (String s : list) {
        //查询营销状态
//            qryTransferStatus("5919932081401070329420250814");
//        }

//        queryCanSubAmount("4511610354");
//        queryAgentBalance("6967215495");

//        getLsWithDrawBalance("4511610354");
//        withdrawApply("2916714623", 0);

//        settlementOrder("20240327005000014093");
        //配置公众号和小程序appid
//        wxpayconfig(1, "wx4e1d0d53645b8f11", "2758519839", false, "643327838");
//        wxpayconfigQry("8354612068", "643327838");
//        queryPayStatus("8949114447", "21202508061356412103216", "");
//        queryPayStatus("9836614035", "21202510222003387552029542598", "");
//        queryPayStatus("6967215495", "", "8001287749525278");

        //分账订单退款
//        profitOrderRefund("21202510051059082917804", "8001519249525278", "6967215495", 1000);
        //普通订单退款
//        unifiedRefund("","", 0);
//        unifiedRefundQuery("7544517585", "21202401021229332631947266","ACEE13AD7032483D957C58320E09AAEE");
//        String leshuaOrderId = "1001612916323059";
        // 8001173649522280
//        JSONObject queryBind = queryBind("6967215495");
//        List<String> data = queryBind.getJSONArray("data");
//        for (String datum : data) {
//            unbind("2355111163", datum);
//        }
//        JSONArray bindIds = queryBind.getJSONArray("data");
//        List<String> list = JSON.parseArray(bindIds.toString(), String.class);
//        bind("2355111163", "0644610749");
//        unbind();

//        regist();
//        profitRefund("2120241110095434490356", "8000168349524315", 0);
//        shareMerchantCancel("0644610749", "9000782574923320", "p21202311161638429818385503");


//        closeOrder("1830015938", "cf485a42748146ac8b6b6a1aff5dc049");
//        flowQuery("6330010434");
//        flowQuery1("6330010434");
//        queryAccreditBanlanceIncome();


        //amount
//        multiQuery("0230415919", "p21202506021222508474542", "", "1001465991025152");

//        accreditQuery("0040819157");
//        elecContractAccredit("8354612068", "浙江大华智慧物联运营服务有限公司");

        //余额分账
//        createSumAccredit();
        //余额分账结果查询
//        querySumAccredit("6967215495", "p61759853123248");
        //撤销余额分账
//        canceSumAccredit();

        //订单分账请求
//        multiApply("8749512946", "p" + TradeNoUtil.getTradeNo(21), "21202410141559331619711", "9000623394624288", 200);

//        transferAccount();
//        getOrderSettleInfo("6330010434", "", "2");

    }

    public static void qryTransferStatus(String transferTradeNo) {

        try {
            Map requestMap = new LinkedHashMap();
            requestMap.put("version", "version");
            requestMap.put("agentId", "5919932");
            JSONObject dateJson = new JSONObject();
            dateJson.put("SubsidyId", transferTradeNo);
            requestMap.put("data", dateJson);
            requestMap.put("sign", getSign(requestMap, 3));
            System.out.println("查询营销转账的参数" + requestMap);
            String result = HttpClientUtil.postHttpsJSON("https://saas-combine.leshuazf.com/open-api/agent-subsidy/query-subsidy-batch", JSON.toJSONString(requestMap));
            System.out.println(JSON.toJSONString(result));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void queryAgentBalance(String merchantId) {
        try {
            Map requestMap = new LinkedHashMap();
            requestMap.put("version","version");
            requestMap.put("agentId", merchantId);

            requestMap.put("sign", getSign(requestMap,3));
            log.error("查询营销余额的参数"+requestMap);
            String result = HttpClientUtil.postHttpsJSON("https://saas-combine.leshuazf.com//open-api/agent-subsidy/query-agent-balance", JSON.toJSONString(requestMap));
            log.error("查询营销余额结果"+result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeOrder(String merchantId, String thirdOrderId) {
        try {
            Map payMap = new HashMap();
            payMap.put("service", "close_order");//接口名 M
            payMap.put("merchant_id", merchantId);
            payMap.put("third_order_id", thirdOrderId);
//            payMap.put("leshua_order_id", thirdOrderId);
            payMap.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32).toUpperCase());//随机字符串  M
            payMap.put("sign", getSign(payMap, 1));//签名 M
            log.info("请求关单:"+JSONObject.toJSONString(payMap));
            String result = HttpClientUtil.postParameters("https://paygate.leshuazf.com/cgi-bin/lepos_pay_gateway.cgi", payMap);
//            String result = HttpClientUtil.postParameters("https://t-paygate.lepass.cn/cgi-bin/lepos_pay_gateway.cgi", payMap);
            Map<String, String> stringStringMap = WxPayUtil.xmlToMap(result);
            log.info("关单返回:"+JSONObject.toJSONString(stringStringMap));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void getOrderSettleInfo(String shopId, String orderId, String tradeType) {
        try {
            JSONObject data = new JSONObject();
            data.put("merchantId", shopId);
            data.put("orderId", orderId);
            data.put("tradeType", tradeType);

            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "2.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
            requestMap.put("data", data.toJSONString());
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + data.toString(), "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

//            System.out.println("商户交易订单清分结果查询" + JSON.toJSONString(requestMap));
            String s = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/merchant/getOrderSettleInfo", requestMap);
//            System.out.println(s);
            JSONObject obj = JSON.parseObject(s);
            JSONArray array = obj.getJSONArray("data");
            JSONObject o = (JSONObject) array.get(0);
            String settleType = o.getString("settle_type");
            System.out.println("settleType = " + settleType);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    public static void transferAccount() {
        try {
            int total = 298;
            JSONArray jsonArray = new JSONArray();
            JSONObject array = new JSONObject(true);
            array.put("MerchantId", "");
            array.put("ServiceFee", 0);
            array.put("MarketingFee", 298);
            jsonArray.add(array);

            JSONObject dateMap = new JSONObject(true);
            dateMap.put("SubsidyDate", TimeTools.getTimeYYYY_MM_DD());
            dateMap.put("TotalServiceFee", 0);
            dateMap.put("TotalMarketingFee", total);
            dateMap.put("TotalSubsidyFee", total);
            dateMap.put("TotalMerchantCount", 1);
            dateMap.put("SubsidyDetailList", jsonArray);

            Map requestMap = new LinkedHashMap();
            requestMap.put("version", "0.0.1");
            requestMap.put("agentId", "5919932");
            requestMap.put("data", dateMap);
            requestMap.put("sign", getSign(requestMap, 3));
            System.out.println(JSON.toJSONString(requestMap));
            String result = HttpClientUtil.postHttpsJSON("https://saas-combine.leshuazf.com//open-api/agent-subsidy/insert-agent-subsidy-msg", JSON.toJSONString(requestMap));
            System.out.println("营销补贴返回的信息为" + result);
            if (!StringUtils.isEmpty(result) && isJson(result)) {
                JSONObject resultJson = JSON.parseObject(result);
                if (resultJson.containsKey("error_code") && "0".equals(String.valueOf(resultJson.get("error_code")))) {
                    Thread.sleep(1000);
                    String subsidyId = String.valueOf(resultJson.get("SubsidyId"));

                    JSONObject dateJson = new JSONObject(true);
                    dateJson.put("SubsidyId", subsidyId);
                    dateJson.put("HandleStatus", 1);

                    requestMap = new LinkedHashMap();
                    requestMap.put("version", "0.0.1");
                    requestMap.put("agentId", "5919932");
                    requestMap.put("data", dateJson);
                    requestMap.put("sign", getSign(requestMap, 3));
                    System.out.println("请求确认营销补贴的 参数信息" + requestMap.toString());
                    String confirmResult = HttpClientUtil.postHttpsJSON("https://saas-combine.leshuazf.com/open-api/agent-subsidy/confirm-agent-subsidy-msg", JSON.toJSONString(requestMap));
                    System.out.println("确认返回结果" + confirmResult);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isJson(String content) {
        try {
            if (content == null ||content.equals(""))
                return false;
            JSONObject.parseObject(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static void shareMerchantCancel(String shopId, String profiTradeNo, String thirdRoyaltyId) {
        try {
            SortedMap reqMap = new TreeMap();
            reqMap.put("merchantId", shopId);
            reqMap.put("leshuaOrderId", profiTradeNo);
            reqMap.put("thirdRoyaltyId", thirdRoyaltyId);

            JSONObject reqJson = new JSONObject(reqMap);

            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
            requestMap.put("data", reqJson.toString());
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + reqJson.toString(), "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("分账请求" + JSON.toJSONString(requestMap));
            String s = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/api/share-merchant/cancel", requestMap);
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void multiApply(String shopId, String profiTradeNo, String outTradeNo, String leshuaTradeNo, Integer amount) {

        Map<String, Object> retmap = new HashMap<>();
        retmap.put("success", false);
        try {
            SortedMap reqMap = new TreeMap();
            reqMap.put("merchantId", shopId);
            reqMap.put("thirdRoyaltyId", profiTradeNo);
            reqMap.put("thirdOrderId", outTradeNo);
            reqMap.put("leshuaOrderId", leshuaTradeNo);

            List<Map> shareDetail = new ArrayList<>();
            SortedMap shareDetailItem = new TreeMap();
            shareDetailItem.put("merchantId", shopId);
            shareDetailItem.put("amount", amount);
            shareDetailItem.put("remark", "手动分账");
            shareDetail.add(shareDetailItem);

            reqMap.put("shareDetail", shareDetail);

            JSONObject reqJson = new JSONObject(reqMap);

            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
            requestMap.put("data", reqJson.toString());
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + reqJson.toString(), "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("分账请求" + JSON.toJSONString(requestMap));
            String s = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/api/share-merchant/multi-apply", requestMap);
            System.out.println(s);
            JSONObject jsonObject = JSONObject.parseObject(s);

            String respCode = jsonObject.getString("respCode");
            if ("000000".equals(respCode)) {
                JSONObject data = jsonObject.getJSONObject("data");
                //分账状态 0-初始化 1-成功 2-失败
                Integer royalty_status = data.getInteger("royalty_status");
                if (royalty_status != null && royalty_status == 1) {
                    retmap.put("success", true);
                }
                retmap.put("msg", data.getString("retmsg"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static JSONObject queryCanSubAmount(String shopId) {
        JSONObject reqJson = new JSONObject();
        reqJson.put("level", "realtime");
        reqJson.put("account_no", shopId);

        Map requestMap = new HashMap();
        requestMap.put("agentId", 5919932);
        requestMap.put("version", "1.0");
        requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
        requestMap.put("data", reqJson.toString());
        String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + reqJson.toString(), "utf-8").toLowerCase().getBytes());
        requestMap.put("sign", sign);
        System.out.println("待分账账户余额查询请求：" + requestMap);
        try {
            String result = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/api/share-merchant/queryCanSubAmount", requestMap);
            System.out.println(result);
            return JSONObject.parseObject(result);
        } catch (Exception e) {
            System.out.println(shopId + "待分账账户余额查询异常");
            e.printStackTrace();
        }
        return null;
    }

    private static JSONObject accreditQuery(String shopId) {

        JSONObject reqJson = new JSONObject();
        reqJson.put("merchantId", shopId);

        Map requestMap = new HashMap();
        requestMap.put("agentId", 5919932);
        requestMap.put("version", "1.0");
        requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
        requestMap.put("data", reqJson.toString());
        String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + reqJson.toString(), "utf-8").toLowerCase().getBytes());
        requestMap.put("sign", sign);
        System.out.println("分账授权状态请求：" + requestMap);
        try {
            String result = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/api/share-merchant/accreditQuery", requestMap);
            System.out.println(result);
            return JSONObject.parseObject(result);
        } catch (Exception e) {
            System.out.println(shopId + "分账授权状态异常");
            e.printStackTrace();
        }
        return null;
    }

    private static JSONObject elecContractAccredit(String shopId, String merchantName) {
        JSONObject reqJson = new JSONObject();
        reqJson.put("merchantId", shopId);
        JSONObject baseInfo = new JSONObject();
        baseInfo.put("shareRole", 0);
        JSONObject licenseInfo = new JSONObject();
        licenseInfo.put("licenseFullName", merchantName);
        JSONObject otherInfo = new JSONObject();
        otherInfo.put("sharePercent", 3000);
        otherInfo.put("authTypes", "1,2");
        reqJson.put("baseInfo", baseInfo);
        reqJson.put("licenseInfo", licenseInfo);
        reqJson.put("otherInfo", otherInfo);
        reqJson.put("ledgerMethod", 2); // 1：订单分账 2：余额分账

        Map requestMap = new HashMap();
        requestMap.put("agentId", 5919932);
        requestMap.put("version", "1.0");
        requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
        requestMap.put("data", reqJson.toString());
        String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + reqJson.toString(), "utf-8").toLowerCase().getBytes());
        requestMap.put("sign", sign);
        System.out.println("分账授权申请请求：" + requestMap);

        try {
            String result = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/api/share-merchant/elec-contract-accredit", requestMap);
            System.out.println("分账授权授权返回:" + result);
            return JSONObject.parseObject(result);
        } catch (Exception e) {
            System.out.println(shopId + "分账授权申请异常");
            e.printStackTrace();
        }
        return null;
    }

    public static void queryPayStatus(String merchantId, String outTradeNo, String leshuaOrderId) {
        try {
            Map payMap = new HashMap();
            payMap.put("service", "query_status");//接口名 M
            payMap.put("merchant_id", merchantId);
            if (StringUtils.isNotBlank(outTradeNo)) {
                payMap.put("third_order_id", outTradeNo);
            }
            if (StringUtils.isNotBlank(leshuaOrderId)) {
                payMap.put("leshua_order_id", leshuaOrderId);
            }
            payMap.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32).toUpperCase());//随机字符串  M
            payMap.put("sign", getSign(payMap, 1));//签名 M
            System.out.println("查询订单信息请求参数" + payMap);
            String result = HttpClientUtil.postParameters("https://paygate.leshuazf.com/cgi-bin/lepos_pay_gateway.cgi", payMap);
            Map<String, String> stringStringMap = WxPayUtil.xmlToMap(result);
            System.out.println(JSON.toJSONString(stringStringMap));
            System.out.println(JSON.toJSONString(stringStringMap.get("refund_amount")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void canceSumAccredit() {
        try {
            JSONObject data = new JSONObject();
            // 6967215495 线上商户号
            data.put("merchant_id", "2355111163");
            data.put("cmd", "balance_allot_cancel");
            data.put("third_id", "p21202310271423477751976054");

            Map requestMap = new HashMap();
            requestMap.put("agentId", "5919932");
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
            requestMap.put("data", data);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + data, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("乐刷分账下单请求参数:" + JSONObject.toJSONString(requestMap));
            String result = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/api/share-merchant/canceSumAccredit", requestMap);
            System.out.println("乐刷分账下单响应参数:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createSumAccredit() {
        try {
            String third_id = "p" + TradeNoUtil.getTradeNo(21);
            JSONObject data = new JSONObject();
            // 6967215495 线上商户号
            data.put("merchant_id", "3646511662");
            data.put("cmd", "balance_allot_create");
            data.put("third_id", third_id);
            data.put("amount", "");
            data.put("remark", "手动分账");

            List<Map> details = new ArrayList<>();
            SortedMap detailItem = new TreeMap();
            detailItem.put("user_id", "3646511662");
            detailItem.put("amount", "");
            detailItem.put("user_type", "2");
            details.add(detailItem);
            SortedMap detailItem1 = new TreeMap();
            detailItem1.put("user_id", "");
            detailItem1.put("amount", "");
            detailItem1.put("user_type", "2");
//            details.add(detailItem1);
            SortedMap detailItem2 = new TreeMap();
            detailItem2.put("user_id", "");
            detailItem2.put("amount", "");
            detailItem2.put("user_type", "2");
//            details.add(detailItem2);
            data.put("details", JSONObject.toJSONString(details));

            Map requestMap = new HashMap();
            requestMap.put("agentId", "5919932");
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
            requestMap.put("data", data);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + data, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("乐刷余额分账下单请求参数:" + JSONObject.toJSONString(requestMap));
            String result = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/api/share-merchant/createSumAccredit", requestMap);
            System.out.println("乐刷余额分账下单响应参数:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void querySumAccredit(String merchant_id, String third_id) {
        try {
            JSONObject data = new JSONObject();
            data.put("merchant_id", merchant_id);
            data.put("third_id", third_id);

            Map requestMap = new HashMap();
            requestMap.put("agentId", "5919932");
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
            requestMap.put("data", data);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + data, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("乐刷余额分账查询请求参数:" + JSONObject.toJSONString(requestMap));
            String result = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/api/share-merchant/querySumAccredit", requestMap);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //订单分账查询
    public static void multiQuery(String merchantId, String thirdRoyaltyId, String leshuaRoyaltyId, String leshuaOrderId) {
        try {
            JSONObject data = new JSONObject();
            data.put("merchantId", merchantId);
            if (StrUtil.isNotEmpty(thirdRoyaltyId)) data.put("thirdRoyaltyId", thirdRoyaltyId);
            if (StrUtil.isNotEmpty(leshuaRoyaltyId)) data.put("leshuaRoyaltyId", leshuaRoyaltyId);
            data.put("leshuaOrderId", leshuaOrderId);
            data.put("allRoyaltyFlag", 1);

            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", data);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + data, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

//            System.out.println("订单分账查询-----" + requestMap);
            String resultstr = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/api/share-merchant/multi-query", requestMap);
            JSONObject resultJson = JSONObject.parseObject(resultstr);
            System.out.println(resultJson);
            String royalty_records = resultJson.getJSONObject("data").getString("royalty_records");
            if ("[]".equals(royalty_records)) {
                System.out.println(leshuaOrderId + "==" + royalty_records);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static JSONObject queryBind(String shopId) {
        try {


            JSONObject data = new JSONObject();
            //商户号
            data.put("merchantId1", shopId);

            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", data);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + data, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println(requestMap);
            String resultstr = HttpUtil.post("https://saas-mch.leshuazf.com/api/share-merchant/queryBind", requestMap);
            JSONObject resultJson = JSONObject.parseObject(resultstr);
            System.out.println(resultJson);
            JSONArray data1 = resultJson.getJSONArray("data");
            System.out.println(data1.size());

            return resultJson;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void queryAccreditBanlanceIncome() {
        try {
            JSONObject data = new JSONObject();
            data.put("cmd", "query_all_flow_info");
            data.put("level", "realtime");
            data.put("account_no", "2355111163");
            data.put("begin_time", "2023-02-28 00:00:00");
            data.put("end_time", "2023-02-28 23:59:59");
            data.put("page", 1);
            data.put("num", "200");
            data.put("type", "1");

            Map requestMap = new LinkedHashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", data);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + data.toJSONString(), "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);
            System.out.println("查询余额参数-----" + JSONObject.toJSONString(requestMap));
            String resultJson = HttpClientUtil.post("https://saas-mch.leshuazf.com/api/share-merchant/queryAccreditBanlanceIncome", JSON.toJSONString(requestMap), "application/json", "utf-8", 5000, 5000);
            JSONObject dataJSon = JSONObject.parseObject(resultJson);
            System.out.println("查询余额返回-----" + JSONObject.toJSONString(dataJSon, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Integer flowQuery(String shopId) {
        try {
            JSONObject requestMap = new JSONObject(true);
            JSONObject reqJson = new JSONObject(true);
            //商户号
            reqJson.put("beginTime", "2023-09-26 00:00:00");
            reqJson.put("bookType", 1);
            reqJson.put("endTime", "2023-10-11 00:00:00");
            reqJson.put("merchantId", shopId);
            reqJson.put("num", 10);
            reqJson.put("page", 1);

            requestMap.put("agentId", "5919932");
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));

            String sign = MD5Util.MD5Encode("5D5DC123FD9DE1B4984C9FF07E19EFCA" + requestMap.get("reqSerialNo") + requestMap.get("version") + reqJson, "utf-8").toLowerCase();

//            String requestContent = "5919932" + requestMap.get("reqSerialNo") + requestMap.get("version") + JSON.toJSONString(reqJson);
//            String sign = org.springframework.util.DigestUtils.md5DigestAsHex(requestContent.getBytes("utf-8"));

            requestMap.put("sign", sign);
            requestMap.put("data", reqJson);
            System.out.println(JSONObject.toJSONString(requestMap));
            String resultJson = HttpClientUtil.post("https://saas-combine.leshuazf.com/open-api/merchant-withdraw/new-flow-query", requestMap.toJSONString(), "application/json", "utf-8", 5000, 5000);
            JSONObject dataJSon = JSONObject.parseObject(resultJson);
            System.out.println(JSONObject.toJSONString(dataJSon));

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Integer flowQuery1(String shopId) {
        try {
            Map requestMap = new LinkedHashMap();
            JSONObject reqJson = new JSONObject();
            //商户号
            reqJson.put("merchantId", shopId);
            reqJson.put("page", 1);
            reqJson.put("num", 10);
            reqJson.put("beginTime", 1655793879000L);
            reqJson.put("endTime", 1661064279000L);

            requestMap.put("agentId", 5919932);
            requestMap.put("version", "2.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", reqJson);
            String sign = MD5Util.MD5Encode("5D5DC123FD9DE1B4984C9FF07E19EFCA" + requestMap.get("reqSerialNo") + requestMap.get("version") + reqJson.toString(), "utf-8").toLowerCase();
            requestMap.put("sign", sign);
            System.out.println("查询余额参数-----" + JSONObject.toJSONString(requestMap));
            String resultJson = HttpClientUtil.post("https://saas-combine.leshuazf.com/open-api/merchant-withdraw/flow-query", JSON.toJSONString(requestMap), "application/json", "utf-8", 5000, 5000);
            JSONObject dataJSon = JSONObject.parseObject(resultJson);
            System.out.println(JSONObject.toJSONString(dataJSon));
            Integer code = dataJSon.getInteger("code");
            if (code != null && code == 0) {
                JSONObject data = dataJSon.getJSONObject("data");
                int contributoryAmount = data.getInteger("contributoryAmount");
                return contributoryAmount;
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Integer getLsWithDrawBalance(String shopId) {
        try {
            Map requestMap = new LinkedHashMap();
            JSONObject reqJson = new JSONObject();
            //商户号
            reqJson.put("merchantId", shopId);

            requestMap.put("agentId", 5919932);
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", reqJson);
            String sign = MD5Util.MD5Encode("5D5DC123FD9DE1B4984C9FF07E19EFCA" + requestMap.get("reqSerialNo") + requestMap.get("version") + reqJson.toString(), "utf-8").toLowerCase();
            requestMap.put("sign", sign);
            System.out.println("查询余额参数-----" + JSONObject.toJSONString(requestMap));
            String resultJson = HttpClientUtil.post("https://saas-combine.leshuazf.com/open-api/merchant-withdraw/balance-query", JSON.toJSONString(requestMap), "application/json", "utf-8", 5000, 5000);
            JSONObject dataJSon = JSONObject.parseObject(resultJson);
            System.out.println(JSONObject.toJSONString(dataJSon));
            Integer code = dataJSon.getInteger("code");
            if (code != null && code == 0) {
                JSONObject data = dataJSon.getJSONObject("data");
                int contributoryAmount = data.getInteger("contributoryAmount");
                return contributoryAmount;
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map common(String data) {
        try {
            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", data);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + data, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);
            return requestMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void regist() {
        try {
            String data = "{\"baseInfo\":{\"mccCode\":\"7523\",\"merchantShortName\":\"进件停车场\",\"servicePhone\":\"18827688654\",\"merchantType\":1,\"merchantName\":\"乐刷进件车场\"},\"accountInfo\":{\"idCardType\":1,\"legalFlag\":\"1\",\"bankCardNo\":\"6222620610021606702\",\"idCardNo\":\"421083199403140915\",\"unionpay\":\"301521009027\",\"holder\":\"徐干\",\"bankCardFrontPic\":\"picturepro/M00/3C/78/rBQLM2P9neiAaopMAAG56MnCP_c597.png\",\"type\":1},\"addressInfo\":{\"areaCode\":110101,\"address\":\"跑马路123号\",\"provinceCode\":110000,\"cityCode\":110100},\"otherInfo\":{\"reportConfigId\":\"1663\"},\"shopInfo\":{\"insidePic\":\"picturepro/M00/3C/78/rBQLM2P9neiAUAhOAAUwsYul9J8557.png\",\"cashierDeskPic\":\"picturepro/M00/3C/78/rBQLM2P9neiAUAhOAAUwsYul9J8557.png\",\"doorPic\":\"picturepro/M00/3C/78/rBQLM2P9neiAUAhOAAUwsYul9J8557.png\",\"headMerchantId\":\"5159017478\",\"shopType\":\"0\"},\"contactInfo\":{\"name\":\"徐干\",\"mobile\":18827688654},\"legalPerson\":{\"legalName\":\"徐干\",\"idCardEnd\":\"2042-06-10\",\"idcardBackPic\":\"picturepro/M00/3C/78/rBQLM2P9neiADgxXAAG56MnCP_c796.png\",\"idCardNo\":\"421083199403140915\",\"idcardFrontPic\":\"picturepro/M00/3C/78/rBQLM2P9neiAQqQBAAG56MnCP_c469.png\",\"idCardStart\":\"2022-06-10\"},\"wechatInfo\":{}}";

            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", data);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + data, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("进件参数-----" + requestMap);
            String resultstr = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/merchant/register", requestMap);
            System.out.println("-----进件结果-----" + resultstr);
            JSONObject resultJson = JSONObject.parseObject(resultstr);
            System.out.println("resultJson = " + resultJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void unifiedRefund(String leshuaOrderId, String merchantId, Integer refundAmount) {
        try {
            Map payMap = new HashMap();
            payMap.put("service", "unified_refund");//接口名 M
            payMap.put("merchant_id", merchantId);
//            payMap.put("third_order_id", thirdOrderId);
            payMap.put("leshua_order_id", leshuaOrderId);
            String refund = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32).toUpperCase();
            payMap.put("merchant_refund_id", refund);
            payMap.put("refund_type", "1");
            payMap.put("refund_amount", refundAmount);
            payMap.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32).toUpperCase());//随机字符串  M
            payMap.put("sign", getSign(payMap, 1));//签名 M
            System.out.println("退款请求参数" + JSON.toJSONString(payMap));
            String result = HttpClientUtil.postParameters("https://paygate.leshuazf.com/cgi-bin/lepos_pay_gateway.cgi", payMap);
            Map<String, String> stringStringMap = WxPayUtil.xmlToMap(result);
            System.out.println(JSON.toJSONString(stringStringMap));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void profitOrderRefund(String thirdOrderId, String lsOrderId, String merchantId, Integer refundAmount) {
        try {
            //调用乐刷
            Map refundMap = new HashMap();
            refundMap.put("merchantId", merchantId);//接口名 M
            refundMap.put("thirdOrderId", thirdOrderId);
            refundMap.put("leshuaOrderId", lsOrderId);
            refundMap.put("refundAmount", refundAmount);
            String refundTradeNo = TradeNoUtil.getTradeNo(null);
            refundMap.put("thirdRefundId", "b" + refundTradeNo);
            String dataStr = JSON.toJSONString(refundMap, SerializerFeature.UseSingleQuotes);

            Map requestMap = new HashMap();
            requestMap.put("agentId", "5919932");
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
            requestMap.put("data", dataStr);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + dataStr, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("乐刷分账订单退款下单请求参数:" + JSONObject.toJSONString(requestMap));
            String refundResult = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/api/share-merchant/refund", requestMap);
            System.out.println("乐刷分账订单退款下单响应参数:" + refundResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unifiedRefundQuery(String merchant_id, String tradeNo, String merchant_refund_id) {
        try {
            Map payMap = new HashMap();
            payMap.put("service", "unified_query_refund");//接口名 M
            payMap.put("merchant_id", merchant_id);
            payMap.put("third_order_id", tradeNo);
            payMap.put("merchant_refund_id", merchant_refund_id);
            payMap.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32).toUpperCase());//随机字符串  M
            payMap.put("sign", getSign(payMap, 1));//签名 M
            System.out.println("退款请求参数" + JSON.toJSONString(payMap));
            String result = HttpClientUtil.postParameters("https://paygate.leshuazf.com/cgi-bin/lepos_pay_gateway.cgi", payMap);
            Map<String, String> stringStringMap = WxPayUtil.xmlToMap(result);
            System.out.println(JSON.toJSONString(stringStringMap));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            String str = paramStr.substring(1) + "&key=5D5DC123FD9DE1B4984C9FF07E19EFCA";
            String result = MD5Util.MD5Encode(str, "utf-8");
            return type == 1 ? result.toUpperCase() : result.toLowerCase();
        } else if (type == 3) {

//            return MD5Util.MD5Encode("{\"version\":\"0.0.1\",\"agentId\":\"5963222\",\"data\":{\"SubsidyDate\":\"2023-10-27\",\"TotalServiceFee\":0,\"TotalMarketingFee\":298,\"TotalSubsidyFee\":298,\"TotalMerchantCount\":1,\"SubsidyDetailList\":[{\"MerchantId\":\"2417612124\",\"ServiceFee\":0,\"MarketingFee\":298}]}}5D5DC123FD9DE1B4984C9FF07E19EFCA", "utf-8").toLowerCase();
            return MD5Util.MD5Encode(JSON.toJSONString(source) + "5D5DC123FD9DE1B4984C9FF07E19EFCA", "utf-8").toLowerCase();
        }
        return null;
    }

    public static void profitRefund(String thirdOrderId, String leshuaOrderId, Integer refundAmount) {
        try {
            Map refundMap = new HashMap();
            refundMap.put("merchantId", "");//接口名 M
            refundMap.put("thirdOrderId", thirdOrderId);
            refundMap.put("leshuaOrderId", leshuaOrderId);
            refundMap.put("refundAmount", refundAmount);
            String refundTradeNo = System.currentTimeMillis() / 1000 + "";
            refundMap.put("thirdRefundId", "b" + refundTradeNo);
            refundMap.put("notifyUrl", "");
            String dataStr = JSON.toJSONString(refundMap, SerializerFeature.UseSingleQuotes);

            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
            requestMap.put("data", dataStr);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + dataStr, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("乐刷分账订单退款下单请求参数:" + JSONObject.toJSONString(requestMap));
            String refundResult = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/api/share-merchant/refund", requestMap);
            System.out.println("乐刷分账订单退款下单响应参数:" + refundResult);
            JSONObject jsonObject = JSONObject.parseObject(refundResult);
            String respCode = jsonObject.getString("respCode");
            if ("000000".equals(respCode)) {
                Integer refundStatus = jsonObject.getJSONObject("data").getInteger("status");
                if (refundStatus != null && refundStatus == 11) {

                } else if (refundStatus != null && refundStatus == 10) {

                } else {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void withdrawApply(String merchantId, Integer applyAmount) {
        try {
            Map requestMap = new TreeMap();
            Map dataMap = new LinkedHashMap();
            dataMap.put("applyAmount", applyAmount);
            dataMap.put("merchantId", merchantId);
            dataMap.put("reqId", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));

            requestMap.put("agentId", "5919932");
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", dataMap);
            String sign = MD5Util.MD5Encode("5D5DC123FD9DE1B4984C9FF07E19EFCA" + requestMap.get("reqSerialNo") + requestMap.get("version") + JSON.toJSONString(dataMap), "utf-8").toLowerCase();
            requestMap.put("sign", sign);
            System.out.println("商户提现请求参数" + JSON.toJSONString(requestMap));
            String resultJson = HttpClientUtil.post("https://saas-combine.leshuazf.com/open-api/merchant-withdraw/apply", JSONObject.toJSONString(requestMap), "application/json", "utf-8", 5000, 5000);
            System.out.println(resultJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void settlementOrder(String billId) {
        try {
            Map requestMap = new TreeMap();
            requestMap.put("billId", billId);
            requestMap.put("agentId", "5919932");
            requestMap.put("type", "t1");
            requestMap.put("sign", getSign(requestMap, 2));

            System.out.println("商户提现查询参数" + JSON.toJSONString(requestMap));
            String resultJson = post("https://saas-combine.leshuazf.com/open-api/agent/settlement-order", requestMap);
            System.out.println(resultJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String post(String reqUrl, Map<String, String> params) {
        try {
            HttpClient httpClient = new HttpClient();
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(6000);
            PostMethod postMethod = new PostMethod(reqUrl) ;
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            NameValuePair[] pairs = new NameValuePair[params.size()];
            Object[] objects = params.keySet().toArray();
            for (int i = 0; i < objects.length; i++) {
                String k = (String) objects[i];
                String v = params.get(k);
                pairs[i] = new NameValuePair(k, v);
            }
            postMethod.setRequestBody(pairs);
            httpClient.executeMethod(postMethod);
            InputStream inputStream = postMethod.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void unbind(String merchantId1, String merchantId2) {
        JSONObject payMap = new JSONObject();

        payMap.put("merchantId1", merchantId1);
        payMap.put("merchantId2", merchantId2);

        Map requestMap = new HashMap();
        requestMap.put("agentId", 5919932);
        requestMap.put("version", "1.0");
        requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
        requestMap.put("data", payMap.toString());
        String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + payMap.toString(), "utf-8").toLowerCase().getBytes());
        requestMap.put("sign", sign);
        try {
            System.out.println("乐刷分账关系解绑请求参数:" + JSONObject.toJSONString(requestMap));
            String lsResult = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/api/share-merchant/unbind", requestMap);
            System.out.println("乐刷分账关系解绑响应参数:" + lsResult);
            JSONObject jsonObject = JSONObject.parseObject(lsResult);
            String respCode = jsonObject.getString("respCode");
            if ("000000".equals(respCode)) {
                System.out.println("=======================");
            } else {
                System.out.println("------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bind(String merchantId1, String merchantId2) {
        JSONObject payMap = new JSONObject();

        payMap.put("merchantId1", merchantId1);
        payMap.put("merchantId2", merchantId2);

        Map requestMap = new HashMap();
        requestMap.put("agentId", 5919932);
        requestMap.put("version", "1.0");
        requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
        requestMap.put("data", payMap.toString());
        String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + payMap.toString(), "utf-8").toLowerCase().getBytes());
        requestMap.put("sign", sign);
        try {
            System.out.println("乐刷分账关系绑定请求参数:" + JSONObject.toJSONString(requestMap));
            String lsResult = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/api/share-merchant/bind", requestMap);
            System.out.println("乐刷分账关系绑定响应参数:" + lsResult);
            JSONObject jsonObject = JSONObject.parseObject(lsResult);
            String respCode = jsonObject.getString("respCode");
            if ("000000".equals(respCode)) {
                System.out.println("=======================");
            } else {
                System.out.println("------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void wxpayconfig(Integer type, String appid, String shopId, boolean isSubShop, String wxSubMchId) {
        JSONObject payMap = new JSONObject();
        payMap.put("merchantId", shopId);//商户号 M
        payMap.put("configType", type);//订单号 M
        payMap.put("sub_appid", appid);

        if (isSubShop) {
            //泊链软件
//            payMap.put("reportConfigId","1663");
            payMap.put("wxSubMchId", wxSubMchId);
        }
        Map requestMap = new HashMap();
        requestMap.put("agentId", 5919932);
        requestMap.put("version", "1.0");
        requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
        requestMap.put("data", payMap.toString());
        String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + payMap.toString(), "utf-8").toLowerCase().getBytes());
        requestMap.put("sign", sign);
        try {
            String result = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/wechat/wxpayconfig", requestMap);
            System.out.println("请求报文:" + JSONObject.toJSONString(requestMap));
            System.out.println("====配置公众号和小程序appid结果=====");
            System.out.println("返回报文:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void wxpayconfigQry(String shopId, String wxSubMchId) {
        JSONObject payMap = new JSONObject();
        payMap.put("merchantId", shopId);//商户号 M
        payMap.put("wxSubMchId", wxSubMchId);

        Map requestMap = new HashMap();
        requestMap.put("agentId", "5919932");
        requestMap.put("version", "1.0");
        requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
        requestMap.put("data", payMap.toString());
        String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + payMap.toString(), "utf-8").toLowerCase().getBytes());
        requestMap.put("sign", sign);
        try {
            String result = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/wechat/wxpayconfig_qry", requestMap);
            System.out.println("请求报文:" + JSONObject.toJSONString(requestMap));
            System.out.println("返回报文:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
