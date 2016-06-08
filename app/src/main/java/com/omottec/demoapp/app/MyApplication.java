package com.omottec.demoapp.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

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
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(Tag.MEMORY, "onLowMemory");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d(Tag.MEMORY, "onTrimMemory level:" + level);
    }
}
