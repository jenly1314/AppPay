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
class WXAPI private constructor(context: Context) {

    private val api: IWXAPI = WXAPIFactory.createWXAPI(context, null)

    private var registerApp: Boolean = false

    private var appId: String? = null

    private var onPayListener: WXPay.OnPayListener? = null

    companion object {
        @Volatile
        private var instance: WXAPI? = null

        /**
         * 获取单例
         *
         * @param context
         * @return [WXAPI]
         */
        @JvmStatic
        fun getInstance(context: Context): WXAPI {
            return instance ?: synchronized(WXAPI::class.java) {
                instance ?: WXAPI(context).also { instance = it }
            }
        }
    }

    /**
     * 注册App
     *
     * @param appId 申请的微信appId
     */
    fun registerApp(appId: String): WXAPI {
        if (!(registerApp && TextUtils.equals(this.appId, appId))) {
            this.appId = appId
            registerApp = api.registerApp(appId)
        }
        return this
    }

    /**
     * 获取 [IWXAPI]
     */
    fun getApi(): IWXAPI = api

    /**
     * 设置支付监听
     */
    fun setOnPayListener(listener: WXPay.OnPayListener?): WXAPI {
        this.onPayListener = listener
        return this
    }

    /**
     * 响应结果处理
     *
     * @param code
     * @param errorMessage
     */
    fun onResp(code: Int, errorMessage: String?) {
        onPayListener?.onPayResult(WXPayResult(code, errorMessage))
        onPayListener = null
    }
}
