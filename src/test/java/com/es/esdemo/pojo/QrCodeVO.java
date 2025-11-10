package com.es.esdemo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 二维码信息对象 VO
 * @author pengzhuo
 */
@Data
@Accessors(chain = true)
public class QrCodeVO implements Serializable {
    private static final long serialVersionUID = -5902747323828509800L;

    /** 表主键(二维码id) */
    private Long id;

    /** 二维码code */
    private String code;

    /** 厂商id */
    private Long unionId;

    /** 车场编号(厂商下唯一) */
    private String parkId;

    /** 出场通道id; 出口通道--出场等待结算订单时使用 */
    private String outChannelId;

    /** 入场通道id; 入口通道--出场等待结算订单时使用 */
    private String inChannelId;

    /** 二维码类型: 1预付 2出场结算 3无牌车入场 */
    private Short type;

    /** 二维码生成类型 1-后台 2-接口 */
    private Integer produceType;

    /** 二维码类别 1-H5支付码  2-小程序支付码 */
    private Integer qrCodeType;

    /** 跳转地址 */
    private String redirectUrl;

    /** 二维码图片地址 */
    private String picUrl;

    /** 背景图 0-无背景图 >1对应页面背景图 */
    private Integer picId;

    /** 二维码添加来源  1-车场后台物料申请 */
    private Integer fromPark;

    /** 是否显示车场名称 1-显示 */
    private Integer showParkName;

    /** 是否显示客服电话 1-显示 */
    private Integer showPhone;

    /** 是否预览生成，1-是, 如果是1表示是预览生成的二维码专给预览操作使用的(直接用该码信息保存的话, 会重置该字段为0表示是正式二维码);
     * 如果预览后关掉则该条数据保留预览状态在数据库不变动; 当下次创建或预览时条件相同则会继续使用... */
    private Integer isPreview;

    /** 状态 1-正常 0-禁用 */
    private Integer state;

    /** 是否手动输入金额 */
    private Integer manualInputAmount;

    /** 默认金额 */
    private String amounts;

    /** 编号是否在阿里注册，营销功能用  1-是 */
    private Integer registInAli;

    /** 创建时间(s) */
    private Long ctime;

    /** 更新时间(s) */
    private Long utime;

    /** 同步数据是否删除标识(1表示删除),用于发送kafka消息映射字段; 该字段只允许序列化,在本项目中不允许反序列化 */
    @JSONField(deserialize = false)
    private Integer syncDel;

}
