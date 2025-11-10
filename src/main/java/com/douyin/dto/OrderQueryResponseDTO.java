package com.douyin.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * 抖音订单查询响应DTO
 * 根据抖音官方文档实际响应格式定义
 */
@Data
public class OrderQueryResponseDTO {
    
    /**
     * 日志ID
     */
    @JSONField(name = "log_id")
    private String logId;
    
    /**
     * 错误码
     * 0表示成功
     */
    @JSONField(name = "err_no")
    private Integer errNo;
    
    /**
     * 错误信息
     */
    @JSONField(name = "err_msg")
    private String errMsg;
    
    /**
     * 订单数据
     */
    @JSONField(name = "data")
    private OrderData data;
    
    /**
     * 订单数据详情
     */
    @Data
    public static class OrderData {
        
        /**
         * 抖音开放平台侧订单id
         */
        @JSONField(name = "order_id")
        private String orderId;
        
        /**
         * 开发者系统生成的订单号
         */
        @JSONField(name = "out_order_no")
        private String outOrderNo;
        
        /**
         * 应用ID
         */
        @JSONField(name = "app_id")
        private String appId;
        
        /**
         * 支付状态
         * "SUCCESS"：支付成功
         * "PROCESSING"：处理中
         * "FAIL"：支付失败
         */
        @JSONField(name = "pay_status")
        private String payStatus;
        
        /**
         * 订单总金额
         * 单位：分
         */
        @JSONField(name = "total_amount")
        private Long totalAmount;
        
        /**
         * 订单总金额（币种金额）
         * 单位：分
         */
        @JSONField(name = "total_currency_amount")
        private Long totalCurrencyAmount;
        
        /**
         * 币种
         */
        @JSONField(name = "currency")
        private String currency;
        
        /**
         * 订单优惠金额
         * 单位：分
         */
        @JSONField(name = "discount_amount")
        private Long discountAmount;
        
        /**
         * 支付渠道
         * 1:微信 2:支付宝 10:抖音支付 20:抖音钻石
         */
        @JSONField(name = "pay_channel")
        private Integer payChannel;
        
        /**
         * 渠道支付单号
         * 如微信/支付宝的支付单号
         */
        @JSONField(name = "channel_pay_id")
        private String channelPayId;
        
        /**
         * 该笔交易的卖家商户号
         */
        @JSONField(name = "merchant_uid")
        private String merchantUid;
        
        /**
         * 真实商户号
         */
        @JSONField(name = "real_merchant_uid")
        private String realMerchantUid;
        
        /**
         * 对应用户抖音账单里的"支付单号"
         */
        @JSONField(name = "user_bill_pay_id")
        private String userBillPayId;
        
        /**
         * 交易时间戳
         * 单位为毫秒
         */
        @JSONField(name = "trade_time")
        private Long tradeTime;
        
        /**
         * 订单支付时间戳
         * 单位为毫秒
         */
        @JSONField(name = "pay_time")
        private Long payTime;
        
        /**
         * 商品订单列表
         */
        @JSONField(name = "item_order_list")
        private List<ItemOrder> itemOrderList;
        
        /**
         * 促销信息列表
         */
        @JSONField(name = "promotion_list")
        private List<Promotion> promotionList;
        
        /**
         * 商品订单信息
         */
        @Data
        public static class ItemOrder {
            
            /**
             * 商品订单ID
             */
            @JSONField(name = "item_order_id")
            private String itemOrderId;
            
            /**
             * 商品ID
             */
            @JSONField(name = "sku_id")
            private String skuId;
            
            /**
             * 商品订单金额
             * 单位：分
             */
            @JSONField(name = "item_order_amount")
            private Long itemOrderAmount;
            
            /**
             * 商品订单币种金额
             * 单位：分
             */
            @JSONField(name = "item_order_currency_amount")
            private Long itemOrderCurrencyAmount;
        }
        
        /**
         * 促销信息
         */
        @Data
        public static class Promotion {
            
            /**
             * 促销ID
             */
            @JSONField(name = "promotion_id")
            private String promotionId;
            
            /**
             * 促销金额
             */
            @JSONField(name = "promotion_amount")
            private Long promotionAmount;
        }
    }
    
    /**
     * 判断是否成功
     */
    public boolean isSuccess() {
        return errNo != null && errNo == 0;
    }
    
    /**
     * 创建成功响应
     */
    public static OrderQueryResponseDTO success(OrderData orderData) {
        OrderQueryResponseDTO response = new OrderQueryResponseDTO();
        response.setErrNo(0);
        response.setErrMsg("");
        response.setData(orderData);
        return response;
    }
    
    /**
     * 创建错误响应
     */
    public static OrderQueryResponseDTO error(Integer errNo, String errMsg) {
        OrderQueryResponseDTO response = new OrderQueryResponseDTO();
        response.setErrNo(errNo);
        response.setErrMsg(errMsg);
        return response;
    }
    
    /**
     * 创建错误响应（简化版）
     */
    public static OrderQueryResponseDTO error(String errMsg) {
        return error(-1, errMsg);
    }
}
