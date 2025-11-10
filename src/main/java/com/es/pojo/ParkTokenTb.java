package com.es.pojo;

public class ParkTokenTb {
    private Long id;

    private Long unionId;

    private String parkId;

    private String token;

    private Long loginTime;

    private String serverIp;

    private String sourceIp;

    private String localId;

    private String channelId;

    private Long lastbeatTime;

    private Integer prepaySend;

    private Integer monthSend;

    private Integer version;

    private Integer loginType;

    //是否登陆云平台，仅http模式，0-否 1-是
    private Integer loginCloud;

    private Integer prepayQuery;

    public Integer getPrepayQuery() {
        return prepayQuery;
    }

    public void setPrepayQuery(Integer prepayQuery) {
        this.prepayQuery = prepayQuery;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.parkId = parkId == null ? null : parkId.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp == null ? null : serverIp.trim();
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp == null ? null : sourceIp.trim();
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId == null ? null : localId.trim();
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public Long getLastbeatTime() {
        return lastbeatTime;
    }

    public void setLastbeatTime(Long lastbeatTime) {
        this.lastbeatTime = lastbeatTime;
    }

    public Integer getPrepaySend() {
        return prepaySend;
    }

    public void setPrepaySend(Integer prepaySend) {
        this.prepaySend = prepaySend;
    }

    public Integer getMonthSend() {
        return monthSend;
    }

    public void setMonthSend(Integer monthSend) {
        this.monthSend = monthSend;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public Integer getLoginCloud() {
        return loginCloud;
    }

    public void setLoginCloud(Integer loginCloud) {
        this.loginCloud = loginCloud;
    }

    @Override
    public String toString() {
        return "ParkTokenTb{" +
                "unionId=" + unionId +
                ", parkId='" + parkId + '\'' +
                ", token='" + token + '\'' +
                ", serverIp='" + serverIp + '\'' +
                ", channelId='" + channelId + '\'' +
                ", prepaySend=" + prepaySend +
                ", monthSend=" + monthSend +
                ", version=" + version +
                ", loginType=" + loginType +
                ", loginCloud=" + loginCloud +
                ", prepayQuery=" + prepayQuery +
                '}';
    }
}
