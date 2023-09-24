package com.king.pay.wxpay.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.king.pay.wxpay.WXAPI;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * 微信支付需在项目包路径中的wxapi目录中实现一个WXPayEntryActivity类来处理微信响应结果
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WXAPI.getInstance(this).getApi().handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        WXAPI.getInstance(this).getApi().handleIntent(intent, this);
    }

    /**
     * 支付请求
     *
     * @param baseReq
     */
    @Override
    public void onReq(BaseReq baseReq) {

    }

    /**
     * 支付响应
     *
     * @param baseResp
     */
    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            onPayResult(baseResp.errCode, baseResp.errStr);
        }
    }

    /**
     * 支付结果，注意一定不能以客户端返回作为用户支付的结果，应以服务器端的接收的支付通知或查询API返回的结果为准
     *
     * @param code  0.成功
     *              -1.错误
     *              -2.用户取消
     * @param error 错误描述
     */
    public void onPayResult(int code, String error) {
        WXAPI.getInstance(this).onResp(code, error);
        finish();
    }

}