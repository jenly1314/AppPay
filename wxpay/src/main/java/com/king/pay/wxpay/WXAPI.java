package com.king.pay.wxpay;

import android.content.Context;
import android.text.TextUtils;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * 微信API
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
public class WXAPI {

    private final IWXAPI mApi;

    private boolean registerApp;

    private String appId;

    private WXPay.OnPayListener mOnPayListener;

    private static volatile WXAPI sInstance;

    /**
     * 获取单例
     *
     * @param context
     * @return {@link WXAPI}
     */
    public static WXAPI getInstance(Context context) {
        if (sInstance == null) {
            synchronized (WXAPI.class) {
                if (sInstance == null) {
                    sInstance = new WXAPI(context);
                }
            }
        }
        return sInstance;
    }

    private WXAPI(Context context) {
        mApi = WXAPIFactory.createWXAPI(context, null);
    }

    /**
     * 注册App
     *
     * @param appId 申请的微信appId
     */
    public WXAPI registerApp(String appId) {
        if (!(registerApp && TextUtils.equals(this.appId, appId))) {
            this.appId = appId;
            registerApp = mApi.registerApp(appId);
        }
        return this;
    }

    /**
     * 获取 {@link IWXAPI}
     *
     * @return
     */
    public IWXAPI getApi() {
        return mApi;
    }

    /**
     * 设置支付监听
     *
     * @param listener
     * @return
     */
    public WXAPI setOnPayListener(WXPay.OnPayListener listener) {
        this.mOnPayListener = listener;
        return this;
    }

    /**
     * 响应结果处理
     *
     * @param code
     * @param errorMessage
     */
    public void onResp(int code, String errorMessage) {
        if (mOnPayListener != null) {
            mOnPayListener.onPayResult(new WXPayResult(code, errorMessage));
            mOnPayListener = null;
        }
    }

}
