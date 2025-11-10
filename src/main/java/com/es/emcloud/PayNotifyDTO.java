package com.es.emcloud;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PayNotifyDTO {

    private String outTradeNo;
    private String openId;
    private Long payTime;
    private String payPlateTradeNo;
    private String channelTradeNo;
    private PayCallParamDTO payCallParamDTO;

}
