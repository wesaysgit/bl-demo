package com.es.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

@Data
public class TranItemAccountReq implements Serializable {

    private static final long serialVersionUID = 4521817245379744794L;

    /**
     * 分账子单号
     * 分账订单的子订单号
     */
    @JSONField(name = "SubAcctOrderNo")
    private String SubAcctOrderNo;
    /**
     * 分账转入子账户
     * 可与OutCustAcctId同值
     */
    @JSONField(name = "InSubAcctNo")
    private String InSubAcctNo;
    /**
     * 分账金额
     * 支付到分账转入方的金额
     */
    @JSONField(name = "SubAcctAmt")
    private String SubAcctAmt;
    /**
     * 分账订单描述
     * 描述订单的用途
     */
    @JSONField(name = "SubAcctOrderDesc")
    private String SubAcctOrderDesc;
    /**
     * 分账备注
     * 体现在分账对账文件中
     */
    @JSONField(name = "SubAcctRemark")
    private String SubAcctRemark;
    /**
     * 分账保留域
     *
     */
    @JSONField(name = "SubAcctReservedMsg")
    private String SubAcctReservedMsg;

}
