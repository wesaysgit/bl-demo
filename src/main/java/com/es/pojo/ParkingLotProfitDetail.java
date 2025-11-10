package com.es.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ParkingLotProfitDetail implements Serializable {

    private static final long serialVersionUID = 6503641720330995097L;

    /** 厂商编号 */
    private Long unionId;
    /** 服务商编号 */
    private Long serverId;
    /** 车场编号 */
    private String parkId;
    /** 平台类型2-厂商；3-服务商；4-车场 */
    private Integer platformType;
    /** 分润金额 */
    private Long amount;
    /** 到账金额 */
    private Long toMoney;
    /** 手续费 */
    private Long fee;
    /** 乐刷商户号 */
    private String shopId;
    /** 交易主体appid */
    private String appid;
    /** 是否钱包提现（业主身份才有 0-否；1-是） */
    private Integer wxWallet;
    /** 主体-名称（车场--xx;厂商-xx;...） */
    private String remark;
    /** 平台编号 */
    private Long platformId;
    /** 手续费溢出承担主体 */
    private Integer feeSubject;
    /** 子订单分账订单号 */
    private String subAcctOrderNo1;
    /** 转入方的交易网会员代码 */
    private String inMemberCode;;

}
