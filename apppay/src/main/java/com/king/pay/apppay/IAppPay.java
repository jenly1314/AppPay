package com.king.pay.apppay;

import com.king.pay.alipay.AliPay;
import com.king.pay.wxpay.WXPay;
import com.king.pay.wxpay.WXPayReq;
import com.king.pay.unionpay.UnionPay;

/**
 * AppPay主要包含微信支付、支付宝支付，让App集成支付功能变的更简单。
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
@SuppressWarnings("unused")
public interface IAppPay {

    /**
     * 发送微信支付请求
     *
     * @param req 支付请求参数
     */
    void sendWXPayReq(WXPayReq req);

    /**
     * 发送微信支付请求
     *
     * @param req 支付请求参数
     * @param listener 监听器
     */
    void sendWXPayReq(WXPayReq req, WXPay.OnPayListener listener);

    /**
     * 发送支付宝支付请求
     *
     * @param orderInfo 订单信息
     */
    void sendAliPayReq(String orderInfo);

    /**
     * 发送支付宝支付请求
     *
     * @param orderInfo 订单信息
     * @param listener 监听器
     */
    void sendAliPayReq(String orderInfo, AliPay.OnPayListener listener);

    /**
     * 检测支付宝授权
     *
     * @param authInfo 授权信息
     */
    void checkAliAuth(String authInfo);

    /**
     * 检测支付宝授权
     *
     * @param authInfo 授权信息
     * @param listener 监听器
     */
    void checkAliAuth(String authInfo, AliPay.OnAuthListener listener);

    /**
     * 发起银联支付请求
     *
     * @param orderInfo 订单信息为交易流水号，即TN，为商户后台从银联后台获取。
     */
    void sendUnionPayReq(String orderInfo);

    /**
     * 发起银联支付请求
     *
     * @param orderInfo 订单信息为交易流水号，即TN，为商户后台从银联后台获取。
     * @param listener 监听器
     */
    void sendUnionPayReq(String orderInfo, UnionPay.OnPayListener listener);

    /**
     * 发起银联支付请求
     *
     * @param orderInfo 订单信息为交易流水号，即TN，为商户后台从银联后台获取。
     * @param serverMode 银联后台环境标识；用于区分使用测试环境还是正式环境；说明参见：{@link UnionPay#PRO_SERVER_MODE} 和 {@link UnionPay#TEST_SERVER_MODE}
     */
    void sendUnionPayReq(String orderInfo, String serverMode);

    /**
     * 发起银联支付请求
     *
     * @param orderInfo 订单信息为交易流水号，即TN，为商户后台从银联后台获取。
     * @param serverMode 银联后台环境标识；用于区分使用测试环境还是正式环境；说明参见：{@link UnionPay#PRO_SERVER_MODE} 和 {@link UnionPay#TEST_SERVER_MODE}
     * @param listener 监听器
     */
    void sendUnionPayReq(String orderInfo, String serverMode, UnionPay.OnPayListener listener);
}
