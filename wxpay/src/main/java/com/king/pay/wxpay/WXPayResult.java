package com.king.pay.wxpay;

/**
 * 微信支付结果
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
public class WXPayResult {

    private int code;
    private String message;

    WXPayResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        return code == 0;
    }

    /**
     * 结果码
     *
     * @return 结果码；0.成功、-1.错误、-2.用户取消
     */
    public int getCode() {
        return code;
    }

    /**
     * 结果信息
     * @return
     */
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "WXPayResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
