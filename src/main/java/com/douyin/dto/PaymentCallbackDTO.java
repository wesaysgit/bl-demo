package com.douyin.dto;

import lombok.Data;

/**
 * 抖音支付回调请求DTO
 */
@Data
public class PaymentCallbackDTO {
    
    /**
     * 订单相关信息的json字符串
     */
    private String msg;
    
    /**
     * 回调类型（支付结果回调为payment）
     * payment（支付成功/支付取消）
     */
    private String type;
    
    /**
     * 固定值："3.0"
     * 回调版本，用于开发者识别回调参数的变更
     */
    private String version;
}
