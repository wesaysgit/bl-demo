package com.es.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 平安结算DTO
 */
@Data
public class PingAnSettDTO implements Serializable {

    private static final long serialVersionUID = -2875555743174487803L;

    /** 原支付子订单号 */
    private String oldPaySubOrderNo;
    /** 原支付订单所属日期 */
    private String oldPayOrderDate;
    /** 分账转出子账户 */
    private String outSubAcctNo;
    /** 总金额 */
    private String tranTotalAmt;
    /** 订单数量 */
    private String ordersCount;
    /** appid */
    private String appid;
    /** 支付交易号 */
    private String outTradeNo;
    /** 三方交易号 */
    private String thirdTradeNo;
    /** 备注 */
    private String remark;
    /** paas追缴 */
    private Integer paasHermes;
    /** 预约停车 */
    private Integer parkingLot;
    /** 转入方的交易网会员代码 */
    private String inMemberCode;
    /** 分账详情 */
    private List<ParkingLotProfitDetail> profitDetails;
    /** 手续费分账单号 */
    private String feeSubAcctOrderNo;
    /** 分账请求参数 */
    private List<TranItemAccountReq> tranItemArray;
}
