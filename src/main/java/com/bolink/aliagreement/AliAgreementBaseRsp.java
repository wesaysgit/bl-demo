package com.bolink.aliagreement;

import lombok.Data;

import java.io.Serializable;

@Data
public class AliAgreementBaseRsp  implements Serializable {

    private static final long serialVersionUID = -3032934602623086937L;

    private String code;

    private String msg;

    private String subMsg;
}
