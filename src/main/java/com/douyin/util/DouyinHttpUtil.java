package com.douyin.util;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * 抖音HTTP工具类
 * 提供抖音支付相关的HTTP请求方法
 */
public class DouyinHttpUtil {

    private static final Logger logger = Logger.getLogger(DouyinHttpUtil.class);
    
    private static final int CONNECT_TIMEOUT = 30000; // 30秒
    private static final int READ_TIMEOUT = 60000; // 60秒
    private static final String CONTENT_TYPE = "application/json; charset=UTF-8";
    private static final String USER_AGENT = "DouyinPaySDK/1.0";

    /**
     * 发送POST请求
     * 
     * @param url 请求URL
     * @param data 请求数据
     * @param authorization 授权信息
     * @return 响应结果
     */
    public static String post(String url, String data, String authorization) {
        return post(url, data, authorization, null);
    }
    
    /**
     * 发送POST请求（支持access-token）
     * 
     * @param url 请求URL
     * @param data 请求数据
     * @param authorization 授权信息
     * @param accessToken 访问令牌（可选）
     * @return 响应结果
     */
    public static String post(String url, String data, String authorization, String accessToken) {
        HttpURLConnection connection = null;
        try {
            // 1. 创建连接
            URL requestUrl = new URL(url);
            connection = (HttpURLConnection) requestUrl.openConnection();
            
            // 2. 设置请求属性
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", CONTENT_TYPE);
//            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestProperty("Authorization", "ByteAuthorization " + authorization);
            
            // 如果提供了access-token，添加到请求头
            if (accessToken != null && !accessToken.isEmpty()) {
                connection.setRequestProperty("access-token", accessToken);
                logger.info("设置access-token请求头: " + accessToken);
            }
            
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(CONNECT_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);
            
            // 3. 发送请求数据
            if (data != null && !data.isEmpty()) {
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = data.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
            }
            
            // 4. 获取响应
            int responseCode = connection.getResponseCode();
            logger.info("HTTP响应码: " + responseCode);
            
            // 5. 读取响应数据
            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    responseCode >= 200 && responseCode < 300 ? 
                    connection.getInputStream() : connection.getErrorStream(),
                    StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }
            
            String result = response.toString();
            logger.info("HTTP响应内容: " + result);
            return result;
        } catch (Exception e) {
            logger.error("发送HTTP请求失败", e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }
    
    /**
     * 发送GET请求
     * 
     * @param url 请求URL
     * @param authorization 授权信息
     * @return 响应结果
     */
    public static String get(String url, String authorization) {
        HttpURLConnection connection = null;
        try {
            // 1. 创建连接
            URL requestUrl = new URL(url);
            connection = (HttpURLConnection) requestUrl.openConnection();
            
            // 2. 设置请求属性
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestProperty("Authorization", "ByteAuthorization " + authorization);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(CONNECT_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);
            
            // 3. 获取响应
            int responseCode = connection.getResponseCode();
            logger.info("HTTP响应码: " + responseCode);
            
            // 4. 读取响应数据
            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    responseCode >= 200 && responseCode < 300 ? 
                    connection.getInputStream() : connection.getErrorStream(),
                    StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }
            
            String result = response.toString();
            logger.info("HTTP响应内容: " + result);
            return result;
            
        } catch (Exception e) {
            logger.error("发送HTTP请求失败", e);
            throw new RuntimeException("发送HTTP请求失败: " + e.getMessage(), e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    
    /**
     * 检查HTTP响应是否成功
     * 
     * @param responseCode HTTP响应码
     * @return 是否成功
     */
    public static boolean isSuccess(int responseCode) {
        return responseCode >= 200 && responseCode < 300;
    }
    
    /**
     * 获取错误信息
     * 
     * @param responseCode HTTP响应码
     * @return 错误信息
     */
    public static String getErrorMessage(int responseCode) {
        switch (responseCode) {
            case 400:
                return "请求参数错误";
            case 401:
                return "未授权，请检查签名";
            case 403:
                return "禁止访问";
            case 404:
                return "请求的资源不存在";
            case 500:
                return "服务器内部错误";
            case 502:
                return "网关错误";
            case 503:
                return "服务不可用";
            default:
                return "未知错误，响应码: " + responseCode;
        }
    }
}
