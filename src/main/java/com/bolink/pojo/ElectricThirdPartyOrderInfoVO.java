package com.bolink.pojo;


import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 充电业务-第三方支付虚拟账户支付订单
 *
 * @author houjt
 * @version 1.0.0 2024-09-11
 */
@Data
@ToString
public class ElectricThirdPartyOrderInfoVO implements java.io.Serializable {

    /**
     * 云平台订单号
     */
    private String outOrderId;

    /**
     * 厂商id
     */
    private Long unionId;

    /**
     * 电站名称
     */
    private String powerPlantName;

    /**
     * 电站id
     */
    private Long powerPlantId;


    /**
     * 充电开始时间
     */
    private String electricStartTime;

    /**
     * 充电结束时间
     */
    private String electricEndTime;

    /**
     * 电量
     */
    private BigDecimal electricQuantity;

    /**
     * 电费
     */
    private BigDecimal electricAmount;

    /**
     * 服务费
     */
    private BigDecimal serverAmount;

    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    /**
     * 占位费
     */
    private BigDecimal seatAmount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 分账明细
     */
//    private List<ElectricThirdPartySharingTbVO> sharingDetails;

    /**
     * 充电互联订单号
     */
    private String cecOrderNo;

    /**
     * 审核人
     */
    private String auditUser;

}