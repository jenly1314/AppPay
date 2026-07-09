package com.king.pay.wxpay

import android.content.Context
import android.text.TextUtils
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory

/**
 * 微信API
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
open class WXAPI private constructor(context: Context) {

    private val mApi: IWXAPI = WXAPIFactory.createWXAPI(context, null)

    private var registerApp: Boolean = false

    private var appId: String? = null

    private var mOnPayListener: WXPay.OnPayListener? = null

    companion object {
        @Volatile
        private var sInstance: WXAPI? = null

        /**
         * 获取单例
         *
         * @param context
         * @return [WXAPI]
         */
        @JvmStatic
        fun getInstance(context: Context): WXAPI {
            return sInstance ?: synchronized(WXAPI::class.java) {
                sInstance ?: WXAPI(context).also { sInstance = it }
            }
        }
    }

    /**
     * 注册App
     *
     * @param appId 申请的微信appId
     */
    fun registerApp(appId: String?): WXAPI {
        if (!(registerApp && TextUtils.equals(this.appId, appId))) {
            this.appId = appId
            registerApp = mApi.registerApp(appId)
        }
        return this
    }

    /**
     * 获取 [IWXAPI]
     */
    fun getApi(): IWXAPI = mApi

    /**
     * 设置支付监听
     */
    fun setOnPayListener(listener: WXPay.OnPayListener?): WXAPI {
        this.mOnPayListener = listener
        return this
    }

    /**
     * 响应结果处理
     *
     * @param code
     * @param errorMessage
     */
    fun onResp(code: Int, errorMessage: String?) {
        mOnPayListener?.let {
            it.onPayResult(WXPayResult(code, errorMessage))
            mOnPayListener = null
        }
    }
}
