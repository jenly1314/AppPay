package com.king.pay.alipay;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

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
            PayResult payResult = new PayResult(result);

            // 对于支付结果，请商户依赖服务端的异步通知结果。此处的同步通知结果，仅作为支付结束的通知。
            String resultInfo = payResult.getResult();
            String resultStatus = payResult.getResultStatus();
            mHandler.post(() -> {
                if (mOnPayListener != null) {
                    // 判断resultStatus 为9000则代表支付成功
                    boolean isSuccess = TextUtils.equals(resultStatus, "9000");
                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                    mOnPayListener.onPayResult(isSuccess, resultInfo);
                }
            });
        });
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
            AuthResult authResult = new AuthResult(result, true);

            String resultInfo = authResult.getResult();
            String resultStatus = authResult.getResultStatus();
            mHandler.post(() -> {
                if (mOnAuthListener != null) {
                    // 判断resultStatus 为“9000”且result_code为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    boolean isSuccess = TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200");
                    mOnAuthListener.onAuthResult(isSuccess, resultInfo);
                }
            });
        });
    }

    /**
     * 设置授权监听
     *
     * @param listener
     * @return
     */
    public AliPay setOnAuthListener(OnAuthListener listener) {
        this.mOnAuthListener = listener;
        return this;
    }

    /**
     * 设置支付监听
     *
     * @param listener
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
         * @param isSuccess
         * @param resultInfo
         */
        void onAuthResult(boolean isSuccess, String resultInfo);
    }

    /**
     * 支付监听
     */
    public interface OnPayListener {
        /**
         * 支付结果回调
         *
         * @param isSuccess
         * @param resultInfo
         */
        void onPayResult(boolean isSuccess, String resultInfo);
    }

}
