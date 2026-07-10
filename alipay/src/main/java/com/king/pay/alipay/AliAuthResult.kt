package com.king.pay.alipay

import android.text.TextUtils

/**
 * 参见支付宝支付的demo
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
@Suppress("unused")
open class AliAuthResult(rawResult: Map<String, String>?, removeBrackets: Boolean) {

    var resultStatus: String? = null
        private set
    var result: String? = null
        private set
    var memo: String? = null
        private set
    var resultCode: String? = null
        private set
    var authCode: String? = null
        private set
    var alipayOpenId: String? = null
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

            val resultValue = result?.split("&") ?: emptyList()
            for (value in resultValue) {
                when {
                    value.startsWith("alipay_open_id") -> {
                        alipayOpenId = removeBrackets(getValue("alipay_open_id=", value), removeBrackets)
                        continue
                    }
                    value.startsWith("auth_code") -> {
                        authCode = removeBrackets(getValue("auth_code=", value), removeBrackets)
                        continue
                    }
                    value.startsWith("result_code") -> {
                        resultCode = removeBrackets(getValue("result_code=", value), removeBrackets)
                        continue
                    }
                }
            }
        }
    }

    private fun removeBrackets(str: String?, remove: Boolean): String? {
        var s = str
        if (remove && !TextUtils.isEmpty(s)) {
            if (s!!.startsWith("\"")) {
                s = s.replaceFirst("\"", "")
            }
            if (s.endsWith("\"")) {
                s = s.substring(0, s.length - 1)
            }
        }
        return s
    }

    override fun toString(): String {
        return "authCode={$authCode}; resultStatus={$resultStatus}; memo={$memo}; result={$result}"
    }

    /**
     * 是否成功；判断resultStatus 为"9000"且result_code为"200"则代表授权成功，具体状态码代表含义可参考授权接口文档
     */
    fun isSuccess(): Boolean {
        return TextUtils.equals(resultStatus, "9000") && TextUtils.equals(resultCode, "200")
    }

    private fun getValue(header: String, data: String): String {
        return data.substring(header.length, data.length)
    }

    /**
     * @return the resultStatus
     */
    fun getResultStatus(): String? = resultStatus

    /**
     * @return the memo
     */
    fun getMemo(): String? = memo

    /**
     * @return the result
     */
    fun getResult(): String? = result

    /**
     * @return the resultCode
     */
    fun getResultCode(): String? = resultCode

    /**
     * @return the authCode
     */
    fun getAuthCode(): String? = authCode

    /**
     * @return the alipayOpenId
     */
    fun getAlipayOpenId(): String? = alipayOpenId
}
