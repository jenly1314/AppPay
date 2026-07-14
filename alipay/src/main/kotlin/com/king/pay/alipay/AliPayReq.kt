package com.king.pay.alipay

/**
 * 参数说明请参见：<a href="https://docs.open.alipay.com/204/105465/">支付宝支付请求参数说明</a>
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
class AliPayReq {

    /**
     * 支付宝支付分配的应用ID
     */
    var appId: String? = null
        private set

    /**
     * 接口名称 示例值：alipay.trade.app.pay
     */
    var method: String = "alipay.trade.app.pay"
        private set

    /**
     * 格式
     */
    var format: String = "JSON"
        private set

    /**
     * 请求使用的编码格式，如utf-8,gbk,gb2312等
     */
    var charset: String = "utf-8"
        private set

    /**
     * 签名类型 示例值：RSA2
     */
    var signType: String = "RSA2"
        private set

    /**
     * 签名
     */
    var sign: String? = null
        private set

    /**
     * 时间戳
     */
    var timestamp: String? = null
        private set

    /**
     * 调用的接口版本，固定为：1.0
     */
    var version: String = "1.0"
        private set

    /**
     * 支付宝服务器主动通知商户服务器里指定的页面http/https路径。建议商户使用https
     */
    var notifyUrl: String? = null
        private set

    /**
     * 业务请求参数的集合
     */
    var bizContent: String? = null
        private set

    fun setAppId(appId: String?): AliPayReq = apply { this.appId = appId }

    fun setMethod(method: String): AliPayReq = apply { this.method = method }

    fun setFormat(format: String): AliPayReq = apply { this.format = format }

    fun setCharset(charset: String): AliPayReq = apply { this.charset = charset }

    fun setSignType(signType: String): AliPayReq = apply { this.signType = signType }

    fun setSign(sign: String?): AliPayReq = apply { this.sign = sign }

    fun setTimestamp(timestamp: String?): AliPayReq = apply { this.timestamp = timestamp }

    fun setVersion(version: String): AliPayReq = apply { this.version = version }

    fun setNotifyUrl(notifyUrl: String?): AliPayReq = apply { this.notifyUrl = notifyUrl }

    fun setBizContent(bizContent: String?): AliPayReq = apply { this.bizContent = bizContent }
}
