package com.es.lsapp.zhimaScore;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.es.lsapp.HttpClientUtil;
import com.es.lsapp.MD5Util;
import com.es.lsapp.TradeNoUtil;
import com.es.pojo.ZhimaScoreCloseOrderReq;
import com.es.pojo.ZhimaScoreCreateOrderReq;
import com.es.pojo.ZhimaScorePeriodOrdeReq;
import org.springframework.util.Base64Utils;

import java.text.SimpleDateFormat;
import java.util.*;

public class ZhimaScoreUtils {

    public static void main(String[] args) {
//        createOrder(new ZhimaScoreCreateOrderReq());
        getAgreementIdList(new ZhimaScoreCloseOrderReq("2088012080804960","29202403191530554982341020","2024040101502300000009600082889070"));
    }

    public static void createOrder(ZhimaScoreCreateOrderReq req) {
        try {
            JSONObject data = new JSONObject();
            data.put("leShuaMerchantId", "1830015938");
            data.put("agentId", "5919932");
            data.put("buyerId", "2088012080804960");

            JSONObject creditInfo = new JSONObject();
            creditInfo.put("outAgreementNo", "2088012080804960"+System.currentTimeMillis()/1000);
            creditInfo.put("zmServiceId", "2024031300001003000007211200");
            data.put("creditInfo", creditInfo);

            JSONObject extInfo = new JSONObject();
            extInfo.put("deductSignScene", "INDUSTRY|SILUNCHONGDIAN_LESHUA");
            data.put("extInfo", extInfo);

            data.put("merchantBizType", "INDIRECT_CHARGE_WITHHOLD");

            JSONObject orderDetail = new JSONObject();

            List itemInfos = new ArrayList();
            Map itemInfo = new HashMap();
            itemInfo.put("goodsId", "123");
            itemInfo.put("goodsName", "充电");
            itemInfo.put("itemCnt", "1");
            Map itemInstallmentInfo = new HashMap();
            itemInstallmentInfo.put("periodMaxPrice", "10");
            itemInstallmentInfo.put("periodNum", 10);
            itemInfo.put("itemInstallmentInfo", itemInstallmentInfo);
            itemInfo.put("salePrice", "10");
            itemInfos.add(itemInfo);

            JSONObject priceInfo = new JSONObject();
            priceInfo.put("orderPrice", "100");

            orderDetail.put("itemInfos", itemInfos);
            orderDetail.put("priceInfo", priceInfo);

            data.put("outOrderId", TradeNoUtil.getTradeNo(29));
            data.put("path", "");
            data.put("sourceId", "");

            JSONObject subMerchant = new JSONObject();
            subMerchant.put("merchantId", req.getMerchantId());
            subMerchant.put("merchantType", "alipay");
            data.put("subMerchant", subMerchant);

            data.put("title", req.getTitle());

            String dataStr = JSON.toJSONString(data);

            Map requestMap = new HashMap();
            requestMap.put("agentId", "5919932");
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", data);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + dataStr, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("【芝麻分】芝麻分下单请求:" + JSON.toJSONString(requestMap));
            String preCheckResult = HttpClientUtil.postParameters("http://p-saas-mch.lepass.cn/apiv2/zhimaScoreOpen/createOrder", requestMap);
            System.out.println(preCheckResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 芝麻分创建分期单接口
     * @param req
     */
    public static void createPeriodOrder(ZhimaScorePeriodOrdeReq req) {
        try {
            Map data = new HashMap();
            data.put("leShuaMerchantId", req.getLeShuaMerchantId());
            data.put("installmentNo", req.getInstallmentNo());//本次分期号
            data.put("installmentNoType", "PERIOD");//分期数类型
            data.put("installmentPrice", req.getInstallmentPrice());//分期金额
            data.put("isFinishPerformance", req.getIsFinishPerformance());//分期是否完结，0-未完结，1-已完结
            data.put("orderId", req.getOrderId());//交易组件订单号
            data.put("outInstallmentOrderId", req.getOutInstallmentOrderId());//外部分期单号
            data.put("outOrderId", req.getOutOrderId());//外部商户订单号
            data.put("type", "WITHHOLD");//分期类型
            data.put("userId", req.getUserId());//买家id

            String dataStr = JSON.toJSONString(data, SerializerFeature.UseSingleQuotes);

            Map requestMap = new HashMap();
            requestMap.put("agentId", "5919932");
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", dataStr);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + dataStr, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("【芝麻分】芝麻分创建分期单接口请求:" + JSONObject.toJSONString(requestMap));
            String preCheckResult = HttpClientUtil.postParameters("http://p-saas-mch.lepass.cn/apiv2/zhimaScoreOpen/createPeriodOrder", requestMap);
            System.out.println(preCheckResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeOrder(ZhimaScoreCloseOrderReq req) {
        try {
            Map data = new HashMap();
            data.put("userId", req.getUserId());//用户userId
            data.put("outOrderId", req.getOrderId());//商户订单号
            data.put("orderId", req.getOrderId());//交易组件订单号

            String dataStr = JSON.toJSONString(data, SerializerFeature.UseSingleQuotes);

            Map requestMap = new HashMap();
            requestMap.put("agentId", "5919932");
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", dataStr);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + dataStr, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("【芝麻分】取消订单接口请求:" + JSONObject.toJSONString(requestMap));
            String preCheckResult = HttpClientUtil.postParameters("http://p-saas-mch.lepass.cn/apiv2/zhimaScoreOpen/closeOrder", requestMap);
            System.out.println(preCheckResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getAgreementIdList(ZhimaScoreCloseOrderReq req) {
        try {
            Map data = new HashMap();
            data.put("userId", req.getUserId());//用户userId
            data.put("outOrderId", req.getOrderId());//商户订单号
            data.put("orderId", req.getOrderId());//交易组件订单号

            String dataStr = JSON.toJSONString(data, SerializerFeature.UseSingleQuotes);

            Map requestMap = new HashMap();
            requestMap.put("agentId", "5919932");
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", dataStr);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + dataStr, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("【芝麻分】取消订单接口请求:" + JSONObject.toJSONString(requestMap));
            String preCheckResult = HttpClientUtil.postParameters("http://p-saas-mch.lepass.cn/apiv2/zhimaScoreOpen/closeOrder", requestMap);
            System.out.println(preCheckResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
