package com.bolink.aliagreement;

import lombok.Data;

import java.io.Serializable;

@Data
public class AgreementUnSignReq implements Serializable {

    private static final long serialVersionUID = -4453459188566985786L;

    private String externalAgreementNo;

}
