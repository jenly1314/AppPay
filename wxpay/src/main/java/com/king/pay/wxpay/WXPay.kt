package com.king.pay.wxpay

import android.content.Context
import com.tencent.mm.opensdk.modelpay.PayReq

/**
 * 微信支付
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
@Suppress("unused")
open class WXPay(private var mContext: Context) {

    private var mOnPayListener: OnPayListener? = null

    /**
     * 构造
     *
     * @param context
     * @param appId
     */
    @Deprecated("请使用 WXPay(Context)")
    constructor(context: Context, appId: String) : this(context) {
        WXAPI.getInstance(context).registerApp(appId)
    }

    /**
     * 发送支付请求；SDK拉起微信支付
     *
     * @param req 支付请求参数
     */
    fun sendReq(req: WXPayReq) {
        val request = PayReq()
        request.appId = req.appId
        request.partnerId = req.partnerId
        request.prepayId = req.prepayId
        request.packageValue = req.packageValue
        request.nonceStr = req.nonceStr
        request.timeStamp = req.timestamp
        request.sign = req.sign
        sendReq(request)
    }

    /**
     * 发送支付请求；SDK拉起微信支付
     *
     * @param req 支付请求参数
     */
    fun sendReq(req: PayReq) {
        if (req.checkArgs()) {
            WXAPI.getInstance(mContext)
                .registerApp(req.appId)
                .setOnPayListener(mOnPayListener)
                .getApi()
                .sendReq(req)
        }
    }

    /**
     * 发送支付请求；SDK拉起微信支付
     *
     * @param req      请求参数
     * @param listener 监听器
     */
    fun sendReq(req: WXPayReq, listener: OnPayListener) {
        setOnPayListener(listener)
        sendReq(req)
    }

    /**
     * 发送支付请求；SDK拉起微信支付
     *
     * @param req      请求参数
     * @param listener 监听器
     */
    fun sendReq(req: PayReq, listener: OnPayListener) {
        setOnPayListener(listener)
        sendReq(req)
    }

    /**
     * 设置支付监听
     *
     * @param listener 监听器
     */
    fun setOnPayListener(listener: OnPayListener?) {
        this.mOnPayListener = listener
        WXAPI.getInstance(mContext).setOnPayListener(mOnPayListener)
    }

    /**
     * 微信支付监听
     */
    interface OnPayListener {
        /**
         * 支付结果回调
         *
         * @param result 支付结果
         */
        fun onPayResult(result: WXPayResult)
    }
}
