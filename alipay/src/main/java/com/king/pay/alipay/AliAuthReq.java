package com.king.pay.alipay;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class AliAuthReq {

    /** 商户签约拿到的app_id */
    private String appId;
    /**  商户签约拿到的pid */
    private String pid;
    /** 服务接口名称 */
    private String apiname = "com.alipay.account.auth";
    /** 服务接口名称， 固定值 */
    private String methodname = "alipay.open.auth.sdk.code.get";
    /** 商户类型标识 */
    private String appName = "mc";
    /** 业务类型 */
    private String bizType = "openservice";
    /** 产品码 */
    private String productId = "APP_FAST_LOGIN";
    /** 授权范围 */
    private String scope = "kuaijie";
    /** 商户唯一标识 */
    private String targetId;
    /** 授权类型 */
    private String authType = "AUTHACCOUNT";
    /** 签名类型 */
    private String signType = "RSA2";
    /** 签名 */
    private String sign;

    public String getAppId() {
        return appId;
    }

    public AliAuthReq setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getPid() {
        return pid;
    }

    public AliAuthReq setPid(String pid) {
        this.pid = pid;
        return this;
    }

    public String getApiname() {
        return apiname;
    }

    public AliAuthReq setApiname(String apiname) {
        this.apiname = apiname;
        return this;
    }

    public String getMethodname(){
        return methodname;
    }

    public AliAuthReq setMethodname(String methodname) {
        this.methodname = methodname;
        return this;
    }

    public String getAppName() {
        return appName;
    }

    public AliAuthReq setAppName(String appName) {
        this.appName = appName;
        return this;
    }

    public String getBizType() {
        return bizType;
    }

    public AliAuthReq setBizType(String bizType) {
        this.bizType = bizType;
        return this;
    }

    public String getProductId() {
        return productId;
    }

    public AliAuthReq setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public String getScope() {
        return scope;
    }

    public AliAuthReq setScope(String scope) {
        this.scope = scope;
        return this;
    }

    public String getTargetId() {
        return targetId;
    }

    public AliAuthReq setTargetId(String targetId) {
        this.targetId = targetId;
        return this;
    }

    public String getAuthType() {
        return authType;
    }

    public AliAuthReq setAuthType(String authType) {
        this.authType = authType;
        return this;
    }

    public String getSignType() {
        return signType;
    }

    public AliAuthReq setSignType(String signType) {
        this.signType = signType;
        return this;
    }

    public String getSign() {
        return sign;
    }

    public AliAuthReq setSign(String sign) {
        this.sign = sign;
        return this;
    }
}
