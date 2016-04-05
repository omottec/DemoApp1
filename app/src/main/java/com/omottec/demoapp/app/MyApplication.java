package com.omottec.demoapp.app;

import android.app.Activity;
import android.app.Application;
import android.os.StrictMode;
import android.util.Log;

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
    public void onCreate() {
        super.onCreate();
        Log.d(Tag.TASK, "MyApplication.onCreate");
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
        }
    }
}
