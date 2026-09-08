package com.bolink.pojo;

/**
 * 业务类型枚举
 */
public enum UnifiedBizTypeEnum {

    /**
     * 0-停车缴费(def)
     */
    PARKING_PAYMENT(0, "停车缴费(def)"),

    /**
     * 1-机场预约
     */
    AIRPORT_RESERVATION(1, "机场预约"),

    /**
     * 2-PAAS追缴订单
     */
    PAAS_RECOVER_ORDER(2, "PAAS追缴订单"),

    /**
     * 3-三方追缴(分润)
     */
    THIRD_PARTY_RECOVER(3, "三方追缴(分润)"),

    /**
     * 4-车位优选
     */
    PARKING_SPACE_OPTIMIZATION(4, "车位优选"),

    /**
     * 5-商业咨询服务
     */
    BUSINESS_CONSULTING(5, "商业咨询服务");

    /**
     * 业务编码
     */
    private final Integer code;

    /**
     * 业务描述
     */
    private final String desc;

    /**
     * 构造方法
     */
    UnifiedBizTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据code获取枚举
     */
    public static UnifiedBizTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (UnifiedBizTypeEnum typeEnum : UnifiedBizTypeEnum.values()) {
            if (typeEnum.getCode().equals(code)) {
                return typeEnum;
            }
        }
        return null;
    }

    // getter
    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}