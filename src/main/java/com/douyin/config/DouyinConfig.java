package com.douyin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 抖音配置类
 */
@Data
@Component
@ConfigurationProperties(prefix = "douyin")
public class DouyinConfig {
    
    /**
     * 应用appid
     */
    private String appId;
    
    /**
     * 应用私钥
     */
    private String privateKey;
    
    /**
     * 平台公钥（用于验签）
     */
    private String platformPublicKey;
    
    /**
     * 公钥版本
     */
    private String keyVersion = "1";
    
    /**
     * 支付结果通知地址
     */
    private String payNotifyUrl;
    
    /**
     * 默认支付超时时间（秒）
     */
    private Integer defaultPayExpireSeconds = 300;
    
    /**
     * 是否启用签名验证
     */
    private Boolean enableSignatureVerification = true;
    
    /**
     * 应用密钥
     */
    private String appSecret;
    
    /**
     * 订单查询超时时间（毫秒）
     */
    private Long orderQueryTimeout = 10000L;
    
    /**
     * 是否启用订单查询日志
     */
    private Boolean enableOrderQueryLog = true;
}
