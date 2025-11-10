package com.douyin.dto;

import lombok.Data;

/**
 * 抖音下单签名授权DTO
 */
@Data
public class ByteAuthorizationDTO {
    
    /**
     * 应用appid
     */
    private String appId;
    
    /**
     * 请求随机串
     */
    private String nonceStr;
    
    /**
     * 请求时间戳
     */
    private Long timestamp;
    
    /**
     * 公钥版本
     * 每次重新上传公钥后需要更新
     */
    private String keyVersion;
    
    /**
     * 签名值
     */
    private String signature;
    private String data;



    /**
     * 完整的byteAuthorization字符串
     * 格式：SHA256-RSA2048 appid=xxx,nonce_str=xxx,timestamp=xxx,key_version=xxx,signature=xxx
     */
    private String byteAuthorization;
}
