# Kwai-SDK接入说明

目前已知kwai max适配器SDK1.2.17版本之前有重大问题：

1.kwai sdk获取后台配置参数是通过获取自定义参数来初始化kwai本身SDK的，max sdk 在初始化的时候不支持获取自定义参数，这就导致获取不到app_id 和token 导致初始化失败。

2.激励视频播放的时候奖励已经发放，但是不能点击右上角跳过(但是点击一下视频广告返回后就可以跳过了)，要等视频播放完自动关闭(很坑)。

3.激励视频播放完，会出现崩溃问题，因为kwai-max适配器调用了max已经移除的回调。

针对上面的1,3问题，做了自定义适配,下面是适配接入方法：

## 1.添加kwai支持的依赖
```
//Kwai自身SDK，如需查看依赖的最新版本，请参考上面的Kwai-SDK Maven依赖库
implementation("io.github.kwainetwork:adApi:1.2.18")//必须添加
implementation("io.github.kwainetwork:adImpl:1.2.18")//必须添加

implementation "androidx.media3:media3-exoplayer:1.7.1"//播放视频广告需要，最低1.0.0-alpha01

```
## 2.下载自定义适配了MAX适配器

-[kwai_maxAdapter_custom_v1.2.17]()
