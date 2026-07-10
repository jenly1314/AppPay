package com.king.pay.unionpay

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.unionpay.UPPayAssistEx

/**
 * 银联支付
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
@Suppress("unused")
open class UnionPay(private var mContext: Context) {

    private var mOnPayListener: OnPayListener? = null

    /**
     * 发送支付请求；
     *
     * @param orderInfo  订单信息为交易流水号，即TN，为商户后台从银联后台获取。
     * @param serverMode 银联后台环境标识；用于区分使用测试环境还是正式环境；说明参见：[PRO_SERVER_MODE] 和 [TEST_SERVER_MODE]
     */
    fun sendReq(orderInfo: String, serverMode: String) {
        UPPayAssistEx.startPay(mContext, null, null, orderInfo, serverMode)
    }

    /**
     * 发送支付请求；
     *
     * @param orderInfo  订单信息为交易流水号，即TN，为商户后台从银联后台获取。
     * @param serverMode 银联后台环境标识；用于区分使用测试环境还是正式环境；说明参见：[PRO_SERVER_MODE] 和 [TEST_SERVER_MODE]
     * @param listener   监听器
     */
    fun sendReq(orderInfo: String, serverMode: String, listener: OnPayListener) {
        setOnPayListener(listener)
        UPPayAssistEx.startPay(mContext, null, null, orderInfo, serverMode)
    }

    /**
     * 在 [Activity] 中的 onActivityResult 方法中调用此方法，来接收支付结果监听回调
     */
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data != null && mOnPayListener != null) {
            val result = data.getStringExtra(PAY_RESULT)
            if (PAY_SUCCESS.equals(result, ignoreCase = true)) {
                mOnPayListener!!.onPayResult(UnionPayResult(true, result))
            } else if (PAY_FAIL.equals(result, ignoreCase = true) || PAY_CANCEL.equals(result, ignoreCase = true)) {
                mOnPayListener!!.onPayResult(UnionPayResult(false, result))
            }
        }
    }

    /**
     * 设置支付监听；记得在 [Activity] 中的 onActivityResult 方法中调用 [UnionPay.onActivityResult] 方法，这样设置的支付监听才会被触发。
     *
     * @param listener 监听器
     */
    fun setOnPayListener(listener: OnPayListener) {
        this.mOnPayListener = listener
    }

    /**
     * 银联支付监听
     */
    interface OnPayListener {
        /**
         * 支付结果回调
         *
         * @param result 支付结果
         */
        fun onPayResult(result: UnionPayResult)
    }

    companion object {
        /**
         * 银联测试环境，该环境中不发生真实交易
         */
        const val TEST_SERVER_MODE = "01"

        /**
         * 银联生产环境模式，该环境中发起真实交易
         */
        const val PRO_SERVER_MODE = "00"

        /**
         * 支付结果
         */
        private const val PAY_RESULT = "pay_result"

        /**
         * 支付成功
         */
        private const val PAY_SUCCESS = "success"

        /**
         * 支付失败
         */
        private const val PAY_FAIL = "fail"

        /**
         * 支付取消
         */
        private const val PAY_CANCEL = "cancel"
    }
}
