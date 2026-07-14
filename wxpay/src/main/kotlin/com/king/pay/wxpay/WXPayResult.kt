package com.king.pay.wxpay

/**
 * 微信支付结果
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
data class WXPayResult(
    private val code: Int,
    private val message: String?
) {

    /**
     * 是否成功
     */
    fun isSuccess(): Boolean = code == 0

    /**
     * 结果码
     *
     * @return 结果码；0.成功、-1.错误、-2.用户取消
     */
    fun getCode(): Int = code

    /**
     * 结果信息
     */
    fun getMessage(): String? = message
    
}
