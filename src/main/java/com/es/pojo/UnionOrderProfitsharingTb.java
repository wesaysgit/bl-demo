package com.es.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 分账交易信息 Entity
 */
@Data
@Accessors(chain = true)
public class UnionOrderProfitsharingTb implements Serializable {

    private static final long serialVersionUID = -1719828229910252839L;

    /** 主键ID */
    private Long id;
    /** 联盟ID */
    private Long unionId;
    /** 停车场ID */
    private String parkId;
    /** 微信单号 */
    private String transactionId;
    /** 商户分账单号 */
    private String outOrderNo;
    /** 分账金额 */
    private BigDecimal amount;
    /** 分账接收方-服务商id */
    private Long serverId;
    /** 分账接收商户id */
    private String accountNo;
    /** 分账回退单号 */
    private String outReturnNo;
    /** 商户支付订单号 */
    private String outTradeNo;
    /** 创建时间 */
    private Long createTime;
    /** 分账状态 0-待分账 1-完成分账  2-已分账回退 */
    private Integer state;
    /** 1-服务商分账 2-微信钱包通道分账 */
    private Integer type;
    /** 车场ID */
    private Long comId;
    /** 失败原因 */
    private String failReason;
    /** 重试次数 */
    private Integer retryTimes;
    /** 是否完结  1钱包分账完结 3，已营销转账 */
    private Integer finish;
    /** 子单号 */
    private String subOrders;
    /** 乐刷商户号 */
    private String shopId;
    /** 主单单号 */
    private Long pid;
} 