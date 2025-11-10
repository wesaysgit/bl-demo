package com.es.lsapp.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LsPayScoreRequestDto {

    private String merchantId;
    private String merchantPayScoreId;
    private String merchantOrderId;
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
    private String needUserConfirm;
    private String notifyUrl;
    private String attach;
    private String signType;
    private Integer totalAmount;
    private String completeTime;
    private String nonceStr;
    private String tradeScene;
    private String reason;

}
