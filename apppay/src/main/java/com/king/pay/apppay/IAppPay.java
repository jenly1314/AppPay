package com.king.pay.apppay;

import android.support.annotation.NonNull;

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
    void sendWXPayReq(@NonNull WXPayReq req);

    /**
     * 发送支付宝支付请求
     * @param req
     */
    void sendAliPayReq(@NonNull AliPayReq req);

    /**
     * 发送支付宝支付请求
     * @param orderInfo
     */
    void sendAliPayReq(@NonNull String orderInfo);

    /**
     * 检测支付宝授权
     * @param req
     */
    void checkAliAuth(@NonNull AliAuthReq req);

    /**
     * 检测支付宝授权
     * @param authInfo
     */
    void checkAliAuth(@NonNull String authInfo);
}
