package com.es.bolink;

import java.math.BigDecimal;

public class UnionPlatfromTb {

    private Long id;

    /** ��Ա���� */
    private String name;

    /** ��ַ */
    private String address;

    /** ע������ */
    private Long regTime;

    /** �޸�ʱ�� */
    private Long updateTime;

    /** ������ */
    private Long operateId;

    /** ��ϵ�绰 */
    private String phone;

    /** ��� */
    private BigDecimal balance;

    /** ״̬  0�½� 1����� 2ɾ�� 3����� 4��˲�ͨ��  */
    private Integer state;

    /** ��Կ */
    private String ukey;

    /** �Ƿ����  0�� 1�� */
    private Integer isdistibution;

    /** �ܱ��绰 , ���ڶ��˻������������ݲ���ʱ�İ�ȫ��֤ */
    private String mobile;

    /** �Խ�����  0sdk  1api */
    private Integer dockingType;

    /** ΢��Ǯ����� */
    private Long webankWalletId;

    /** ��ʢ�̻�id(�տת��) */
    private String ysShopId;

    /** ��ʢ�̻����� */
    private String ysShopName;

    private Integer score;

    /** 0-�������� 1-ͣ��ȯ���� */
    private Integer platType;

    /** �����ṩ����Կ */
    private String thirdKey;

    /** ͣ��ȯ���̷���� */
    private BigDecimal serviceFee;

    private Long bdId;

    /** ͣ��ȯlogo */
    private String logoUrl;

    private String cloudUrl;

    private Integer registAuth;

    private Integer passAuth;

    private Integer twocodeAuth;

    private Integer downloadAuth;

    private Integer parkIdSwitch;

    private String parkIdPre;

    //新增车场无感开启状态 1-开启
    private Integer wxAutopay;

    private Integer serverAuth;

    private Integer isRedPacket;

    private String redPackAmount;

    private Integer serverTrans;

    private Integer unionLost;

    private Integer unionRedpacketType;

    private Integer serverRedpacketType;

    private Integer parkRedpacketType;

    private Integer sharePercent;

    public Integer getSharePercent() {
        return sharePercent;
    }

    public void setSharePercent(Integer sharePercent) {
        this.sharePercent = sharePercent;
    }

    public Integer getUnionRedpacketType() {
        return unionRedpacketType;
    }

    public void setUnionRedpacketType(Integer unionRedpacketType) {
        this.unionRedpacketType = unionRedpacketType;
    }

    public Integer getServerRedpacketType() {
        return serverRedpacketType;
    }

    public void setServerRedpacketType(Integer serverRedpacketType) {
        this.serverRedpacketType = serverRedpacketType;
    }

    public Integer getParkRedpacketType() {
        return parkRedpacketType;
    }

    public void setParkRedpacketType(Integer parkRedpacketType) {
        this.parkRedpacketType = parkRedpacketType;
    }

    public Integer getUnionLost() {
        return unionLost;
    }

    public void setUnionLost(Integer unionLost) {
        this.unionLost = unionLost;
    }

    public Integer getServerTrans() {
        return serverTrans;
    }

    public void setServerTrans(Integer serverTrans) {
        this.serverTrans = serverTrans;
    }

    public Integer getIsRedPacket() {
        return isRedPacket;
    }

    public void setIsRedPacket(Integer isRedPacket) {
        this.isRedPacket = isRedPacket;
    }

    public String getRedPackAmount() {
        return redPackAmount;
    }

    public void setRedPackAmount(String redPackAmount) {
        this.redPackAmount = redPackAmount;
    }

    public Integer getServerAuth() {
        return serverAuth;
    }

    public void setServerAuth(Integer serverAuth) {
        this.serverAuth = serverAuth;
    }

    public Integer getWxAutopay() {
        return wxAutopay;
    }

    public void setWxAutopay(Integer wxAutopay) {
        this.wxAutopay = wxAutopay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Long getRegTime() {
        return regTime;
    }

    public void setRegTime(Long regTime) {
        this.regTime = regTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getOperateId() {
        return operateId;
    }

    public void setOperateId(Long operateId) {
        this.operateId = operateId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUkey() {
        return ukey;
    }

    public void setUkey(String ukey) {
        this.ukey = ukey == null ? null : ukey.trim();
    }

    public Integer getIsdistibution() {
        return isdistibution;
    }

    public void setIsdistibution(Integer isdistibution) {
        this.isdistibution = isdistibution;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getDockingType() {
        return dockingType;
    }

    public void setDockingType(Integer dockingType) {
        this.dockingType = dockingType;
    }

    public Long getWebankWalletId() {
        return webankWalletId;
    }

    public void setWebankWalletId(Long webankWalletId) {
        this.webankWalletId = webankWalletId;
    }

    public String getYsShopId() {
        return ysShopId;
    }

    public void setYsShopId(String ysShopId) {
        this.ysShopId = ysShopId == null ? null : ysShopId.trim();
    }

    public String getYsShopName() {
        return ysShopName;
    }

    public void setYsShopName(String ysShopName) {
        this.ysShopName = ysShopName == null ? null : ysShopName.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getPlatType() {
        return platType;
    }

    public void setPlatType(Integer platType) {
        this.platType = platType;
    }

    public String getThirdKey() {
        return thirdKey;
    }

    public void setThirdKey(String thirdKey) {
        this.thirdKey = thirdKey == null ? null : thirdKey.trim();
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Long getBdId() {
        return bdId;
    }

    public void setBdId(Long bdId) {
        this.bdId = bdId;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl == null ? null : logoUrl.trim();
    }

    public String getCloudUrl() {
        return cloudUrl;
    }

    public void setCloudUrl(String cloudUrl) {
        this.cloudUrl = cloudUrl;
    }

    public Integer getRegistAuth() {
        return registAuth;
    }

    public void setRegistAuth(Integer registAuth) {
        this.registAuth = registAuth;
    }

    public Integer getPassAuth() {
        return passAuth;
    }

    public void setPassAuth(Integer pathAuth) {
        this.passAuth = pathAuth;
    }

    public Integer getTwocodeAuth() {
        return twocodeAuth;
    }

    public void setTwocodeAuth(Integer twocodeAuth) {
        this.twocodeAuth = twocodeAuth;
    }

    public Integer getDownloadAuth() {
        return downloadAuth;
    }

    public void setDownloadAuth(Integer downloadAuth) {
        this.downloadAuth = downloadAuth;
    }

    public Integer getParkIdSwitch() {
        return parkIdSwitch;
    }

    public void setParkIdSwitch(Integer parkIdSwitch) {
        this.parkIdSwitch = parkIdSwitch;
    }

    public String getParkIdPre() {
        return parkIdPre;
    }

    public void setParkIdPre(String parkIdPre) {
        this.parkIdPre = parkIdPre;
    }

    @Override
    public String toString() {
        return "UnionPlatfromTb{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", regTime=" + regTime +
                ", updateTime=" + updateTime +
                ", operateId=" + operateId +
                ", phone='" + phone + '\'' +
                ", balance=" + balance +
                ", state=" + state +
                ", ukey='" + ukey + '\'' +
                ", isdistibution=" + isdistibution +
                ", mobile='" + mobile + '\'' +
                ", dockingType=" + dockingType +
                ", webankWalletId=" + webankWalletId +
                ", ysShopId='" + ysShopId + '\'' +
                ", ysShopName='" + ysShopName + '\'' +
                ", score=" + score +
                ", platType=" + platType +
                ", thirdKey='" + thirdKey + '\'' +
                ", serviceFee=" + serviceFee +
                ", bdId=" + bdId +
                ", logoUrl='" + logoUrl + '\'' +
                ", cloudUrl='" + cloudUrl + '\'' +
                ", registAuth=" + registAuth +
                ", passAuth=" + passAuth +
                ", twocodeAuth=" + twocodeAuth +
                ", downloadAuth=" + downloadAuth +
                ", parkIdSwitch=" + parkIdSwitch +
                ", parkIdPre='" + parkIdPre + '\'' +
                ", wxAutopay=" + wxAutopay +
                '}';
    }
}