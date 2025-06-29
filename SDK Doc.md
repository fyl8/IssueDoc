### [Google SDK 参考文档](https://firebase.google.com/docs/run?hl=zh-cn) 

  + [Firebase接入文档](https://firebase.google.com/docs/android/setup?hl=zh-cn) , [Firebase事件文档](https://firebase.google.com/docs/analytics/get-started?hl=zh-cn&platform=android) , [Firebase事件错误码文档](https://firebase.google.com/docs/analytics/errors?hl=zh-cn)
  + [Google AdMob接入文档](https://firebase.google.com/docs/admob?hl=zh-cn) , [Google AdMob测试广告ID文档](https://developers.google.com/admob/android/test-ads?hl=zh-cn) , [Google AdMob广告错误码参考文档](https://developers.google.com/admob/android/reference/com/google/android/gms/ads/AdRequest?hl=zh-cn#ERROR_CODE_INTERNAL_ERROR)
    

### Adjust文档(V5版本)

  + [Adjust接入文档](https://dev.adjust.com/en/sdk) , [Adjust事件接入文档](https://dev.adjust.com/zh/sdk/android/features/events) , [Adjust广告收入事件-MAX文档](https://dev.adjust.com/zh/sdk/android/integrations/applovin) , [Adjust广告收入事件-TopOn文档](https://dev.adjust.com/zh/sdk/android/integrations/topon) , [Adjust测试指南](https://dev.adjust.com/en/sdk/testing/event-recording)


### Max广告文档

  + [MAx集成文档-unity为例](https://developers.axon.ai/zh/max/unity/overview/integration/) , [Max广告错误文档](https://developers.axon.ai/zh/max/unity/overview/error-handling/) , [Max广告测试指南](https://developers.axon.ai/zh/max/unity/testing-networks/mediation-debugger/) , [Max选择接入广告平台](https://developers.axon.ai/zh/max/android/preparing-mediated-networks)
  + [Max对接快手(kwai)适配器官方文档](https://docs.qingque.cn/d/home/eZQC3WlIjed3sTY4UVMl07Fqz?identityId=2Ez7ByR2eFI#section=h.e8mhdvvnhkir) , [Kwai 官方SDK PDF文档](https://static.yximgs.com/udata/pkg/KS-Android-KSAdSDk/doc/4701b963d40a77bc0f45fd71d30b57da394.pdf)
  + [Kwai-SDK Maven依赖库](https://central.sonatype.com/search?q=io.github.kwainetwork)
  + [Kwai Maven依赖接入](#KwaiMaven依赖接入)
    

### TopOn广告文档

  + [TopOn广告接入文档-unity为例](https://help.toponad.net/cn/docs/wHwXTU) , [TopOn广告接入测试指南](https://help.toponad.net/cn/docs/5xiiue) , [TopOn广告错误码文档](https://help.toponad.net/cn/docs/qYfOSS)


### AppsFlyer文档

  + [AppsFlyer接入文档-Android为例](https://dev.appsflyer.com/hc/docs/android-sdk) , [AppsFlyer事件接入文档](https://dev.appsflyer.com/hc/docs/in-app-events-android) , [AppsFlyer收入事件接入文档](https://dev.appsflyer.com/hc/docs/ad-revenue-1) , [AppsFlyer归因接入文档](https://dev.appsflyer.com/hc/docs/conversion-data-android#organic-vs-non-organic-conversions)


### 其它相关参考文档

  + [Google Play SDK 收录第三方SDK索引](https://play.google.com/sdks)
  + [Facebook广告接入文档](https://developers.facebook.com/docs/audience-network/setting-up/platform-setup/android/add-sdk?locale=zh_CN) , [Facebook广告错误码文档](https://developers.facebook.com/docs/audience-network/setting-up/test/checklist-errors#errors) , [Facebook广告测试指南](https://developers.facebook.com/docs/audience-network/setting-up/test/test-device)
  + [Facebook SDK文档](https://developers.facebook.com/docs/android/componentsdks) , [Facebook SDK事件接入文档](https://developers.facebook.com/docs/app-events/getting-started-app-events-android)
  + [Pangle广告SDK文档](https://www.pangleglobal.com/zh/integration/integrate-pangle-sdk-for-android) , [Pangle错误码文档](https://www.pangleglobal.com/zh/integration/error-code)


# KwaiMaven依赖接入

Kwai的官方文档没找到Maven依赖方式，下面是直接添加依赖的方式

```
//Kwai自身SDK，如需查看依赖的最新版本，请参考上面的Kwai-SDK Maven依赖库
implementation("io.github.kwainetwork:adApi:1.2.18")//必须添加
implementation("io.github.kwainetwork:adImpl:1.2.18")//必须添加

//Max适配器依赖
implementation("io.github.kwainetwork:maxAdapter:1.2.15")//根据需求选择
//这是topon的适配器依赖
implementation("com.anythink.sdk:adapter-tpn-kwai:6.4.27")//根据需求选择
//这是admon的适配器
implementation("io.github.kwainetwork:admobAdapter:1.2.15")//根据需求选择

```



    
  
