package com.king.pay.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.king.pay.alipay.AliPay;
import com.king.pay.apppay.AppPay;
import com.king.pay.wxpay.WXPayReq;
import com.king.pay.wxpay.wxapi.WXPayActivity;

/**
 * AppPay集成demo
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    AppPay mAppPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAppPay = new AppPay(this);
        //支付宝支付监听
        mAppPay.setOnAliPayListener(new AliPay.OnPayListener() {
            @Override
            public void onPayResult(boolean isSuccess, String resultInfo) {
                if(isSuccess){//TODO 支付宝支付成功
                    //务必以服务端结果为准
                }
            }
        });

        mAppPay.setOnAliPayAuthListener(new AliPay.OnAuthListener() {
            @Override
            public void onAuthResult(boolean isSuccess, String resultInfo) {
                if(isSuccess){//TODO  支付宝授权成功

                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerPayResultReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterPayResultReceiver();
    }

    /**
     * 注册广播广播
     */
    private void registerPayResultReceiver(){
        IntentFilter filter = new IntentFilter();
        filter.addAction(WXPayActivity.ACTION_WX_PAY_RESULT);
        registerReceiver(mPayResultReceiver,filter);
    }

    /**
     * 注销广播
     */
    private void unregisterPayResultReceiver(){
        unregisterReceiver(mPayResultReceiver);
    }

    /**
     * 支付结果广播接收
     */
    private BroadcastReceiver mPayResultReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int code = intent.getIntExtra(WXPayActivity.KEY_CODE,0);
            String error = intent.getStringExtra(WXPayActivity.KEY_ERROR);
            Log.d(TAG,"code=" + code + ",error=" + error);
            //接收到客户端支付结果，这里一般与服务器同步支付结果，以服务器端的接收的支付通知或查询API返回的结果为准
            switch (code){
                case 0://成功
                    showToast("支付成功");
                    break;
                case -1://错误
                    showToast("支付错误");
                    break;
                case 2://用户取消
                    showToast("用户取消");
                    break;
            }
        }
    };

    private void showToast(String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    /**
     * 微信支付
     */
    private void clickBtn1(){
        //TODO  配置好微信支付请求相关的参数,发送微信支付请求，可参见：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=8_5
        //微信请求相关属性务必放在服务端，通过预支付下单接口返回相关参数，这样比较安全。
        WXPayReq req = new WXPayReq();
//        req.setAppId("");
//        mAppPay.sendWXPayReq(req);
        showToast("请配置微信支付请求相关参数");
    }

    /**
     * 支付宝支付
     */
    private void clickBtn2(){
        //TODO  配置好支付宝支付请求相关的参数,发送支付宝支付请求，可参见：https://docs.open.alipay.com/204/105296/
        //支付宝支付请求的订单信息，务必放在服务端通过接口返回，这样比较安全。
//        String orderInfo = "";
//        mAppPay.sendAliPayReq(orderInfo);

        showToast("请配置支付宝支付订单信息");
    }

    /**
     * 支付宝授权
     */
    private void clickBtn3(){
        //TODO 支付宝授权信息privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成
        //支付宝授权信息
//        String authInfo = "";
//        mAppPay.checkAliAuth(authInfo);
        showToast("请配置支付宝授权信息");
    }

    private void clickBtn4(){
        showToast("待续");
        AliPay aliPay = new AliPay(this);

    }



    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn1:
                clickBtn1();
                break;
            case R.id.btn2:
                clickBtn2();
                break;
            case R.id.btn3:
                clickBtn3();
                break;
            case R.id.btn4:
                clickBtn4();
                break;
        }
    }
}
