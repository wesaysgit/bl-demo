package com.bolink.aliagreement;

import lombok.Data;

import java.io.Serializable;

@Data
public class AgreementPayRefundReq implements Serializable {
    private static final long serialVersionUID = 3824671833531336580L;

    private String outTradeNo;
    private String refundAmount;
    private String refundReason;
    private String outRequestNo;

}
