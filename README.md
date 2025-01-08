# 对接文档

[一、Firebase Google Analytics对接](#Firebase接入)

* [准备工作](###1.准备工作)
 
* [记录事件](###2.记录事件)
 
[二、Adjust 对接](#Adjust接入)

[三、Max广告接入](#Max广告接入)

[四、Topon广告接入](#Topon广告接入)

[五、提交游戏](#提交游戏)

# Firebase接入
### 1.准备工作
* [Unity添加Firebase](https://firebase.google.com/docs/unity/setup?hl=zh-cn) 

* [Android添加Firebase](https://firebase.google.com/docs/android/setup?hl=zh-cn) 

* [C++添加Firebase](https://firebase.google.com/docs/cpp/setup?hl=zh-cn&platform=android) 

### 2.记录事件
* Unity 记录事件

  Unity可以使用 [LogEvent()](https://firebase.google.com/docs/reference/unity/class/firebase/analytics/firebase-analytics?hl=zh-cn#logevent) 方法立即开始记录事件。

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

// 记录具有多个参数的事件，作为结构传递：
Firebase.Analytics.Parameter[] LevelUpParameters = {
  new Firebase.Analytics.Parameter(Firebase.Analytics.FirebaseAnalytics.ParameterLevel, 5),
  new Firebase.Analytics.Parameter(Firebase.Analytics.FirebaseAnalytics.ParameterCharacter, "mrspoon"),
  new Firebase.Analytics.Parameter("hit_accuracy", 3.14f)
};

Firebase.Analytics.FirebaseAnalytics.LogEvent(
  Firebase.Analytics.FirebaseAnalytics.EventLevelUp,LevelUpParameters);

```

  
菲律宾 &hl=en-PH

香港 &hl=zh_HK

台湾 &hl=zh_TW

泰国 &hl=th_TH

德国 &hl=de_DE

新加坡 &hl=en_SG

加拿大 &hl=en_CA

澳大利亚 &hl=en_AU

法国 &hl=fr_FR

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
