# AppPay

![Image](app/src/main/ic_launcher-web.png)

[![MavenCentral](https://img.shields.io/maven-central/v/com.github.jenly1314.AppPay/apppay?logo=sonatype)](https://repo1.maven.org/maven2/com/github/jenly1314/AppPay)
[![JitPack](https://img.shields.io/jitpack/v/github/jenly1314/AppPay?logo=jitpack)](https://jitpack.io/#jenly1314/AppPay)
[![CI](https://img.shields.io/github/actions/workflow/status/jenly1314/AppPay/build.yml?logo=github)](https://github.com/jenly1314/AppPay/actions/workflows/build.yml)
[![Download](https://img.shields.io/badge/download-APK-brightgreen?logo=github)](https://raw.githubusercontent.com/jenly1314/AppPay/master/app/release/app-release.apk)
[![API](https://img.shields.io/badge/API-16%2B-brightgreen?logo=android)](https://developer.android.com/guide/topics/manifest/uses-sdk-element#ApiLevels)
[![License](https://img.shields.io/github/license/jenly1314/AppPay?logo=open-source-initiative)](https://opensource.org/licenses/mit)


AppPay for Android 是一个专注于App支付的库，将主流的官方App支付集成方式进行二次封装，简化集成步骤，让实现App支付更简单。

## AppPay的各Module相关说明

| 模块（子库）               | 模块说明                            |
|:---------------------|:--------------------------------|
| [WXPay](wxpay)       | 封装的微信支付库                        |
| [AliPay](alipay)     | 封装的支付宝支付库                       |
| [UnionPay](unionpay) | 封装的银联支付库                        |
| [AppPay](apppay)     | 基于以上所有子库进行整合再次封装，让集成App支付一步到位   |

> AppPay的整体结构：将多个独立封装的子库再次封装，并且使用更简单。

## 结构
![Image](art/AppPay_architecture.jpg)

## 引入

### Gradle:

1. 在Project的 **build.gradle** 或 **setting.gradle** 中添加远程仓库

    ```gradle
    repositories {
        //...
        mavenCentral()
    }
    ```

2. 在Module的 **build.gradle** 中添加依赖项
   ```gradle
       // WXPay
       implementation 'com.github.jenly1314.AppPay:wxpay:2.1.0'

       // AliPay
       implementation 'com.github.jenly1314.AppPay:alipay:2.1.0'

       // UnionPay
       implementation 'com.github.jenly1314.AppPay:unionpay:2.1.0'

       // AppPay
       implementation 'com.github.jenly1314.AppPay:apppay:2.1.0'
   ```

## 使用

### WXPay

微信App支付：支持商户App调用微信提供的SDK调用微信支付模块，商户App会跳转到微信中完成支付，支付完后跳回到商户App内，最后展示支付结果；

**WXPay** 主要是基于官方的微信支付SDK进行二次封装，简化集成步骤；使用 **WXPay** 可快速接入微信App支付；

##### WXPay代码示例

```Java
// 初始化微信支付
mWXPay = new WXPay(Context context);

// 设置微信支付监听
mWXPay.setOnPayListener(new WXPay.OnPayListener() {
   @Override
   public void onPayResult(WXPayResult result) {
      // 支付结果
      if (result.isSuccess()) {
         // TODO 支付成功
      }
   }
});

// 发送微信支付请求
mWXPay.sendReq(WXPayReq req);

```
或

```Java
// 发送微信支付请求并监听（参数：req为拉起支付的请求参数）
mWXPay.sendReq(req, new WXPay.OnPayListener() {
   @Override
   public void onPayResult(WXPayResult result) {
        // 支付结果
        if (result.isSuccess()) {
            // TODO 支付成功
        }
    }
});
```

###  AliPay

支付宝App支付：指商家在商家移动端 App 中集成支付宝 SDK，调起支付宝来完成付款的一种支付产品。适用于在商家移动端 App 内使用支付宝支付功能的场景。

**AliPay** 主要是基于官方的支付宝支付SDK进行二次封装，简化集成步骤；使用 **AliPay** 可快速接入支付宝App支付；

##### AliPay代码示例

```Java
 // 初始化支付宝支付
 mAliPay = new AliPay(Activity activity);

 // 设置支付宝支付监听
 mAliPay.setOnPayListener(new AliPay.OnPayListener() {
     @Override
     public void onPayResult(AliPayResult result) {
         // 支付结果
         if(result.isSuccess()){
             //TODO 支付成功
         }
     }
 });

 // 发送支付宝支付请求；
 mAliPay.sendReq(String orderInfo);

```
或
```Java
// 发送支付宝支付请求并监听（参数：orderInfo为拉起支付的订单信息）
mAliPay.sendReq(orderInfo, new AliPay.OnPayListener() {
    @Override
    public void onPayResult(AliPayResult result) {
        // 支付结果
        if(result.isSuccess()){
            //TODO 支付成功
        }
    }
});
```

### UnionPay

银联支付：支持商户移动端APP或者WAP网页中拉起云闪付APP、手机Pay、银行APP（云网版、网银版）等支付工具完成支付；使用 **UnionPay** 可快速接入银联支付；

**UnionPay** 主要是基于官方的银联支付SDK进行二次封装，简化集成步骤；使用 **UnionPay** 可快速接入银联支付；

#### UnionPay代码示例

```java
 // 初始化银联支付
 mUnionPay = new UnionPay(Context context);

 // 设置银联支付监听
 mUnionPay.setOnPayListener(new UnionPay.OnPayListener() {
     @Override
     public void onPayResult(UnionPayResult result) {
         // 支付结果
         if(result.isSuccess()){
             //TODO 支付成功
         }
     }
 });

 // 发送银联支付请求；（参数：orderInfo为订单信息的流水号，即TN；serverMode为银联后台环境标识；用于区分使用测试环境还是正式环境；说明参见：UnionPay.PRO_SERVER_MODE 和 UnionPay.TEST_SERVER_MODE）
 mUnionPay.sendReq(String orderInfo, String serverMode);
```
或
```java
// 发送银联支付请求并监听；（参数：orderInfo为订单信息的流水号，即TN；serverMode为银联后台环境标识；用于区分使用测试环境还是正式环境；说明参见：UnionPay.PRO_SERVER_MODE 和 UnionPay.TEST_SERVER_MODE）
mUnionPay.sendReq(orderInfo, serverMode, new UnionPay.OnPayListener() {
    @Override
    public void onPayResult(UnionPayResult result) {
        // 支付结果
        if(result.isSuccess()){
            //TODO 支付成功
        }
    }
});
```

> 使用银联支付时需要在 `Activity` 中的 `onActivityResult` 方法中调用 **UnionPay** 的 **onActivityResult(int, int, Intent)}** 方法，这样设置的银联支付监听才会被触发。

### AppPay

**AppPay** 是基于以上所有子库进行整合再次封装，让集成App支付一步到位。

#### AppPay代码示例

```Java

 // 初始化AppPay
 mAppPay = new AppPay(Activity activity);

// 发送微信支付请求（参数：req为拉起支付的请求参数）
mAppPay.sendWXPayReq(req, new WXPay.OnPayListener() {
    @Override
    public void onPayResult(WXPayResult result) {
         // 支付结果
         if (result.isSuccess()) {
            // TODO 支付成功
         }
    }
});


// 发送支付宝支付请求（参数：orderInfo为拉起支付的订单信息）
mAppPay.sendAliPayReq(orderInfo, new AliPay.OnPayListener() {
    @Override
    public void onPayResult(AliPayResult result) {
         // 支付结果
         if (result.isSuccess()) {
            // TODO 支付成功
         }
    }
});


// 发送银联支付请求（参数：orderInfo为订单信息的流水号，即TN；serverMode为银联后台环境标识；用于区分使用测试环境还是正式环境；说明参见：UnionPay.PRO_SERVER_MODE 和 UnionPay.TEST_SERVER_MODE）
mAppPay.sendUnionPayReq(orderInfo, serverMode, new UnionPay.OnPayListener() {
    @Override
    public void onPayResult(UnionPayResult result) {
         // 支付结果
         if (result.isSuccess()) {
            // TODO 支付成功
         }
    }
});

```

> 使用银联支付时需要在 `Activity` 中的 `onActivityResult` 方法中调用 **AppPay** 的 **onActivityResult(int, int, Intent)}** 方法，这样设置的银联支付监听才会被触发。

更多使用详情，请查看[app](app)中的源码使用示例或直接查看 [API帮助文档](https://jenly1314.github.io/AppPay/api/)

## 补充说明

### 关于银联支付相关的应用可见性适配

当 **targetSdkVersion** 为30或以上时，请在 **AndroidManifest** 中加入以下内容，以符合 Android 应用可见性机制的要求。

方式一：直接配置读取所有应用列表的权限（可能会影响应用上架）

```java
<uses-permission android:name="android.permission.QUERY_ALL_PACKAGES" />

```

方式二：配置需要查询的应用对应的包名（如果你无法使用方式一，那么可以用方式二）

```xml
    <!-- 适配应用可见性：银联支付支持对应各商户相关的App包名 -->
    <queries>
        <!-- 云闪付 -->
        <package android:name="com.unionpay" />
        <!-- 其他安卓 pay -->
        <package android:name="com.unionpay.tsmservice" />
        <!-- 小米 pay -->
        <package android:name="com.unionpay.tsmservice.mi" />
        <!-- 华为钱包 -->
        <package android:name="com.huawei.wallet" />
        <!-- 平安口袋银行 -->
        <package android:name="com.cmbc.cc.mbank" />
        <!-- 中国建设银行 -->
        <package android:name="com.pingan.paces.ccms" />
        <!-- 建行生活 -->
        <package android:name="com.chinamworld.main" />
        <!-- 中信银行 -->
        <package android:name="com.ecitic.bank.mobile" />
        <!-- 动卡空间 -->
        <package android:name="com.citiccard.mobilebank" />
        <!-- 光大银行 -->
        <package android:name="com.cebbank.mobile.cemb" />
        <!-- 阳光惠生活 -->
        <package android:name="com.ebank.creditcard" />
        <!-- 民生银行 -->
        <package android:name="cn.com.cmbc.newmbank" />
        <!-- 全民生活 -->
        <package android:name="com.cmbc.cc.mbank" />
        <!-- 浦发银行 -->
        <package android:name="cn.com.spdb.mobilebank.per" />
        <!-- 浦大喜奔 -->
        <package android:name="com.spdbccc.app" />
        <!-- 交通银行 -->
        <package android:name="com.bankcomm.Bankcomm" />
        <!-- 买单吧 -->
        <package android:name="com.bankcomm.maidanba" />
        <!-- 招商银行 -->
        <package android:name="cmb.pb" />
        <!-- 掌上生活 -->
        <package android:name="com.cmbchina.ccd.pluto.cmbActivity" />
        <!-- 上海银行 -->
        <package android:name="cn.com.shbank.mper" />
        <!-- 上银美好生活 -->
        <package android:name="cn.com.shbank.pension" />
        <!-- 北京银行（京彩生活） -->
        <package android:name="com.bankofbeijing.mobilebanking" />
        <!-- 掌上京彩 -->
        <package android:name="com.csii.bj.ui" />
        <!-- 中国工商银行 -->
        <package android:name="com.icbc" />
        <!-- 工银 e 生活 -->
        <package android:name="com.icbc.elife" />
        <!-- 中国农业银行 -->
        <package android:name="com.android.bankabc" />
        <!-- 农银 e 管家 -->
        <package android:name="com.abchina.ebizbtob" />
        <!-- 邮储银行 -->
        <package android:name="com.yitong.mbank.psbc" />
        <!-- 邮储信用卡 -->
        <package android:name="com.yitong.mbank.psbc.creditcard" />
        <!-- 中国银行 -->
        <package android:name="com.chinamworld.bocmbci" />
        <!-- 缤纷生活 -->
        <package android:name="com.forms" />
        <!-- 广发银行 -->
        <package android:name="com.cgbchina.xpt" />
        <!-- 发现精彩 -->
        <package android:name="com.cs_credit_bank" />
        <!-- 兴业银行 -->
        <package android:name="com.cib.cibmb" />
        <!-- 好兴动 -->
        <package android:name="com.cib.xyk" />
        <!-- 华夏银行 -->
        <package android:name="com.hxb.mobile.client" />
        <!-- 华彩生活 -->
        <package android:name="com.HuaXiaBank.HuaCard" />
        <!-- 兰州银行 -->
        <package android:name="cn.com.lzb.mobilebank.per" />
    </queries>
```

## 其他

### ABI过滤

在Module的 **build.gradle** 里面的 android{} 中设置支持的 SO 库架构（可选，支持多个平台的 so，支持的平台越多，APK体积越大）

```gradle
    defaultConfig {

        //...

        ndk {
            //设置支持的 SO 库架构（开发者可以根据需要，选择一个或多个平台的 so）
            abiFilters 'armeabi-v7a' // , 'arm64-v8a', 'x86', 'x86_64'
        }
    }
```

## 相关推荐
- [AppUpdater](http://github.com/jenly1314/AppUpdater) 一个专注于App更新，一键傻瓜式集成App版本升级的轻量开源库。
- [RetrofitHelper](http://github.com/jenly1314/RetrofitHelper) 一个支持动态改变BaseUrl，动态配置超时时长的Retrofit帮助类。
- [BaseUrlManager](http://github.com/jenly1314/BaseUrlManager) 一个BaseUrl管理器，主要用于打测试包时，一个App可动态切换到不同的开发环境或测试环境。
- [SuperTextView](http://github.com/jenly1314/SuperTextView) 一个在TextView的基础上扩展了几种动画效果的控件。
- [ImageViewer](http://github.com/AndroidKTX/ImageViewer) 一个图片查看器，一般用来查看图片详情或查看大图时使用。
- [GuidePage](http://github.com/AndroidKTX/GuidePage) 一个App欢迎引导页。一般用于首次打开App时场景，通过引导页指南，概述App特色等相关信息。
- [LogX](http://github.com/jenly1314/LogX) 一个轻量而强大的日志框架；好用不解释。
- [KVCache](http://github.com/jenly1314/KVCache) 一个便于统一管理的键值缓存库；支持无缝切换缓存实现。
- [AndroidKTX](http://github.com/AndroidKTX/AndroidKTX) 一个简化 Android 开发的 Kotlin 工具类集合。
- [AndroidUtil](http://github.com/AndroidUtil/AndroidUtil) 一个整理了Android常用工具类集合，平时在开发的过程中可能会经常用到。
- [MVVMFrame](https://github.com/jenly1314/MVVMFrame) 一个基于Google官方推出的JetPack构建的MVVM快速开发框架。
- [AppTemplate](https://github.com/jenly1314/AppTemplate) 一款基于 MVVMFrame 构建的App模板。

## 官方文档

[微信支付Android接入指南](https://developers.weixin.qq.com/doc/oplatform/Mobile_App/Access_Guide/Android.html)

[支付宝支付Android接入指南](https://opendocs.alipay.com/open/204/105296)

[银联支付Android接入指南](doc/银联支付接入指南Android_v1.0.9.pdf)

<!-- end -->

## 版本日志

#### v2.1.0 ：2023-09-24
* 简化集成步骤
* 优化细节（统一结果判定）

#### [查看更多日志](CHANGELOG.md)


---

![footer](https://jenly1314.github.io/page/footer.svg)

