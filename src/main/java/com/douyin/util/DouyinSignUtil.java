package com.douyin.util;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 抖音签名工具类
 * 提供抖音支付相关的签名生成和验证方法
 */
public class DouyinSignUtil {

    /**
     * 请求信息类
     */
    public static class ReqInfo {
        public String body;
        public String timestamp;
        public String nonce;
        public String signature;
    }

    private static final Logger logger = Logger.getLogger(DouyinSignUtil.class);

    /**
     * 生成签名
     * 
     * @param privateKeyStr 私钥字符串
     * @param method HTTP方法
     * @param timestamp 时间戳
     * @param nonce 随机字符串
     * @param data 请求数据
     * @return 签名字符串
     */
    public static String generateSignature(String privateKeyStr, String method, String uri,
                                           long timestamp, String nonce, String data) {
        try {
            // 构造待签名串
            String rawStr = method + "\n" +
                    uri + "\n" +
                    timestamp + "\n" +
                    nonce + "\n" +
                    data + "\n";

            logger.debug("待签名串: " + rawStr);
            System.out.println("待签名串: \n" + rawStr);

            // 使用SHA256withRSA算法签名
            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(string2PrivateKey(privateKeyStr));
            sign.update(rawStr.getBytes(StandardCharsets.UTF_8));

            // Base64编码
            return Base64.getEncoder().encodeToString(sign.sign());
        } catch (Exception e) {
            logger.error("生成签名失败", e);
        }
        return null;
    }

    /**
     * 将字符串转换为私钥
     *
     * @param privateKeyStr 私钥字符串
     * @return 私钥对象
     */
    public static PrivateKey string2PrivateKey(String privateKeyStr) {
        try {
            // 移除换行符
            String cleanPrivateKeyStr = privateKeyStr.replace("\n", "");
            // Base64解码
            byte[] privateBytes = Base64.getDecoder().decode(cleanPrivateKeyStr);
            // 创建PKCS8EncodedKeySpec
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateBytes);
            // 生成私钥
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            logger.error("转换私钥失败", e);
        }
        return null;
    }

    /**
     * 生成byteAuthorization
     * 格式: app_id="appId",nonce_str="nonceStr",timestamp="timestamp",key_version="keyVersion",signature="signature"
     */
    public static String generateByteAuthorization(String appId, String nonceStr,
                                                   long timestamp, String keyVersion, String signature) {
        return String.format(
                "SHA256-RSA2048 appid=%s,nonce_str=%s,timestamp=%d,key_version=%s,signature=%s",
                appId, nonceStr, timestamp, keyVersion, signature
        );
    }
    
    /**
     * 验证签名
     * 
     * @param publicKeyStr 公钥字符串
     * @param method HTTP方法
     * @param timestamp 时间戳
     * @param nonce 随机字符串
     * @param data 请求数据
     * @param signature 签名
     * @return 验证结果
     */
    public static boolean verifySignature(String publicKeyStr, String method, String uri,
                                          long timestamp, String nonce, String data, String signature) {
        try {
            // 构造待验证串
            String rawStr = method + "\n" +
                    uri + "\n" +
                    timestamp + "\n" +
                    nonce + "\n" +
                    data + "\n";

            logger.debug("待验证串: " + rawStr);

            // 使用SHA256withRSA算法验证
            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initVerify(string2PublicKey(publicKeyStr));
            sign.update(rawStr.getBytes(StandardCharsets.UTF_8));

            return sign.verify(Base64.getDecoder().decode(signature));
        } catch (Exception e) {
            logger.error("验证签名失败", e);
            return false;
        }
    }

    /**
     * 将字符串转换为公钥
     *
     * @param publicKeyStr 公钥字符串
     * @return 公钥对象
     */
    public static PublicKey string2PublicKey(String publicKeyStr) {
        try {
            byte[] decoded = Base64.getDecoder().decode(publicKeyStr);
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        } catch (Exception e) {
            logger.error("转换公钥失败", e);
            throw new RuntimeException("转换公钥失败", e);
        }
    }

    /**
     * 解析HTTP请求信息
     *
     * @param request HTTP请求
     * @return 请求信息
     */
    public static ReqInfo resolveRequest(HttpServletRequest request) {
        logger.info("receive payNotify");

        String body = getRequestBodyParamsStr(request);
        String timestamp = request.getHeader("Byte-Timestamp");
        String nonce = request.getHeader("Byte-Nonce-Str");
        String signature = request.getHeader("Byte-Signature");

        logger.info("body: " + body + "timestamp: " + timestamp + ", nonce: " + nonce + ", signature: " + signature);

        ReqInfo reqInfo = new ReqInfo();
        reqInfo.body = body;
        reqInfo.timestamp = timestamp;
        reqInfo.nonce = nonce;
        reqInfo.signature = signature;

        return reqInfo;
    }

    /**
     * 获取请求体参数字符串
     *
     * @param request HTTP请求
     * @return 请求体字符串
     */
    public static String getRequestBodyParamsStr(HttpServletRequest request) {
        BufferedReader br;
        String str;
        StringBuilder wholeStr = new StringBuilder();
        try {
            br = request.getReader();
            while ((str = br.readLine()) != null) {
                wholeStr.append(str);
            }
            wholeStr = new StringBuilder(wholeStr.toString().replaceAll(" ", ""));
            logger.info("请求体内容: " + wholeStr);
        } catch (IOException e) {
            logger.error("读取请求体失败", e);
        }
        return wholeStr.toString();
    }

    /**
     * 验证回调签名（便捷方法）
     *
     * @param reqInfo 通过resolveReq方法解析出来的请求体数据
     * @param publicKey 平台公钥，注意验签应使用平台公钥，而非应用公钥
     * @return 验签结果
     */
    public static boolean verifyCallbackSignature(ReqInfo reqInfo, String publicKey) {
        try {
            return verifySignature(
                    publicKey,
                    "POST",
                    "/notify",
                    Long.parseLong(reqInfo.timestamp),
                    reqInfo.nonce,
                    reqInfo.body,
                    reqInfo.signature
            );
        } catch (Exception e) {
            logger.error("验签失败", e);
            return false;
        }
    }

}