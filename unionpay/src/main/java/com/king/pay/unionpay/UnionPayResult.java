package com.king.pay.unionpay;

/**
 * 银联支付结果
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
public class UnionPayResult {

    private boolean success;
    private String message;

    UnionPayResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * 是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 结果信息
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "UnionPayResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
