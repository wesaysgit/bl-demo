package com.bolink.aliagreement;

import lombok.Data;

import java.io.Serializable;

@Data
public class AgreementPayReq implements Serializable {

    private static final long serialVersionUID = 8937333727334941227L;

    private String outTradeNo;
    private String totalAmount;
    private String subject;
    private String agreementNo;

}
