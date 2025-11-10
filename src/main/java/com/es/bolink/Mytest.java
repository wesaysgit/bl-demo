package com.es.bolink;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.es.BASE64;
import org.apache.http.conn.ConnectTimeoutException;

import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


public class Mytest {
    //	private static String url = "http://x1x8089748.iok.la/unionapi/bolinkunified";
//	private static String url = "https://s.bolink.club/unionapi/bolinkunified";  //http://ts.bolink.club/unionapi/bolinkunified
//	private static String url1 = "https://s.bolink.club/unionapi/bolinkparkpay";//lintingjiaofei	
    private static String url = "http://beta.bolink.club/unionapi/bolinkunified";//https://beta.bolink.club/unionapi/bolinkunified  http://ts.bolink.club/unionapi/bolinkunified
    private static String url1 = "http://beta.bolink.club/unionapi/bolinkparkpay";//lintingjiaofei  //https://beta.bolink.club/unionapi/bolinkparkpay   http://ts.bolink.club/unionapi/bolinkparkpay 
    // private static String url2 = "http://bt2.bolink.club/unionapi/bolinkunified";
//	private static String url = "http://spcl.bolink.club/unionapi/bolinkunified";//https://beta.bolink.club/unionapi/bolinkunified  http://ts.bolink.club/unionapi/bolinkunified
    // private static String url1 = "http://spcl.bolink.club/unionapi/bolinkparkpay";//furui
//	private static String url = "http://bgds.bolink.club/unionapi/bolinkunified";//https://beta.bolink.club/unionapi/bolinkunified  http://ts.bolink.club/unionapi/bolinkunified   
//	private static String url1 = "http://bgds.bolink.club/unionapi/bolinkparkpay";//aidi
//	private static String url = "http://ha-pay-01.bolink.club/unionapi/bolinkunified";// 两地三中心
//	private static String url1 = "http://ha-pay-01.bolink.club/unionapi/bolinkparkpay";/// 两地三中心


    public static void main(String[] args) throws Exception {
//		String uuid = UUID.randomUUID().toString().replace("-", "");
//		System.out.println(uuid);
//		String sign = MD5( uuid+"LSCHANNELSWITCH");
//		System.out.println(sign);

        //设置无牌车入场获取短信验证码
//		parkSet();

        //测试红包
//		redPacket();

        //统一下单测试-通用支付
//		commonPay();

        //月卡续费测试
//        monthPay();

        //临停缴费云对接
//		prepay();

        //订单状态查询
//		queryOrder();

        //小程序通用支付
//		appletsPay();

        //乐刷获取电子合同地址   3.5接口
        //   	 lsaccountsplit();

        //乐刷绑定接收分帐方       3.6接口
//		lsbind();
        /**/

        //逃单查询
//		  queryEscape();

        //逃单补缴
        //   	escapePay();

        //etc扣费测试
//		  etc();

        //快捷支付
//		payorder();

        //退款
		refundOrder();
//        commonPay();

    }

    private static void refundOrder() {
        try {
            HashMap<String, Object> parammap = new HashMap<String, Object>();

            parammap.put("union_id", 200712);//厂商平台id   200568
            parammap.put("park_id", "56399");//车场编号 123456  123   hxy2021101401
            //jiao yi hao
            parammap.put("trade_no", "138202508101459482709383");
            parammap.put("end_time", 1754805276);
            parammap.put("refund_money", 0.10);
            parammap.put("subsidy_cancel_money", 0.02);
            parammap.put("refund_reason", "123");
//            parammap.put("share_details", );
//            parammap.put("notify_url", "http://106.75.7.56:8081/test_bolink/callparkapi/querycurrorder");

            HashMap<String, Object> json = new HashMap<String, Object>();
            String sign = MD5(JSON.toJSONString(parammap) + "key=" + "4F80BF35DF83858D");//CFE95B01232DD942   7B315B5A5350500E
            json.put("sign", sign.toUpperCase());
            json.put("data", parammap);
            String params = JSON.toJSONString(json);
            System.out.println(params);

            String result = HttpClientUtil.post("https://beta.bolink.club/unionapi/neworder/refund", params, "application/json", "utf-8", 10000, 10000);
            System.out.println(result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private static void parkSet() throws ConnectTimeoutException, SocketTimeoutException, Exception {
        HashMap<String, Object> parammap = new HashMap<String, Object>();

        parammap.put("park_id", "123456");//123456   123
        parammap.put("union_id", 200568);//200568   200776
        Map<String, Object> nolience_config = new HashMap<>();
        nolience_config.put("is_verify", "0");  //是否进行手机验证码验证    1-是     0-否

        parammap.put("nolience_config", nolience_config);

        HashMap<String, Object> json = new HashMap<String, Object>();
        String data = JSON.toJSONString(parammap);

        String sign = MD5(data + "key=" + "7B315B5A5350500E");//7B315B5A5350500E   CFE95B01232DD942
        json.put("sign", sign.toUpperCase());
        json.put("data", parammap);
        System.out.println(JSON.toJSONString(json));
        String params = JSON.toJSONString(json);

        Long time = System.currentTimeMillis();
        String result = HttpClientUtil.post("http://beta.bolink.club/unionapi/newpark/setUp", params, "application/json", "utf-8", 10000, 10000);
        System.out.print(result);
    }

    private static void redPacket() {
        // TODO Auto-generated method stub
        try {
            HashMap<String, Object> parammap = new HashMap<String, Object>();
            parammap.put("order_id", "A1_2C1661478152");//进场订单号A1_2C1627377703
            parammap.put("park_id", "123");//123456  123
            parammap.put("car_number", "陕SD9999");//车牌号  陕AAA002
            parammap.put("trade_no", "123091290312" + System.currentTimeMillis());
            parammap.put("timestamp", System.currentTimeMillis() / 1000);
            parammap.put("union_id", 200776);//200568   200776
            parammap.put("amount", "1");
            parammap.put("notify_url", "http://106.75.7.56:8081/test_bolink/callparkapi/preorder");//http://106.75.7.56:8081/test_bolink/callparkapi/preorder

            HashMap<String, Object> json = new HashMap<String, Object>();

            String sign = MD5(JSON.toJSONString(parammap) + "key=" + "CFE95B01232DD942");//7B315B5A5350500E   CFE95B01232DD942
            json.put("sign", sign.toUpperCase());
            json.put("data", parammap);
            String params = JSON.toJSONString(json);
            System.out.println(params);

            String result = HttpClientUtil.post("http://s.bolink.club/unionapi/getWxRedPacketUrlNew", params, "application/json", "utf-8", 10000, 10000);
            System.out.println(result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void escapePay() {
        try {
            HashMap<String, Object> parammap = new HashMap<String, Object>();
            parammap.put("order_id", "A1_2C1660806368");//进场订单号

            parammap.put("park_id", "123");
            parammap.put("title", "补缴");//标题
            parammap.put("car_number", "陕A05559");//车牌号
            parammap.put("trade_no", "123091290312" + System.currentTimeMillis());
            parammap.put("channel", "alipay"); //weixin   / alipay
            parammap.put("pay_type", 0);  //支付类型 0扫码支付，1微信公众号支付，2扫码枪支付，默认0
            parammap.put("time_temp", System.currentTimeMillis() / 1000);
            parammap.put("auth_code", "133594105086097184");
            parammap.put("description", "逃单测试");
            parammap.put("notify_url", "http://106.75.7.uerycurrorder");
            //http://106.75.7.56:8081/test_bolink/callparkapi/querycurrorder

            HashMap<String, Object> json = new HashMap<String, Object>();
            json.put("union_id", 200776);

            String sign = MD5(JSON.toJSONString(parammap) + "key=" + "CFE95B01232DD942");//7B315B5A5350500E   CFE95B01232DD942
            json.put("sign", sign.toUpperCase());
            json.put("data", parammap);
            String params = JSON.toJSONString(json);
            System.out.println(params);

            String result = HttpClientUtil.post("http://s.bolink.club/unionapi/bolinkescapePay", params, "application/json", "utf-8", 10000, 10000);
            System.out.println(result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void queryEscape() {
        try {
            HashMap<String, Object> parammap = new HashMap<String, Object>();

            parammap.put("park_id", "123");//车场编号 123456  123
            parammap.put("car_number", "苏DTTT18");
            parammap.put("time_temp", System.currentTimeMillis() / 1000);
            HashMap<String, Object> json = new HashMap<String, Object>();
            json.put("union_id", 200776);//厂商平台id   200568
            String sign = MD5(JSON.toJSONString(parammap) + "key=" + "CFE95B01232DD942");//CFE95B01232DD942     7B315B5A5350500E
            json.put("sign", sign.toUpperCase());
            json.put("data", parammap);
            String params = JSON.toJSONString(json);
            System.out.println(params);

            String result = HttpClientUtil.post("http://s.bolink.club/unionapi/neworder/queryescape", params, "application/json", "utf-8", 10000, 10000);
            System.out.println(result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private static void etc() {
        try {
            HashMap<String, Object> parammap = new HashMap<String, Object>();
            parammap.put("park_id", "123");//28888   //20180427
            parammap.put("car_number", "京A00001");//川AK6F63  京A00001
            parammap.put("in_time", 1658996957);
            parammap.put("license_color", 0);
            // parammap.put("in_time",System.currentTimeMillis()/1000 - 7000);
            parammap.put("out_time", System.currentTimeMillis() / 1000);
            // parammap.put("out_time",1621562760 );
            //parammap.put("empty_plot","200");S
            parammap.put("total_original", "0.01");//折扣前订单金额
            parammap.put("total", "0.01");//43000000   折扣后实际支付金额
            //  parammap.put("order_id","test20077081900002");
            parammap.put("order_id", "order" + System.currentTimeMillis());
            parammap.put("in_channel_id", "2");
            parammap.put("out_channel_id", "2");
            parammap.put("inpark_img", "https://www.flowerplus.cn/");
            parammap.put("outpark_img", "https://imgl0.xx.cn/?host=20.2.10.221&url=/c/200217/mIM1vUfkZuKH2tFjpeb.jpga");
            parammap.put("parking_time", "120");
            parammap.put("pay_type", "jyyh");//金溢etc
            //支付类型为金溢云盒时，lane_no为金溢云盒的编号
            parammap.put("lane_no", "TEST20220823");//金溢ETC盒子编号  PK22071159   TEST20220823
            //parammap.put("order_id","A1_2C1539834970");
            //parammap.put("time_temp",System.currentTimeMillis()/1000);
            HashMap<String, Object> json = new HashMap<String, Object>();
            json.put("union_id", 200776);//200265  //200159
            //   json.put("code", "093S1S0w3ofiBV25vk3w3nvZGu0S1S0E");
            String sign = MD5(JSON.toJSONString(parammap) + "key=" + "CFE95B01232DD942");//61E2EEC7DD0BD62D//C160955B1479621F 古月  200159
            // 7B315B5A5350500E   CFE95B01232DD942
            json.put("sign", sign.toUpperCase());
            json.put("data", parammap);
            System.out.println("etc扣费请求：" + JSON.toJSONString(json));
            String params = JSON.toJSONString(json);

            Long time = System.currentTimeMillis();
            String result = HttpClientUtil.post("http://s.bolink.club/unionapi/etcPayApi", params, "application/json", "utf-8", 10000, 10000);
            System.out.println("etc扣费返回：" + result);
            System.out.print("接口耗时 " + (System.currentTimeMillis() - time) + " 毫秒");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    //	5118819446  GY2021042509  //2021033109  进件对应的商户号：
    private static void lsaccountsplit() {
        try {
            HashMap<String, Object> parammap = new HashMap<String, Object>();
            parammap.put("park_id", "123456");//lpj2021031901//古月 GY2021042507//hxy：123456
            parammap.put("account_no", "7912812570");//btls泊链渠道个人代理15(GY2021042507)作为分账方，分账给btls泊链渠道对公法人代理16(GY2021042508)  乐刷商户号  8118818791//hxy:7912812570
            parammap.put("account_name", "泊链渠道进件测试车场");  //接收分账方商户名称 :泊链渠道进件测试车场
            parammap.put("percent", 30);//分账比例
            HashMap<String, Object> json = new HashMap<String, Object>();
            json.put("union_id", 200568);//200565 ，古月200159
            String sign = MD5(JSON.toJSONString(parammap) + "key=" + "7B315B5A5350500E");//3196BC8578FDC184   //200159  C160955B1479621F  古月
            json.put("sign", sign.toUpperCase());
            json.put("data", parammap);
            String params = JSON.toJSONString(json);
            System.out.println("乐刷签约发送参数----------" + params);
            String result = HttpClientUtil.post("https://beta.bolink.club/unionapi/lsaccountsplit", params, "application/json", "utf-8", 10000, 10000);
            System.out.println(result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void lsbind() {
        try {
            HashMap<String, Object> parammap = new HashMap<String, Object>();
            parammap.put("park_id", "GY2021041503");//lpj2021031901//GY2021042507
            HashMap<String, Object> json = new HashMap<String, Object>();
            json.put("union_id", 200159);//200565
            String sign = MD5(JSON.toJSONString(parammap) + "key=" + "7B315B5A5350500E");//3196BC8578FDC184//C160955B1479621F
            json.put("sign", sign.toUpperCase());
            json.put("data", parammap);
            String params = JSON.toJSONString(json);
            System.out.println("乐刷绑定发送参数----" + params);
            String result = HttpClientUtil.post("https://beta.bolink.club/unionapi/lsbind", params, "application/json", "utf-8", 10000, 10000);
            System.out.println(result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

//	private static void redPacket() throws ConnectTimeoutException, SocketTimeoutException, Exception{
//		
////		Long unionId = 200265L; 61E2EEC7DD0BD62D
////		String parkId = "28888";
//		
//		Long unionId = 200776L;//200159 C160955B1479621F 古月  //200568 新玉7B315B5A5350500E
//		String parkId = "123";//20180427  //新玉 123456//2021033106    未开通钱包线上200159厂商 API厂商服务商注册SDK车场车场
//		
//		HashMap<String, Object> parammap = new HashMap<String, Object>();
//
//		parammap.put("park_id",parkId);
//		parammap.put("union_id", unionId);
//		parammap.put("trade_no","GYa123091290413"+System.currentTimeMillis());
////		parammap.put("trade_no","Q2309129031234567890123457b");
//		parammap.put("timestamp",System.currentTimeMillis()/1000);
//		parammap.put("amount","1.0");     //金额
//
//		String str = StringUtils.createLinkStrings2(parammap);
//
//		
//		String sign = MD5( str+"key="+"CFE95B01232DD942");//200159 C160955B1479621F 古月  //200568 新玉7B315B5A5350500E
//		JSONObject json = new JSONObject();
//		
//		json.put("sign", sign.toUpperCase());
//		json.put("data",parammap);
//		System.out.println("红包请求："+JSON.toJSONString(json));
//		String params = JSON.toJSONString(json);
//
//		String result = HttpClientUtil.post("http://s.bolink.club/unionapi/getWxRedPacketUrlNew",  params,"application/json","utf-8",5000,5000);
//
//		System.out.println("红包返回："+result);
//		
//		JSONObject ret = (JSONObject) JSONObject.parse(result);
//		if(ret.getInteger("state") == 1){
//			JSONObject data = ret.getJSONObject("data");
//            System.out.println("红包链接："+data.getString("url"));
//		}
//		
//		
//	}

    private static void appletsPay() {
        String parkId = "24063";
        Long uionId = 200159L;
        String key = "C160955B1479621F";
        try {
            HashMap<String, Object> parammap = new HashMap<String, Object>();
            parammap.put("park_id", parkId);
            parammap.put("trade_no", "202101050001" + System.currentTimeMillis());
            parammap.put("amount", "0.01");
            parammap.put("title", "测试微信跳,转支持通用支付");
            parammap.put("pay_type", 0);
            parammap.put("channel", "applets");
            parammap.put("car_number", "苏DDDGG04");
            parammap.put("description", "orderid=liu20201206_hul-abcapi-04");

//			parammap.put("order_id","A1_2C1539834970");
            parammap.put("time_temp", System.currentTimeMillis() / 1000);
            HashMap<String, Object> json = new HashMap<String, Object>();
            json.put("union_id", uionId);
//			json.put("code", "093S1S0w3ofiBV25vk3w3nvZGu0S1S0E");


            String sign = MD5(JSON.toJSONString(parammap) + "key=" + key);
            json.put("sign", sign.toUpperCase());
            json.put("data", parammap);
            System.out.println(JSON.toJSONString(json));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void queryOrder() {
        try {
            HashMap<String, Object> parammap = new HashMap<String, Object>();
            parammap.put("park_id", "hxy2022072202");//dlytest_no
//			parammap.put("order_id","");
            parammap.put("union_id", "20000003");//200461
            //	parammap.put("car_number","辽100004");

            //新增月卡查询字段
            parammap.put("query_type", 2);
            parammap.put("trade_no", "20226210000000011658735118525");//1234567820210421649663305402

            HashMap<String, Object> json = new HashMap<String, Object>();

            String sign = MD5(JSON.toJSONString(parammap) + "key=" + "D9DBD246E315170B"); //61E2EEC7DD0BD62D,4E4B387623962F64//7B315B5A5350500E  CFE95B01232DD942
            json.put("sign", sign.toUpperCase());
            json.put("data", parammap);
            String params = JSON.toJSONString(json);
            System.out.println(params);

            String result = HttpClientUtil.post("http://ha-pay-01.bolink.club/unionapi/neworder/queryOrderState", params, "application/json", "utf-8", 5000, 5000);
            System.out.println(result);       //https://beta.bolink.club/unionapi/neworder/queryOrderState
            //https://s.bolink.club/unionapi/neworder/queryOrderState
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static void commonPay() {
        try {
            HashMap<String, Object> parammap = new HashMap<String, Object>();
            String parkId = "123456";//lpj2021031901//车场名称 btls泊链渠道对公非法人代理17 车场编号 GY2021042509车场 没设置分账
            //  123456   123
            Long unionId = 200568L;//200565
            //200568   200776  20000003
            String key = "7B315B5A5350500E"; //7B315B5A5350500E  CFE95B01232DD942 D9DBD246E315170B
            parammap.put("park_id", parkId);
            parammap.put("trade_no", "123456782021040" + System.currentTimeMillis());
            parammap.put("amount", "0.01");
            parammap.put("wx_open_id", "o_Kpe0nuBvLMdbDXjNxi-cDuyb2M");
            parammap.put("title", "停车费-新ZHZF01");
            parammap.put("pay_type", 2);//支付类型 0扫码支付，1微信公众号支付，2扫码枪支付，默认0
            parammap.put("channel", "alipay");//weixin   / alipay
            parammap.put("auth_code", "281206734901602933");

            parammap.put("order_id", "liu20210421_hul-abcapi-013");
            parammap.put("wx_app_id", "wx78e05882cbaf38c1");
            parammap.put("car_number", "新A11110");//  苏ABC456  新ABC001
            parammap.put("callback_url", "https://www.bolink.club?a=1");//https://www.bolink.club/   //https://www.bolink.club?a=1
            parammap.put("in_time", 1663220855);
            parammap.put("time_temp", System.currentTimeMillis() / 1000);
            HashMap<String, Object> json = new HashMap<String, Object>();
            json.put("union_id", unionId);

            String sign = MD5(JSON.toJSONString(parammap) + "key=" + key);
            json.put("sign", sign.toUpperCase());
            json.put("data", parammap);
            String params = JSON.toJSONString(json);
            System.out.println(params);

            String result = HttpClientUtil.post(url, params, "application/json", "utf-8", 10000, 10000);
            System.out.println(result);

            System.out.println("==============================");
            String str = "{\"msg_id\":\"20240611105747.11\",\"service_name\":\"query_price\",\"sign\":\"BFB8B3D593E7BD25999A1FCA36F4C862\",\"token\":\"f77c0a18775e41a3bf98c16b3dc45008\",\"data\":{\"service_name\":\"query_price\",\"data_target\":\"bolink\",\"errmsg\":\"查到啦\",\"state\":1,\"park_id\":\"36406\",\"order_id\":\"A1_2C1717754731\",\"price\":\"0.01\",\"duration\":5332,\"free_out_time\":100,\"query_time\":1718074667,\"is_report\":0,\"empty_plot\":100,\"derate_money\":\"0.0\",\"derate_duration\":\"0.0\",\"total\":\"0.01\",\"query_order_no\":\"3640620240611105746ep-282\",\"discount_info\":{\"member_id\":\"100000002\",\"use_type\":\"1\",\"limit_hour\":\"2\",\"limit_count\":\"1\",\"member_points_info\":{\"member_points\":\"10000\",\"points_per_hour\":\"100\"},\"coupon_list\":[{\"coupon_id\":\"1000002\",\"amount\":\"10\",\"unit\":\"1\"}]},\"isEscape\":0},\"messageId\":\"0e0f03cf-8740-459c-ba4f-57ea2cbb1a5a\",\"mqttMessageId\":\"0e0f03cf-8740-459c-ba4f-57ea2cbb1a5a\",\"tcpTopic\":\"/tcp/down/other/tcp_0607182540_10.42.0.152\",\"server_ip\":\"ps-tcp\",\"client_ip\":\"/10.123.4.14:44474\"}";
            JSONObject obj = JSONObject.parseObject(str);
            JSONObject data = obj.getJSONObject("data");
            String sign1 = obj.getString("sign");
            String uk = "5541BE4DCB5E8374";
            String _sign = MD5(JSON.toJSONString(data) + "key=" + uk);
            System.out.println(_sign);
            System.out.println(sign1);
        } catch (Exception e) {
            // TODO Auto-generated catch block    8A9957DB72608427
            e.printStackTrace();
        }
    }


    private static void prepay() {
        try {
            HashMap<String, Object> parammap = new HashMap<String, Object>();
            //-------------  park-------------------
            //hxl park
//		String parkId = "123456";//28018//24063//20180427//GY2020032701////HU2020080603//GY2020072701//GY2020122602 //zzj 200604
//			String parkId = "park2020010921";GY2020072701// GY2020122602//GY2020112001 gyls进件1（小微）--分账车场
//			String parkId = "dlytest_no";
//			String parkId = "kytest2020032501";

            //zq park
//			String parkId = "21798";
            // ly park
//			String parkId = "21845";
//			String parkId = "22146";

            //乐刷灵活分帐//btls泊链渠道个人代理15(GY2021042507)作为分账方，分账给btls泊链渠道对公法人代理16(GY2021042508)  乐刷商户号  8118818791
//			线上bl渠道个人6(GY2021041610)  6216819095  分账给GY2021042002 线上bl渠道个人8   0117811314			
            //String parkId = "wk123456";//k2022052501//wk123456//车场名称 btls泊链渠道对公非法人代理17 车场编号 GY2021042509车场 没设置分账  //zzj 1838783994994//zzj正式 18600537647
            //--------------union----------------
            //hxl union
//			Long unionId = 200568L;//hxy测试厂商200568
//		    Long unionId = 200541L;//200541  GY测试厂商SDK  D1F3365A31EF5F6B
//			Long unionId = 200461L;
            //zqunion
//			Long unionId = 200185L;

            //ly union
//			Long unionId = 200279L;
            //200200
//			Long unionId = 200200L;

            //乐刷灵活分帐
            //Long unionId =201060L;//200695 //201060//zzj 200604//zzj正式200929

            //---------------key-------------
            //hxl key
//			String key = "7B315B5A5350500E";//hxy测试厂商  7B315B5A5350500E
//		    String key = "D1F3365A31EF5F6B";////200541  GY测试厂商SDK  D1F3365A31EF5F6B
//			String key = "4E4B387623962F64";
//			String key = "5E4DEB8FBF3EC50F";
            //zq key
//			String key = "BDF96C62DDD12ECB";

            //ly key
//			String key = "B5D778E995CAB2F6";
            //200200 key
//			String key = "CDD274DC0ACAADD2";
            //乐刷灵活分帐
            //String key = "4C11CA156F88F745";//6A2CE3ACE764DACE//4C11CA156F88F745//200159  C160955B1479621F    //hxy 7B315B5A5350500E   CFE95B01232DD942
//			Long unionId = 200279L;
//			String parkId = "kytest2020032501";
//			String key = "4E4B387623962F64";
            Long unionId = 200568L;//200568  200776  20000003
            String parkId = "123456";//123456  123  hxy2022072202
            String key = "7B315B5A5350500E";//7B315B5A5350500E  CFE95B01232DD942  D9DBD246E315170B
            parammap.put("park_id", parkId);
            parammap.put("trade_no", "2022621000000001" + System.currentTimeMillis());
            parammap.put("amount", "0.01");
            parammap.put("wx_open_id", "o_Kpe0nuBvLMdbDXjNxi-cDuyb2M");
            parammap.put("title", "临停缴费停车费测试车场");
            parammap.put("order_id", "test202202032200a2");
//			parammap.put("order_id","liu20210513_hul-abcapi-14");
            parammap.put("pay_type", 0);//支付类型 0扫码支付，1微信公众号支付，2扫码枪支付，默认0
            parammap.put("channel", "alipay");//weixin   /  alipay
            parammap.put("auth_code", "130263439660196093");


//			parammap.put("order_id","liu20210519_hul-abcapi-06");
            parammap.put("wx_app_id", "wx78e05882cbaf38c1");
            parammap.put("car_number", "新Z00001");
            parammap.put("in_time", 1655692629);//直接跳转我们地址会报错，地址后面拼接?a=1后就能正常跳转了。
            parammap.put("callback_url", "https://www.bolink.club/?a=1");//https://www.bolink.club/  //https://ssp.lejuliang.com/?a=1
//			parammap.put("order_id","gy-LTJF2020081206");
            //parammap.put("notify_url","http://106.75.7.56:8081/test_bolinks/callparkapi/editorder");
            //乐刷灵活分帐
//			parammap.put("server_id",800250L);//800416//小雨服务商2 800177//
//			parammap.put("split_amount","0.03");
//			parammap.put("is_separate_accounts",1);//0代表不分帐，1代表分帐

            parammap.put("time_temp", System.currentTimeMillis() / 1000);
            HashMap<String, Object> json = new HashMap<String, Object>();
            json.put("union_id", unionId);

            String sign = MD5(JSON.toJSONString(parammap) + "key=" + key);
            json.put("sign", sign.toUpperCase());
            json.put("data", parammap);
            String params = JSON.toJSONString(json);
            System.out.println(params);

            String result = HttpClientUtil.post(url1, params, "application/json", "utf-8", 10000, 10000);
            System.out.println(result);
        } catch (Exception e) {
            // TODO Auto-generated catch block    8A9957DB72608427
            e.printStackTrace();
        }
    }

    private static void monthPay() {
        try {
            HashMap<String, Object> parammap = new HashMap<String, Object>();
            parammap.put("park_id", "223355");//28018//24063//20180427// GY2020122602//GY2020112001 gyls进件1（小微）--分账车场     //hxy测试123456    正式123
            parammap.put("car_number", "晋F11111");
            parammap.put("trade_no", "20210202" + System.currentTimeMillis());
            parammap.put("amount", "0.01");
            parammap.put("pay_channel", "weixin");//weixin , ali
            parammap.put("wx_open_id", "o_Kpe0nuBvLMdbDXjNxi-cDuyb2M");
            parammap.put("card_id", "200577");//123123202104276
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("", "");
            parammap.put("attach", jsonObject);
            parammap.put("back_url", "http://1029.parkingos.club?a=1/");
            parammap.put("time_temp", System.currentTimeMillis() / 1000);

            HashMap<String, Object> json = new HashMap<>();
            json.put("union_id", 201018);
            String sign = MD5(JSON.toJSONString(parammap) + "key=" + "06BE6FCB92A64961");

            json.put("sign", sign.toUpperCase());
            json.put("data", parammap);
            String params = JSON.toJSONString(json);
            System.out.println(params);
            String result = HttpClientUtil.post("http://127.0.0.1:8080/unionapi/paymonthcard", params, "application/json", "utf-8", 10000, 10000);
            System.out.println(result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*
        快捷支付
     */
    private static void payorder() {
        try {

            HashMap<String, Object> parammap = new HashMap<String, Object>();
            parammap.put("order_id", "A1_2C1660804800");//进场订单号
            parammap.put("in_time", System.currentTimeMillis() / 1000);//进场时间
            parammap.put("plate_number", "陕A00009");
            parammap.put("price", "0.01");
            parammap.put("duration", 10);
            parammap.put("is_prepay", 0);//预付 1    直付 0
            parammap.put("time_temp", System.currentTimeMillis() / 1000);
            parammap.put("free_out_time", "100");//分钟
            parammap.put("notify_url", "//http://106.75.7.56:8081/test_bolink/callparkapi/querycurrorder");//http://106.75.7.56:8081/test_bolink/callparkapi/querycurrorder

            //		parammap.put("park_id","whjj1");
            parammap.put("park_id", "123");// 123456   123
            //		parammap.put("park_id","GY2021042512");//雪莲
            //		parammap.put("park_id","123456");//线上

            HashMap<String, Object> json = new HashMap<String, Object>();
            //		String sign = MD5( JSON.toJSONString(parammap)+"key="+"549CB87F6ED853D9");//7B315B5A5350500E
            String sign = MD5(JSON.toJSONString(parammap) + "key=" + "CFE95B01232DD942");//新玉  7B315B5A5350500E  CFE95B01232DD942
            //		String sign = MD5( JSON.toJSONString(parammap)+"key="+"C160955B1479621F");//雪莲
            //		String sign = MD5( JSON.toJSONString(parammap)+"key="+"D95EDE55502FA764");//线上

            json.put("sign", sign.toUpperCase());
            json.put("data", parammap);
            String params = JSON.toJSONString(json);
            System.out.println(params);
            String dastr = BASE64.encode(params.getBytes("utf-8"));
            String url = "http://s.bolink.club/unionapi/payorder";
            url = url + "?union_id=200776&data=" + URLEncoder.encode(dastr);  //厂商编号
            System.out.println(url);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 生成MD5
     */
    public static String MD5(String s) {
        //System.err.println(s);
        try {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.reset();
            byte abyte0[] = messagedigest.digest(s.getBytes("utf-8"));
            return byteToString(abyte0);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String byteToString(byte abyte0[]) {
        int i = abyte0.length;
        char ac[] = new char[i * 2];
        int j = 0;
        for (int k = 0; k < i; k++) {
            byte byte0 = abyte0[k];
            ac[j++] = hexDigits[byte0 >>> 4 & 0xf];
            ac[j++] = hexDigits[byte0 & 0xf];
        }

        return new String(ac);
    }

    private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


}
