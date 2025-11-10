package com.es.esdemo.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UnionOrderApppayTb {
    private Long id;

    private String plateNumber;

    private Long startTime;

    private Long endTime;

    private Long recordTime;

    private Long unionId;

    private BigDecimal total;

    private Long unionUserId;

    private Long payTime;

    private String orderId;

    private Long carUnionId;

    private String parkId;

    private BigDecimal unionService;

    private BigDecimal serverService;

    private BigDecimal carUnionService;

    private Long serverId;

    private String tradeNo;

    private Long comId;

    private Integer state;

    private Integer prepayDuartion;

    private String title;

    private Integer payType;

    private Integer payChannel;

    private Long operatorId;

    private String backReason;

    private String localId;

    // 0:未开票 1:开票成功 2:开票中 3:开票失败 枚举类：com.zld.bolink_api.constant.OrderAppPayInvoiceState
    private Integer invoiceState;

    private String invoiceTradeno;

    private Integer isRoute;

    private Integer payCompany;

    private Integer isDirectpay;

    private Integer isCouponUser;

    private String couponCodes;

    private BigDecimal couponAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber == null ? null : plateNumber.trim();
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Long recordTime) {
        this.recordTime = recordTime;
    }

    public Long getUnionId() {
        return unionId;
    }

    public void setUnionId(Long unionId) {
        this.unionId = unionId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Long getUnionUserId() {
        return unionUserId;
    }

    public void setUnionUserId(Long unionUserId) {
        this.unionUserId = unionUserId;
    }

    public Long getPayTime() {
        return payTime;
    }

    public void setPayTime(Long payTime) {
        this.payTime = payTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Long getCarUnionId() {
        return carUnionId;
    }

    public void setCarUnionId(Long carUnionId) {
        this.carUnionId = carUnionId;
    }

    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId == null ? null : parkId.trim();
    }

    public BigDecimal getUnionService() {
        return unionService;
    }

    public void setUnionService(BigDecimal unionService) {
        this.unionService = unionService;
    }

    public BigDecimal getServerService() {
        return serverService;
    }

    public void setServerService(BigDecimal serverService) {
        this.serverService = serverService;
    }

    public BigDecimal getCarUnionService() {
        return carUnionService;
    }

    public void setCarUnionService(BigDecimal carUnionService) {
        this.carUnionService = carUnionService;
    }

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getPrepayDuartion() {
        return prepayDuartion;
    }

    public void setPrepayDuartion(Integer prepayDuartion) {
        this.prepayDuartion = prepayDuartion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(Integer payChannel) {
        this.payChannel = payChannel;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getBackReason() {
        return backReason;
    }

    public void setBackReason(String backReason) {
        this.backReason = backReason == null ? null : backReason.trim();
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId == null ? null : localId.trim();
    }

    public Integer getInvoiceState() {
        return invoiceState;
    }

    /**
     * 对应枚举类 com.zld.bolink_api.constant.OrderAppPayInvoiceState
     *
     * @param invoiceState 0:未开票 1:开票成功 2:开票中 3:开票失败
     */
    public void setInvoiceState(Integer invoiceState) {
        this.invoiceState = invoiceState;
    }

    public String getInvoiceTradeno() {
        return invoiceTradeno;
    }

    public void setInvoiceTradeno(String invoiceTradeno) {
        this.invoiceTradeno = invoiceTradeno == null ? null : invoiceTradeno.trim();
    }

    public Integer getIsRoute() {
        return isRoute;
    }

    public void setIsRoute(Integer isRoute) {
        this.isRoute = isRoute;
    }

    public Integer getPayCompany() {
        return payCompany;
    }

    public void setPayCompany(Integer payCompany) {
        this.payCompany = payCompany;
    }

    public Integer getIsDirectpay() {
        return isDirectpay;
    }

    public void setIsDirectpay(Integer isDirectpay) {
        this.isDirectpay = isDirectpay;
    }

    public Integer getIsCouponUser() {
        return isCouponUser;
    }

    public void setIsCouponUser(Integer isCouponUser) {
        this.isCouponUser = isCouponUser;
    }

    public String getCouponCodes() {
        return couponCodes;
    }

    public void setCouponCodes(String couponCodes) {
        this.couponCodes = couponCodes == null ? null : couponCodes.trim();
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }
}
