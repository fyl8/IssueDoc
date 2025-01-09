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
 ###### 您可在以下位置找到推荐的事件类型的实现详情：

  - 推荐事件：请参阅 [com.google.firebase.analytics.FirebaseAnalytics.Event](https://firebase.google.com/docs/reference/android/com/google/firebase/analytics/FirebaseAnalytics.Event?hl=zh-cn) 类参考文档。

  - 预设参数：请参阅 [com.google.firebase.analytics.FirebaseAnalytics.Param](https://firebase.google.com/docs/reference/android/com/google/firebase/analytics/FirebaseAnalytics.Param?hl=zh-cn) 参考文档。

 ###### 查看 Android Studio 调试日志中的事件
 
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
</br>
  #### 验证事件
###### 查看 Android Studio 调试日志中的事件
 
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

  
韩国 &hl=ko_KR

日本 &hl=ja_JP

英国 &hl=en_GB

美国 &hl=en_US

印度 &hl=en_IN 

印尼 &hl=in_ID

阿联酋 &hl=ar_AE

西班牙 &hl=ast_ES

俄罗斯 &hl=ru_RU

新西兰 &hl=en_NZ

巴西 &hl=pt_BR

南非 &hl=af_ZA

瑞典 &hl=se_SE

瑞士 &hl=de_CH

马来西亚 &hl=en_MY

比利时 &hl=de_BE

芬兰 &hl=sv_FI

乌克兰 &hl= UA ru_UA

埃及 &hl=ar

阿拉伯&hl=ar

土耳其&hl=tr-TR

# Adjust接入
## 1. subtitle1
xxx
## 2. subtitle2
yyy

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
