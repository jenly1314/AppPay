package com.king.pay.wxpay.wxapi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.king.pay.wxpay.WXAPI
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler

/**
 * 微信支付需在项目包路径中的wxapi目录中实现一个WXPayEntryActivity类来处理微信响应结果
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
open class WXPayEntryActivity : Activity(), IWXAPIEventHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WXAPI.getInstance(this).getApi().handleIntent(intent, this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        WXAPI.getInstance(this).getApi().handleIntent(intent, this)
    }

    /**
     * 支付请求
     */
    override fun onReq(baseReq: BaseReq) {
    }

    /**
     * 支付响应
     */
    override fun onResp(baseResp: BaseResp) {
        if (baseResp.type == ConstantsAPI.COMMAND_PAY_BY_WX) {
            onPayResult(baseResp.errCode, baseResp.errStr)
        }
    }

    /**
     * 支付结果，注意一定不能以客户端返回作为用户支付的结果，应以服务器端的接收的支付通知或查询API返回的结果为准
     *
     * @param code  0.成功
     *              -1.错误
     *              -2.用户取消
     * @param error 错误描述
     */
    open fun onPayResult(code: Int, error: String?) {
        WXAPI.getInstance(this).onResp(code, error)
        finish()
    }
}
