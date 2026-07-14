package com.king.pay.wxpay

/**
 * 微信支付请求参数对象
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
class WXPayReq {

    /**
     * appId
     */
    var appId: String? = null
        private set

    /**
     * 商户号
     */
    var partnerId: String? = null
        private set

    /**
     * 预支付交易会话ID
     */
    var prepayId: String? = null
        private set

    /**
     * 扩展字段
     */
    var packageValue: String = "Sign=WXPay"
        private set

    /**
     * 随机字符串
     */
    var nonceStr: String? = null
        private set

    /**
     * 时间戳
     */
    var timestamp: String? = null
        private set

    /**
     * 签名
     */
    var sign: String? = null
        private set

    fun setAppId(appId: String): WXPayReq = apply { this.appId = appId }

    fun setPartnerId(partnerId: String): WXPayReq = apply { this.partnerId = partnerId }

    fun setPrepayId(prepayId: String): WXPayReq = apply { this.prepayId = prepayId }

    fun setPackageValue(packageValue: String): WXPayReq = apply { this.packageValue = packageValue }

    fun setNonceStr(nonceStr: String): WXPayReq = apply { this.nonceStr = nonceStr }

    fun setTimestamp(timestamp: String): WXPayReq = apply { this.timestamp = timestamp }

    fun setSign(sign: String): WXPayReq = apply { this.sign = sign }

    override fun toString(): String {
        return "WXPayReq{" +
                "appId='$appId'" +
                ", partnerId='$partnerId'" +
                ", prepayId='$prepayId'" +
                ", packageValue='$packageValue'" +
                ", nonceStr='$nonceStr'" +
                ", timestamp='$timestamp'" +
                ", sign='$sign'" +
                '}'
    }
}
