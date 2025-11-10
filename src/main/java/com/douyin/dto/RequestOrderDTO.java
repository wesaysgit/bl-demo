package com.douyin.dto;

import lombok.Data;

import java.util.List;

/**
 * 抖音下单请求参数DTO
 */
@Data
public class RequestOrderDTO {
    
    /**
     * 下单商品信息
     * 注意：目前只支持传入一项
     */
    private List<SkuInfo> skuList;
    
    /**
     * 外部订单号
     */
    private String outOrderNo;
    
    /**
     * 订单总金额
     * 单位：分
     */
    private Long totalAmount;
    
    /**
     * 支付超时时间
     * 单位秒，例如 300 表示 300 秒后过期
     * 不传或传 0 会使用默认值 300，不能超过48小时
     */
    private Integer payExpireSeconds;
    
    /**
     * 支付结果通知地址
     * 必须是 HTTPS 类型，传入后该笔订单将通知到此地址
     */
    private String payNotifyUrl;
    
    /**
     * 开发者自定义收款商户号
     */
    private String merchantUid;
    
    /**
     * 订单详情页
     */
    private OrderEntrySchema orderEntrySchema;

    /**
     * 屏蔽的支付方式
     * 当开发者没有进件某个支付渠道，可在下单时屏蔽对应的支付方式
     * 如：[1, 2]表示屏蔽微信和支付宝
     * 枚举说明：1-微信 2-支付宝
     */
    private List<Integer> limitPayWayList;
    
    /**
     * 商品信息
     */
    @Data
    public static class SkuInfo {
        
        /**
         * 外部商品id
         * 如：号卡商品id、会员充值套餐id、某类服务id、付费工具id等
         */
        private String skuId;
        
        /**
         * 价格
         * 单位：分
         */
        private Long price;
        
        /**
         * 购买数量
         * 0 < quantity <= 100
         */
        private Integer quantity;
        
        /**
         * 商品标题
         * 长度限制：1-100字符
         */
        private String title;
        
        /**
         * 商品图片列表
         * 最多5张图片
         */
        private List<String> imageList;
        
        /**
         * 商品类型
         * 枚举说明：401-虚拟商品 402-实物商品
         */
        private Integer type;
        
        /**
         * 标签组id
         * 用于商品分类
         */
        private String tagGroupId;
    }
    
    /**
     * 订单详情页Schema
     */
    @Data
    public static class OrderEntrySchema {
        
        /**
         * 页面路径
         */
        private String path;
        
        /**
         * 页面参数
         * JSON字符串格式
         */
        private String params;
    }
}
