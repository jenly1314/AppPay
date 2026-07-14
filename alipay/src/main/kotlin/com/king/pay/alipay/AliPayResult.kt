package com.king.pay.alipay

import android.text.TextUtils

/**
 * 参见支付宝支付的demo
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
class AliPayResult(rawResult: Map<String, String>?) {

    var resultStatus: String? = null
        private set
    var result: String? = null
        private set
    var memo: String? = null
        private set

    init {
        if (rawResult != null) {
            for (key in rawResult.keys) {
                when {
                    TextUtils.equals(key, "resultStatus") -> resultStatus = rawResult[key]
                    TextUtils.equals(key, "result") -> result = rawResult[key]
                    TextUtils.equals(key, "memo") -> memo = rawResult[key]
                }
            }
        }
    }

    override fun toString(): String {
        return "resultStatus={$resultStatus};memo={$memo};result={$result}"
    }

    /**
     * 是否成功；判断resultStatus 为9000则代表支付成功；该笔订单是否真实支付成功，需要依赖服务端的异步通知。
     */
    fun isSuccess(): Boolean = TextUtils.equals(resultStatus, "9000")
}
