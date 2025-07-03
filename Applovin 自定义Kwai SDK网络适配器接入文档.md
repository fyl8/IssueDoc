# Kwai-SDK接入说明

目前已知kwai max适配器有重问题：

1.kwai sdk获取后台配置参数是通过获取自定义参数来初始化kwai本身SDK的，max sdk 在初始化的时候首次不支持获取自定义参数，这就导致首次获取不到app_id 和token 导致初始化失败。

2.激励视频播放的时候奖励已经发放，但是不能点击右上角跳过(但是点击一下视频广告返回后就可以跳过了)，要等视频播放完自动关闭(很坑)。

3.激励视频播放完，会出现崩溃问题，因为kwai-max适配器调用了max已经移除的回调。

针对上面的1,3问题，做了自定义适配,下面是适配接入方法：

## 1.添加kwai支持的依赖

- [点击这里下载最新版Kwai SDK](https://github.com/fyl8/IssueDoc/blob/main/kwai_SDK_v1.2.19_202507020313.zip)

  1.把压缩文件里面的3个aar文件复制到工程libs文件目录下

  2.添加aar依赖支持

```
dependencies {
    ...
    implementation "androidx.media3:media3-exoplayer:1.7.1"
    implementation fileTree(include: ['*.jar', '*.aar'], dir: 'libs')
}
```
  3.AndroidManifest文件添加支持

```
<meta-data
            android:name="com.kwai.network.maxadapter.KwaiMaxMediationAdapter"
            android:value="KwaiAds" />
```

## 2.MAX后台配置说明(需要运营操作)

Max后台创建自定义网络：

```
Custom Network Name ：KwaiAds

iOS Adapter Class Name ：KCOUnionMaxMediationAdapter

Android / Fire OS Adapter Class Name：
com.kwai.network.maxadapter.KwaiMaxMediationAdapter
```


如图：

![image](https://github.com/user-attachments/assets/99878215-2ce6-412b-b44b-69a7d4c7d21f)


Max后台配置广告位，设置自定义网络的时候需要在自定义网络App Id 输入框设置以下内容：

```

//这里模板是kwai的测试广告位参数，测试完成后记得替换成正式的参数。参数每次更换都要等30-60分钟才生效
{"appId":"899999","token":"EaCw0AipSYyvf3E7","appName":"TEST","domain":"www.kwai.com","storeUrl":"","tagId":"8999996001","floorPrice":"0.1"}

```
参考图：
![image](https://github.com/user-attachments/assets/ee1f383b-c19c-4d18-991c-b603d6c56ce1)


## 3.测试指南

1.确保3个aar包已经依赖进工程，可打包反编译查看是否依赖成功，如图：

![image](https://github.com/user-attachments/assets/0fc6f4c2-2d34-4fda-8810-ccb8c35da38e)

2.修改包名为测试包名：com.yunyu.test ,方便测试.

3.运行应用，筛选日志：Applovin （也可以筛选日志：IAA），日志会打印以下内容：

```
2025-07-02 17:05:06.366 20972-20972 IAA                     com.yunyu.test                       E  初始化成功
2025-07-02 17:05:06.498 20972-21077 IAA                     com.yunyu.test                       E  initProperties result = {"appId":"899999","token":"EaCw0AipSYyvf3E7","appName":"yunyuTest","domain":"www.kwai.com","storeUrl":"","tagId":"8999996001","floorPrice":"0.01"}
2025-07-02 17:05:06.736 20972-20972 IAA                     com.yunyu.test                       D  KwaiAdapter initialize onSuccess

```

4.点击加载广告会打印日志(筛选日志：AppLovinSdk)(搜索日志：networkName=)：

```
2025-07-02 17:05:20.625 20972-20972 AppLovinSdk             com.yunyu.test                       D  [MaxRewardedAd] MaxAdListener.onAdLoaded(ad=MediatedAd{thirdPartyAdPlacementId=8999996001, adUnitId=97f1e4a461d53cc9, format=REWARDED, networkName='KwaiAds'}), listener=com.github.iaa.applovin.MaxAds$1@511e599

```

### PS:如果初始化成功，加载的网络是Applovin，则多看几个广告尝试一下。Kwai的测试广告如图：

![1751510702629](https://github.com/user-attachments/assets/305f0864-d1ef-4cff-8efb-9f465e03b6be)






