package com.es.lsapp.easyexcel;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.converters.string.StringStringConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LsFenZhangDto {

    @ExcelProperty(value = "金额")
    private Double money;

    @ExcelProperty(value = "交易号")
    private String outTradeNo;

    @ExcelProperty(value = "乐刷订单号")
    private String transactionId;

    @ExcelProperty(value = "状态")
    private String status;

}