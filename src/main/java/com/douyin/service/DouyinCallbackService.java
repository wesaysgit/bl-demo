package com.douyin.service;

import com.douyin.dto.PaymentCallbackResponseDTO;

/**
 * 抖音回调服务接口
 * 定义各种回调事件的处理方法
 */
public interface DouyinCallbackService {
    
    /**
     * 处理支付回调
     * 
     * @param msgJson 回调消息JSON字符串
     * @return 处理结果
     */
    PaymentCallbackResponseDTO handlePaymentCallback(String msgJson);
    
    /**
     * 处理退款回调
     * 
     * @param msgJson 回调消息JSON字符串
     * @return 处理结果
     */
    PaymentCallbackResponseDTO handleRefundCallback(String msgJson);
    
    /**
     * 处理履约回调
     * 
     * @param msgJson 回调消息JSON字符串
     * @return 处理结果
     */
    PaymentCallbackResponseDTO handleFulfillmentCallback(String msgJson);
    
    /**
     * 处理结算回调
     * 
     * @param msgJson 回调消息JSON字符串
     * @return 处理结果
     */
    PaymentCallbackResponseDTO handleSettlementCallback(String msgJson);
}
