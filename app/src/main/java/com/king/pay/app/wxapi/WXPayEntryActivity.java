package com.king.pay.app.wxapi;

import android.support.annotation.NonNull;

import com.king.pay.wxpay.wxapi.WXPayActivity;
import com.king.pay.app.MainActivity;

/**
 * 微信支付需在你的项目包路径中实现WXPayEntryActivity类继承{@link WXPayActivity}
 * 你也可以将本类直接拷贝到你的项目中，记住是你项目的包路径的wxapi目录中
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class WXPayEntryActivity extends WXPayActivity {

    @NonNull
    @Override
    public String getAppId() {
        //TODO 此处填写微信支付申请的AppID
        return "{your appId}";
    }

    @Override
    public void onPayResult(int code, String error) {
        //TODO 支付结果
        //此处可通过发送广播或者EventBus发送事件来通知支付结果，注意一定不能以客户端返回作为用户支付的结果，应以服务器端的接收的支付通知或查询API返回的结果为准
        /**
         * 发送支付结果广播，在{@link MainActivity} 中接收
         */
        sendPayResultBroadcast(code,error);
        switch (code){
            case 0://成功
                break;
            case -1://错误
                break;
            case 2://用户取消
                break;
        }


    }
}
