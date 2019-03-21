package com.king.pay.wxpay;

import android.content.Context;
import android.support.annotation.NonNull;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * 微信支付
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class WXPay {

    private IWXAPI mApi;

    public WXPay(@NonNull Context context,@NonNull String appId){
        mApi = WXAPIFactory.createWXAPI(context,appId);

    }

    /**
     * 发送支付请求
     * @param req
     */
    public void sendReq(@NonNull WXPayReq req){
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
     * 发送支付请求
     * @param req
     */
    private void sendReq(@NonNull BaseReq req){
        if(req.checkArgs()){
            mApi.sendReq(req);
        }
    }

    public IWXAPI getIWXApi(){
        return mApi;
    }

}
