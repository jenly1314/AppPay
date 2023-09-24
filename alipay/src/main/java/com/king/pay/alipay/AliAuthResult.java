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
public class AliAuthResult {

    private String resultStatus;
    private String result;
    private String memo;
    private String resultCode;
    private String authCode;
    private String alipayOpenId;

    public AliAuthResult(Map<String, String> rawResult, boolean removeBrackets) {
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

        String[] resultValue = result.split("&");
        for (String value : resultValue) {
            if (value.startsWith("alipay_open_id")) {
                alipayOpenId = removeBrackets(getValue("alipay_open_id=", value), removeBrackets);
                continue;
            }
            if (value.startsWith("auth_code")) {
                authCode = removeBrackets(getValue("auth_code=", value), removeBrackets);
                continue;
            }
            if (value.startsWith("result_code")) {
                resultCode = removeBrackets(getValue("result_code=", value), removeBrackets);
                continue;
            }
        }

    }

    private String removeBrackets(String str, boolean remove) {
        if (remove) {
            if (!TextUtils.isEmpty(str)) {
                if (str.startsWith("\"")) {
                    str = str.replaceFirst("\"", "");
                }
                if (str.endsWith("\"")) {
                    str = str.substring(0, str.length() - 1);
                }
            }
        }
        return str;
    }

    @Override
    public String toString() {
        return "authCode={" + authCode + "}; resultStatus={" + resultStatus + "}; memo={" + memo + "}; result={" + result + "}";
    }

    /**
     * 是否成功；判断resultStatus 为“9000”且result_code为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
     *
     * @return
     */
    public boolean isSuccess() {
        return TextUtils.equals(resultStatus, "9000") && TextUtils.equals(resultCode, "200");
    }

    private String getValue(String header, String data) {
        return data.substring(header.length(), data.length());
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

    /**
     * @return the resultCode
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * @return the authCode
     */
    public String getAuthCode() {
        return authCode;
    }

    /**
     * @return the alipayOpenId
     */
    public String getAlipayOpenId() {
        return alipayOpenId;
    }
}

