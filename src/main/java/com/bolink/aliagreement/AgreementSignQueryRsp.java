package com.bolink.aliagreement;

import lombok.Data;

@Data
public class AgreementSignQueryRsp extends AliAgreementBaseRsp {

    private static final long serialVersionUID = -3044014598498995359L;

    private String externalAgreementNo;
    private String status;
    private String validTime;
    private String alipayLogonId;
    private String invalidTime;
    private String agreementNo;
    private String signTime;

}
