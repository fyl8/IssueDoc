package io.iaa.topon.ad.manager;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustAdRevenue;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.AdjustEvent;
import com.adjust.sdk.LogLevel;
import com.applovin.mediation.MaxAd;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;
import java.util.Map;

public class EventManager {

    private Context context;
    private static boolean log_level = false;
    private static boolean isEnvironment = false;
    private static FirebaseAnalytics mFirebaseAnalytics;
    private static Map<String,String> params = new HashMap<>();

    private static EventManager topManager = null;

    public static EventManager getInstance(Context context) {
        if (topManager == null) {
            topManager = new EventManager(context);
        }
        return topManager;
    }
    public EventManager(Context context){
        this.context = context;
    }

    /**
     * 是否开启打印日志
     */
    public EventManager setEnabledLog(boolean isLog){
        log_level = isLog;
        return topManager;
    }

    /***
     * 是否开启测试环境
     */
    public EventManager setEnvironment(boolean environment){
        isEnvironment = environment;
        return topManager;
    }
    /**初始化Adjust*/
    public EventManager initAdjust(String app_token) {
        String environment = isEnvironment? AdjustConfig.ENVIRONMENT_SANDBOX:AdjustConfig.ENVIRONMENT_PRODUCTION;//测试环境   ENVIRONMENT_PRODUCTION//正式环境
        AdjustConfig config = new AdjustConfig(context, app_token, environment);
        config.setLogLevel(log_level? LogLevel.VERBOSE: LogLevel.SUPPRESS); //LogLevel.VERBOSE	启用所有日志记录  LogLevel.SUPPRESS	禁止所有日志记录
        Adjust.initSdk(config);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
        return topManager;
    }

    /**
     * 上报普通事件
     * @param eventName 事件名称
     * @param adjToken 事件Token
     */
    public static void logEvent(String eventName,String adjToken){
        if(null != mFirebaseAnalytics)mFirebaseAnalytics.logEvent(eventName, new Bundle());
        if(!TextUtils.isEmpty(adjToken)){
            AdjustEvent event = new AdjustEvent(adjToken);
            Adjust.trackEvent(event);
        }
    }

    /**
     * 上报广告收入事件
     * @param ad max广告参数
     */
    public static void logAdRevenueEvent(MaxAd ad){
        if(null != mFirebaseAnalytics){
            Bundle params = new Bundle();
            params.putString(FirebaseAnalytics.Param.AD_PLATFORM, "appLovin");
            params.putString(FirebaseAnalytics.Param.AD_SOURCE, ad.getNetworkName());
            params.putString(FirebaseAnalytics.Param.AD_FORMAT, ad.getFormat().getLabel());
            params.putString(FirebaseAnalytics.Param.AD_UNIT_NAME, ad.getAdUnitId());
            params.putDouble(FirebaseAnalytics.Param.VALUE, ad.getRevenue());
            params.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.AD_IMPRESSION, params);
        }
        AdjustAdRevenue adjustAdRevenue = new AdjustAdRevenue("applovin_max_sdk");
        adjustAdRevenue.setRevenue(ad.getRevenue(), "USD");
        adjustAdRevenue.setAdRevenueNetwork(ad.getNetworkName());
        adjustAdRevenue.setAdRevenueUnit(ad.getAdUnitId());
        adjustAdRevenue.setAdRevenuePlacement(ad.getPlacement());
        adjustAdRevenue.setAdRevenuePlacement("banner");//如果是激励视频，设置成：reward
        Adjust.trackAdRevenue( adjustAdRevenue);
    }

    /**
     * 上报应用内收入(IAP)事件
     * @param adjToken 内购事件Token
     */
    public static void logRevenueEvent(String adjToken,double revenue){
        if(!TextUtils.isEmpty(adjToken)){
            AdjustEvent event = new AdjustEvent(adjToken);
            event.setRevenue(revenue, "USD");
            Adjust.trackEvent(event);
        }
    }





}


