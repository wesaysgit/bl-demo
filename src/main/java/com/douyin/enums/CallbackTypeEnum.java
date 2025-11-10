package com.douyin.enums;

/**
 * 抖音回调类型枚举
 */
public enum CallbackTypeEnum {
    
    /**
     * 支付回调
     */
    PAYMENT("payment", "支付回调"),
    
    /**
     * 退款回调
     */
    REFUND("refund", "退款回调"),
    
    /**
     * 履约回调
     */
    FULFILLMENT("fulfillment", "履约回调"),
    
    /**
     * 结算回调
     */
    SETTLEMENT("settlement", "结算回调");
    
    private final String code;
    private final String description;
    
    CallbackTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getDescription() {
        return description;
    }
    
    /**
     * 根据代码获取枚举
     * 
     * @param code 代码
     * @return 枚举值
     */
    public static CallbackTypeEnum fromCode(String code) {
        for (CallbackTypeEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
    
    /**
     * 判断是否为支持的回调类型
     * 
     * @param code 代码
     * @return 是否支持
     */
    public static boolean isSupported(String code) {
        return fromCode(code) != null;
    }
}
