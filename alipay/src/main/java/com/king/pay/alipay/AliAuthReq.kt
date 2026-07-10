package com.king.pay.alipay

/**
 * 授权请求参数
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
@Suppress("unused")
open class AliAuthReq {

    /**
     * 商户签约拿到的app_id
     */
    var appId: String? = null
        private set

    /**
     * 商户签约拿到的pid
     */
    var pid: String? = null
        private set

    /**
     * 服务接口名称
     */
    var apiname: String? = "com.alipay.account.auth"
        private set

    /**
     * 服务接口名称， 固定值
     */
    var methodname: String? = "alipay.open.auth.sdk.code.get"
        private set

    /**
     * 商户类型标识
     */
    var appName: String? = "mc"
        private set

    /**
     * 业务类型
     */
    var bizType: String? = "openservice"
        private set

    /**
     * 产品码
     */
    var productId: String? = "APP_FAST_LOGIN"
        private set

    /**
     * 授权范围
     */
    var scope: String? = "kuaijie"
        private set

    /**
     * 商户唯一标识
     */
    var targetId: String? = null
        private set

    /**
     * 授权类型
     */
    var authType: String? = "AUTHACCOUNT"
        private set

    /**
     * 签名类型
     */
    var signType: String? = "RSA2"
        private set

    /**
     * 签名
     */
    var sign: String? = null
        private set

    fun setAppId(appId: String?): AliAuthReq = apply { this.appId = appId }

    fun setPid(pid: String?): AliAuthReq = apply { this.pid = pid }

    fun setApiname(apiname: String?): AliAuthReq = apply { this.apiname = apiname }

    fun setMethodname(methodname: String?): AliAuthReq = apply { this.methodname = methodname }

    fun setAppName(appName: String?): AliAuthReq = apply { this.appName = appName }

    fun setBizType(bizType: String?): AliAuthReq = apply { this.bizType = bizType }

    fun setProductId(productId: String?): AliAuthReq = apply { this.productId = productId }

    fun setScope(scope: String?): AliAuthReq = apply { this.scope = scope }

    fun setTargetId(targetId: String?): AliAuthReq = apply { this.targetId = targetId }

    fun setAuthType(authType: String?): AliAuthReq = apply { this.authType = authType }

    fun setSignType(signType: String?): AliAuthReq = apply { this.signType = signType }

    fun setSign(sign: String?): AliAuthReq = apply { this.sign = sign }
}
