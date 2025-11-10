package com.bolink.pojo;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class LsUnionMchIdDTO {

    @ExcelProperty("车场名称")
    private String name;
    @ExcelProperty("车场id")
    private String parkId;
    @ExcelProperty("厂商id")
    private String unionId;
    @ExcelProperty("泊链车场编号")
    private String id;
    @ExcelProperty("省")
    private String province;
    @ExcelProperty("市")
    private String city;
    @ExcelProperty("地址")
    private String address;
    @ExcelProperty("乐刷商户号")
    private String lsMchId;
    @ExcelProperty("银联商户号")
    private String unionMchId;
    @ExcelProperty("停车场")
    private String parkLot;
}
