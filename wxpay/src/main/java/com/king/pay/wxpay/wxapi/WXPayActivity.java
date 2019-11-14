package com.king.pay.wxpay.wxapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.king.pay.wxpay.R;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class WXPayActivity extends Activity implements IWXAPIEventHandler {

    public static final String ACTION_WX_PAY_RESULT = "action_wx_pay_result";

    public static final String KEY_CODE = "key_code";
    public static final String KEY_ERROR = "key_error";

    private IWXAPI mApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wxpay_activity);

        mApi = WXAPIFactory.createWXAPI(this, getAppId());
        mApi.handleIntent(getIntent(), this);

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        mApi.handleIntent(intent, this);
    }

    public Context getContext(){
        return this;
    }

    /**
     * 支付请求
     * @param baseReq
     */
    @Override
    public void onReq(BaseReq baseReq) {

    }

    /**
     * 支付响应
     * @param baseResp
     */
    @Override
    public void onResp(BaseResp baseResp) {
        if(baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX){
            onPayResult(baseResp.errCode,baseResp.errStr);
        }
    }

    /**
     * 微信支付AppId （示例：wx****************）
     * @return
     */
    public abstract String getAppId();

    /**
     * 支付结果，注意一定不能以客户端返回作为用户支付的结果，应以服务器端的接收的支付通知或查询API返回的结果为准
     * @param code
     *      0.成功
     *      -1.错误
     *      2.用户取消
     * @param error
     *      错误描述
     */
    public abstract void onPayResult(int code, String error);

    /**
     * 发送支付结果广播
     * @param code
     */
    protected void sendPayResultBroadcast(int code,String error){
        Intent intent = new Intent(ACTION_WX_PAY_RESULT);
        intent.putExtra(KEY_CODE,code);
        intent.putExtra(KEY_ERROR,error);
        sendBroadcast(intent);
    }

}