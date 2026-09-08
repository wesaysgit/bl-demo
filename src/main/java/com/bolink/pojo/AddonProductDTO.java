package com.bolink.pojo;

import lombok.Data;

/**
 * 加购详情DTO
 */
@Data
public class AddonProductDTO {

    /** 加购商品ID */
    private String productId;
    /** 加购商品类型 */
    private String productType;
    /** 加购金额 */
    private Double productAmount;
    /** 立减优惠券编码 */
    private String disCountCoupon;
    /** 立减金额 */
    private Double discountAmount;
    /** 是否使用立减 */
    private Boolean hasDiscount = false;
    /** 加购小程序/公众号UnionId */
    private String addonWxUnionId;
    /** 支付小程序/公众号appId */
    private String payAppId;
    /** 加购小程序/公众号appid */
    private String addonAppId;
    /** 加购小程序/公众号openid */
    private String addonOpenId;
    /** 分账接收方 */
    private ProfitReceiverDTO profitReceiver;

    /**
     * 分账接收方
     */
    @Data
    public static class ProfitReceiverDTO {
        /** 厂商编号 */
        private Long unionId;
        /** 服务商编号 */
        private Long serverId;
        /** 车场编号 */
        private String parkId;
        /** 平台类型2-厂商；3-服务商；4-车场 */
        private Integer platformType;
    }
}