package com.omottec.demoapp.app;

import android.app.Application;
import android.util.Log;

import com.omottec.demoapp.Tag;

/**
 * Created by qinbingbing on 3/23/16.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Tag.TASK, "MyApplication.onCreate");
    }
}
