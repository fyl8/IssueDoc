# 对接文档

[一、Firebase Google Analytics对接](#Firebase接入)

 * [准备工作](#准备工作)
 
 * [记录事件](#记录事件)
   
   - [记录Unity事件](#记录unity事件)
   
   - [记录Android事件](#记录android事件)
   
   - [记录C++事件](#记录c事件)
   
   - [Analytics错误代码](#Analytics错误代码)
  
   - [验证事件](#验证事件)
 
[二、Adjust 对接](#Adjust接入)

  * [Android接入](#Android接入)

     - [添加依赖](#添加依赖)
       
     - [添加权限](#添加权限)
       
     - [设置Proguard](#设置proguard)
       
     - [集成配置Adjust](#集成配置adjust)
       
     - [记录广告收入](#记录广告收入)
   
     - [记录购买订阅信息](#记录购买订阅信息)
   
  * [Unity接入](#unity接入)

     - [集成指南](#集成指南)
       
     - [配置](#配置)
       
     - [记录广告收入](#记录广告收入)
       
     - [记录购买订阅信息](#记录购买订阅信息)

  * [测试指南](#测试指南)

[三、Topon广告接入](#Topon广告接入)

  * [Android接入指南](#android接入指南)

      - [集成](#集成)

      - [广告样式](#广告样式)

      - [高级设置说明](#高级设置说明)

      - [政策合规](#政策合规)

      - [集成测试](#集成测试)

  * [Unity接入指南](#unity接入指南)

  * [错误码](#错误码)

[四、Max广告接入](#Max广告接入)

[五、提交游戏](#提交游戏)

# Firebase接入 
### 准备工作 
  + [Unity添加Firebase](https://firebase.google.com/docs/unity/setup?hl=zh-cn) 

  + [Android添加Firebase](https://firebase.google.com/docs/android/setup?hl=zh-cn) 

  + [C++添加Firebase](https://firebase.google.com/docs/cpp/setup?hl=zh-cn&platform=android) 

### 记录事件 
  #### 记录Unity事件

Unity可以使用 [LogEvent()](https://firebase.google.com/docs/reference/unity/class/firebase/analytics/firebase-analytics?hl=zh-cn#logevent) 方法立即开始记录事件。

您可在以下位置找到推荐事件的实现详情：

   + 推荐事件：请查阅 [Event](https://firebase.google.com/docs/reference/unity/class/firebase/analytics/firebase-analytics?hl=zh-cn#eventaddpaymentinfo) 常量列表。
   + 预设参数：请参阅 [Parameters](https://firebase.google.com/docs/reference/unity/class/firebase/analytics/parameter?hl=zh-cn) 常量列表。

以下示例使用各种类型的参数来记录事件：
```ruby
 //参数1：事件名称。  参数2：参数名称。  参数3：参数值
Firebase.Analytics.FirebaseAnalytics.LogEvent(string name);
Firebase.Analytics.FirebaseAnalytics.LogEvent(string name,string parameterName,string parameterValue);

  // 记录没有参数的事件。记录login的事件
Firebase.Analytics.FirebaseAnalytics.LogEvent(Firebase.Analytics.FirebaseAnalytics.EventLogin);

// 记录带有浮点参数的事件  例如：记录观看广告获得的收入
Firebase.Analytics.FirebaseAnalytics.LogEvent("ad_purchase", "revenue_value", 0.04f);

// 记录带有 int 参数的事件。 例如：记录了游戏关卡达到5关
Firebase.Analytics.FirebaseAnalytics.LogEvent("level","level_value",5);

// 记录带有字符串参数的事件。
Firebase.Analytics.FirebaseAnalytics.LogEvent(
    Firebase.Analytics.FirebaseAnalytics.EventJoinGroup,
    Firebase.Analytics.FirebaseAnalytics.ParameterGroupId,
    "spoon_welders");

// 记录具有多个参数的事件,例如：记录广告收入，广告id，广告名称等等：
Firebase.Analytics.Parameter[] parameters = {
  new Firebase.Analytics.Parameter("adUnitId", "这里传入广告ID"),
  new Firebase.Analytics.Parameter("networkName", "这里传入广告名称"),
  new Firebase.Analytics.Parameter("revenue", 3.14f)
};
Firebase.Analytics.FirebaseAnalytics.LogEvent("ad_purchase",parameters);
```
</br>
</br>
</br>
  #### 记录Android事件

创建 FirebaseAnalytics 实例后，您就可以使用该实例通过 [logEvent()](https://firebase.google.com/docs/reference/android/com/google/firebase/analytics/FirebaseAnalytics?hl=zh-cn#logEvent(java.lang.String,%20android.os.Bundle)) 方法来记录事件。

```ruby
// Obtain the FirebaseAnalytics instance.
FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
// 记录具有多个参数的事件，例如：记录广告收入，广告id，广告名称
Bundle params = new Bundle();
params.putString("adUnitId", "这里传入广告ID");
params.putInt("networkName", text);
params.putInt("networkName", "这里传入广告名称");
params.putFloat("revenue",0.0003f);
mFirebaseAnalytics.logEvent("ad_purchase", params);
```

您可在以下位置找到推荐的事件类型的实现详情：

  - 推荐事件：请参阅 [com.google.firebase.analytics.FirebaseAnalytics.Event](https://firebase.google.com/docs/reference/android/com/google/firebase/analytics/FirebaseAnalytics.Event?hl=zh-cn) 类参考文档。

  - 预设参数：请参阅 [com.google.firebase.analytics.FirebaseAnalytics.Param](https://firebase.google.com/docs/reference/android/com/google/firebase/analytics/FirebaseAnalytics.Param?hl=zh-cn) 参考文档。

查看 Android Studio 调试日志中的事件
 
您可以启用详细日志记录功能以监控 SDK 的事件记录，从而帮助验证是否正确记录了事件，包括自动和手动记录的事件。

您可以通过一系列 adb 命令启用详细日志记录功能：
```ruby
adb shell setprop log.tag.FA VERBOSE
```
```ruby
adb shell setprop log.tag.FA-SVC VERBOSE
```
```ruby
adb logcat -v time -s FA FA-SVC
```
此命令可在 Android Studio logcat 中显示您的事件，帮助您立即验证所发送的事件。


  #### Analytics错误代码 
 - [Analytics错误代码](https://firebase.google.com/docs/analytics/errors?hl=zh-cn)
   
</br>

  #### 验证事件

查看 Android Studio 调试日志中的事件
 
您可以启用详细日志记录功能以监控 SDK 的事件记录，从而帮助验证是否正确记录了事件，包括自动和手动记录的事件。

您可以通过一系列 adb 命令启用详细日志记录功能：
```ruby
adb shell setprop log.tag.FA VERBOSE
```
```ruby
adb shell setprop log.tag.FA-SVC VERBOSE
```
```ruby
adb logcat -v time -s FA FA-SVC
```
此命令可在 Android Studio logcat 中显示您的事件，帮助您立即验证所发送的事件。
</br>
</br>
</br>


  #### 记录C事件

配置 [firebase::App](https://firebase.google.com/docs/analytics/cpp/start?hl=zh-cn) 实例后,，您可以开始使用 [LogEvent()](https://firebase.google.com/docs/reference/cpp/namespace/firebase/analytics?hl=zh-cn#logevent) 方法记录事件。

记录事件，请参阅[文档](https://firebase.google.com/docs/analytics/cpp/events?hl=zh-cn)

  


# Adjust接入
 ## Android接入

#### 添加依赖
```ruby
dependencies {
   implementation 'com.adjust.sdk:adjust-android:5.0.2'
   implementation 'com.android.installreferrer:installreferrer:2.2'
   // Add the following if you are using the Adjust SDK inside web views on your app
   implementation 'com.adjust.sdk:adjust-android-webbridge:5.0.2'
   implementation 'com.google.android.gms:play-services-ads-identifier:18.0.1'
   implementation 'com.android.installreferrer:installreferrer:2.2'
}
```

#### 添加权限
```ruby
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

<!--Adjust SDKcom.google.android.gms.AD_ID默认包含该权限。如果您需要让您的应用符合 COPPA（儿童在线隐私保护法）或您的应用不针对 Google Play 商店，则必须使用remove指令删除此权限。-->
<uses-permission android:name="com.google.android.gms.permission.AD_ID"/>
```

#### 设置Proguard
```ruby
-keep class com.adjust.sdk.** { *; }
-keep class com.google.android.gms.common.ConnectionResult {
   int SUCCESS;
}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient {
   com.google.android.gms.ads.identifier.AdvertisingIdClient$Info getAdvertisingIdInfo(android.content.Context);
}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info {
   java.lang.String getId();
   boolean isLimitAdTrackingEnabled();
}
-keep public class com.android.installreferrer.** { *; }
```

#### 集成配置Adjust

要配置 Adjust SDK，您需要实例化一个AdjustConfig对象。此对象包含您需要传递给 Adjust SDK 的只读配置选项。

要实例化您的配置对象，请创建一个新AdjustConfig实例并传递以下参数：

  - context( Context)：您的应用正在其中运行的Android 上下文this。传递以获取当前应用上下文。
  - appToken( String)：您的Adjust 应用令牌。
  - environment( String)：您要在其中运行 SDK 的环境。通过AdjustConfig.ENVIRONMENT_SANDBOX以沙盒模式运行 SDK 进行测试。通过AdjustConfig.ENVIRONMENT_PRODUCTION以生产模式运行 SDK 进行发布。
  - allowSuppressLogLevel( Boolean)：是否禁止所有日志记录。设置为true禁止日志记录或false启用日志记录。

设置日志记录级别：
| 日志级别 | 描述 |
| :-    |  :-   |
| LogLevel.VERBOSE    |  启用所有日志记录   |
| LogLevel.DEBUG    |  启用调试日志记录   |
| LogLevel.INFO    |  仅显示信息级别日志（默认选项）   |
| LogLevel.WARN    |  禁用信息警告   |
| LogLevel.ERROR    |  禁用警告级别及以下的日志记录   |
| LogLevel.ASSERT    |  禁用错误级别及以下的日志记录   |
| LogLevel.SUPPRESS    |  禁止所有日志记录   |

更多配置请参考[Adjust官方文档](https://dev.adjust.com/en/sdk/android/configuration)

```ruby
import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustConfig;

public class GlobalApplication extends Application {
   @Override
   public void onCreate() {
      super.onCreate();

      String appToken = "{AppToken 找运营人员索要}";
      String environment = AdjustConfig.ENVIRONMENT_SANDBOX;//沙盒模式运行 SDK 进行测试
      //String environment = AdjustConfig.ENVIRONMENT_PRODUCTION;//以生产模式运行 SDK 进行发布
      AdjustConfig config = new AdjustConfig(this, appToken, environment);
      config.setLogLevel(LogLevel.VERBOSE);//设置日志记录级别
      Adjust.initSdk(config);
   }
}
```
</br>

#### 记录广告收入
要实例化广告收入对象，请创建一个新AdjustAdRevenue实例并传递以下参数：

 - source（String）：广告收入来源。请参阅下表了解可用来源

| Argument | 广告收入来源 |
| :-    |  :-   |
| "applovin_max_sdk"    |  AppLovin MAX   |
| "admob_sdk"    |  AdMob   |
| "ironsource_sdk"    |  ironSource   |
| "admost_sdk"    |  AdMost   |
| "unity_sdk"    |  Unity   |
| "helium_chartboost_sdk"    |  Helium Chartboost   |
| "adx_sdk"    |  Ad(X)   |
| "publisher_sdk"    |  Generic source   |
| "tradplus_sdk"    |  TradPlus   |
| "topon_sdk"    |  TopOn   |
| "mopub"    |  Mopub   |

```ruby
AdjustAdRevenue adjustAdRevenue = new AdjustAdRevenue("applovin_max_sdk");
Adjust.trackAdRevenue(adjustAdRevenue);
```

此示例显示如何设置和记录具有以下属性的广告收入对象：

  - AppLovin MAX 作为收入来源
  - 1 欧元作为收入金额
  - 10 次广告展示
  - "network1"作为广告收入网络
  - "unit1"作为广告收入单位
  - "banner"作为广告收入展示位置
  - 回调参数："key1" = "value1"
  - 合作伙伴参数："key2" = "value2"
```ruby
AdjustAdRevenue adjustAdRevenue = new AdjustAdRevenue(AdjustConfig.AD_REVENUE_APPLOVIN_MAX);
adjustAdRevenue.setRevenue(广告收入金额, "货币单位(一般是USD)");
//adjustAdRevenue.setAdImpressionsCount(10);
adjustAdRevenue.setAdRevenueNetwork("network1");//广告收入网络
//adjustAdRevenue.setAdRevenueUnit("unit1");//广告收入单位
adjustAdRevenue.setAdRevenuePlacement("banner");//广告收入展示位置
//adjustAdRevenue.addCallbackParameter("key1", "value1");
//adjustAdRevenue.addPartnerParameter("key2", "value2");
Adjust.trackAdRevenue(adjustAdRevenue);
```
了解更多更详细参数请参考[官方文档](https://dev.adjust.com/en/sdk/android/features/ad-revenue)
</br>
</br>

#### 记录购买订阅信息

首先，您需要实例化一个包含订阅购买详情的订阅对象。为此，请创建一个新AdjustPlayStoreSubscription对象并传递以下参数：
| Parameter | Data type |  Description  |
| :-    |  :-   |  :-   |
| price    |  long   |  订阅价格   |
| currency    |  String   |  订阅货币   |
| sku    |  String   |  产品ID   |
| orderId    |  String   |  您的交易 ID   |
| signature    |  String   |  购买数据的签名   |
| purchaseToken    |  String   |  交易的唯一令牌。有关详细信息，请参阅[Google 的文档](https://developer.android.com/reference/com/android/billingclient/api/Purchase)   |

```ruby
AdjustPlayStoreSubscription subscription = new AdjustPlayStoreSubscription(price,currency,sku,orderId,signature,purchaseToken);
Adjust.trackPlayStoreSubscription(subscription);
```

更多详细参数方法，请参考[官方文档](https://dev.adjust.com/en/sdk/android/features/subscriptions)
</br>
</br>

 ## Unity接入
 
  ### 集成指南
  
   [Unity SDK 集成指南](https://dev.adjust.com/en/sdk/unity#get-the-adjust-sdk)
   
  ### 配置

   要配置 Adjust SDK，您需要实例化一个AdjustConfig对象。此对象包含您需要传递给 Adjust SDK 的只读配置选项。

   要实例化您的配置对象，请创建一个新AdjustConfig实例并传递以下参数：

  - appToken( String)：您的Adjust 应用令牌。
  - environment( String)：您要在其中运行 SDK 的环境。通过AdjustConfig.ENVIRONMENT_SANDBOX以沙盒模式运行 SDK 进行测试。通过AdjustConfig.ENVIRONMENT_PRODUCTION以生产模式运行 SDK 进行发布。
  - allowSuppressLogLevel( Boolean)：是否禁止所有日志记录。设置为true禁止日志记录或false启用日志记录。

设置日志记录级别：
| 日志级别 | 描述 |
| :-    |  :-   |
| LogLevel.VERBOSE    |  启用所有日志记录   |
| LogLevel.DEBUG    |  启用调试日志记录   |
| LogLevel.INFO    |  仅显示信息级别日志（默认选项）   |
| LogLevel.WARN    |  禁用信息警告   |
| LogLevel.ERROR    |  禁用警告级别及以下的日志记录   |
| LogLevel.ASSERT    |  禁用错误级别及以下的日志记录   |
| LogLevel.SUPPRESS    |  禁止所有日志记录   |

```ruby
AdjustConfig adjustConfig = new AdjustConfig("{YourAppToken}", AdjustEnvironment.Sandbox, false);
// ...
Adjust.InitSdk(adjustConfig);
```
更多配置请参考[Adjust官方文档](https://dev.adjust.com/en/sdk/unity/configuration)

  
  ### 记录广告收入

此示例显示如何设置和记录具有以下属性的广告收入对象：

  - AppLovin MAX 作为收入来源
  - 1 欧元作为收入金额
  - 10 次广告展示
  - "network1"作为广告收入网络
  - "unit1"作为广告收入单位
  - "banner"作为广告收入展示位置
  - 回调参数："key1" = "value1"
  - 合作伙伴参数："key2" = "value2"
```ruby
AdjustAdRevenue adjustAdRevenue = new AdjustAdRevenue(AdjustConfig.AD_REVENUE_APPLOVIN_MAX);
adjustAdRevenue.setRevenue(广告收入金额, "货币单位(一般是USD)");
//adjustAdRevenue.setAdImpressionsCount(10);
adjustAdRevenue.setAdRevenueNetwork("network1");//广告收入网络
//adjustAdRevenue.setAdRevenueUnit("unit1");//广告收入单位
adjustAdRevenue.setAdRevenuePlacement("banner");//广告收入展示位置
//adjustAdRevenue.addCallbackParameter("key1", "value1");
//adjustAdRevenue.addPartnerParameter("key2", "value2");
Adjust.trackAdRevenue(adjustAdRevenue);
```
了解更多更详细参数请参考[官方文档](https://dev.adjust.com/en/sdk/unity/features/ad-revenue)
</br>
</br>
  
  ### 记录购买订阅信息
首先，您需要实例化一个包含订阅购买详情的订阅对象。为此，请创建一个新AdjustPlayStoreSubscription对象并传递以下参数：
| Parameter | Data type |  Description  |
| :-    |  :-   |  :-   |
| price    |  long   |  订阅价格   |
| currency    |  String   |  订阅货币   |
| sku    |  String   |  产品ID   |
| orderId    |  String   |  您的交易 ID   |
| signature    |  String   |  购买数据的签名   |
| purchaseToken    |  String   |  交易的唯一令牌。有关详细信息，请参阅[Google 的文档](https://developer.android.com/reference/com/android/billingclient/api/Purchase#getPurchaseToken())   |

```ruby
AdjustPlayStoreSubscription subscription = new AdjustPlayStoreSubscription(price,currency,sku,orderId,signature,purchaseToken);
Adjust.trackPlayStoreSubscription(subscription);
```

更多详细参数方法，请参考[官方文档](https://dev.adjust.com/en/sdk/unity/features/subscriptions)
</br>
</br>



 ## 测试指南
 1.测试 SDK 是否将您的应用内事件发送到 Adjust。
   * 测试控制台
     - 按照您平台的集成指南集成 Adjust SDK。
     - 将环境设置为sandbox。这可确保您的应用仅向测试控制台发送信息。
     - 将日志级别设置为详细，以捕获应用程序中的所有日志信息。
     - 设置测试设备或模拟设备并找到设备的广告 ID。
     - 在您的测试设备上触发测试事件。
     - 打开测试控制台，检查设备的广告ID状态。
     
如果事件记录成功，事件令牌和事件时间将显示在控制台的“上次事件时间”部分。

![](https://dev.adjust.com/_astro/testing-console.DvnTIhzU_1rcN8D.webp)

   * 集成开发环境
     
     - 按照您平台的入门指南集成 Adjust SDK。
       
     - 将环境设置为sandbox。这可确保您的应用仅向测试控制台发送信息。
       
     - 将日志级别设置为详细，以捕获应用程序中的所有日志信息。
       
     - 设置测试设备或模拟设备并找到设备的广告 ID。
       
     - 在您的测试设备上触发测试事件。
       
     - 在 IDE 中打开日志控制台并查找记录的事件Adjust。
     
如果事件记录成功，则会记录事件令牌以及消息“事件跟踪”。


 2.测试 SDK 是否正在将您的订阅信息发送到 Adjust。
 
   * 测试它是否有效：
     - 按照您平台的集成指南集成 Adjust SDK。
       
     - 将环境设置为sandbox。这可确保您的应用仅向测试控制台发送信息。
       
     - 将日志级别设置为详细，以捕获应用程序中的所有日志信息。
       
     - 设置测试设备或模拟设备并找到设备的广告 ID。
       
     - 如果您之前曾使用该设备进行过测试:
       
       - 从测试设备中删除您的应用程序。
         
       - [从测试控制台中清除设备的广告 ID。按照AppView 中测试控制台](https://help.adjust.com/en/article/testing-console#forget-device)的说明进行操作。
         
       - 或者，调用[忘记设备端点](https://dev.adjust.com/en/api/device-api/forget/)来删除有关该设备的现有信息。
         
     - 下载并打开包含 Adjust SDK 的应用测试版本。
       
     - 触发测试购买。
       
     - 打开测试控制台，检查设备的广告ID状态。
       
 如果订阅记录成功，控制台输出中将填充以下字段：
 
- 上次订阅事件类型
- [上次订阅事件子类型](https://help.adjust.com/en/article/manage-subscription-data#subscription-event-subtypes)
- 上次订阅事件时间戳
- 产品 ID
  
  ![](https://dev.adjust.com/_astro/testing-console.PELeaOX1_x2rTl.webp)

# Topon广告接入
## Android接入指南
 ### 集成
 
  - [SDK下载](https://help.toponad.com/cn/docs/ji-cheng-rnnt#1._SDK%E4%B8%8B%E8%BD%BD),每个账号下的SDK都有一定的区别,请联系我方运营人员索要下载SDK，

  - [集成配置](https://help.toponad.com/cn/docs/ji-cheng-rnnt#2._%E9%9B%86%E6%88%90%E9%85%8D%E7%BD%AE),请参考官方文档。

  - [初始化SDK](https://help.toponad.com/cn/docs/ji-cheng-rnnt#3._%E5%88%9D%E5%A7%8B%E5%8C%96SDK)

示例代码：
```ruby
   public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate()
        ATSDK.init(this, "Your App ID", "Your App Key");
    }
}
```
 
 ### 广告样式
 
  [激励视频广告接入](https://help.toponad.com/cn/docs/ji-li-shi-pin)
  
  [插屏广告接入](https://help.toponad.com/cn/docs/cha-ping-guang-gao)
  
  [开屏广告接入](https://help.toponad.com/cn/docs/kai-ping-guang-gao)
  
  [横幅广告接入](https://help.toponad.com/cn/docs/heng-fu-guang-gao)
  
  [原生广告接入](https://help.toponad.com/cn/docs/yuan-sheng-guang-gao)

  [回调信息说明](https://help.toponad.com/cn/docs/hui-diao-xin-xi-shuo-ming)
  
  [高级设置说明](https://help.toponad.com/cn/docs/eePVq0)
 
  [政策合规](https://help.toponad.com/cn/docs/XTVV9t)
 
 ### 集成测试

  * 如何测试广告

    * SDK日志开关
   
    ```ruby
    ATSDK.setNetworkLogDebug(true);
    ```

    打开日志功能之后，可以获取到测试设备ID：
    
      1.打开AndroidStudio的Logcat，以 "anythink" 为 tag 进行过滤，查看SDK的日志
            
      2.TopOn SDK初始化时会打印如下日志，可以从日志中获取设备ID
            
      3.如果没有打印GAID的值，请检查当前手机有没有Google服务，需要使用带Google服务的手机机型测试
    
```ruby
anythink: ****************************************************************************
anythink: GAID(ADID): b796a53f-61bf-4e91-bc67-d505cdb97cf8 , AndroidID: f669f2b7137d82b9
anythink: ****************************************************************************
 ```

* 通过测试工具测试广告

引入测试工具，build.gradle中添加引用

```ruby
repositories {
    maven {
        url "https://jfrog.anythinktech.com/artifactory/debugger"
    }
}
dependencies {
    // Debugger UI Tools
    implementation 'com.anythink.sdk:debugger-ui:1.1.0'
}

```


调起测试工具,注意：必须在初始化TopOn SDK之后，调用下面的方法才能使用测试工具。

```ruby
ATDebuggerUITest.showDebuggerUI(context);
```
 
 * 通过调试模式测试广告

 使用调试模式可以测试单个广告平台，具体详细使用方法，请参考[官方文档](https://help.toponad.com/cn/docs/5xiiue#3.1_%E8%B0%83%E8%AF%95%E6%A8%A1%E5%BC%8F%E6%94%AF%E6%8C%81%E7%9A%84%E5%B9%BF%E5%91%8A%E5%B9%B3%E5%8F%B0)
 

## Unity接入指南

  ### SDK下载和导入说明
  
请联系运营人员帮忙下载对应的Unity SDK

SDK导入请参考导入说明：[Android & iOS导入说明](https://help.toponad.com/cn/docs/hd01b0)

  ### UnitySDK初始化

API说明，请参考官方对应的[API说明表](https://help.toponad.com/cn/docs/Unity-SDK-chu-shi-hua)

示例代码，详细参考[demo project](https://github.com/toponteam/TopOn-Unity-Demo-test):

```ruby
//（可选配置）设置自定义的Map信息，可匹配后台配置的广告商顺序的列表（App纬度）
//注意：调用此方法会清除setChannel()、setSubChannel()方法设置的信息，如果有设置这些信息，请在调用此方法后重新设置
ATSDKAPI.initCustomMap(new Dictionary<string, string> { { "unity3d_data", "test_data" } }); 

//（可选配置）设置自定义的Map信息，可匹配后台配置的广告商顺序的列表（Placement纬度）
ATSDKAPI.setCustomDataForPlacementID(new Dictionary<string, string> { { "unity3d_data_pl", "test_data_pl" } } ,placementId);

//（可选配置）设置渠道的信息，开发者可以通过该渠道信息在后台来区分看各个渠道的广告数据
//注意：如果有使用initCustomMap()方法，必须在initCustomMap()方法之后调用此方法
ATSDKAPI.setChannel("unity3d_test_channel"); 

//（可选配置）设置子渠道的信息，开发者可以通过该渠道信息在后台来区分看各个渠道的子渠道广告数据
//注意：如果有使用initCustomMap()方法，必须在initCustomMap()方法之后调用此方法
ATSDKAPI.setSubChannel("unity3d_test_subchannel"); 

//设置开启Debug日志（强烈建议测试阶段开启，方便排查问题）
ATSDKAPI.setLogDebug(true);

//（必须配置）SDK的初始化
ATSDKAPI.initSDK("a5c4ad280995c9", "7b4e37f819dbee652ef79c4506e14288");//Use your own app_id & app_key here
```

  ### 不同形式的广告接入
  
[激励视频广告接入](https://help.toponad.com/cn/docs/ji-li-shi-pin-guang-gao-ji-cheng-shuo-ming)

[插屏广告接入](https://help.toponad.com/cn/docs/cha-ping-guang-gao-ji-cheng-shuo-ming)

[Banner广告接入](https://help.toponad.com/cn/docs/Banner-guang-gao-ji-cheng-shuo-ming)

[原生广告接入](https://help.toponad.com/cn/docs/yuan-sheng-guang-gao-ji-cheng-shuo-ming)

[开屏广告接入](https://help.toponad.com/cn/docs/kai-ping-guang-gao-jie-ru)

</br>
</br>

## 错误码

  ### TopOn错误码信息说明

参阅下表：

| 错误码 | 说明 |
| :-    |  :-   |
| 10001    |  App ID或App Key错误，请检查初始化TopOn SDK时传入的App ID和App Key   |
| 10003    |  1. App ID错误，请检查初始化TopOn SDK时传入的App ID</br>2. TopOn广告位ID与App ID不匹配，请检查代码中调用load方法时传入的Placement ID   |
| 10004    |  TopOn广告位ID错误，请检查调用load方法时传入的Placement ID   |
| 9999    |  1. 网络请求出现错误，检查网络状态是否正常</br>2. 出现错误信息：chain validation failed，请检查是否有调整过测试设备的系统时间   |
| 9990    |  HTTP接口请求返回的状态错误，需要联系TopOn同事查看错误信息   |
| 9991    |  接口请求返回的业务代码错误，需要联系TopOn同事查看错误信息   |
| 9992    |  GDPR的等级设置过低，检查是否手动设置了FORBIDDEN等级   |
| 2001    |  广告加载超时，检查当前的测试的广告源是否是海外平台，手机网络是否已经翻墙   |
| 2002    |  TopOn的SDK包导入不全，缺失第三方广告厂商的Adapter包（anythink_network_*.aar），确认是否已经按照指引导入聚合的第三方需要的SDK包   |
| 2003    |  当前广告位的展示次数已经达到上限，需要确认TopOn的后台配置是否限制了该广告位的展示次数   |
| 2004    |  当前广告位处于非展示时间段，需要确认TopOn的后台配置是否限制了广告位的展示间隔   |
| 2005    |  该广告位处于加载阶段，同一个广告位发起请求后，在接收到加载成功或失败的回调之前，该广告位不能发起下一次的加载，请等待加载成功、失败的回调   |
| 2006    |  检查导入第三方广告平台的SDK包是否齐全，如果齐全则检查导入的版本是否与GitHub上指定的版本是否相符合，否则需要将第三方SDK包补充完整   |
| 2007    |  通常发生于，在加载失败的回调中立刻发起广告加载。禁止在加载失败的回调中立刻发起广告加载，距离上一次该广告位加载失败需满足一定时间间隔才可发起广告加载，请延迟调用广告加载的时间   |
| 2008    |  同一个广告位加载失败后禁止在加载失败的回调里立马调用load方法进行重试，请延迟10s以上再进行重试   |
| 2009    |  在一定时间间隔内广告位的加载次数达到上限   |
| 3001    |  策略获取错误</br>1. 检查网络是否正常</br>2. 检查使用的appid，appkey，placementid是否匹配</br>3. 检查代码中appid，appkey，placementid是否正确并且匹配（不能包含空格）</br>调用 ATSDK.setNetworkLogDebug(true); 在Logcat中过滤 anythink 可查看当前传入SDK的参数，请检查这些参数   |
| 3002    |  传入的appid,appkey，placementid其中有一个为空字符</br>调用 ATSDK.setNetworkLogDebug(true); 在Logcat中过滤 anythink 可查看当前传入SDK的参数，请检查这些参数   |
| 3003    |  广告位与调用的API不匹配，例如：Banner的广告位调用了激励视频的API去加载广告   |
| 4001    |  通常发生于第三方广告平台返回错误导致没有广告填充，可通过AdError.getFullErrorInfo()获取完全的错误信息，通过platformCode及platformMsg查看广告平台的错误码及错误信息，请查看第三方广告平台错误码进行排查   |
| 4002    |  Context的上下文已经被销毁，需要重新创建相应的广告类型对象再重新发起广告加载   |
| 4003    |  该广告位的状态已经关闭，检查TopOn后台该广告位的状态开关是否开启   |
| 4004    |  该广告位没有在TopOn后台配置广告源的信息，需要到TopOn后台-聚合管理 为广告位添加第三方广告平台的广告源   |
| 4005    |  广告位下的所有广告源被过滤，可能的原因如下：</br>1. 检查是否在TopOn后台设置了广告源的展示上限、展示间隔</br>2. 如果只配置了头部竞价广告源，询价失败时，头部竞价广告源将被过滤   |
| 4006    |  视频播放失败，参照 4001错误码 进行排查   |
| 4007    |  广告源竞价失败，参照 4001错误码 进行排查   |
| 4008    |  因为开发者代码中的自定义过滤逻辑，导致广告源被过滤。如果过滤不符合预期，请排查自定义过滤逻辑   |
| 4009    |  调试模式下，该广告位没有配置广告源信息   |

  ### 第三方平台错误码信息说明

详细错误码文档，请参考[官方文档](https://help.toponad.com/cn/docs/qYfOSS#2._%E7%AC%AC%E4%B8%89%E6%96%B9%E5%B9%BF%E5%91%8A%E5%B9%B3%E5%8F%B0%E9%94%99%E8%AF%AF%E7%A0%81)

  ### [TopOn常见问题-Android](https://help.toponad.com/cn/docs/Android-j9K8)

  ### [TopOn常见问题-Unity](https://help.toponad.com/cn/docs/Unity-8xiG)

# Max广告接入
## 1. subtitle1
xxx
## 2. subtitle2
yyy


# 提交游戏
## 1. subtitle1
xxx
## 2. subtitle2
yyy
