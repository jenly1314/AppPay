package com.king.pay.alipay;

/**
 * 参数说明请参见：<a href="https://docs.open.alipay.com/204/105465/">支付宝支付请求参数说明</a>
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class AliPayReq {

    /** 支付宝支付分配的应用ID */
    private String appId;
    /** 接口名称 示例值：alipay.trade.app.pay */
    private String method = "alipay.trade.app.pay";
    /** 格式 */
    private String format = "JSON";
    /** 请求使用的编码格式，如utf-8,gbk,gb2312等 */
    private String charset = "utf-8";
    /** 签名类型 示例值：RSA2 */
    private String signType = "RSA2";
    /** 签名 */
    private String sign;
    /** 时间戳 */
    private String timestamp;
    /** 调用的接口版本，固定为：1.0 */
    private String version = "1.0";
    /** 支付宝服务器主动通知商户服务器里指定的页面http/https路径。建议商户使用https */
    private String notifyUrl;
    /** 业务请求参数的集合 */
    private String bizContent;

    public String getAppId() {
        return appId;
    }

    public AliPayReq setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public AliPayReq setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getFormat() {
        return format;
    }

    public AliPayReq setFormat(String format) {
        this.format = format;
        return this;
    }

    public String getCharset() {
        return charset;
    }

    public AliPayReq setCharset(String charset) {
        this.charset = charset;
        return this;
    }

    public String getSignType() {
        return signType;
    }

    public AliPayReq setSignType(String signType) {
        this.signType = signType;
        return this;
    }

    public String getSign() {
        return sign;
    }

    public AliPayReq setSign(String sign) {
        this.sign = sign;
        return this;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public AliPayReq setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public AliPayReq setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public AliPayReq setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
        return this;
    }

    public String getBizContent() {
        return bizContent;
    }

    public AliPayReq setBizContent(String bizContent) {
        this.bizContent = bizContent;
        return this;
    }
}
