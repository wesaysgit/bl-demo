package com.bolink.aliagreement;

import lombok.Data;

@Data
public class AgreementPayQueryRsp extends AliAgreementBaseRsp {

    private static final long serialVersionUID = -858280343685387699L;

    private String tradeNo;
    private String outTradeNo;
    private String status;
    private String buyerLogonId;
    private String totalAmount;

}
