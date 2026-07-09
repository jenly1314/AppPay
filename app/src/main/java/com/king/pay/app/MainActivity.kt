package com.king.pay.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.king.pay.apppay.AppPay
import com.king.pay.wxpay.WXPayReq

/**
 * AppPay示例
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
open class MainActivity : AppCompatActivity() {

    lateinit var mAppPay: AppPay

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAppPay = AppPay(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 当使用银联支付时，需要在 Activity 中的 onActivityResult 方法中调用此方法，来接收支付结果监听回调
        mAppPay.onActivityResult(resultCode, resultCode, data)
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    /**
     * 微信支付
     * <p>
     * 官方接入文档：https://developers.weixin.qq.com/doc/oplatform/Mobile_App/Access_Guide/Android.html
     */
    private fun clickBtnWXPay() {
        // TODO  配置好微信支付请求相关的参数,发送微信支付请求
        // 微信请求相关属性务必放在服务端，通过预支付下单接口返回相关参数，这样比较安全。
        val req = WXPayReq()
//       req.setAppId("")
//       mAppPay.sendWXPayReq(req, object : WXPay.OnPayListener {
//           override fun onPayResult(result: WXPayResult) {
//               // 支付结果
//               if (result.isSuccess()) {
//                   // TODO 支付成功
//
//                   // 务必以服务端结果为准
//               }
//           }
//       })

        showToast("请配置微信支付请求相关参数")
    }

    /**
     * 支付宝支付
     * <p>
     * 官网文档可参见：https://docs.open.alipay.com/204/105296/
     */
    private fun clickBtnAliPay() {
        // TODO  配置好支付宝支付请求订单信息相关的参数,发送支付宝支付请求（订单信息一般从后台获取）

//        val orderInfo = ""
//        mAppPay.sendAliPayReq(orderInfo, object : AliPay.OnPayListener {
//            override fun onPayResult(result: AliPayResult) {
//                // 支付结果
//                if (result.isSuccess()) {
//                    // TODO 支付成功
//
//                    // 务必以服务端结果为准
//                }
//            }
//        })

        showToast("请配置支付宝支付订单信息")
    }

    /**
     * 银联支付
     */
    private fun clickBtnUnionPay() {
        // TODO 银联支付；订单信息为交易流水号，即TN，为商户后台从银联后台获取。

//        val orderInfo = ""
//        mAppPay.sendUnionPayReq(orderInfo, object : UnionPay.OnPayListener {
//            override fun onPayResult(result: UnionPayResult) {
//
//            }
//        })

        showToast("请配置银联支付订单信息")
    }

    fun onClick(v: View) {
        val id = v.id
        when (id) {
            R.id.btnWXPay -> clickBtnWXPay()
            R.id.btnAliPay -> clickBtnAliPay()
            R.id.btnUnionPay -> clickBtnUnionPay()
        }
    }
}
