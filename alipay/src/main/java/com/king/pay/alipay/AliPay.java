package com.king.pay.alipay;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;
import com.king.pay.alipay.util.OrderInfoUtils;

import java.util.Map;

/**
 * 支付宝支付
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class AliPay {

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    private Activity mActivity;

    private Handler mHandler;

    private OnAuthListener mOnAuthListener;

    private OnPayListener mOnPayListener;

    public AliPay(Activity activity){
        this.mActivity = activity;
        mHandler = new Handler() {

            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case SDK_PAY_FLAG: {
                        PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                        /**
                         * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                         */
                        String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                        String resultStatus = payResult.getResultStatus();
                        // 判断resultStatus 为9000则代表支付成功
                        if(mOnPayListener!=null){
                            // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                            mOnPayListener.onPayResult(TextUtils.equals(resultStatus, "9000"),resultInfo);
                        }
                        break;
                    }
                    case SDK_AUTH_FLAG: {
                        AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);

                        String resultInfo = authResult.getResult();
                        String resultStatus = authResult.getResultStatus();

                        // 判断resultStatus 为“9000”且result_code
                        // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                        if(mOnAuthListener!=null){
                            boolean isSuccess = TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200");
                            mOnAuthListener.onAuthResult(isSuccess,resultInfo);
                        }
                        break;
                    }
                    default:
                        break;
                }
            }
        };
    }

    /**
     * 发送支付请求，因为订单参数与签名加密前内容关联密切，所以强烈建议服务端直接返回拼接后的orderInfo，使用{@link #sendReq(String)}
     * @param req
     */
    public void sendReq(AliPayReq req){
        sendReq(OrderInfoUtils.buildOrderInfo(req));
    }

    /**
     * 发送支付请求
     * @param orderInfo
     */
    public void sendReq(final String orderInfo){
        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask aliPay = new PayTask(mActivity);
                Map<String, String> result = aliPay.payV2(orderInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }



    /**
     * 支付宝授权业务检测，因为授权参数与签名加密前内容关联密切，所以强烈建议服务端直接返回拼接后的authInfo，使用{@link #checkAuth(String)}
     * @param req
     */
    public void checkAuth(AliAuthReq req){
        sendReq(OrderInfoUtils.buildAuthInfo(req));
    }

    /**
     * 支付宝账户授权业务检测
     * @param authInfo
     */
    public void checkAuth(final String authInfo){
        final Runnable authRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造AuthTask 对象
                AuthTask authTask = new AuthTask(mActivity);
                // 调用授权接口，获取授权结果
                Map<String, String> result = authTask.authV2(authInfo, true);

                Message msg = new Message();
                msg.what = SDK_AUTH_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread authThread = new Thread(authRunnable);
        authThread.start();
    }

    /**
     * 设置授权监听
     * @param listener
     * @return
     */
    public AliPay setOnAuthListener(OnAuthListener listener){
        this.mOnAuthListener = listener;
        return this;
    }

    /**
     * 设置支付监听
     * @param listener
     * @return
     */
    public AliPay setOnPayListener(OnPayListener listener){
        this.mOnPayListener = listener;
        return this;
    }


    /**
     * 授权监听
     */
    public interface OnAuthListener{
        void onAuthResult(boolean isSuccess,String resultInfo);
    }

    /**
     * 支付监听
     */
    public interface OnPayListener{
        void onPayResult(boolean isSuccess,String resultInfo);
    }

}
