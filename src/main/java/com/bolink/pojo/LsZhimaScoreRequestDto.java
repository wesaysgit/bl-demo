package com.bolink.pojo;

import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LsZhimaScoreRequestDto {

    //废弃。
    private String merchantPayScoreId;
    private String serviceId;
    private String subMchid;
    private String subAppid;
    private String channelId;
    private String serviceIntroduction;
    private List postPayments;
    private List postDiscounts;
    private JSONObject riskFund;
    private JSONObject timeRange;
    private String subOpenid;
    private Boolean needUserConfirm;
    private String notifyUrl;
    private String attach;
    private String signType;
    private String leshuaPayscoreId;
    private Integer totalAmount;
    private String merchantOrderId;
    private String reason;
    private String tradeScene;

    //下单
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

    //创建分期单
    private String installmentNo;
    private String installmentPrice;
    private int isFinishPerformance;
    private String orderId;
    private String outInstallmentOrderId;
    private String userId;
    private String openId;
    private int periodNum;

    //代扣
    private String amount;
    private String agreementNo;
    private String tradeComponentOrderId;
    private String tcInstallmentOrderId;
    private String outTradeNo;

}
