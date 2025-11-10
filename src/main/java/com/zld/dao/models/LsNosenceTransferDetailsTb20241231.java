package com.zld.dao.models;

import java.math.BigDecimal;

public class LsNosenceTransferDetailsTb20241231 {
    private Long id;

    private String orderTradeNo;

    private String transferTradeNo;

    private String shopId;

    private Integer payCompany;

    private Integer status;

    private Long transferTime;

    private Long unionId;

    private String parkId;

    private BigDecimal transferAmount;

    private Integer costType;

    private Integer refundStatus;

    private String transferRemark;

    private BigDecimal realRefundMoney;

    private String carNumber;

    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderTradeNo() {
        return orderTradeNo;
    }

    public void setOrderTradeNo(String orderTradeNo) {
        this.orderTradeNo = orderTradeNo;
    }

    public String getTransferTradeNo() {
        return transferTradeNo;
    }

    public void setTransferTradeNo(String transferTradeNo) {
        this.transferTradeNo = transferTradeNo;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Integer getPayCompany() {
        return payCompany;
    }

    public void setPayCompany(Integer payCompany) {
        this.payCompany = payCompany;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Long transferTime) {
        this.transferTime = transferTime;
    }

    public Long getUnionId() {
        return unionId;
    }

    public void setUnionId(Long unionId) {
        this.unionId = unionId;
    }

    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public Integer getCostType() {
        return costType;
    }

    public void setCostType(Integer costType) {
        this.costType = costType;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getTransferRemark() {
        return transferRemark;
    }

    public void setTransferRemark(String transferRemark) {
        this.transferRemark = transferRemark;
    }

    public BigDecimal getRealRefundMoney() {
        return realRefundMoney;
    }

    public void setRealRefundMoney(BigDecimal realRefundMoney) {
        this.realRefundMoney = realRefundMoney;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}