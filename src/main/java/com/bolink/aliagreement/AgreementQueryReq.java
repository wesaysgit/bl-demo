package com.bolink.aliagreement;

import lombok.Data;

import java.io.Serializable;

@Data
public class AgreementQueryReq implements Serializable {

    private static final long serialVersionUID = 6325632458715308734L;

    /** 合作方签约单号 */
    private String externalAgreementNo;

}
