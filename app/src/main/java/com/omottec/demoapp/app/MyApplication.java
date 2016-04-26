package com.omottec.demoapp.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import com.didi.passenger.onehttpdns.HttpDnsManager;
import com.omottec.demoapp.BuildConfig;
import com.omottec.demoapp.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinbingbing on 3/23/16.
 */
public class MyApplication extends Application {
    public static List<Activity> sLeakActivities = new ArrayList<>();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.d(Tag.APP_PROCESS, "MyApplication.attachBaseContext");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Tag.TASK, "MyApplication.onCreate");
        Log.d(Tag.APP_PROCESS, "MyApplication.onCreate");
        /*if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
        }*/
        HttpDnsManager.INSTANCE.init(this);
    }
}
