package com.es.emcloud;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 支付回调参数 DTO
 * @Author  pengzhuo
 * @Date    2024/1/21
 */
@Data
@Accessors(chain = true)
public class PayCallParamDTO implements Serializable {
    private static final long serialVersionUID = -1141210125750246592L;

    /** 支付平台的商户id */
    private String mchId;

    /** 支付平台的渠道商户id */
    private String subMchId;

    /** 支付平台使用的支付渠道标识(ZFBZF/WXZF...), 可用下单表的pay_company字段转化得到 */
    private String payWay;

    // --------------------- 订单信息 --------------------------\\

    /** 支付订单交易号 */
    private String outTradeNo;

    /** 支付平台的商户订单号 */
    private String mchOrderId;

    /** 支付平台的渠道商户订单号 */
    private String subMchOrderId;

    /** 支付平台的渠道商户订单交易号 */
    private String subMchTradeNo;

   //...


}
