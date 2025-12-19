### Android

1. 复制文件：[EventManager.java](https://github.com/fyl8/IssueDoc/blob/main/EventManager.java)

2. 初始化：
```
EventManager.getInstance(this)//初始化
    .setEnabledLog(true) //是否启用Adjust所有日志,默认禁止所有日志 tips:打正式包记得设置为false
    .setEnvironment(true) //是否开启Adjust测试环境, 默认生产环境 tips:打正式包记得设置为false
    .initAdjust("{Adjust App token}");
```

3. 使用：
```
EventManager.logEvent("{事件名称}","{Adjust事件token}");//记录普通事件


rewardedAd.setRevenueListener(maxAd -> { //在MAX的这个广告回调里面上报
    EventManager.logAdRevenueEvent(maxAd);//记录广告收入事件
}); 


EventManager.logRevenueEvent("{内购事件token}",0.1);//记录内购事件
```

### Unity
1. 复制文件：[EventManager.cs](https://github.com/fyl8/IssueDoc/blob/main/EventManager.cs)

2. 初始化：
```
EventManager.Instance
    .SetEnabledLog(true)        // Adjust 日志  tips:打正式包记得设置为false
    .SetEnvironment(true)       // true = Sandbox / false = Production  tips:打正式包记得设置为false
    .InitAdjust("YOUR_ADJUST_APP_TOKEN");
```

3. 使用：
```
EventManager.LogEvent("{事件名称}","{Adjust事件token}");//记录普通事件


void OnAdRevenuePaidEvent(string adUnitId, MaxSdkBase.AdInfo adInfo){//在MAX的这个广告回调里面上报
    EventManager.LogAdRevenue(adInfo);//记录广告收入事件
}


EventManager.LogRevenue("{内购事件token}",0.99);//记录内购事件
```
