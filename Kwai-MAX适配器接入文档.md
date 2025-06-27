# Kwai-SDK接入说明

目前已知kwai max适配器SDK1.2.17版本之前有重大问题：

1.kwai sdk获取后台配置参数是通过获取自定义参数来初始化kwai本身SDK的，max sdk 在初始化的时候不支持获取自定义参数，这就导致获取不到app_id 和token 导致初始化失败。

2.激励视频播放的时候奖励已经发放，但是不能点击右上角跳过(但是点击一下视频广告返回后就可以跳过了)，要等视频播放完自动关闭(很坑)。

3.激励视频播放完，会出现崩溃问题，因为kwai-max适配器调用了max已经移除的回调。

针对上面的1,3问题，做了自定义适配,下面是适配接入方法：

## 1.添加kwai支持的依赖
```
//Kwai自身SDK，如需查看依赖的最新版本，请参Kwai-SDK Maven依赖库
implementation("io.github.kwainetwork:adApi:1.2.18")//必须添加
implementation("io.github.kwainetwork:adImpl:1.2.18")//必须添加
implementation "androidx.media3:media3-exoplayer:1.7.1"//播放视频广告需要，最低1.0.0-alpha01

```

  aar依赖方式：
  
- [Android-1.2.17](https://github.com/fyl8/IssueDoc/blob/main/KwaiAdsSDKFull-Android-1.2.17.zip)

  压缩文件里面的 kwai_maxAdapter_release_v1.2.17_202503030246.aar 替换为下面的自定义Max适配器aar.

## 2.下载自定义适配了MAX适配器

- [kwai_maxAdapter_custom_v1.2.17](https://github.com/fyl8/IssueDoc/blob/main/kwai_maxAdapter_custom_v1.2.17.aar)


## 3.MAX后台配置说明(需要运营操作)

在max后台配置广告位，设置自定义网络的时候需要在自定义网络App Id 输入框设置以下内容：

```

//这里模板是kwai的测试广告位参数，测试完成后记得替换成正式的参数。参数每次更换都要等30-60分钟才生效
{"appId":"899999","token":"EaCw0AipSYyvf3E7","appName":"TEST","domain":"www.kwai.com","storeUrl":"","tagId":"8999996001","floorPrice":"0.01"}

```
参考图：
![image](https://github.com/user-attachments/assets/ee1f383b-c19c-4d18-991c-b603d6c56ce1)


## 4.测试指南

上面都集成完成后，把包名修改成测试包名：com.yunyu.test , 运行后筛选日志：MyKwaiAdapter,会打印下面的日志：
![image](https://github.com/user-attachments/assets/b7a33704-7bdd-434c-9f99-6a391493ee2a)



