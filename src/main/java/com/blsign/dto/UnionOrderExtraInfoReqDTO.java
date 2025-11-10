package com.blsign.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 获取订单拓展信息参数 DTO
 */
@Data
@Accessors(chain = true)
public class UnionOrderExtraInfoReqDTO implements Serializable {

    private static final long serialVersionUID = -1096630052611127606L;

    /** 交易号 */
    private String outTradeNo;

    /** 支付时间 */
    private Long payTime;

}
