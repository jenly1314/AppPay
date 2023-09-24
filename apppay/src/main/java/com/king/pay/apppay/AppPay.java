package com.king.pay.apppay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.king.pay.alipay.AliPay;
import com.king.pay.unionpay.UnionPay;
import com.king.pay.wxpay.WXPay;
import com.king.pay.wxpay.WXPayReq;

/**
 * AppPay主要包含微信支付、支付宝支付，让App集成支付功能变的更简单。
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
@SuppressWarnings("unused")
public class AppPay implements IAppPay {

    private volatile WXPay mWXPay;

    private volatile AliPay mAliPay;

    private volatile UnionPay mUnionPay;

    private Activity mActivity;

    public AppPay(Activity activity) {
        this.mActivity = activity;
    }

    /**
     * 初始化WXPay
     *
     * @param context
     */
    private void initWXPay(Context context) {
        if (mWXPay == null) {
            synchronized (AppPay.class) {
                if (mWXPay == null) {
                    mWXPay = new WXPay(context);
                }
            }
        }
    }

    /**
     * 初始化AliPay
     *
     * @param activity
     */
    private void initAliPay(Activity activity) {
        if (mAliPay == null) {
            synchronized (AppPay.class) {
                if (mAliPay == null) {
                    mAliPay = new AliPay(activity);
                }
            }
        }
    }

    /**
     * 初始化UnionPay
     *
     * @param activity
     */
    private void initUnionPay(Activity activity) {
        if (mUnionPay == null) {
            synchronized (AppPay.class) {
                if (mUnionPay == null) {
                    mUnionPay = new UnionPay(activity);
                }
            }
        }
    }

    @Override
    public void sendWXPayReq(WXPayReq req) {
        getWXPay().sendReq(req);
    }

    public void sendWXPayReq(WXPayReq req, WXPay.OnPayListener listener) {
        getWXPay().sendReq(req, listener);
    }

    @Override
    public void sendAliPayReq(String orderInfo) {
        getAliPay().sendReq(orderInfo);
    }

    @Override
    public void sendAliPayReq(String orderInfo, AliPay.OnPayListener listener) {
        getAliPay().sendReq(orderInfo, listener);
    }

    @Override
    public void checkAliAuth(String authInfo) {
        getAliPay().checkAuth(authInfo);
    }

    @Override
    public void checkAliAuth(String authInfo, AliPay.OnAuthListener listener) {
        getAliPay().checkAuth(authInfo, listener);
    }

    @Override
    public void sendUnionPayReq(String orderInfo) {
        sendUnionPayReq(orderInfo, UnionPay.PRO_SERVER_MODE);
    }

    @Override
    public void sendUnionPayReq(String orderInfo, UnionPay.OnPayListener listener) {
        getUnionPay().sendReq(orderInfo, UnionPay.PRO_SERVER_MODE, listener);
    }

    @Override
    public void sendUnionPayReq(String orderInfo, String serverMode) {
        getUnionPay().sendReq(orderInfo, serverMode);
    }

    @Override
    public void sendUnionPayReq(String orderInfo, String serverMode, UnionPay.OnPayListener listener) {
        getUnionPay().sendReq(orderInfo, serverMode, listener);
    }

    /**
     * 设置微信支付监听
     *
     * @param listener
     * @return
     */
    public AppPay setOnWXPayListener(WXPay.OnPayListener listener) {
        getWXPay().setOnPayListener(listener);
        return this;
    }

    /**
     * 设置支付宝授权监听
     *
     * @param listener
     * @return
     */
    public AppPay setOnAliPayAuthListener(AliPay.OnAuthListener listener) {
        getAliPay().setOnAuthListener(listener);
        return this;
    }

    /**
     * 设置支付宝支付监听
     *
     * @param listener
     * @return
     */
    public AppPay setOnAliPayListener(AliPay.OnPayListener listener) {
        getAliPay().setOnPayListener(listener);
        return this;
    }

    /**
     * 设置银联支付监听；记得在 {@link Activity} 中的 onActivityResult 方法中调用 {@link AppPay#onActivityResult(int, int, Intent)}方法，这样设置的银联支付监听才会被触发。
     *
     * @param listener
     * @return
     */
    public AppPay setOnUnionPayListener(UnionPay.OnPayListener listener) {
        getUnionPay().setOnPayListener(listener);
        return this;
    }

    /**
     * 当使用银联支付时，需要在 {@link Activity} 中的 onActivityResult 方法中调用此方法，来接收支付结果监听回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getUnionPay().onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 获取 AliPay
     *
     * @return
     */
    public AliPay getAliPay() {
        initAliPay(mActivity);
        return mAliPay;
    }

    /**
     * 获取 WXPay
     *
     * @return
     */
    public WXPay getWXPay() {
        initWXPay(mActivity);
        return mWXPay;
    }

    /**
     * 获取 UnionPay
     *
     * @return
     */
    public UnionPay getUnionPay() {
        initUnionPay(mActivity);
        return mUnionPay;
    }
}
