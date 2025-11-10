package com.es.pojo;

import lombok.Data;

import java.util.List;

@Data
public class ProfitNotifyDTO {
    /** 支付交易号 */
    private String outTradeNo;
    /** 三方交易号 */
    private String thirdOutTradeNo;
    /** 厂商编号 */
    private Long unionId;
    /** 车场编号 */
    private String parkId;
    /** appId */
    private String appId;
    /** 分账详情 */
    private List<ParkingLotProfitDetail> profitDetails;
}
