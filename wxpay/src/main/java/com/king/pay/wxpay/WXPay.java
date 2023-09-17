package com.king.pay.wxpay;

import android.content.Context;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * 微信支付
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
@SuppressWarnings("unused")
public class WXPay {

    private IWXAPI mApi;

    public WXPay(Context context, String appId) {
        mApi = WXAPIFactory.createWXAPI(context, appId);
        mApi.registerApp(appId);
    }

    /**
     * 发送支付请求；SDK拉起微信支付
     *
     * @param req 支付请求参数
     */
    public void sendReq(WXPayReq req) {
        PayReq request = new PayReq();
        request.appId = req.getAppId();
        request.partnerId = req.getPartnerId();
        request.prepayId = req.getPrepayId();
        request.packageValue = req.getPackageValue();
        request.nonceStr = req.getNonceStr();
        request.timeStamp = req.getTimestamp();
        request.sign = req.getSign();
        sendReq(request);
    }

    /**
     * 发送支付请求；SDK拉起微信支付
     *
     * @param req 支付请求参数
     */
    public void sendReq(PayReq req) {
        if (req.checkArgs()) {
            mApi.sendReq(req);
        }
    }

    /**
     * 获取IWXAPI
     *
     * @return {@link IWXAPI}
     */
    public IWXAPI getIWXApi() {
        return mApi;
    }

}
