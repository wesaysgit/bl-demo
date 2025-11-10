package com.es.pojo;

import lombok.Data;

import java.util.List;

@Data
public class ZhimaScoreCreateOrderReq {

    private String leShuaMerchantId;
    private String buyerId;
    private String outAgreementNo;
    private String zmServiceId;
    private String deductSignScene;
    private List itemInfos;
    private String orderPrice;
    private String outOrderId;
    private String path;
    private String sourceId;
    private String merchantId;
    private String title;

}
