package com.es.xinxiang;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CyTest {

    public static void main(String[] args) throws Exception {

        String sign = "4E2EBE1E21C0AD7E18F82245C0AB5FE9";
        String uk = "4F80BF35DF83858D";
        String ss = "{\"total\":\"0.01\",\"in_time\":1658996957,\"out_channel_id\":\"4\",\"car_number\":\"云A627AA\",\"license_color\":4,\"inpark_img\":\"https://www.flowerplus.cn/\",\"parking_time\":\"120\",\"order_id\":\"order1683539328300\",\"total_original\":\"0.01\",\"outpark_img\":\"https://imgl0.xx.cn/?host=20.2.10.221&url=/c/200217/mIM1vUfkZuKH2tFjpeb.jpga\",\"park_id\":\"36286\",\"pay_type\":\"hm\",\"lane_no\":\"TEST20220823\",\"out_time\":1683539328,\"in_channel_id\":\"3\"}key=7B315B5A5350500E";
        String sign3 = MD5(ss + "key=" + uk);
        String sign4 = StringUtils.MD5(ss);
        System.out.println(sign3);
        System.out.println(sign4);
        System.out.println(sign.equals(sign4.toUpperCase()));
    }

    private static  void md5() {
        //F7182A07C62EE89F
        HashMap<String, Object> parammap = new HashMap<String, Object>();
        parammap.put("union_id", 200779);
        parammap.put("park_id", "115599");
        parammap.put("type", 3);
        parammap.put("channel_id", "1");
        parammap.put("change", 1);

        String key = "F7182A07C62EE89F";
        Map<String, Object> json = new HashMap<>();
        String sign = MD5(JSON.toJSONString(parammap) + "key=" + key);
        json.put("sign", sign.toUpperCase());
        json.put("data", parammap);
        System.out.println(JSON.toJSONString(json));
    }

    private static String cyTest() throws Exception {
        HashMap<String, Object> parammap = new HashMap<String, Object>();
        parammap.put("rand", UUID.randomUUID().toString().replace("-", ""));
        parammap.put("union_id", 200712);
        parammap.put("park_id", "36342");
        parammap.put("plate_number", "湘AAAAAA");
        parammap.put("trade_stime", "20230201000000");
        parammap.put("trade_etime", "20230231235959");
        parammap.put("profit_stime", "20221201");
        parammap.put("profit_etime", "20221230");
        parammap.put("timestamp", System.currentTimeMillis() / 1000);

        // 加密key，厂商token
        String key = "4F80BF35DF83858D";
        Map<String, Object> json = new HashMap<>();
        String sign = MD5(JSON.toJSONString(parammap) + "key=" + key);
        json.put("sign", sign.toUpperCase());
        json.put("data", parammap);
        String params = JSON.toJSONString(json);
        System.out.println(params);
        String response = HttpUtil.post("https://beta.bolink.club/unionapi/cy/cyorder", JSON.toJSONString(params));
        System.out.println(response);
        return response;
    }

    /**
     * 生成MD5
     */
    public static String MD5(String s) {
        try {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.reset();
            byte[] abyte0 = messagedigest.digest(s.getBytes("utf-8"));
            return byteToString(abyte0);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
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
