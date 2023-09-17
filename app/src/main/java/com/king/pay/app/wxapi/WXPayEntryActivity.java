package com.king.pay.app.wxapi;

import com.king.pay.wxpay.wxapi.WXPayActivity;

/**
 * 微信支付需在你的项目包路径中实现WXPayEntryActivity类继承{@link WXPayActivity}
 * 你也可以将本类直接拷贝到你的项目中，记住是你项目的包路径的wxapi目录中
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
public class WXPayEntryActivity extends WXPayActivity {

    @Override
    public String getAppId() {
        // TODO 此处填写微信支付申请的AppID
        return "{your appId}";
    }

    /**
     * 支付结果，注意一定不能以客户端返回作为用户支付的结果，应以服务器端的接收的支付通知或查询API返回的结果为准
     *
     * @param code  0.成功
     *              -1.错误
     *              -2.用户取消
     * @param error 错误描述
     */
    @Override
    public void onPayResult(int code, String error) {
        // TODO 支付结果；此处可通过发送广播或者其他方式将支付结果发送出去

        // 示例：通过广播发送支付结果，然后在 MainActivity 中接收
        sendPayResultBroadcast(code, error);
    }
}
