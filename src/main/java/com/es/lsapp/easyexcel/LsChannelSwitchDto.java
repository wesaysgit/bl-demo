package com.es.lsapp.easyexcel;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LsChannelSwitchDto {

    @ExcelProperty("乐刷商户号")
    private String merchantId;

    @ExcelProperty("乐刷商户记录状态")
    private String isExist;

    @ExcelProperty("设置费率状态")
    private String isFeeConfig;

    @ExcelProperty("旧微信商户号")
    private String oldWxMchId;

    @ExcelProperty("微信解挂状态")
    private String isOldWxUnBind;

    @ExcelProperty("新微信商户号")
    private String newWxMchId;

    @ExcelProperty("微信挂靠状态")
    private String isNewWxBind;

    @ExcelProperty("旧支付宝商户号")
    private String oldZfbMchId;

    @ExcelProperty("支付宝解挂状态")
    private String isOldZfbUnBind;

    @ExcelProperty("新支付宝商户号")
    private String newZfbMchId;

    @ExcelProperty("支付宝挂靠状态")
    private String isNewZfbBind;

    @ExcelProperty("新乐刷支付通道状态")
    private String isPaySceneChannelSwitch;

    @ExcelProperty("说明")
    private String remark;

}