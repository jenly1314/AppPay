package com.king.pay.apppay

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.king.pay.alipay.AliPay
import com.king.pay.unionpay.UnionPay
import com.king.pay.wxpay.WXPay
import com.king.pay.wxpay.WXPayReq

/**
 * AppPay主要包含微信支付、支付宝支付，让App集成支付功能变的更简单。
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
@Suppress("unused")
open class AppPay(private var mActivity: Activity) : IAppPay {

    @Volatile
    private var mWXPay: WXPay? = null

    @Volatile
    private var mAliPay: AliPay? = null

    @Volatile
    private var mUnionPay: UnionPay? = null

    /**
     * 初始化WXPay
     */
    private fun initWXPay(context: Context) {
        if (mWXPay == null) {
            synchronized(AppPay::class.java) {
                if (mWXPay == null) {
                    mWXPay = WXPay(context)
                }
            }
        }
    }

    /**
     * 初始化AliPay
     */
    private fun initAliPay(activity: Activity) {
        if (mAliPay == null) {
            synchronized(AppPay::class.java) {
                if (mAliPay == null) {
                    mAliPay = AliPay(activity)
                }
            }
        }
    }

    /**
     * 初始化UnionPay
     */
    private fun initUnionPay(activity: Activity) {
        if (mUnionPay == null) {
            synchronized(AppPay::class.java) {
                if (mUnionPay == null) {
                    mUnionPay = UnionPay(activity)
                }
            }
        }
    }

    override fun sendWXPayReq(req: WXPayReq) {
        getWXPay().sendReq(req)
    }

    override fun sendWXPayReq(req: WXPayReq, listener: WXPay.OnPayListener) {
        getWXPay().sendReq(req, listener)
    }

    override fun sendAliPayReq(orderInfo: String) {
        getAliPay().sendReq(orderInfo)
    }

    override fun sendAliPayReq(orderInfo: String, listener: AliPay.OnPayListener) {
        getAliPay().sendReq(orderInfo, listener)
    }

    override fun checkAliAuth(authInfo: String) {
        getAliPay().checkAuth(authInfo)
    }

    override fun checkAliAuth(authInfo: String, listener: AliPay.OnAuthListener) {
        getAliPay().checkAuth(authInfo, listener)
    }

    override fun sendUnionPayReq(orderInfo: String) {
        sendUnionPayReq(orderInfo, UnionPay.PRO_SERVER_MODE)
    }

    override fun sendUnionPayReq(orderInfo: String, listener: UnionPay.OnPayListener) {
        getUnionPay().sendReq(orderInfo, UnionPay.PRO_SERVER_MODE, listener)
    }

    override fun sendUnionPayReq(orderInfo: String, serverMode: String) {
        getUnionPay().sendReq(orderInfo, serverMode)
    }

    override fun sendUnionPayReq(orderInfo: String, serverMode: String, listener: UnionPay.OnPayListener) {
        getUnionPay().sendReq(orderInfo, serverMode, listener)
    }

    /**
     * 设置微信支付监听
     */
    fun setOnWXPayListener(listener: WXPay.OnPayListener): AppPay {
        getWXPay().setOnPayListener(listener)
        return this
    }

    /**
     * 设置支付宝授权监听
     */
    fun setOnAliPayAuthListener(listener: AliPay.OnAuthListener): AppPay {
        getAliPay().setOnAuthListener(listener)
        return this
    }

    /**
     * 设置支付宝支付监听
     */
    fun setOnAliPayListener(listener: AliPay.OnPayListener): AppPay {
        getAliPay().setOnPayListener(listener)
        return this
    }

    /**
     * 设置银联支付监听；记得在 [Activity] 中的 onActivityResult 方法中调用 [AppPay.onActivityResult] 方法，这样设置的银联支付监听才会被触发。
     */
    fun setOnUnionPayListener(listener: UnionPay.OnPayListener): AppPay {
        getUnionPay().setOnPayListener(listener)
        return this
    }

    /**
     * 当使用银联支付时，需要在 [Activity] 中的 onActivityResult 方法中调用此方法，来接收支付结果监听回调
     */
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        getUnionPay().onActivityResult(requestCode, resultCode, data)
    }

    /**
     * 获取 AliPay
     */
    fun getAliPay(): AliPay {
        initAliPay(mActivity)
        return mAliPay!!
    }

    /**
     * 获取 WXPay
     */
    fun getWXPay(): WXPay {
        initWXPay(mActivity)
        return mWXPay!!
    }

    /**
     * 获取 UnionPay
     */
    fun getUnionPay(): UnionPay {
        initUnionPay(mActivity)
        return mUnionPay!!
    }
}
