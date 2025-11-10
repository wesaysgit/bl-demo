package com.bolink.aliagreement;

import lombok.Data;

import java.io.Serializable;

@Data
public class AgreementPayQueryReq implements Serializable {

    private static final long serialVersionUID = 7576233825073289124L;

    private String outTradeNo;

}
