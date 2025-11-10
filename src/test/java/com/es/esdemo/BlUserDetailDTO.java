package com.es.esdemo;

import lombok.Data;

import java.io.Serializable;

/**
 * 泊链用户中心-用户详情 dto
 * @author pengzhuo
 */
@Data
public class BlUserDetailDTO implements Serializable {
    private static final long serialVersionUID = -3715022954850544335L;

    /** 用户编号; 微信openid/支付宝userId... */
    private String userNo;

    /** 类型 1:微信小程序; 2:支付宝小程序; 3:H5; 4:云平台; 5:公众号H5 */
    private Integer type;

    /** 微信union_id */
    private String unionId;

    /** 应用ID */
    private String appId;


}
