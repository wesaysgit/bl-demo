package com.es.lsapp;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LsShopBind1 {
//    public static final String FMerchantId = "2444118828";
    public static final String FMerchantId = "4453712093";


    public static void main(String[] args) throws Exception{

//        String str = "8749512946,7345410669";
//        String[] shopIds = str.split(",");
//        for (String shopId : shopIds) {
////            subbranchTradeBind(shopId, LsShopWxAliConfigEnum.WXINSURANCEBIND);
////            subbranchTradeUnBind(shopId, LsShopWxAliConfigEnum.WXINSURANCEUNBIND);
//            System.out.println("-----------------------------------------------------------------------------------------");
//        }
//        subbranchTradeBind("", LsShopWxAliConfigEnum.WXINSURANCEBIND);
//        subbranchTradeUnBind("", LsShopWxAliConfigEnum.WXINSURANCEBINDTEMP);
//        subbranchTradeUnBind("", LsShopWxAliConfigEnum.ALICHANNELCONFIG);
//        attachQuery(FMerchantId, LsShopWxAliConfigEnum.ALICHANNELCONFIG.getFChannelType());

//        System.out.println("============================================================");
//        attachQuery(FMerchantId, LsShopWxAliConfigEnum.WXCHANNELCONFIG.getFChannelType());

//        subbranchTradeBind("4453712093", LsShopWxAliConfigEnum.NEWWXINSURANCEBIND);
//        subbranchTradeBind("4453712093", LsShopWxAliConfigEnum.NEALIXINSURANCEBIND);
//        subbranchTradeUnBind("4453712093", LsShopWxAliConfigEnum.NEWWXINSURANCEBIND);
//        subbranchTradeBind("4453712093", LsShopWxAliConfigEnum.HEADWXUNBINDTEMP);

//        attachQuery("8354612068", "1");
//        attachQuery("7345410669", "1");


//-----------------------费率相关-------------------------
//        openFee("", 0);
//        feeQry("2364719849");
//------------------------------------------------

        /** 总店挂靠能力申请接口*/
//        configApply("", "2", "",
//                "picture2pro/M00/4A/6E/.pdf", 2);
        // 4644410453 picture2pro/M00/4A/6A/rBQLNWW3ECyAVQsKAAN4z8ShWmo537.pdf
        // 8666610384 picture2pro/M00/4A/6C/rBQLNWW3EEyAa86WAAOHgX0tXR0358.pdf
        // 0953516082 picture2pro/M00/4A/6C/rBQLNWW3EGiAFUVzAAN1S9pL4T4382.pdf
        // 2953516106 picture2pro/M00/4A/6E/rBQLNWW3EIKAUPPDAAOsoXoUWAU037.pdf
//        pdfUpload();
//        configUpdate("6347510437", "1", "591510349",
//                "picture2pro/M00/6A/30/rBQLNWTnH6GAYiDlAAPTRV_KlZg036.pdf", "591993263475104371692864341516");

//        configQuery("9961613083", "");

        /**被挂靠方挂靠关系查询接口*/
//        attachedQuery("6937110657","1", "570597363");
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
    public static JSONObject openFee(String merchantId, int effectiveType) {
        try {
            Map reqMap = new HashMap();
            reqMap.put("merchantId", merchantId);
            reqMap.put("effectiveType", effectiveType);

            Integer t1Rate = 39;
            Integer onlineRate = 61;
            Integer insuranceRate = 30;
            Map t1 = new HashMap();
            t1.put("rate", t1Rate);

            Map online = new HashMap();
            online.put("rate", 61);

            Map insurance = new HashMap();
            insurance.put("rate", 40);

            Map weixin = new HashMap();

            weixin.put("t1", t1);
            weixin.put("online", online);
            weixin.put("insurance", insurance);
//            reqMap.put("weixin",weixin);

            JSONObject ali = new JSONObject();
            JSONObject rate = new JSONObject();
            rate.put("rate", 60);
            ali.put("t1",rate);
            reqMap.put("alipay",ali);

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
            reqMap.put("FFeeType", FFeeType);

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
            String fileName = "/Users/xugan/Desktop/2953516106.pdf";
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
