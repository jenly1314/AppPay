# AppPay

![Image](app/src/main/ic_launcher-web.png)

[![](https://jitpack.io/v/jenly1314/AppPay.svg)](https://jitpack.io/#jenly1314/AppPay)
[![CI](https://travis-ci.org/jenly1314/AppPay.svg?branch=master)](https://travis-ci.org/jenly1314/AppPay)
[![API](https://img.shields.io/badge/API-16%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=16)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://opensource.org/licenses/mit-license.php)
[![Blog](https://img.shields.io/badge/blog-Jenly-9933CC.svg)](http://blog.csdn.net/jenly121)
[![QQGroup](https://img.shields.io/badge/QQGroup-20867961-blue.svg)](http://shang.qq.com/wpa/qunwpa?idkey=8fcc6a2f88552ea44b1411582c94fd124f7bb3ec227e2a400dbbfaad3dc2f5ad)

AppPay for Android 是一个专注于App支付的库，将主流的官方App支付集成方式进行封装、简化，让实现App支付简单到只需几句代码。

> AppPay目前包括的库
* WXPay 封装的微信支付库，使用起来更简单。
* AliPay 封装的支付宝支付库（初始化，监听支付结果，发送支付请求，三句代码搞定。）
* AppPay 将以上所有子库进行整合再次封装，让集成App支付一步到位。


AppPay的的整体结构：包含多个独立封装的子库，每个子库可以独立集成，也可以直接集成AppPay，一步到位，拥有所有子库的能力，并且使用更简单。

## 结构
![Image](image/AppPay_architecture.jpg)

## 引入

### Maven：
```maven
    //AppPay
    <dependency>
      <groupId>com.king.pay</groupId>
      <artifactId>apppay</artifactId>
      <version>1.0.0</version>
      <type>pom</type>
    </dependency>
    
    //WXPay
    <dependency>
      <groupId>com.king.pay</groupId>
      <artifactId>wxpay</artifactId>
      <version>1.0.0</version>
      <type>pom</type>
    </dependency>
    
    //AliPay
    <dependency>
      <groupId>com.king.pay</groupId>
      <artifactId>alipay</artifactId>
      <version>1.0.0</version>
      <type>pom</type>
    </dependency>    
```
### Gradle:
```gradle
    //AppPay
    implementation 'com.king.pay:apppay:1.0.0'
    
    //WXPay
    implementation 'com.king.pay:wxpay:1.0.0'

    //AliPay
    implementation 'com.king.pay:alipay:1.0.0'
```
> 注意：当集成涉及到支付宝支付时（即使用了AliPay或者AppPay时），需在您项目中引入alipaySdk依赖，具体详情请参见[App中的build.gradle](app/build.gradle)，在下面的<a href="#record">版本记录</a>中查看AppPay使用alipaySdk对应的版本

### Lvy:
```lvy
    //AppPay
    <dependency org='com.king.pay' name='wxpay' rev='1.0.0'>
      <artifact name='$AID' ext='pom'></artifact>
    </dependency>
    
    //WXPay
    <dependency org='com.king.pay' name='wxpay' rev='1.0.0'>
      <artifact name='$AID' ext='pom'></artifact>
    </dependency>

    //AliPay
    <dependency org='com.king.pay' name='alipay' rev='1.0.0'>
      <artifact name='$AID' ext='pom'></artifact>
    </dependency>
```

###### 如果Gradle出现compile失败的情况，可以在Project的build.gradle里面添加如下：（也可以使用上面的GitPack来complie）
```gradle
    allprojects {
        repositories {
            //...
            maven { url 'https://dl.bintray.com/jenly/maven' }
        }
    }
```

## 示例
##### AppPay
```Java

    //在Activity中初始化AppPay
    mAppPay = new AppPay(this);

    //设置支付宝支付监听
    mAppPay.setOnAliPayListener(new AliPay.OnPayListener() {
        @Override
        public void onPayResult(boolean isSuccess, String resultInfo) {
            if(isSuccess){//TODO 支付宝支付成功
                //务必以服务端结果为准
            }
        }
    });

    //发送微信支付请求
    mAppPay.sendWXPayReq(WXPayReq req);

    //发送支付宝支付请求
    mAppPay.sendAliPayReq(String orderInfo);

```
##### WXPay
```Java
    //初始化微信支付
    mWXPay = new WXPay(Context context,String appId);

    //发送微信支付请求
    mWXPay.sendReq(WXPayReq req);

```
##### AliPay
```Java
    //初始化支付宝支付
    mAliPay = new AliPay(Activity activity);

    //设置支付宝支付监听
    mAliPay.setOnPayListener(new AliPay.OnPayListener() {
        @Override
        public void onPayResult(boolean isSuccess, String resultInfo) {
            if(isSuccess){//TODO 支付宝支付成功
                //务必以服务端结果为准
            }
        }
    });

    //发送支付宝支付请求
    mAliPay.sendReq(String orderInfo);

```

更多使用示例请查看[App](app)。

## <a name="record">版本记录</a>

#### v1.0.0 ：2019-3-21
*  AppPay初始版本
*  AliPay 依赖AlipaySdk版本 [alipaySdk-15.6.0-20190226104053](alipaySdk/alipaySdk-15.6.0-20190226104053.aar)

## 赞赏
如果您喜欢AppPay，或感觉AppPay帮助到了您，可以点右上角“Star”支持一下，您的支持就是我的动力，谢谢 :smiley:<p>
也可以扫描下面的二维码，请作者喝杯咖啡 :coffee:
    <div>
        <img src="https://image-1252383324.cos.ap-guangzhou.myqcloud.com/pay/wxpay.png" width="280" heght="350">
        <img src="https://image-1252383324.cos.ap-guangzhou.myqcloud.com/pay/alipay.png" width="280" heght="350">
        <img src="https://image-1252383324.cos.ap-guangzhou.myqcloud.com/pay/qqpay.png" width="280" heght="350">
    </div>

## 关于我
   Name: <a title="关于作者" href="https://about.me/jenly1314" target="_blank">Jenly</a>

   Email: <a title="欢迎邮件与我交流" href="mailto:jenly1314@gmail.com" target="_blank">jenly1314#gmail.com</a> / <a title="给我发邮件" href="mailto:jenly1314@vip.qq.com" target="_blank">jenly1314#vip.qq.com</a>

   CSDN: <a title="CSDN博客" href="http://blog.csdn.net/jenly121" target="_blank">jenly121</a>

   Github: <a title="Github开源项目" href="https://github.com/jenly1314" target="_blank">jenly1314</a>

   加入QQ群: <a title="点击加入QQ群" href="http://shang.qq.com/wpa/qunwpa?idkey=8fcc6a2f88552ea44b1411582c94fd124f7bb3ec227e2a400dbbfaad3dc2f5ad" target="_blank">20867961</a>
   <div>
       <img src="https://image-1252383324.cos.ap-guangzhou.myqcloud.com/jenly666.png">
       <img src="https://image-1252383324.cos.ap-guangzhou.myqcloud.com/qqgourp.png">
   </div>

