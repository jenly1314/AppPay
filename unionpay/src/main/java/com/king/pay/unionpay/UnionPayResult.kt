package com.king.pay.unionpay

/**
 * 银联支付结果
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
open class UnionPayResult internal constructor(
    private val success: Boolean,
    private val message: String?
) {

    /**
     * 是否成功
     */
    fun isSuccess(): Boolean = success

    /**
     * 结果信息
     */
    fun getMessage(): String? = message

    override fun toString(): String {
        return "UnionPayResult{" +
                "success=$success" +
                ", message='$message'" +
                '}'
    }
}
