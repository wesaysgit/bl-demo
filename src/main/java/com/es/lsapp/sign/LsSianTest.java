package com.es.lsapp.sign;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.es.lsapp.HttpClientUtil;
import com.es.lsapp.MD5Util;
import org.springframework.util.Base64Utils;

import java.text.SimpleDateFormat;
import java.util.*;

public class LsSianTest {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
//        wechatSettleIdUpdate("7239111077", "574195977", 1);
//        getauthorizestateByWxMerId("796451297");
//        getauthorizestateByWxMerId("615196623");
//        wechatSubjectPreCheck("2040214765", null);
//        wechatSubjectApply("7239111077", "613235793");
//        wechatSubjectQuery("9211610528");
//        submchQuery("7316514044", 1);
//
//
//2233618758
//
//        List<String> list = Arrays.asList("8159414551", "3149716326", "2153916312", "4162116247", "6139516018", "1150415539", "9150214495", "0167516017", "2167311753", "4132511756", "5046717957", "6039117908", "7047616723", "3055115523", "8063914982", "8054914988", "5052212447", "8345912545", "9058411487", "7245915920", "3744913813", "1344915963", "5152216400", "7344913563", "5443918977", "4243913910", "6049211119", "1342911366", "1041916998", "2049910358", "7447411803", "4346817598", "1357910509", "0445814451", "8334616834", "8056914427", "1056914419", "9056914398", "3334616753", "2034617983", "3655914079", "4245812193", "4733614835", "4744814510", "8343810752", "2158814513", "1932510535", "5031518183", "7851813244", "2340712185", "5850819524", "7564812646", "5554818117", "7930318263", "5847618097", "5935411248", "3735412405", "7746615953", "1834416523", "4453712093", "3031311801", "0650710643", "4538317166", "3356617911", "7930318263", "3031311801", "3031311801", "6767719802", "1433317723", "3031311801", "3031311801", "8754612372", "6067719059", "3031311801", "8933216855", "3353619085", "5353619044", "9066710714", "3031311801", "8933216855", "8933216855", "8933216855", "8933216855", "8933216855", "3365713867", "3031311801", "6652618707", "7930318263", "7930318263", "8933216855", "8933216855", "3930314766", "3031311801", "8265718872", "8265718872", "8265718872", "8530310301", "6152615750", "7749515023", "8430313548");
        List<String> list = Arrays.asList("4453712093");
        ArrayList<String> objects = new ArrayList<>();
        for (String s : list) {
            try {
                submchQuery(s, 4, objects);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(JSONObject.toJSONString(objects));

        }



//        zfbVerifyApply("2444118828", "");
//        queryApplyStatus("2444118828", null);
//        queryApplyStatus("4048014128", null);
//        queryApplyStatus("8961715529", null);

//        zfbVerifyQueryMchStatus("2444118828", "2088730542700945");

//        subbranchMultiReport("7239111077", "1", "1", "1", "134709823");

//        zfbVerifyRevoke("7239111077", "2000002452731696");
//        wechatSubjectCancel("7239111077");


//        wxactivityApply("4062717325");
        //9462719580 134709823 2088641520478058
//        subbranchMultiReport("4138217711", "1", "1", "134709823");
//        subbranchMultiReport("", "2", "2", "2088641520478058");
    }

    //修改结算ID为线上：微信通道费率调整接口
    public static void subbranchMultiReport(String FMerchantId, String FChannelType, String FPayType, String FChannelNo) {
        try {
            Map reqMap = new HashMap();
            reqMap.put("FMerchantId", FMerchantId);
            reqMap.put("FChannelType", FChannelType);
            reqMap.put("FPayType", FPayType);
            reqMap.put("FReportSubject", "1");
            reqMap.put("FChannelNo", FChannelNo);

            String reqJson = JSONObject.toJSONString(reqMap);

            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", reqJson);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + reqJson, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("商户多次上报:" + JSON.toJSONString(requestMap));
            String resultJson = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/shop/subbranchMultiReport", requestMap);
            System.out.println(resultJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void wechatSettleIdUpdate(String merchantId, String subMchId, int feeType) {
        try {
            Map reqMap = new HashMap();
            reqMap.put("merchantId", merchantId);
            reqMap.put("subMchId", subMchId);
            reqMap.put("feeType", feeType);

            String reqJson = JSONObject.toJSONString(reqMap);

            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", reqJson);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + reqJson, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("微信通道费率调整:" + JSON.toJSONString(requestMap));
            String resultJson = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/wechat/settleId/update", requestMap);
            System.out.println(resultJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //商户授权状态查询
    public static void getauthorizestateByWxMerId(String wxSubMchId) {
        try {
            Map reqMap = new HashMap();
            reqMap.put("wxSubMchId", wxSubMchId);

            String reqJson = JSONObject.toJSONString(reqMap);

            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "2.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", reqJson);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + reqJson, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("商户授权状态查询:" + JSON.toJSONString(requestMap));
            String resultJson = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/submch/getauthorizestateByWxMerId", requestMap);
            System.out.println(resultJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //实名认证前置检查
    public static void wechatSubjectPreCheck(String merchantId, String reportConfigId) {
        try {
            Map preCheck = new HashMap();
            preCheck.put("merchantId", "3956512214");
//            preCheck.put("reportConfigId", reportConfigId);

            String dataStr = JSON.toJSONString(preCheck, SerializerFeature.UseSingleQuotes);

            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "2.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
            requestMap.put("data", dataStr);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + dataStr, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("实名认证前置检查请求参数:" + JSONObject.toJSONString(requestMap));
            String preCheckResult = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/wechat/subject/preCheck", requestMap);
            System.out.println(preCheckResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //实名认证申请
    public static void wechatSubjectApply(String merchantId, String confirmMchidList) {
        try {
            Map preCheck = new HashMap();
            preCheck.put("merchantId", merchantId);
//            preCheck.put("microBizType", microBizType);
            preCheck.put("confirmMchidList", confirmMchidList);
//            preCheck.put("reportConfigId", reportConfigId);

            String dataStr = JSON.toJSONString(preCheck, SerializerFeature.UseSingleQuotes);

            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "2.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
            requestMap.put("data", dataStr);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + dataStr, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("实名认证申请请求参数:" + JSONObject.toJSONString(requestMap));
            String preCheckResult = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/wechat/subject/apply", requestMap);
            System.out.println(preCheckResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //实名认证查询
    public static void wechatSubjectQuery(String merchantId) {
        try {
            Map preCheck = new HashMap();
            preCheck.put("merchantId", merchantId);

            String dataStr = JSON.toJSONString(preCheck, SerializerFeature.UseSingleQuotes);

            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "2.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
            requestMap.put("data", dataStr);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + dataStr, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("实名认证查询请求参数:" + JSONObject.toJSONString(requestMap));
            String preCheckResult = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/wechat/subject/query", requestMap);
            System.out.println(preCheckResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //【微信】实名认证撤销
    public static void wechatSubjectCancel(String merchantId) {
        try {
            Map preCheck = new HashMap();
            preCheck.put("merchantId", merchantId);

            String dataStr = JSON.toJSONString(preCheck, SerializerFeature.UseSingleQuotes);

            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "2.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
            requestMap.put("data", dataStr);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + dataStr, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("实名认证查询请求参数:" + JSONObject.toJSONString(requestMap));
            String preCheckResult = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/wechat/subject/cancel", requestMap);
            System.out.println(preCheckResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //【微信】微信商户号查询
    public static void submchQuery(String merchantId, Integer subMchType, ArrayList<String> objects) {
        try {
            Map submchQuery = new HashMap();
            submchQuery.put("merchantId", merchantId);
            submchQuery.put("subMchType", subMchType);


            String dataStr = JSON.toJSONString(submchQuery, SerializerFeature.UseSingleQuotes);

            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "2.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
            requestMap.put("data", dataStr);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + dataStr, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("实名认证查询请求参数:" + JSONObject.toJSONString(requestMap));
            String subMchQueryResult = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/submch/query", requestMap);
            System.out.println(subMchQueryResult);
            JSONObject resData = JSON.parseObject(subMchQueryResult);
            JSONObject data = resData.getJSONObject("data");
            JSONArray cups = data.getJSONArray("cups");
            if (cups != null && !cups.isEmpty()) {
                JSONObject sub = cups.getJSONObject(0);
                if (StrUtil.isNotEmpty(sub.getString("subMchId"))) {
                    System.out.println("=-=====" + sub.getString("subMchId"));
                    objects.add(merchantId);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LsSignResVo defaultFailRes() {
        return LsSignResVo.builder().state(0).msg("操作失败").build();
    }

    //【微信】优惠费率活动报名
    public static void wxactivityApply(String merchantId) {
        try {
            Map wxactivity = new HashMap();
            wxactivity.put("merchantId", merchantId);

            //活动报名主要信息
            Map<String, Object> applyDetailMap = new HashMap<>();
            //1005：停车行业优惠活动
            applyDetailMap.put("activityId", "1005");

            List<Map<String, Object>> applyInformationList = new ArrayList<>();
            Map<String, Object> applyInformation = new HashMap<>();
            //活动材料编号，PARKING_QUALIFICATION_PROOF：停车场资质证明，最多5张
            applyInformation.put("applyMaterialId", "PARKING_QUALIFICATION_PROOF");
            //材料图片url数组，以数组方式提交。
//            applyInformation.put("picUrl", picUrlList);
            applyInformationList.add(applyInformation);

            applyDetailMap.put("applyInformationList", applyInformationList);

            String dataStr = JSON.toJSONString(wxactivity, SerializerFeature.UseSingleQuotes);

            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "2.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
            requestMap.put("data", dataStr);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + dataStr, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("微信活动报名:" + JSONObject.toJSONString(requestMap));
            String preCheckResult = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/activity/wxactivity/apply", requestMap);
            System.out.println(preCheckResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //【微信】优惠费率活动报名审核结果查询
    public static void wxactivityQuery(String applicationId) {
        try {
            Map wxactivityQuery = new HashMap();
            wxactivityQuery.put("applicationId", applicationId);

            String dataStr = JSON.toJSONString(wxactivityQuery, SerializerFeature.UseSingleQuotes);

            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "2.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
            requestMap.put("data", dataStr);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + dataStr, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("实名认证查询请求参数:" + JSONObject.toJSONString(requestMap));
            String preCheckResult = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/activity/wxactivity/query", requestMap);
            System.out.println(preCheckResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //【支付宝】商户授权状态查询
    public static void zfbVerifyQueryMchStatus(String merchantId, String subMchId) {
        try {
            Map zfbVerifyQueryMchStatus = new HashMap();
            zfbVerifyQueryMchStatus.put("merchantId", merchantId);
            zfbVerifyQueryMchStatus.put("subMchId", subMchId);

            String dataStr = JSON.toJSONString(zfbVerifyQueryMchStatus, SerializerFeature.UseSingleQuotes);

            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "2.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
            requestMap.put("data", dataStr);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + dataStr, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("【支付宝】商户授权状态查询:" + JSONObject.toJSONString(requestMap));
            String preCheckResult = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/zfbVerify/queryMchStatus", requestMap);
            System.out.println(preCheckResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //【支付宝】实名认证申请
    public static void zfbVerifyApply(String merchantId, String confirmMchidList) {
        try {
            Map zfbVerifyApply = new HashMap();
            zfbVerifyApply.put("merchantId", merchantId);
            if (StrUtil.isNotEmpty(confirmMchidList)) {
                zfbVerifyApply.put("confirmMchidList", confirmMchidList);
            }

            String dataStr = JSON.toJSONString(zfbVerifyApply, SerializerFeature.UseSingleQuotes);

            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "2.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
            requestMap.put("data", dataStr);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + dataStr, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("【支付宝】实名认证申请:" + JSONObject.toJSONString(requestMap));
            String preCheckResult = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/zfbVerify/apply", requestMap);
            System.out.println(preCheckResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //【支付宝】实名认证查询
    public static void queryApplyStatus(String merchantId, String applymentId) {
        try {
            Map queryApplyStatus = new HashMap();
            queryApplyStatus.put("merchantId", merchantId);
            String dataStr = JSON.toJSONString(queryApplyStatus, SerializerFeature.UseSingleQuotes);

            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "2.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
            requestMap.put("data", dataStr);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + dataStr, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("【支付宝】实名认证查询:" + JSONObject.toJSONString(requestMap));
            String preCheckResult = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/zfbVerify/queryApplyStatus", requestMap);
            System.out.println(preCheckResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //【支付宝】实名认证撤销
    public static void zfbVerifyRevoke(String merchantId, String applymentId) {
        try {
            Map queryApplyStatus = new HashMap();
            queryApplyStatus.put("merchantId", merchantId);
            queryApplyStatus.put("applymentId", applymentId);
            String dataStr = JSON.toJSONString(queryApplyStatus, SerializerFeature.UseSingleQuotes);

            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "2.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
            requestMap.put("data", dataStr);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + dataStr, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("【支付宝】实名认证查询:" + JSONObject.toJSONString(requestMap));
            String preCheckResult = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/zfbVerify/revoke", requestMap);
            System.out.println(preCheckResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //商户多次上报
    public static void subbranchMultiReport(String FMerchantId, String FChannelType, String FPayType, String FReportSubject, String FChannelNo) {
        try {
            Map queryApplyStatus = new HashMap();
            queryApplyStatus.put("FMerchantId", FMerchantId);
            queryApplyStatus.put("FChannelType", FChannelType);
            queryApplyStatus.put("FPayType", FPayType);
            queryApplyStatus.put("FReportSubject", FReportSubject);
            queryApplyStatus.put("FChannelNo", FChannelNo);
            String dataStr = JSON.toJSONString(queryApplyStatus, SerializerFeature.UseSingleQuotes);

            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "2.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
            requestMap.put("data", dataStr);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + dataStr, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("商户多次上报:" + JSONObject.toJSONString(requestMap));
            String preCheckResult = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/shop/subbranchMultiReport", requestMap);
            System.out.println(preCheckResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
