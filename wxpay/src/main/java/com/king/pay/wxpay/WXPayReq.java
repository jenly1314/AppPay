package com.king.pay.wxpay;

/**
 * 微信支付请求参数对象
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
@SuppressWarnings("unused")
public class WXPayReq {

    /**
     * appId
     */
    private String appId;
    /**
     * 商户号
     */
    private String partnerId;
    /**
     * 预支付交易会话ID
     */
    private String prepayId;
    /**
     * 扩展字段
     */
    private String packageValue = "Sign=WXPay";
    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 时间戳
     */
    private String timestamp;
    /**
     * 签名
     */
    private String sign;

    public WXPayReq() {
    }

    public String getAppId() {
        return appId;
    }

    public WXPayReq setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public WXPayReq setPartnerId(String partnerId) {
        this.partnerId = partnerId;
        return this;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public WXPayReq setPrepayId(String prepayId) {
        this.prepayId = prepayId;
        return this;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public WXPayReq setPackageValue(String packageValue) {
        this.packageValue = packageValue;
        return this;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public WXPayReq setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
        return this;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public WXPayReq setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getSign() {
        return sign;
    }

    public WXPayReq setSign(String sign) {
        this.sign = sign;
        return this;
    }

    @Override
    public String toString() {
        return "WXPayReq{" +
                "appId='" + appId + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", prepayId='" + prepayId + '\'' +
                ", packageValue='" + packageValue + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
