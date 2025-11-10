package com.es.esdemo.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class KfcAuthcodeOrderDTO implements Serializable {

    private static final long serialVersionUID = 3460312569869240139L;

    private static final int operateType = -1;

    private Long id;

    private Long ctime;

    private Long unionId;

    private String parkId;

    private BigDecimal amount;

    private String outTradeNo;

    private String returnData;

    private String requestData;

    private String callbackData;

    private String orderId;

    private Integer payState;

    private Integer payType;

    private Integer payChannel;

    private String addParams;

    private Integer orderType;

    private String plateNumber;

    private Integer payScene;

    private String outChannelId;

    private Integer isRoute;

    private Integer payCompany;

    private String thirdTradeNo;

    private String coupons;

    private Integer isCouponUser;

    private BigDecimal couponAmount;

    private Long couponUnionId;

    private Integer couponConsumeState;

    private BigDecimal deduction;

    private Long carUnionId;

}
