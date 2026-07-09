package com.king.pay.alipay.util

import android.text.TextUtils
import com.king.pay.alipay.AliAuthReq
import com.king.pay.alipay.AliPayReq
import java.net.URLEncoder

/**
 * 订单信息工具类
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
object OrderInfoUtils {

    /**
     * 构造支付订单请求信息
     */
    @JvmStatic
    fun buildOrderInfo(req: AliPayReq): String {
        val params = buildOrderInfoParamMap(req)
        return buildParam(params)
    }

    /**
     * 构造支付订单参数集合
     */
    private fun buildOrderInfoParamMap(req: AliPayReq): Map<String, String?> {
        val keyValues = HashMap<String, String?>()

        keyValues["app_id"] = req.appId
        keyValues["method"] = req.method
        keyValues["format"] = req.format
        keyValues["charset"] = req.charset
        keyValues["sign_type"] = req.signType
        keyValues["sign"] = req.sign
        keyValues["timestamp"] = req.timestamp
        keyValues["version"] = req.version

        if (!TextUtils.isEmpty(req.notifyUrl)) {
            keyValues["notify_url"] = req.notifyUrl
        }
        keyValues["biz_content"] = req.bizContent

        return keyValues
    }

    /**
     * 构造授权请求信息
     */
    @JvmStatic
    fun buildAuthInfo(req: AliAuthReq): String {
        val params = buildAuthInfoMap(req)
        return buildParam(params)
    }

    /**
     * 构造授权参数集合
     */
    private fun buildAuthInfoMap(req: AliAuthReq): Map<String, String?> {
        val keyValues = HashMap<String, String?>()

        // 商户签约拿到的app_id，如：2013081700024223
        keyValues["app_id"] = req.appId
        // 商户签约拿到的pid，如：2088102123816631
        keyValues["pid"] = req.pid
        // 服务接口名称
        keyValues["apiname"] = req.apiname
        // 服务接口名称， 固定值
        keyValues["methodname"] = req.methodname
        // 商户类型标识
        keyValues["app_name"] = req.appName
        // 业务类型
        keyValues["biz_type"] = req.bizType
        // 产品码
        keyValues["product_id"] = req.productId
        // 授权范围
        keyValues["scope"] = req.scope
        // 商户唯一标识
        keyValues["target_id"] = req.targetId
        // 授权类型
        keyValues["auth_type"] = req.authType
        // 签名类型
        keyValues["sign_type"] = req.signType
        // 签名
        keyValues["sign"] = req.sign

        return keyValues
    }

    /**
     * 构造请求参数
     */
    private fun buildParam(map: Map<String, String?>): String {
        val keys = ArrayList(map.keys)

        val sb = StringBuilder()
        for (i in 0 until keys.size - 1) {
            val key = keys[i]
            val value = map[key]
            sb.append(buildKeyValue(key, value, true))
            sb.append("&")
        }

        val tailKey = keys[keys.size - 1]
        val tailValue = map[tailKey]
        sb.append(buildKeyValue(tailKey, tailValue, true))

        return sb.toString()
    }

    /**
     * 拼接键值对
     */
    private fun buildKeyValue(key: String, value: String?, isEncode: Boolean): String {
        val sb = StringBuilder()
        sb.append(key)
        sb.append("=")
        if (isEncode) {
            try {
                sb.append(URLEncoder.encode(value, "UTF-8"))
            } catch (e: Exception) {
                sb.append(value)
            }
        } else {
            sb.append(value)
        }
        return sb.toString()
    }
}
