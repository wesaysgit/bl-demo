package com.es.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZhimaScoreCloseOrderReq {

    private String userId;
    private String outOrderId;
    private String orderId;

}
