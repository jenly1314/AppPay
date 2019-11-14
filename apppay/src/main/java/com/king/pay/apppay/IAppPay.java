package com.king.pay.apppay;

import com.king.pay.alipay.AliAuthReq;
import com.king.pay.alipay.AliPayReq;
import com.king.pay.wxpay.WXPayReq;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public interface IAppPay {

    /**
     * 发送微信支付请求
     * @param req
     */
    void sendWXPayReq(WXPayReq req);

    /**
     * 发送支付宝支付请求
     * @param req
     */
    void sendAliPayReq(AliPayReq req);

    /**
     * 发送支付宝支付请求
     * @param orderInfo
     */
    void sendAliPayReq(String orderInfo);

    /**
     * 检测支付宝授权
     * @param req
     */
    void checkAliAuth(AliAuthReq req);

    /**
     * 检测支付宝授权
     * @param authInfo
     */
    void checkAliAuth(String authInfo);
}
