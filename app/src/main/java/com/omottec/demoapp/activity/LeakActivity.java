package com.omottec.demoapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;

import com.omottec.demoapp.R;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * Created by qinbingbing on 3/31/16.
 */
public class LeakActivity extends FragmentActivity {
    private byte[] largeObj = new byte[10 * 1024 * 1024];
    private StrongRefHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_screen_text);
//        new LeakThread().start();
//        new StaticThread().start();
//        new StaticStrongRefThread(this).start();
//        new StaticWeakRefThread(this).start();
        mHandler = new StrongRefHandler(this);
        mHandler.sendMessageDelayed(Message.obtain(), 10 * 60 * 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    class LeakThread extends Thread {
        @Override
        public void run() {
            try {
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class StaticThread extends Thread {
        @Override
        public void run() {
            try {
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class StaticStrongRefThread extends Thread {
        private Context context;

        StaticStrongRefThread(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            try {
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Do Something with Context
        }
    }

    static class StaticWeakRefThread extends Thread {
        WeakReference<Context> contextRef;
        StaticWeakRefThread(Context context) {
            contextRef = new WeakReference<Context>(context);
        }

        @Override
        public void run() {
            try {
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Context context = contextRef.get();
            if (context != null)
                ; // Do Something
        }
    }

    class StrongRefHandler extends Handler {
        Context context;

        public StrongRefHandler(Context context) {
            this.context = context;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // Do Something with Context
        }
    }

    static class StaticWeakRefHandler extends Handler {
        WeakReference<Context> contextRef;

        public StaticWeakRefHandler(Context context) {
            contextRef = new WeakReference<Context>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Context context = contextRef.get();
            if (context != null)
                ; // Do Something with Context
        }
    }
}
