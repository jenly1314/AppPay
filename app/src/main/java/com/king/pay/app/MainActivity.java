package com.king.pay.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.king.pay.apppay.AppPay;
import com.king.pay.wxpay.WXPayReq;

/**
 * AppPay示例
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
public class MainActivity extends AppCompatActivity {

    AppPay mAppPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAppPay = new AppPay(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 当使用银联支付时，需要在 Activity 中的 onActivityResult 方法中调用此方法，来接收支付结果监听回调
        mAppPay.onActivityResult(resultCode, resultCode, data);
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 微信支付
     * <p>
     * 官方接入文档：https://developers.weixin.qq.com/doc/oplatform/Mobile_App/Access_Guide/Android.html
     */
    private void clickBtnWXPay() {
        // TODO  配置好微信支付请求相关的参数,发送微信支付请求
        // 微信请求相关属性务必放在服务端，通过预支付下单接口返回相关参数，这样比较安全。
        WXPayReq req = new WXPayReq();
//       req.setAppId("");
//       mAppPay.sendWXPayReq(req, new WXPay.OnPayListener() {
//           @Override
//           public void onPayResult(WXPayResult result) {
//               // 支付结果
//               if (result.isSuccess()) {
//                   // TODO 支付成功
//
//                   // 务必以服务端结果为准
//               }
//           }
//       });

        showToast("请配置微信支付请求相关参数");
    }

    /**
     * 支付宝支付
     * <p>
     * 官网文档可参见：https://docs.open.alipay.com/204/105296/
     */
    private void clickBtnAliPay() {
        // TODO  配置好支付宝支付请求订单信息相关的参数,发送支付宝支付请求（订单信息一般从后台获取）

//        String orderInfo = "";
//        mAppPay.sendAliPayReq(orderInfo, new AliPay.OnPayListener() {
//            @Override
//            public void onPayResult(AliPayResult result) {
//                // 支付结果
//                if (result.isSuccess()) {
//                    // TODO 支付成功
//
//                    // 务必以服务端结果为准
//                }
//            }
//        });

        showToast("请配置支付宝支付订单信息");
    }

    /**
     * 银联支付
     */
    private void clickBtnUnionPay() {
        // TODO 银联支付；订单信息为交易流水号，即TN，为商户后台从银联后台获取。

//        String orderInfo = "";
//        mAppPay.sendUnionPayReq(orderInfo, new UnionPay.OnPayListener() {
//            @Override
//            public void onPayResult(UnionPayResult result) {
//
//            }
//        });

        showToast("请配置银联支付订单信息");
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnWXPay:
                clickBtnWXPay();
                break;
            case R.id.btnAliPay:
                clickBtnAliPay();
                break;
            case R.id.btnUnionPay:
                clickBtnUnionPay();
                break;
        }
    }
}
