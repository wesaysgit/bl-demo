package com.es.esdemo.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProfitAnalysisTb {
    private Long id;

    private Long comid;

    private BigDecimal profitAmount;

    private Long ctime;

    private String wDate;

    private Long orderCount;

    private Long platefromId;

    private String type;

    private Long unionId;

    private String parkId;

    private BigDecimal serverProfitAmount;

    private BigDecimal rate;

    private String sub;

    Integer level;

    //广告状态 0-完全屏蔽 1-无屏蔽 2-部分屏蔽
    private Integer adState;

    private BigDecimal feeAmount;

    private BigDecimal adAmount;

    private BigDecimal serverFeeAmount;

    private BigDecimal serverAdAmount;

    private BigDecimal rabateFee;


}