package com.king.pay.alipay;

import android.text.TextUtils;

import java.util.Map;

/**
 * 参见支付宝支付的demo
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
@SuppressWarnings("unused")
public class AliPayResult {
    private String resultStatus;
    private String result;
    private String memo;

    public AliPayResult(Map<String, String> rawResult) {
        if (rawResult == null) {
            return;
        }

        for (String key : rawResult.keySet()) {
            if (TextUtils.equals(key, "resultStatus")) {
                resultStatus = rawResult.get(key);
            } else if (TextUtils.equals(key, "result")) {
                result = rawResult.get(key);
            } else if (TextUtils.equals(key, "memo")) {
                memo = rawResult.get(key);
            }
        }
    }

    @Override
    public String toString() {
        return "resultStatus={" + resultStatus + "};memo={" + memo + "};result={" + result + "}";
    }

    /**
     * 是否成功；判断resultStatus 为9000则代表支付成功；该笔订单是否真实支付成功，需要依赖服务端的异步通知。
     *
     * @return
     */
    public boolean isSuccess() {
        return TextUtils.equals(resultStatus, "9000");
    }

    /**
     * @return the resultStatus
     */
    public String getResultStatus() {
        return resultStatus;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }
}
