package com.king.pay.unionpay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.unionpay.UPPayAssistEx;

/**
 * 银联支付
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
@SuppressWarnings("unused")
public class UnionPay {

    /**
     * 银联测试环境，该环境中不发生真实交易
     */
    public static final String TEST_SERVER_MODE = "01";
    /**
     * 银联生产环境模式，该环境中发起真实交易
     */
    public static final String PRO_SERVER_MODE = "00";

    /**
     * 支付结果
     */
    private static final String PAY_RESULT = "pay_result";
    /**
     * 支付成功
     */
    private static final String PAY_SUCCESS = "success";
    /**
     * 支付失败
     */
    private static final String PAY_FAIL = "fail";
    /**
     * 支付取消
     */
    private static final String PAY_CANCEL = "cancel";

    private Context mContext;

    private OnPayListener mOnPayListener;

    public UnionPay(Context context) {
        this.mContext = context;
    }

    /**
     * 发送支付请求；
     *
     * @param orderInfo  订单信息为交易流水号，即TN，为商户后台从银联后台获取。
     * @param serverMode 银联后台环境标识；用于区分使用测试环境还是正式环境；说明参见：{@link #PRO_SERVER_MODE} 和 {@link #TEST_SERVER_MODE}
     */
    public void sendReq(String orderInfo, String serverMode) {
        UPPayAssistEx.startPay(mContext, null, null, orderInfo, serverMode);
    }

    /**
     * 发送支付请求；
     *
     * @param orderInfo  订单信息为交易流水号，即TN，为商户后台从银联后台获取。
     * @param serverMode 银联后台环境标识；用于区分使用测试环境还是正式环境；说明参见：{@link #PRO_SERVER_MODE} 和 {@link #TEST_SERVER_MODE}
     * @param listener   监听器
     */
    public void sendReq(String orderInfo, String serverMode, OnPayListener listener) {
        setOnPayListener(listener);
        UPPayAssistEx.startPay(mContext, null, null, orderInfo, serverMode);
    }

    /**
     * 在 {@link Activity} 中的 onActivityResult 方法中调用此方法，来接收支付结果监听回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null && mOnPayListener != null) {
            String result = data.getStringExtra(PAY_RESULT);
            if (PAY_SUCCESS.equalsIgnoreCase(result)) {
                mOnPayListener.onPayResult(new UnionPayResult(true, result));
            } else if (PAY_FAIL.equalsIgnoreCase(result) || PAY_CANCEL.equalsIgnoreCase(result)) {
                mOnPayListener.onPayResult(new UnionPayResult(false, result));
            }
        }
    }

    /**
     * 设置支付监听；记得在 {@link Activity} 中的 onActivityResult 方法中调用 {@link UnionPay#onActivityResult(int, int, Intent)}方法，这样设置的支付监听才会被触发。
     *
     * @param listener 监听器
     */
    public void setOnPayListener(OnPayListener listener) {
        this.mOnPayListener = listener;
    }

    /**
     * 银联支付监听
     */
    public interface OnPayListener {
        /**
         * 支付结果回调
         *
         * @param result 支付结果
         */
        void onPayResult(UnionPayResult result);
    }
}
