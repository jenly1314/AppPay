package com.king.pay.apppay;

import android.app.Activity;
import android.content.Context;

import com.king.pay.alipay.AliAuthReq;
import com.king.pay.alipay.AliPay;
import com.king.pay.alipay.AliPayReq;
import com.king.pay.wxpay.WXPay;
import com.king.pay.wxpay.WXPayReq;

/**
 * AppPay主要包含微信支付、支付宝支付，让App集成支付功能变的更简单。
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class AppPay implements IAppPay {

    private volatile WXPay mWXPay;

    private volatile AliPay mAliPay;

    private Activity mActivity;

    public AppPay(Activity activity){
        this.mActivity = activity;
    }

    /**
     * 初始化WXPay
     * @param context
     * @param appId
     */
    private void initWXPay(Context context, String appId){
        if(mWXPay == null){
            synchronized (AppPay.class){
                if(mWXPay == null){
                    mWXPay = new WXPay(context,appId);
                }
            }
        }
    }

    /**
     * 初始化AliPay
     * @param activity
     */
    private void initAliPay(Activity activity){
        if(mAliPay == null){
            synchronized (AppPay.class){
                if(mAliPay == null){
                    mAliPay = new AliPay(activity);
                }
            }
        }
    }

    @Override
    public void sendWXPayReq(WXPayReq req) {
        initWXPay(mActivity,req.getAppId());
        mWXPay.sendReq(req);
    }

    @Override
    public void sendAliPayReq(AliPayReq req) {
        initAliPay(mActivity);
        mAliPay.sendReq(req);
    }

    @Override
    public void sendAliPayReq(String orderInfo) {
        initAliPay(mActivity);
        mAliPay.sendReq(orderInfo);
    }

    @Override
    public void checkAliAuth(AliAuthReq req) {
        initAliPay(mActivity);
        mAliPay.checkAuth(req);
    }

    @Override
    public void checkAliAuth(String authInfo) {
        initAliPay(mActivity);
        mAliPay.checkAuth(authInfo);
    }

    /**
     * 设置支付宝授权监听
     * @param listener
     * @return
     */
    public AppPay setOnAliPayAuthListener(AliPay.OnAuthListener listener){
        initAliPay(mActivity);
        mAliPay.setOnAuthListener(listener);
        return this;
    }

    /**
     * 设置支付宝支付监听
     * @param listener
     * @return
     */
    public AppPay setOnAliPayListener(AliPay.OnPayListener listener){
        initAliPay(mActivity);
        mAliPay.setOnPayListener(listener);
        return this;
    }

    public AliPay getAliPay(){
        initAliPay(mActivity);
        return mAliPay;
    }

    public WXPay getWXPay(String appId){
        initWXPay(mActivity,appId);
        return mWXPay;
    }
}
