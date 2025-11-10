package com.douyin.dto;

import lombok.Data;

/**
 * 抖音支付回调消息内容DTO
 */
@Data
public class PaymentCallbackMsgDTO {
    
    /**
     * 小程序app_id
     */
    private String appId;
    
    /**
     * 开发者系统生成的订单号
     * 与抖音开放平台交易单号order_id唯一关联，长度 <= 64byte
     */
    private String outOrderNo;
    
    /**
     * 抖音开放平台侧订单id
     * 长度 <= 64byte
     */
    private String orderId;
    
    /**
     * 支付结果状态
     * "SUCCESS"（支付成功）"CANCEL"（支付取消）
     */
    private String status;
    
    /**
     * 订单总金额
     * 单位分，支付金额为 = total_amount - discount_amount
     */
    private Long totalAmount;
    
    /**
     * 订单优惠金额
     * 单位分，接入营销时请关注这个字段
     */
    private Long discountAmount;
    
    /**
     * 支付渠道枚举（支付成功时才有）
     * 1:微信 2:支付宝 10:抖音支付 20:抖音钻石
     */
    private Integer payChannel;
    
    /**
     * 渠道支付单号
     * 如微信/支付宝的支付单号，长度 <= 64byte
     * 注：status="SUCCESS"时一定有值
     */
    private String channelPayId;
    
    /**
     * 该笔交易的卖家商户号
     * 注：status="SUCCESS"时一定有值
     */
    private String merchantUid;
    
    /**
     * 该笔交易取消原因
     * 如："USER_CANCEL"：用户取消"TIME_OUT"：超时取消
     */
    private String message;
    
    /**
     * 用户支付成功/支付取消时间戳
     * 单位为毫秒
     */
    private Long eventTime;
    
    /**
     * 对应用户抖音账单里的"支付单号"
     * 注：status="SUCCESS"时一定有值
     */
    private String userBillPayId;
}
