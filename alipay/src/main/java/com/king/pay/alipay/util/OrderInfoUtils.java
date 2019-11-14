package com.king.pay.alipay.util;

import android.text.TextUtils;

import com.king.pay.alipay.AliAuthReq;
import com.king.pay.alipay.AliPayReq;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class OrderInfoUtils {

    private OrderInfoUtils(){
        throw new AssertionError();
    }

    /**
     * 构造支付订单请求信息
     * @param req
     * @return
     */
    public static String buildOrderInfo(AliPayReq req){
        Map<String, String> params = buildOrderInfoParamMap(req);
        return buildParam(params);
    }

    /**
     * 构造支付订单参数集合
     */
    private static Map<String, String> buildOrderInfoParamMap(AliPayReq req) {
        Map<String, String> keyValues = new HashMap<>();

        keyValues.put("app_id", req.getAppId());

        keyValues.put("method", req.getMethod());

        keyValues.put("format",req.getFormat());

        keyValues.put("charset", req.getCharset());

        keyValues.put("sign_type",req.getSignType());

        keyValues.put("sign",req.getSign());

        keyValues.put("timestamp", req.getTimestamp());

        keyValues.put("version", req.getVersion());

        if(!TextUtils.isEmpty(req.getNotifyUrl())){
            keyValues.put("notify_url",req.getNotifyUrl());
        }
        keyValues.put("biz_content",req.getBizContent());

        return keyValues;
    }

    /**
     * 构造授权请求信息
     * @param req
     * @return
     */
    public static String buildAuthInfo(AliAuthReq req){
        Map<String, String> params = buildAuthInfoMap(req);
        return buildParam(params);
    }

    /**
     * 构造授权参数集合
     * @param req
     * @return
     */
    private static Map<String, String> buildAuthInfoMap(AliAuthReq req) {
        Map<String, String> keyValues = new HashMap<>();

        // 商户签约拿到的app_id，如：2013081700024223
        keyValues.put("app_id", req.getAppId());
        // 商户签约拿到的pid，如：2088102123816631
        keyValues.put("pid", req.getPid());
        // 服务接口名称
        keyValues.put("apiname", req.getApiname());
        // 服务接口名称， 固定值
        keyValues.put("methodname", req.getMethodname());
        // 商户类型标识
        keyValues.put("app_name",req.getAppName());
        // 业务类型
        keyValues.put("biz_type", req.getBizType());
        // 产品码
        keyValues.put("product_id",req.getProductId());
        // 授权范围
        keyValues.put("scope", req.getScope());
        // 商户唯一标识
        keyValues.put("target_id", req.getTargetId());
        // 授权类型
        keyValues.put("auth_type", req.getAuthType());
        // 签名类型
        keyValues.put("sign_type", req.getSignType());
        //签名
        keyValues.put("sign", req.getSign());

        return keyValues;
    }

    /**
     * 构造请求参数
     *
     * @param map
     * @return
     */
    private static String buildParam(Map<String, String> map) {
        List<String> keys = new ArrayList<>(map.keySet());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.size() - 1; i++) {
            String key = keys.get(i);
            String value = map.get(key);
            sb.append(buildKeyValue(key, value, true));
            sb.append("&");
        }

        String tailKey = keys.get(keys.size() - 1);
        String tailValue = map.get(tailKey);
        sb.append(buildKeyValue(tailKey, tailValue, true));

        return sb.toString();
    }

    /**
     * 拼接键值对
     *
     * @param key
     * @param value
     * @param isEncode
     * @return
     */
    private static String buildKeyValue(String key, String value, boolean isEncode) {
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        sb.append("=");
        if (isEncode) {
            try {
                sb.append(URLEncoder.encode(value, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                sb.append(value);
            }
        } else {
            sb.append(value);
        }
        return sb.toString();
    }


}
