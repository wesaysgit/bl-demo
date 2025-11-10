package com.es.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UnionParkTb {

    private Long id;

    /** 车场名称 */
    private String name;

    /** 经度 */
    private BigDecimal lng;

    /** 纬度 */
    private BigDecimal lat;

    /** 总车位数 */
    private Integer totalPlot;

    /** 空闲车位数 */
    private Integer emptyPlot;

    /** 联盟编号 */
    private Long unionId;

    /** 创建日期 */
    private Long ctime;

    /** 修改日期 */
    private Long utime;

    /**  状态 0未审核 1正常 2禁用 */
    private Integer state;

    /** 余额 */
    private BigDecimal balance;

    /** 车场地址 */
    private String address;

    /** 联系电话 */
    private String phone;

    /** 服务商ID */
    private Long serverId;

    /** 厂商平台的车场编号，不可重复 */
    private String parkId;

    /** 备注说明 */
    private String remark;

    /** 价格描述 */
    private String priceDesc;

    /** 城市编号 */
    private Integer cityId;

    /** 第三方支付平台编号 */
    private Long payPlatformId;

    /** 密保电话 , 用于对账户进行敏感内容操作时的安全验证 */
    private String mobile;

    /** 服务商ID */
    private Long operatorId;

    /** 设置自动提现金额 */
    private Double withdrawalMoney;

    /** 设置自动提现周期 */
    private Integer withdrawalCycle;

    /** 设置自动提现金额 */
    private Long webankWallletId;

    /** 银盛商户id(收款、转账) */
    private String ysShopId;

    /** 银盛商户名称 */
    private String ysShopName;

    private Integer provinceId;

    private Integer areaId;

    private Integer parkType;

    private Integer score;

    /** 云平台车场的标记 */
    private Integer isCloudPark;

    /** 最新交易时间 */
    private Long lastPayTime;

    private Long firstPayTime;

    /** 设置自动提现金额 */
    private Double hfWithdrawalMoney;

    private Integer hfWithdrawalCycle;

    /** 是否开启汇付钱包功能 0-否 1-是 */
    private Integer huifuWallet;

    /** 是否从云平台查询订单费用 */
    private Integer queryFeeFromCloud;

    /** 消息收到回执是否以车场主动回复的为准，1-是 */
    private Integer msgReply;

    /** 是否是捷顺平台1是 */
    private Integer cloudPlatform;

    /** 捷顺车场编号 */
    private String deviceCode;

    /** 车场是否支持月卡 0-否 1-是 */
    private Integer monthCard;

    /** 是否模糊匹配订单 */
    private Integer matchOrder;

    /** 新能源优惠金额 */
    private Double newEnergyDuration;

    /** 提现备注摘要 */
    private String accountNote;

    /** 关闭活动通知1关闭 */
    private Integer offNotify;

    /** 对接类型，0:sdk 1:api */
    private Integer dockingType;

    /** 微信头像 */
    private String headurl;

    /** 微信昵称 */
    private String nickname;

    /** 微信openid */
    private String openid;

    /** 支付成功通知地址 */
    private String payNotifyUrl;

    private String unionName;

    /** 客服存的收费标准 */
    private String chargeStandard;

    /** 客服存的收费图片 */
    private Long chargeStandardPic;

    /** 无牌车 0自动入场 1 管理员确认 */
    private Integer nolicencePush;

    /** 是否展示呼叫客服的按钮1显示 */
    private Integer callOutPush;

    /** 0-否 1-是 */
    private Integer isMatch;

    private Double lsWithdrawalMoney;

    private Integer lsWithdrawalCycle;

    private Integer lsWallet;
    //营销转账钱包设置的余额
    private BigDecimal marketingWallet;
    //钱包 余额
    private BigDecimal marketingWalletBalance;

    private Integer regProvinceId;

    private Integer regCityId;

    private Integer regAreaId;

    private Integer lsWithdrawalDay;

}