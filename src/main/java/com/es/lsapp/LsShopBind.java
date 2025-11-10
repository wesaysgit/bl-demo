package com.es.lsapp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LsShopBind {
//    public static final String FMerchantId = "2444118828";
    public static final String FMerchantId = "2247214886";


    public static void main(String[] args) throws Exception{
//        for (int i = 0; i < split.length; i++) {
//            String mchId = split[i];
//            subbranchTradeBind(mchId, LsShopWxAliConfigEnum.WXCHANNELCONFIG);
////            attachQuery(mchId, "1");
//            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
//        }
//        attachQuery("8539112234", "2");

//        subbranchTradeBind("1542716294", LsShopWxAliConfigEnum.HEADWXTEMP3);
//        subbranchTradeUnBind("", LsShopWxAliConfigEnum.HEADWXUNBINDTEMP);
//        System.out.println("============================================================");

//        attachQuery("8539112234", LsShopWxAliConfigEnum.WXCHANNELCONFIG.getFChannelType());

//        feeQry("1830015938");
//        openFee("3069612174");
//        System.out.println("修改后查询=========================");
//        feeQry("2444118828");
//------------------------------------------------

        /** 总店挂靠能力申请接口*/
//        configApply("8539112234", "2", "588554425",
//                "picture2pro/M00/03/4C/rBQLNWUiSZeAXJCPAAQLvhPynGU840.pdf", 5);

        // 4644410453 picture2pro/M00/03/4A/rBQLNWUiSXCAcVvOAAO996bPP8Q013.pdf
        // 4644410097 picture2pro/M00/03/4B/rBQLNWUiSYSAOey-AAO8MIGDTiQ342.pdf
        // 8539112234 picture2pro/M00/03/4C/rBQLNWUiSZeAXJCPAAQLvhPynGU840.pdf
        // 9539112261 picture2pro/M00/03/4D/rBQLNWUiSa2ACk7SAAQA_NyOmuY894.pdf
        //保险通道2
        // 8539112234 picture2pro/M00/13/2E/rBQLNWVRwlqAIH7BAAPatck7SR8539.pdf
//        pdfUpload();
//        configUpdate("6347510437", "1", "591510349",
//                "picture2pro/M00/6A/30/rBQLNWTnH6GAYiDlAAPTRV_KlZg036.pdf", "591993263475104371692864341516");

//        configQuery("7269215926", );

        /**被挂靠方挂靠关系查询接口*/
//        attachedQuery("6347510437","1", "591510349");
//        submchQuery("8539112234");

    }
    /**
     * 总店挂靠能力查询接口
     */
    public static void submchQuery(String merchantId) {
        try {
            Map reqMap = new HashMap();
            reqMap.put("merchantId", merchantId);
            reqMap.put("subMchType", 1);

            Map requestMap = new HashMap();
            requestMap.put("agentId", "5919932");
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", JSONObject.toJSONString(reqMap));
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + JSONObject.toJSONString(reqMap), "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println(JSONObject.toJSONString(requestMap));
            String resultJson = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/submch/query", requestMap);
            System.out.println(resultJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 总店挂靠能力查询接口
     */
    public static void configQuery(String FMerchantId, String FApplyId) {
        try {
            Map reqMap = new HashMap();
            reqMap.put("FMerchantId", FMerchantId);
            reqMap.put("FApplyId", FApplyId);

            Map requestMap = new HashMap();
            requestMap.put("agentId", "5919932");
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", JSONObject.toJSONString(reqMap));
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + JSONObject.toJSONString(reqMap), "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println(JSONObject.toJSONString(requestMap));
            String resultJson = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/shop/configQuery", requestMap);
            System.out.println(resultJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解挂靠接口
     */
    public static void subbranchTradeUnBind(String FMerchantId, LsShopWxAliConfigEnum config) {
        try {
            Map reqMap = new HashMap();
            reqMap.put("FMerchantId", FMerchantId); //乐刷商户号
            reqMap.put("FChannelType", config.getFChannelType()); // 交易类型 1- 微信； 2- 支付宝
            reqMap.put("FChannelId", config.getFChannelId());// 渠道商户号
            reqMap.put("FFeeType", config.getFFeeType());// 交易类型 1-线上；2线下；5-保险

            Map requestMap = new HashMap();
            requestMap.put("agentId", "5919932");
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", JSONObject.toJSONString(reqMap));
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + JSONObject.toJSONString(reqMap), "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println(JSON.toJSONString(requestMap));
            String resultJson = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/shop/subbranchTradeUnBind", requestMap);
            System.out.println(resultJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 挂靠接口
     */
    public static void subbranchTradeBind(String FMerchantId, LsShopWxAliConfigEnum config) {
        try {
            Map reqMap = new HashMap();
            reqMap.put("FMerchantId", FMerchantId);
            reqMap.put("FChannelType", config.getFChannelType());
            reqMap.put("FChannelId", config.getFChannelId());
            reqMap.put("FFeeType", config.getFFeeType());

            Map requestMap = new HashMap();
            requestMap.put("agentId", "5919932");
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", JSONObject.toJSONString(reqMap));
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + JSONObject.toJSONString(reqMap), "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println(JSONObject.toJSONString(requestMap));
            String resultJson = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/shop/subbranchTradeBind", requestMap);
            System.out.println(resultJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 商户设置费率
     */
    public static JSONObject openFee(String merchantId) {
        try {
            Map reqMap = new HashMap();
            reqMap.put("merchantId", merchantId);
            reqMap.put("effectiveType", 2);//0：立即生效 2：次日生效 5：当日生效

            Integer rateVal1 = 30;

            Map rate1 = new HashMap();
            rate1.put("rate", rateVal1);


//            Integer rateVal2 = 61;
//            Map rate2 = new HashMap();
//            rate2.put("rate", rateVal2);

            Map weixin = new HashMap();
//            weixin.put("insurance", rate1);
            weixin.put("t1", rate1);
//            weixin.put("online", rate1);

            Map ali = new HashMap();
            ali.put("t1", rate1);

            reqMap.put("alipay", ali);
            reqMap.put("weixin", weixin);

            Map requestMap = new HashMap();
            requestMap.put("agentId", "5919932");
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
            requestMap.put("data", JSONObject.toJSONString(reqMap));
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + JSONObject.toJSONString(reqMap), "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);
            System.out.println(requestMap);
            String result = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/merchant/open", requestMap);
            System.out.println(result);
            if (!StringUtils.isEmpty(result) && isJson(result)) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                if (jsonObject.containsKey("respCode") && "000000".equalsIgnoreCase(String.valueOf(jsonObject.get("respCode")))) {
                    return null;
                }
            }
            return null;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 商户设置费率查询
     */
    public static JSONObject feeQry(String merchantId) {
        try {
            Map reqMap = new HashMap();
            reqMap.put("merchantId", merchantId);

            Map requestMap = new HashMap();
            requestMap.put("agentId", "5919932");
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
            requestMap.put("data", JSONObject.toJSONString(reqMap));
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + JSONObject.toJSONString(reqMap), "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);
            System.out.println(JSONObject.toJSONString(requestMap));
            String result = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/merchant/fee_qry", requestMap);
            System.out.println(result);
            if (!StringUtils.isEmpty(result) && isJson(result)) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                if (jsonObject.containsKey("respCode") && "000000".equalsIgnoreCase(String.valueOf(jsonObject.get("respCode")))) {
                    return jsonObject;
                }
            }
            return null;
        } catch (Exception e) {

        }
        return null;
    }

    public static boolean isJson(String content) {
        try {
            if (StringUtils.isEmpty(content))
                return false;
            JSONObject.parseObject(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 挂靠方挂靠关系查询接口
     */
    public static JSONObject attachQuery(String merchantId, String channelType) {
        try {
            Map reqMap = new HashMap();
            reqMap.put("merchantId", merchantId);
            reqMap.put("channelType", channelType);

            Map requestMap = new HashMap();
            requestMap.put("agentId", "5919932");
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", JSONObject.toJSONString(reqMap));
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + JSONObject.toJSONString(reqMap), "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println(JSONObject.toJSONString(requestMap));
            String resultJson = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/shop/attachQuery", requestMap);
            System.out.println(resultJson);
            return JSONObject.parseObject(resultJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void attachedQuery(String merchantId, String channelType, String channelId) {
        try {
            Map reqMap = new HashMap();
            reqMap.put("merchantId", merchantId);
            reqMap.put("channelType", channelType);
            reqMap.put("channelId", channelId);

            Map requestMap = new HashMap();
            requestMap.put("agentId", "5919932");
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", JSONObject.toJSONString(reqMap));
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + JSONObject.toJSONString(reqMap), "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println(JSONObject.toJSONString(requestMap));
            String resultJson = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/shop/attachedQuery", requestMap);
            System.out.println(resultJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 总店挂靠能力申请接口
     * FChannelType: 1-wx;2-zfb
     * FFeeType: 1 线上费率类型，默认不传 2就是线下
     */
    public static void configApply(String FHeadMerchantId, String FChannelType, String FChannelId, String FAttachFile, int FFeeType) {
        try {
            Map reqMap = new HashMap();
            reqMap.put("FHeadMerchantId", FHeadMerchantId);
            reqMap.put("FApplyType", "1");
            reqMap.put("FChannelType", FChannelType);
            reqMap.put("FChannelId", FChannelId);
            reqMap.put("FAttachFile", FAttachFile);
            reqMap.put("FFeetype", FFeeType);

            Map requestMap = new HashMap();
            requestMap.put("agentId", "5919932");
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", JSONObject.toJSONString(reqMap));
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + JSONObject.toJSONString(reqMap), "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println(JSONObject.toJSONString(requestMap));
            String resultJson = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/shop/configApply", requestMap);
            System.out.println(resultJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 总店挂靠能力申请接口
     * FChannelType: 1-wx;2-zfb
     * FFeeType: 1 线上费率类型，默认不传 2就是线下
     */
    public static void configUpdate(String FHeadMerchantId, String FChannelType, String FChannelId, String FAttachFile, String FApplyId) {
        try {
            Map reqMap = new HashMap();
            reqMap.put("FHeadMerchantId", FHeadMerchantId);
            reqMap.put("FApplyType", "1");
            reqMap.put("FChannelType", FChannelType);
            reqMap.put("FChannelId", FChannelId);
            reqMap.put("FAttachFile", FAttachFile);
            reqMap.put("FApplyId", FApplyId);

            Map requestMap = new HashMap();
            requestMap.put("agentId", "5919932");
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", JSONObject.toJSONString(reqMap));
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + JSONObject.toJSONString(reqMap), "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println(JSONObject.toJSONString(requestMap));
            String resultJson = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/shop/configUpdate", requestMap);
            System.out.println(resultJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pdfUpload() {
        try {
            String fileName = "C:\\Users\\wesays\\Desktop\\总店能力申请pdf\\8539112234_2.pdf";
            Map reqMap = new HashMap();
            File file = new File(fileName);
            FileInputStream fileInputStream = new FileInputStream(file);
            reqMap.put("fileMD5", DigestUtils.md5Hex(fileInputStream));
            Map requestMap = new HashMap();
            requestMap.put("agentId", "5919932");
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "12345");
            requestMap.put("data", JSONObject.toJSONString(reqMap));
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + JSONObject.toJSONString(reqMap), "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);
            Map<String, String> fileMap = new HashMap<>();
            fileMap.put("media", fileName);
            String contentType = "";//image/png
            String ret = HttpClientUtil.formUpload("https://saas-mch.leshuazf.com/apiv2/upload/pdf", requestMap, fileMap, contentType);
            System.out.println("上传乐刷图片返回：" + ret);
            JSONObject jsonObject = JSONObject.parseObject(ret);
            if (jsonObject.get("data") != null) {
                JSONObject resultJson = JSONObject.parseObject(String.valueOf(jsonObject.get("data")));
                if (resultJson.containsKey("photoUrl")) System.out.println(resultJson.containsKey("photoUrl"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
