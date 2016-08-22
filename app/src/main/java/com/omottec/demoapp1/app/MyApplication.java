package com.omottec.demoapp1.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.util.Log;

import com.omottec.demoapp1.Tag;
import com.omottec.demoapp1.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinbingbing on 3/23/16.
 */
public class MyApplication extends Application {
    public static List<Activity> sLeakActivities = new ArrayList<>();
    private boolean mIsMainProcess;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.d(Tag.APP_PROCESS, this + " attachBaseContext");
        Log.d(Tag.APP_PROCESS, "mIsMainProcess before call:" + mIsMainProcess);
        mIsMainProcess = AppUtils.isMainProcess(this, Process.myPid());
        Log.d(Tag.APP_PROCESS, "mIsMainProcess after call:" + mIsMainProcess);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Tag.TASK, this + " onCreate");
        Log.d(Tag.APP_PROCESS, this + " onCreate");
        Log.d(Tag.APP_PROCESS, "mIsMainProcess:" + mIsMainProcess);
        /*if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
        }*/
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(Tag.MEMORY, this + " onLowMemory");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d(Tag.MEMORY, this + " onTrimMemory level:" + level);
    }
}
