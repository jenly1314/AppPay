package com.king.pay.alipay;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;
import com.king.pay.alipay.util.OrderInfoUtils;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 支付宝支付
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
@SuppressWarnings("unused")
public class AliPay {

    private Activity mActivity;

    private final Handler mHandler;

    private OnAuthListener mOnAuthListener;

    private OnPayListener mOnPayListener;

    private final Executor mExecutor;

    public AliPay(Activity activity) {
        this.mActivity = activity;
        mHandler = new Handler(Looper.getMainLooper());
        mExecutor = Executors.newSingleThreadExecutor();
    }

    /**
     * 发送支付请求，因为订单参数与签名加密前内容关联密切，所以强烈建议服务端直接返回拼接后的orderInfo，使用{@link #sendReq(String)}
     *
     * @param req
     */
    public void sendReq(AliPayReq req) {
        sendReq(OrderInfoUtils.buildOrderInfo(req));
    }

    /**
     * 发送支付请求；SDK拉起支付宝支付
     *
     * @param orderInfo 订单信息
     */
    public void sendReq(String orderInfo) {
        mExecutor.execute(() -> {
            PayTask aliPay = new PayTask(mActivity);
            Map<String, String> result = aliPay.payV2(orderInfo, true);
            AliPayResult aliPayResult = new AliPayResult(result);

            // 对于支付结果，请商户依赖服务端的异步通知结果。此处的同步通知结果，仅作为支付结束的通知。
            mHandler.post(() -> {
                if (mOnPayListener != null) {
                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                    mOnPayListener.onPayResult(aliPayResult);
                }
            });
        });
    }

    /**
     * 发送支付请求；SDK拉起支付宝支付
     *
     * @param orderInfo 订单信息
     * @param listener  监听器
     */
    public void sendReq(String orderInfo, OnPayListener listener) {
        setOnPayListener(listener);
        sendReq(orderInfo);
    }


    /**
     * 支付宝授权业务检测，因为授权参数与签名加密前内容关联密切，所以强烈建议服务端直接返回拼接后的authInfo，使用{@link #checkAuth(String)}
     *
     * @param req
     */
    public void checkAuth(AliAuthReq req) {
        checkAuth(OrderInfoUtils.buildAuthInfo(req));
    }

    /**
     * 支付宝账户授权业务检测
     *
     * @param authInfo 授权信息
     */
    public void checkAuth(final String authInfo) {
        mExecutor.execute(() -> {
            // 构造AuthTask 对象
            AuthTask authTask = new AuthTask(mActivity);
            // 调用授权接口，获取授权结果
            Map<String, String> result = authTask.authV2(authInfo, true);
            AliAuthResult aliAuthResult = new AliAuthResult(result, true);

            mHandler.post(() -> {
                if (mOnAuthListener != null) {
                    mOnAuthListener.onAuthResult(aliAuthResult);
                }
            });
        });
    }

    /**
     * 支付宝账户授权业务检测
     *
     * @param authInfo 授权信息
     * @param listener 监听器
     */
    public void checkAuth(final String authInfo, OnAuthListener listener) {
        setOnAuthListener(listener);
        checkAuth(authInfo);
    }

    /**
     * 设置授权监听
     *
     * @param listener 监听器
     * @return
     */
    public AliPay setOnAuthListener(OnAuthListener listener) {
        this.mOnAuthListener = listener;
        return this;
    }

    /**
     * 设置支付监听
     *
     * @param listener 监听器
     * @return
     */
    public AliPay setOnPayListener(OnPayListener listener) {
        this.mOnPayListener = listener;
        return this;
    }

    /**
     * 授权监听
     */
    public interface OnAuthListener {
        /**
         * 授权结果回调
         *
         * @param result 授权结果
         */
        void onAuthResult(AliAuthResult result);
    }

    /**
     * 支付监听
     */
    public interface OnPayListener {
        /**
         * 支付结果回调
         *
         * @param result 是否结果
         */
        void onPayResult(AliPayResult result);
    }

}
