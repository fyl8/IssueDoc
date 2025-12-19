### Android

1. 复制文件：[EventManager.java](https://github.com/fyl8/IssueDoc/blob/main/EventManager.java)

2. 初始化：
```
EventManager.getInstance(this)//初始化
    .setEnabledLog(true) //是否启用Adjust所有日志,默认禁止所有日志
    .setEnvironment(true) //是否开启Adjust测试环境, 默认生产环境
    .initAdjust("{Adjust App token}");
```

3. 使用：
```
        EventManager.logEvent("{事件名称}","{Adjust事件token}");//记录普通事件

        EventManager.logAdRevenueEvent("{MaxAd}");//记录广告收入事件

        EventManager.logRevenueEvent("{内购事件token}",0.1);//记录内购事件
```

### Unity
1. 复制文件：[EventManager.cs](https://github.com/fyl8/IssueDoc/blob/main/EventManager.cs)

2. 初始化：
```
EventManager.Instance
    .SetEnabledLog(true)        // Adjust 日志
    .SetEnvironment(true)       // true = Sandbox / false = Production
    .InitAdjust("YOUR_ADJUST_APP_TOKEN");
```

3. 使用：
```
EventManager.LogEvent("{事件名称}","{Adjust事件token}");//记录普通事件

void OnAdRevenuePaidEvent(string adUnitId, MaxSdkBase.AdInfo adInfo)
{
    EventManager.LogAdRevenue(adInfo);//记录广告收入事件
}

EventManager.LogRevenue("{内购事件token}",0.99);//记录内购事件
```
