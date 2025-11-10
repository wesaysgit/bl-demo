package com.bolink.aliagreement;

import lombok.Data;

import java.io.Serializable;

@Data
public class AgreementPageSignReq implements Serializable {

    private static final long serialVersionUID = 8144712888343739646L;

    /** 首次执行时间 */
    private String executeTime;

    /** 周期数 */
    private Long period;

    /** 周期类型 DAY/MONTH */
    private String periodType;

    /** 单次扣款最大金额 */
    private String singleAmount;

    /** 总金额限制 */
    private String totalAmount;

    /** 总扣款次数 */
    private Long totalPayments;

    /** 合作方签约单号 */
    private String externalAgreementNo;

    /** 签约有效时间限制，单位是秒 */
    private Long effectTime;

    /** 签约成功后，跳转地址 */
    private String returnUrl;

    /**合作方接收回调通知地址(签约、支付、退款)*/
    private String noticeUrl;


}
