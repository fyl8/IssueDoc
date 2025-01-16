# 问题锦集

* [如何验证Firebase是否集成成功?](#如何验证Firebase是否集成成功)

* [如何验证普通事件是否上报成功?](#如何验证普通事件是否上报成功)

* [如何验证广告收入事件是否上报成功?](#验证广告收入事件是否上报成功)

</br>
</br>

### 如何验证Firebase是否集成成功

在Android studio 日志控制台里面输入 Firebase 过滤打印的日志，启动游戏的时候如果打印Firebase 初始化成功，代表连接上Firebase了，如图：

![image](https://github.com/user-attachments/assets/4bfe3531-bd50-493b-a640-cb9c89de29cc)

</br>

### 如何验证普通事件是否上报成功

在Android studio 日志控制台里面输入 FA-SVC 过滤打印的日志,ctrl+F 搜索埋点的事件名称，会出现日志：

```ruby
2025-01-15 16:40:30.381 12362-17870 FA-SVC                  com.google.android.gms               V  Logging event: origin=app,name={这里是你埋点的事件名称},params=Bundle[{ga_event_origin(_o)=app, ga_screen_class(_sc)=UnityPlayerActivity, ga_screen_id(_si)=177135398416964572}]

```

如图：

![image](https://github.com/user-attachments/assets/0a6916fc-682f-43b1-b999-28060d60bedc)

上图打印的日志中有：Uploading events.   代表事件已经上传

上传的数据如图：

![image](https://github.com/user-attachments/assets/7a49c24a-ee2d-47ad-875d-993d13ff90dd)

</br>

### 验证广告收入事件是否上报成功

在Android studio 日志控制台里面输入 FA-SVC 过滤打印的日志,ctrl+F 搜索埋点的广告收入事件名称，会出现日志：

```ruby
2025-01-15 17:02:59.917 12362-20508 FA-SVC                  com.google.android.gms               V  Logging event: origin=app,name=ad_impression,params=Bundle[{networkName=Adx, adUnitId=300264252, ga_event_origin(_o)=app, ga_screen_class(_sc)=ATRewardVideoActivity, ga_screen_id(_si)=1535523359407231695, unit=USD, revenue=0.001664606505073607}]

```
例如：下图是Firebase广告收入埋点上报：

![image](https://github.com/user-attachments/assets/aab122a8-c296-4163-a69c-61ded27368c4)

所以在控制台搜索事件名称：ad_impression ，如图：

![image](https://github.com/user-attachments/assets/9fba987a-da63-4874-aa32-b2fbfea291ec)











