package com.es.pojo;

import lombok.Data;

@Data
public class ZhimaScorePeriodOrdeReq {

    private String leShuaMerchantId;
    private String installmentNo;
    private String installmentPrice;
    private int isFinishPerformance;
    private String orderId;
    private String outInstallmentOrderId;
    private String outOrderId;
    private String userId;

}
