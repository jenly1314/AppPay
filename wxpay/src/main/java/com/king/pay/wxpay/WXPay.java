package com.king.pay.wxpay;

import android.content.Context;

import com.tencent.mm.opensdk.modelpay.PayReq;

/**
 * 微信支付
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
@SuppressWarnings("unused")
public class WXPay {

    private Context mContext;

    private OnPayListener mOnPayListener;

    /**
     * 构造
     *
     * @param context
     */
    public WXPay(Context context) {
        this.mContext = context;
    }

    /**
     * 构造
     *
     * @param context
     * @param appId
     * @deprecated 请使用 {@link #WXPay(Context)}
     */
    @Deprecated
    public WXPay(Context context, String appId) {
        this.mContext = context;
        WXAPI.getInstance(context).registerApp(appId);
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
            WXAPI.getInstance(mContext)
                    .registerApp(req.appId)
                    .setOnPayListener(mOnPayListener)
                    .getApi()
                    .sendReq(req);
        }
    }

    /**
     * 发送支付请求；SDK拉起微信支付
     *
     * @param req      请求参数
     * @param listener 监听器
     */
    public void sendReq(WXPayReq req, OnPayListener listener) {
        setOnPayListener(listener);
        sendReq(req);
    }

    /**
     * 发送支付请求；SDK拉起微信支付
     *
     * @param req      请求参数
     * @param listener 监听器
     */
    public void sendReq(PayReq req, OnPayListener listener) {
        setOnPayListener(listener);
        sendReq(req);
    }

    /**
     * 设置支付监听
     *
     * @param listener 监听器
     */
    public void setOnPayListener(OnPayListener listener) {
        this.mOnPayListener = listener;
        WXAPI.getInstance(mContext).setOnPayListener(mOnPayListener);
    }

    /**
     * 微信支付监听
     */
    public interface OnPayListener {
        /**
         * 支付结果回调
         *
         * @param result 支付结果
         */
        void onPayResult(WXPayResult result);
    }
}
