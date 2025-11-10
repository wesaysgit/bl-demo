package com.es;

public class ClientDetector {
    public static boolean isIosClient(String userAgent) {
        if (userAgent == null) {
            return false;
        }
        // 正则表达式匹配iOS设备的User-Agent字符串
        return userAgent.matches(".*(iPad|iPhone|iPod).*");
    }

    public static boolean isAndroidClient(String userAgent) {
        if (userAgent == null) {
            return false;
        }
        // 正则表达式匹配iOS设备的User-Agent字符串
        return userAgent.matches(".*(Android).*");
    }

    public static void main(String[] args) {
        // 假设从某种方式获取到User-Agent
        String userAgent = "Mozilla/5.0 (iPhone; CPU Android OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1";
 
        boolean isIOS = isAndroidClient(userAgent);
        System.out.println("Is iOS Client: " + isIOS);
    }
}