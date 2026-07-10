package com.king.pay.alipay

import android.app.Activity
import android.os.Handler
import android.os.Looper
import com.alipay.sdk.app.AuthTask
import com.alipay.sdk.app.PayTask
import com.king.pay.alipay.util.OrderInfoUtils
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * 支付宝支付
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
@Suppress("unused")
open class AliPay(private var mActivity: Activity) {

    private val mHandler: Handler = Handler(Looper.getMainLooper())

    private var mOnAuthListener: OnAuthListener? = null

    private var mOnPayListener: OnPayListener? = null

    private val mExecutor: Executor = Executors.newSingleThreadExecutor()

    /**
     * 发送支付请求，因为订单参数与签名加密前内容关联密切，所以强烈建议服务端直接返回拼接后的orderInfo，使用[sendReq]
     */
    fun sendReq(req: AliPayReq) {
        sendReq(OrderInfoUtils.buildOrderInfo(req))
    }

    /**
     * 发送支付请求；SDK拉起支付宝支付
     *
     * @param orderInfo 订单信息
     */
    fun sendReq(orderInfo: String) {
        mExecutor.execute {
            val aliPay = PayTask(mActivity)
            val result = aliPay.payV2(orderInfo, true)
            val aliPayResult = AliPayResult(result)

            // 对于支付结果，请商户依赖服务端的异步通知结果。此处的同步通知结果，仅作为支付结束的通知。
            mHandler.post {
                // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                mOnPayListener?.onPayResult(aliPayResult)
            }
        }
    }

    /**
     * 发送支付请求；SDK拉起支付宝支付
     *
     * @param orderInfo 订单信息
     * @param listener  监听器
     */
    fun sendReq(orderInfo: String, listener: OnPayListener) {
        setOnPayListener(listener)
        sendReq(orderInfo)
    }

    /**
     * 支付宝授权业务检测，因为授权参数与签名加密前内容关联密切，所以强烈建议服务端直接返回拼接后的authInfo，使用[checkAuth]
     */
    fun checkAuth(req: AliAuthReq) {
        checkAuth(OrderInfoUtils.buildAuthInfo(req))
    }

    /**
     * 支付宝账户授权业务检测
     *
     * @param authInfo 授权信息
     */
    fun checkAuth(authInfo: String) {
        mExecutor.execute {
            // 构造AuthTask 对象
            val authTask = AuthTask(mActivity)
            // 调用授权接口，获取授权结果
            val result = authTask.authV2(authInfo, true)
            val aliAuthResult = AliAuthResult(result, true)

            mHandler.post {
                mOnAuthListener?.onAuthResult(aliAuthResult)
            }
        }
    }

    /**
     * 支付宝账户授权业务检测
     *
     * @param authInfo 授权信息
     * @param listener 监听器
     */
    fun checkAuth(authInfo: String, listener: OnAuthListener) {
        setOnAuthListener(listener)
        checkAuth(authInfo)
    }

    /**
     * 设置授权监听
     *
     * @param listener 监听器
     */
    fun setOnAuthListener(listener: OnAuthListener): AliPay {
        this.mOnAuthListener = listener
        return this
    }

    /**
     * 设置支付监听
     *
     * @param listener 监听器
     */
    fun setOnPayListener(listener: OnPayListener): AliPay {
        this.mOnPayListener = listener
        return this
    }

    /**
     * 授权监听
     */
    interface OnAuthListener {
        /**
         * 授权结果回调
         *
         * @param result 授权结果
         */
        fun onAuthResult(result: AliAuthResult)
    }

    /**
     * 支付监听
     */
    interface OnPayListener {
        /**
         * 支付结果回调
         *
         * @param result 是否结果
         */
        fun onPayResult(result: AliPayResult)
    }
}
