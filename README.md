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

[三、Max广告接入](#Max广告接入)

[四、Topon广告接入](#Topon广告接入)

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

# Max广告接入
## 1. subtitle1
xxx
## 2. subtitle2
yyy

# Topon广告接入
## 1. subtitle1
xxx
## 2. subtitle2
yyy


# 提交游戏
## 1. subtitle1
xxx
## 2. subtitle2
yyy
