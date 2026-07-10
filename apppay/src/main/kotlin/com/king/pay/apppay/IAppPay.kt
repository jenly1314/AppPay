package com.king.pay.apppay

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
interface IAppPay {

    /**
     * 发送微信支付请求
     *
     * @param req 支付请求参数
     */
    fun sendWXPayReq(req: WXPayReq)

    /**
     * 发送微信支付请求
     *
     * @param req 支付请求参数
     * @param listener 监听器
     */
    fun sendWXPayReq(req: WXPayReq, listener: WXPay.OnPayListener)

    /**
     * 发送支付宝支付请求
     *
     * @param orderInfo 订单信息
     */
    fun sendAliPayReq(orderInfo: String)

    /**
     * 发送支付宝支付请求
     *
     * @param orderInfo 订单信息
     * @param listener 监听器
     */
    fun sendAliPayReq(orderInfo: String, listener: AliPay.OnPayListener)

    /**
     * 检测支付宝授权
     *
     * @param authInfo 授权信息
     */
    fun checkAliAuth(authInfo: String)

    /**
     * 检测支付宝授权
     *
     * @param authInfo 授权信息
     * @param listener 监听器
     */
    fun checkAliAuth(authInfo: String, listener: AliPay.OnAuthListener)

    /**
     * 发起银联支付请求
     *
     * @param orderInfo 订单信息为交易流水号，即TN，为商户后台从银联后台获取。
     */
    fun sendUnionPayReq(orderInfo: String)

    /**
     * 发起银联支付请求
     *
     * @param orderInfo 订单信息为交易流水号，即TN，为商户后台从银联后台获取。
     * @param listener 监听器
     */
    fun sendUnionPayReq(orderInfo: String, listener: UnionPay.OnPayListener)

    /**
     * 发起银联支付请求
     *
     * @param orderInfo 订单信息为交易流水号，即TN，为商户后台从银联后台获取。
     * @param serverMode 银联后台环境标识；用于区分使用测试环境还是正式环境；说明参见：[UnionPay.PRO_SERVER_MODE] 和 [UnionPay.TEST_SERVER_MODE]
     */
    fun sendUnionPayReq(orderInfo: String, serverMode: String)

    /**
     * 发起银联支付请求
     *
     * @param orderInfo 订单信息为交易流水号，即TN，为商户后台从银联后台获取。
     * @param serverMode 银联后台环境标识；用于区分使用测试环境还是正式环境；说明参见：[UnionPay.PRO_SERVER_MODE] 和 [UnionPay.TEST_SERVER_MODE]
     * @param listener 监听器
     */
    fun sendUnionPayReq(orderInfo: String, serverMode: String, listener: UnionPay.OnPayListener)
}
