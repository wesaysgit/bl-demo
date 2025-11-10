package com.bolink;

import cn.hutool.core.util.XmlUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
public class WxQuery {

    public static void main(String[] args) {
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        packageParams.put("appid", "wx962fe9d5c0e2a2c7");
        packageParams.put("mch_id", "1493651012"); // 设置商户号
        packageParams.put("sub_mch_id", "1508858441"); // 设置子商户号
        packageParams.put("nonce_str", CreateNoncestr());
        packageParams.put("out_trade_no", "0202303271715464469095232"); // 商户订单号

        String sign = createSign("bolink20180603101600weixipay0000", "UTF-8", packageParams);
        packageParams.put("sign", sign);
        log.info("pay下单参数:" + packageParams);
        String requestXML = getRequestXml(packageParams);
        log.info("pay下单参数XML:" + requestXML);

        String queryorderurl = "https://api.mch.weixin.qq.com/pay/orderquery";
        String qryResult = httpsRequest(queryorderurl, "POST", requestXML);
        Map<String, Object> ret = XmlUtil.xmlToMap(qryResult);
        System.out.println(JSON.toJSONString(ret));

    }

    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        HttpsURLConnection conn = null;
        try{
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            //设置超时时间
            conn.setConnectTimeout(2000);
            conn.setReadTimeout(3000);
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("utf-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            try(InputStream inputStream = conn.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)){
                String str;
                StringBuilder builder = new StringBuilder();
                while ((str = bufferedReader.readLine()) != null) {
                    builder.append(str);
                }
                return builder.toString();
            }
        } catch (Exception e) {
            log.error("CommonUtil.httpsRequest error: "+ e.getMessage(), e);
        }finally {
            if(conn != null){
                conn.disconnect();
            }
        }
        return null;
    }

    public static String createSign(String appKey,String characterEncoding,SortedMap<Object,Object> parameters){
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            if(null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + appKey);
        //log.info("pay 下单签名串:"+sb.toString());
        String sign = DigestUtil.md5Hex(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }

    public static String getRequestXml(SortedMap<Object,Object> parameters){
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();
            if ("attach".equalsIgnoreCase(k)||"body".equalsIgnoreCase(k)||"sign".equalsIgnoreCase(k)) {
                sb.append("<"+k+">"+"<![CDATA["+v+"]]></"+k+">");
            }else {
                sb.append("<"+k+">"+v+"</"+k+">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    public static String CreateNoncestr() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String res = "";
        for (int i = 0; i < 16; i++) {
            Random rd = new Random();
            res += chars.charAt(rd.nextInt(chars.length() - 1));
        }
        return res;
    }

}
