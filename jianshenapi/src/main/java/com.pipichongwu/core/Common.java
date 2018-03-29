package com.pipichongwu.core;

/**
 * Created by dongao on 2018/3/29.
 */
public class Common {
    /*设备名称*/
    private Integer deviceType;
    /*设备唯一标识*/
    private String uniqueId;
    /*版本号*/
    private String version;
    /**/
    private String app;
    /*后台统计使用*/
    private String appName;
    /*密钥串*/
    private String sign;
    /*登录参数A*/
    private String loginParamA;
    /*登录参数B*/
    private String loginParamB;
    /*合作机构ID*/
    private Long partnerId;
    /*登陆方式*/
    private Integer loginType;
    /*用户userId*/
    private String userId;
    /*秘钥*/
    private String mobileAccessToken;

    private String isWXSmallProgram;
    private String userInfo;
    public String getUniqueId() {
        return uniqueId;
    }
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getApp() {
        return app;
    }
    public void setApp(String app) {
        this.app = app;
    }
    public String getAppName() {
        return appName;
    }
    public void setAppName(String appName) {
        this.appName = appName;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public String getLoginParamA() {
        return loginParamA;
    }
    public void setLoginParamA(String loginParamA) {
        this.loginParamA = loginParamA;
    }
    public String getLoginParamB() {
        return loginParamB;
    }
    public void setLoginParamB(String loginParamB) {
        this.loginParamB = loginParamB;
    }
    public Integer getDeviceType() {
        return deviceType;
    }
    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }
    public Integer getLoginType() {
        return loginType;
    }
    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }
    public String getMobileAccessToken() {
        return mobileAccessToken;
    }
    public void setMobileAccessToken(String mobileAccessToken) {
        this.mobileAccessToken = mobileAccessToken;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIsWXSmallProgram() {
        return isWXSmallProgram;
    }

    public void setIsWXSmallProgram(String isWXSmallProgram) {
        this.isWXSmallProgram = isWXSmallProgram;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

}
