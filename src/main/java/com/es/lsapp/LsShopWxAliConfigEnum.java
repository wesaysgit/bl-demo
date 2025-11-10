package com.es.lsapp;


import java.util.Arrays;
import java.util.Objects;

/**
 * FChannelType: 1-wx;2-zfb
 * FFeeType: 1 线上费率类型，默认不传 2就是线下
 */
public enum LsShopWxAliConfigEnum {


    WXCHANNELCONFIG("1", "569826029", 1),
    OLDWXCHANNELCONFIG("1", "588798979", 1),
    ALICHANNELCONFIG("2", "2088620511400557", 2),
    OLDALICHANNELCONFIG("2", "2088620874582233", 2),
    //微信保险费率
    WXINSURANCEBIND("1", "574940358", 5),
    NEWWXINSURANCEBIND("1", "589113128", 5),
    NEALIXINSURANCEBIND("2", "2088630169872712", 2),
    WXINSURANCEUNBIND("1", "569635592", 1),
    WXINSURANCEBINDTEMP("1", "497998390", 2),

    /** 解挂总店wx */
    HEADWXUNBINDTEMP("1", "497998390", 2),
    /** 解挂总店zfb */
    HEADZFBUNBINDTEMP("2", "2088420034460964", 2),

    HEADWXTEMP1("1", "462938464", 2),
    HEADWXTEMP2("1", "444471764", 2),
    HEADWXTEMP3("1", "497998390", 2),


    ;

    private String FChannelType;
    private String FChannelId;
    private int FFeeType;

    public static void main(String[] args) {
        LsShopWxAliConfigEnum lsShopWxAliConfigEnum = ofValue("497998390");
        System.out.println("lsShopWxAliConfigEnum = " + lsShopWxAliConfigEnum);
    }

    public static LsShopWxAliConfigEnum ofValue(String fChannelId) {
        return Arrays.stream(values()).filter(it -> Objects.equals(fChannelId, it.getFChannelId())).findFirst().orElse(null);
    }

    LsShopWxAliConfigEnum(String fChannelType, String fChannelId, int fFeeType) {
        this.FChannelType = fChannelType;
        this.FChannelId = fChannelId;
        this.FFeeType = fFeeType;
    }

    public String getFChannelType() {
        return FChannelType;
    }

    public void setFChannelType(String FChannelType) {
        this.FChannelType = FChannelType;
    }

    public String getFChannelId() {
        return FChannelId;
    }

    public void setFChannelId(String FChannelId) {
        this.FChannelId = FChannelId;
    }

    public int getFFeeType() {
        return FFeeType;
    }

    public void setFFeeType(int FFeeType) {
        this.FFeeType = FFeeType;
    }
}
