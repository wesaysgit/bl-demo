package com.es.lsapp;

import cn.hutool.core.util.XmlUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

public class LsMchInfo {
    private static SecureRandom random = new SecureRandom();
    static String backUrl = "http://beta.bolink.club/unionapi/lsaynotify";
    public static void main(String[] args) throws Exception {

//        refundQuery();
//        refund();
//        shareRefund();
        info_qry("9036614197");
//        querySettlementStatus("1830015938");
//        auditQry("1957111609");
//        update("2355111163");
//        wxpayconfig("", 1, "wx4e1d0d53645b8f11", false, "");
//        wxpayconfigQry("366271");
//        refund();
//        getCode("4453712093", "497998390");
//        appletPay("9211610528", "");

//        queryAccount("9946410168", "2023-09-08");

//        String tradeNoStr = "21202311021717040255541042,21202311021716368969255196,21202311021716126289701411,21202311021708309931907073,21202311021608040101314191,21202311021533109666155776,16989090135311827604,21202311021416479097210128,16989043281594796478,21202311021351409815557730,21202311021053271074732220,21202311020924553372492224,16988797845294208514,21202311012122112925041995,21202311012100283679546003,21202311012015427084681322,21202311011949370929241955,16988375422944054551,21202311011903410138171844,21202311011731524231239809,21202311011730115307889489,21202311011718161296985137,21202311011713257732995050,21202311011601063899814372,21202311010937235913964220,21202311011225558402469400,21202311011225037151326184,21202311011136378714110816,16988062836788848744,16988049381572072811,16988035271878624226,16987957419064294071,16987957552749525603,21202311010721410948562547";
//        String lsOrderStr = "0000969722023306,0000869422023306,0000869122023306,0000770922023306,0000749322023306,0000937922023306,0000932722023306,0000727122023306,0000723822023306,0000723222023306,0000056522023306,0000035922023306,0000106322023306,0001266022023305,0001261622023305,0001246022023305,0001046422023305,0001035622023305,0001027922023305,0000882522023305,0000881922023305,0000777522023305,0000874822023305,0000849522023305,0000239122023305,0000800322023305,0000294522023305,0000274922023305,0000158922023305,0000250722023305,0000144122023305,0000209922023305,0000014022023305,0000009322023305";
//        String moneyStr = "12.00,12.00,12.00,8.00,3.00,10.00,150.00,4.00,150.00,8.00,12.00,39.00,150.00,3.00,3.00,7.00,3.00,300.00,3.00,5.00,6.00,12.00,9.00,11.00,4.00,4.00,4.00,3.00,150.00,150.00,300.00,150.00,150.00,22.00";
//        List<String> tradeNos = Arrays.stream(tradeNoStr.split(",")).collect(Collectors.toList());
//        List<String> lsOrders = Arrays.stream(lsOrderStr.split(",")).collect(Collectors.toList());
//        List<String> moneys = Arrays.stream(moneyStr.split(",")).collect(Collectors.toList());
//        if (tradeNos.size() != 34 || lsOrders.size() != 34 || moneys.size() != 34) {
//            System.out.println("数据有误");
//        } else {
//            for (int i = 0; i < tradeNos.size(); i++) {
//                String s = moneys.get(i);
//                String outTradeNo = tradeNos.get(i);
//                String lsOrder = lsOrders.get(i);
//                long amount = BigDecimal.valueOf(Double.parseDouble(s)).multiply(BigDecimal.valueOf(100)).longValue();
//                multiApply("p" + TradeNoUtil.getTradeNo(21), "outTradeNo", "lsOrder", String.valueOf(1));
//                System.out.println(outTradeNo+"_"+lsOrder+"_"+amount+"---------------------------------------------------------------------------------------------------");
//            }
//        }

    }

    private static void multiApply(String thirdRoyaltyId, String thirdOrderId, String leshuaOrderId, String amount) {

        try {
            SortedMap reqMap = new TreeMap();
            reqMap.put("merchantId", "");
            reqMap.put("thirdRoyaltyId", thirdRoyaltyId);
            reqMap.put("thirdOrderId", thirdOrderId);
            reqMap.put("leshuaOrderId", leshuaOrderId);

            List<Map> shareDetail = new ArrayList<>();
            SortedMap shareDetailItem = new TreeMap();
            shareDetailItem.put("merchantId", "");
            shareDetailItem.put("amount", amount);
            shareDetailItem.put("remark", "分账失败重试");
            shareDetail.add(shareDetailItem);

            reqMap.put("shareDetail", shareDetail);
            JSONObject reqJson = new JSONObject(reqMap);

            Map requestMap = new HashMap();
            requestMap.put("agentId", "5919932");
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
            requestMap.put("data", reqJson.toString());
            String md51 = MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + reqJson, "utf-8");
            String md52 = SecureUtil.md5("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + reqJson);
            System.out.println(md51);
            System.out.println(md52);
            System.out.println(md51.equals(md52));
            String sign = Base64Utils.encodeToString(md51.getBytes());
            requestMap.put("sign", sign);

            System.out.println(JSON.toJSONString(requestMap));
//            String post = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/api/share-merchant/multi-apply", requestMap);
            System.out.println("post");

        } catch (Exception e) {
        }
    }

    public static void queryAccount(String shopId, String date) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("merchantId", shopId);
            jsonObject.put("date", date);
            jsonObject.put("state", 4);
            jsonObject.put("pageNo", 1);
            jsonObject.put("type", "D1");

            Map requestMap = new LinkedHashMap();
            requestMap.put("version", "0.0.1");
            requestMap.put("agentId", "5919932");
            requestMap.put("reqSerialNo", UUID.randomUUID().toString().replace("-", "").substring(1, 17));
            requestMap.put("data", jsonObject.toString());
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + jsonObject, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);
            System.out.println("请求参数信息" + requestMap);
            String post = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/shop/attachQuery", requestMap);
            System.out.println(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//'80682','LH2023010401','YDHT18','1565240913117548545','107943','170448','121133','102189','DB0002','96277','1598951072236494850','76792','126014','102985','119641','1596057652377812994','52155','114591','114752','1588115455848148994','166128','83424','103361','103966','107942','131692','132200','132290','133585','159712','2022080103','65822','A2022101601','LXT2022101501','101218','112441','116366','125960','137203','150567','156338','156940','1630108449131409410','20200515134918494','44887','61731','74425','B2023030803','bjhtc999','NYKJ/62P79','107466','111216','126017','1493796268105330690','149947','1588438580333047810','159714','29541','56845','81960','86002','97260','htc999005','QS2022102001','102895','104018','106504','121307','124108','133241','136699','137378','137746','1458720443387691010','150776','15189718900','1569894819625005058','20211030001','55023','58874','80705','90992','93698','htc901319','102085','113697','136698','137242','143609','1478908160399994881','148356','151462','152371','1588376182211145730','73868','75081','77875','79558','84118','89982','CD2022071101','cm2023041001','htc2021072501','LXT2022101002','htc9990031','97038','86661','20230203','134999','79528','99021','2019091701','120815','119629','155646','20200814001','220105','133265','95928','htc90013','106416','1588468054424481794','66747','96660','158354','381735','109408','112455','114111','131619','15626545503','1599585609255956482','179136','20220311','38920','49207','62255','66106','69603','10044528','107801','108062','110467','112447','117779','124771','138875','142631','154973','155648','1570976988837539842','1595718597143441410','167349','20201202001','25822','48287','53972','73153','79205','83774','88999','93070','LXT2022071201','NYKJ/21P25','San2022040301','100649','108713','112448','117073','119769','123761','126013','134985','135471','138867','15119405233','1587269362911854593','168616','20200114001','20200727001','77226','HRS2021061901'

//200128,200109,200153,1002091,200146,200988,200136,200136,200153,200136,1002091,200136,200988,200873,200139,1002091,200272,200136,200272,1002091,200988,200136,200873,200128,200146,200136,200446,201028,200988,200988,200721,200139,200264,200376,200136,200136,200873,200136,200988,200139,200988,200503,200152,200289,200446,200551,200139,200264,200874,200934,200136,200433,200988,200152,200778,1002091,200988,200272,200152,200136,200493,200136,200874,200264,200136,200874,200139,200874,200139,200874,201028,200589,200988,200152,200272,200475,200152,200207,200136,200272,200139,200152,200136,200874,200136,200139,201028,200988,201086,200152,200139,200988,200988,1002091,200400,200623,200154,200257,200325,200136,200694,200843,200874,200376,200874,200136,200136,200416,200428,200152,200152,200148,200136,200139,200136,200207,200416,200988,200154,200874,200139,1002091,200623,200136,200874,200721,200493,200136,200139,200988,200475,1002091,200146,200988,200257,200152,200479,200634,200400,200128,200428,200152,200080,200136,200313,200988,200313,200428,200741,200136,200152,200152,201028,200207,200136,200136,200139,200136,200152,200136,200152,200136,200376,200251,201028,200136,200136,200136,200874,200988,200136,200988,200136,200272,200988,200475,200152,200257,200207,200207,200152,200719

    public static void getCode(String merchantId, String ass_merchant_id) throws Exception {
        String tradeNo = getTradeNo(21);
        Map payMap = new HashMap();
        payMap.put("service", "get_tdcode");//接口名 M
        payMap.put("pay_way", "WXZF");//支付类型 M
        payMap.put("amount", "1");//订单金额 M
        payMap.put("jspay_flag", "0");
        payMap.put("merchant_id", merchantId);//商户号 M
        payMap.put("third_order_id", tradeNo);//订单号 M
        if (StringUtils.isNotBlank(ass_merchant_id)) {
            payMap.put("ass_merchant_id", ass_merchant_id);
        }
        //支付成功跳转页
        String defaultPaySuccessUrl = "http://beta.bolink.club/unionapi/payComplete?money=1" + "&tradeno=" + tradeNo + "&merchant_id=" + merchantId;
        payMap.put("jump_url", encodeUTF8(defaultPaySuccessUrl));//支付成功跳转页面 需要编码 C
        payMap.put("notify_url", encodeUTF8("https://beta.bolink.club/unionapi/walletNotify/rechargeCallback"));//通知地址 O
        payMap.put("body", "支付测试");//商品描述  O
        payMap.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32).toUpperCase());//随机字符串  M
        payMap.put("sign", getSign(payMap, 1));//签名 M
        System.out.println("下单:" + JSONObject.toJSONString(payMap));
        String lsResultStr = HttpClientUtil.postParameters("https://paygate.leshuazf.com/cgi-bin/lepos_pay_gateway.cgi", payMap);
        Map<String, Object> map = XmlUtil.xmlToMap(lsResultStr);
        System.out.println("返回:" + JSONObject.toJSONString(map));
    }
    public static void appletPay(String merchantId, String ass_merchant_id) throws Exception {
        String tradeNo = getTradeNo(21);
        Map payMap = new HashMap();
        payMap.put("service", "get_tdcode");//接口名 M
        payMap.put("pay_way", "ZFBZF");//支付类型 M
        payMap.put("amount", "1");//订单金额 M
        payMap.put("jspay_flag", "3");
        payMap.put("merchant_id", merchantId);//商户号 M
        payMap.put("third_order_id", tradeNo);//订单号 M
//        payMap.put("sub_openid", "2088502861953225");//订单号 M
        if (StringUtils.isNotBlank(ass_merchant_id)) {
            payMap.put("ass_merchant_id", ass_merchant_id);
        }
        //支付成功跳转页
        String defaultPaySuccessUrl = "http://beta.bolink.club/unionapi/payComplete?money=1" + "&tradeno=" + tradeNo + "&merchant_id=" + merchantId;
        payMap.put("jump_url", encodeUTF8(defaultPaySuccessUrl));//支付成功跳转页面 需要编码 C
        payMap.put("notify_url", encodeUTF8("https://beta.bolink.club/unionapi/walletNotify/rechargeCallback"));//通知地址 O
        payMap.put("body", "支付测试");//商品描述  O
        payMap.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32).toUpperCase());//随机字符串  M
        payMap.put("sign", getSign(payMap, 1));//签名 M
        System.out.println("下单:" + JSONObject.toJSONString(payMap));
        String lsResultStr = HttpClientUtil.postParameters("https://paygate.leshuazf.com/cgi-bin/lepos_pay_gateway.cgi", payMap);
        Map<String, Object> map = XmlUtil.xmlToMap(lsResultStr);
        System.out.println("返回:" + JSONObject.toJSONString(map));
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

    public static void refundQuery() throws Exception {
        Map payMap = new HashMap();
        payMap.put("service", "unified_query_refund");//接口名 M
        payMap.put("merchant_id", "0361610658");
        payMap.put("leshua_order_id", "8001122165823107");
        payMap.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32).toUpperCase());//随机字符串  M
        payMap.put("sign", getSign(payMap, 1));//签名 M
        String result = HttpClientUtil.postParameters("https://paygate.leshuazf.com/cgi-bin/lepos_pay_gateway.cgi", payMap);
        Map<String, String> stringStringMap = WxPayUtil.xmlToMap(result);
        System.out.println(JSONObject.toJSONString(stringStringMap));
    }



    public static void shareRefund() {
        String refund = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32).toUpperCase();

        JSONObject reqJson = new JSONObject();
        reqJson.put("merchantId", "9946410168");
        reqJson.put("thirdOrderId", "21202309121123211098955639");
        reqJson.put("leshuaOrderId", "9000753316823255");
        reqJson.put("thirdRefundId", refund);
        reqJson.put("refundAmount", "200");

        Map requestMap = new HashMap();
        requestMap.put("agentId", 5919932);
        requestMap.put("version", "1.0");
        requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
        requestMap.put("data", reqJson.toString());
        String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + reqJson.toString(), "utf-8").toLowerCase().getBytes());
        requestMap.put("sign", sign);
        try {
            String result = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/api/share-merchant/refund", requestMap);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void refund() throws Exception {
        Map payMap = new HashMap();
        payMap.put("service", "unified_refund");//接口名 M
        payMap.put("merchant_id", "2355111163");
        payMap.put("third_order_id", "21202309191623569261211991");
//        payMap.put("leshua_order_id", "8001122165823107");
        String refund = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32).toUpperCase();
        payMap.put("merchant_refund_id", refund);
        payMap.put("refund_type", "1");
        payMap.put("refund_amount", "5800");
        payMap.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32).toUpperCase());//随机字符串  M
        payMap.put("sign", getSign(payMap, 1));//签名 M
        String result = HttpClientUtil.postParameters("https://paygate.leshuazf.com/cgi-bin/lepos_pay_gateway.cgi", payMap);
        Map<String, String> stringStringMap = WxPayUtil.xmlToMap(result);
        System.out.println(JSONObject.toJSONString(stringStringMap));
    }

    public static void wxpayconfig(String shopId, Integer configType, String sub_appid, boolean isSubShop, String wxSubMchId) {
        JSONObject payMap = new JSONObject();
        payMap.put("merchantId", shopId);//商户号 M
        payMap.put("configType", configType);
        payMap.put("sub_appid", sub_appid);
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
        String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + payMap, "utf-8").toLowerCase().getBytes());
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
    public static void wxpayconfigQry(String merchantId) {
        JSONObject payMap = new JSONObject();
        payMap.put("merchantId", merchantId);//商户号 M
        Map requestMap = new HashMap();
        requestMap.put("agentId", 5919932);
        requestMap.put("version", "1.0");
        requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
        requestMap.put("data", payMap.toString());
        String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + payMap, "utf-8").toLowerCase().getBytes());
        requestMap.put("sign", sign);
        try {
            System.out.println("【微信】支付参数查询:" + JSONObject.toJSONString(requestMap));
            String result = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/wechat/wxpayconfig_qry", requestMap);
            System.out.println(JSONObject.toJSONString(requestMap));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void update(String merchantId) {
        try {
            Map accountInfo = new HashMap();
            //1-自动 2-手动提现
            accountInfo.put("withDrawType", 2);
            Map reqMap = new HashMap();
            reqMap.put("merchantId", merchantId);
            reqMap.put("accountInfo", accountInfo);
            String reqJson = JSONObject.toJSONString(reqMap);
            System.out.println(reqJson);

            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(1000));
            requestMap.put("data", reqJson);
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + reqJson, "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            String resultJson = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/merchant/update", requestMap);
            System.out.println("修改商户请求参数" + requestMap + "修改户请求结果>>>" + resultJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void auditQry(String shopid) {
        JSONObject payMap = new JSONObject();

        payMap.put("merchantId", shopid);

        Map requestMap = new HashMap();
        requestMap.put("agentId", 5919932);
        requestMap.put("version", "1.0");
        requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
        requestMap.put("data", payMap.toString());
        String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + payMap.toString(), "utf-8").toLowerCase().getBytes());
        requestMap.put("sign", sign);
        try {
            System.out.println("乐刷商户审核状态查询请求参数:" + JSONObject.toJSONString(requestMap));
            String lsResult = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com//apiv2/merchant/audit_qry", requestMap);
            System.out.println(lsResult);
            JSONObject jsonObject = JSONObject.parseObject(lsResult);
            String respCode = jsonObject.getString("respCode");
            System.out.println("-------------------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void info_qry(String shopid) {
        JSONObject payMap = new JSONObject();

        payMap.put("merchantId", shopid);

        Map requestMap = new HashMap();
        requestMap.put("agentId", 5919932);
        requestMap.put("version", "1.0");
        requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
        requestMap.put("data", payMap.toString());
        String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + payMap.toString(), "utf-8").toLowerCase().getBytes());
        requestMap.put("sign", sign);
        try {
            System.out.println("乐刷商户信息查询请求参数:" + JSONObject.toJSONString(requestMap));
            String lsResult = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/merchant/info_qry", requestMap);
            System.out.println(lsResult);
            JSONObject jsonObject = JSONObject.parseObject(lsResult);
            String respCode = jsonObject.getString("respCode");
            System.out.println("-------------------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void querySettlementStatus(String shopid) {
        JSONObject payMap = new JSONObject();

        payMap.put("merchantId", shopid);

        Map requestMap = new HashMap();
        requestMap.put("agentId", 5919932);
        requestMap.put("version", "1.0");
        requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
        requestMap.put("data", payMap.toString());
        String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + payMap.toString(), "utf-8").toLowerCase().getBytes());
        requestMap.put("sign", sign);
        try {
            System.out.println("乐刷商户信息查询请求参数:" + JSONObject.toJSONString(requestMap));
            String lsResult = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/apiv2/risk-work-order/querySettlementStatus", requestMap);
            System.out.println(lsResult);
            JSONObject jsonObject = JSONObject.parseObject(lsResult);
            String respCode = jsonObject.getString("respCode");
            System.out.println("-------------------------------------------------------------");
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
            String result = MD5Util.MD5Encode(paramStr.substring(1) + "&key=5D5DC123FD9DE1B4984C9FF07E19EFCA", "utf-8");
            return type == 1 ? result.toUpperCase() : result.toLowerCase();
        } else if (type == 3) {
            return MD5Util.MD5Encode(JSONObject.toJSONString(source) + "5D5DC123FD9DE1B4984C9FF07E19EFCA", "utf-8").toLowerCase();
        }
        return null;
    }
}
