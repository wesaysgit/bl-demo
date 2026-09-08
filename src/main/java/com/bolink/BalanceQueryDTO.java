package com.bolink;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class BalanceQueryDTO {
    @ExcelProperty(value = "账户状态 1-正常 2-异常 3-冻结")
    private int accountState;
    @ExcelProperty(value = "乐刷商户号")
    private String merchantId;
    @ExcelProperty(value = "可用余额")
    private int contributoryAmount;
    @ExcelProperty(value = "账户余额")
    private int balanceAmount;
    @ExcelProperty(value = "止付金额")
    private int suspendAmount;
    @ExcelProperty(value = "已拦截金额")
    private int interceptAmount;
    @ExcelProperty(value = "pendingAmount")
    private int pendingAmount;
    @ExcelProperty(value = "冻结金额")
    private int frozenAmount;

}