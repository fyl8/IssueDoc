using System.Collections.Generic;
using UnityEngine;
using Firebase.Analytics;
using com.adjust.sdk;
using AppLovinMax.Scripts.Api;

public class EventManager : MonoBehaviour
{
    private static EventManager instance;
    private static bool logLevel = false;
    private static bool isEnvironmentSandbox = false;
    private static FirebaseAnalytics firebaseAnalytics;

    public static EventManager Instance
    {
        get
        {
            if (instance == null)
            {
                GameObject go = new GameObject("EventManager");
                instance = go.AddComponent<EventManager>();
                DontDestroyOnLoad(go);
            }
            return instance;
        }
    }

    /// <summary>
    /// Enable or disable Adjust log output
    /// </summary>
    public EventManager SetEnabledLog(bool isLog)
    {
        logLevel = isLog;
        return this;
    }

    /// <summary>
    /// Set Adjust environment (sandbox / production)
    /// </summary>
    public EventManager SetEnvironment(bool sandbox)
    {
        isEnvironmentSandbox = sandbox;
        return this;
    }

    /// <summary>
    /// Initialize Adjust and Firebase
    /// </summary>
    public EventManager InitAdjust(string appToken)
    {
        string environment = isEnvironmentSandbox
            ? AdjustEnvironment.Sandbox
            : AdjustEnvironment.Production;

        AdjustConfig config = new AdjustConfig(appToken, environment);

        if (logLevel)
            config.setLogLevel(AdjustLogLevel.Verbose);
        else
            config.setLogLevel(AdjustLogLevel.Suppress);

        Adjust.start(config);

        firebaseAnalytics = FirebaseAnalytics.DefaultInstance;
        return this;
    }

    /// <summary>
    /// Log normal event (Firebase + Adjust)
    /// </summary>
    public static void LogEvent(string eventName, string adjToken)
    {
        if (firebaseAnalytics != null)
        {
            firebaseAnalytics.LogEvent(eventName);
        }

        if (!string.IsNullOrEmpty(adjToken))
        {
            AdjustEvent adjustEvent = new AdjustEvent(adjToken);
            Adjust.trackEvent(adjustEvent);
        }
    }

    /// <summary>
    /// Log ad revenue event (AppLovin MAX)
    /// </summary>
    public static void LogAdRevenueEvent(MaxSdkBase.AdInfo adInfo)
    {
        if (firebaseAnalytics != null)
        {
            Parameter[] parameters = new Parameter[]
            {
                new Parameter(FirebaseAnalytics.ParameterAdPlatform, "appLovin"),
                new Parameter(FirebaseAnalytics.ParameterAdSource, adInfo.NetworkName),
                new Parameter(FirebaseAnalytics.ParameterAdFormat, adInfo.AdFormat),
                new Parameter(FirebaseAnalytics.ParameterAdUnitName, adInfo.AdUnitIdentifier),
                new Parameter(FirebaseAnalytics.ParameterValue, adInfo.Revenue),
                new Parameter(FirebaseAnalytics.ParameterCurrency, "USD")
            };

            firebaseAnalytics.LogEvent(FirebaseAnalytics.EventAdImpression, parameters);
        }

        AdjustAdRevenue adRevenue = new AdjustAdRevenue("applovin_max_sdk");
        adRevenue.setRevenue(adInfo.Revenue, "USD");
        adRevenue.setAdRevenueNetwork(adInfo.NetworkName);
        adRevenue.setAdRevenueUnit(adInfo.AdUnitIdentifier);
        adRevenue.setAdRevenuePlacement(adInfo.Placement);

        Adjust.trackAdRevenue(adRevenue);
    }

    /// <summary>
    /// Log IAP revenue event
    /// </summary>
    public static void LogRevenueEvent(string adjToken, double revenue)
    {
        if (!string.IsNullOrEmpty(adjToken))
        {
            AdjustEvent adjustEvent = new AdjustEvent(adjToken);
            adjustEvent.setRevenue(revenue, "USD");
            Adjust.trackEvent(adjustEvent);
        }
    }
}
