package com.omottec.demoapp.task;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.omottec.demoapp.R;
import com.omottec.demoapp.Tag;

/**
 * Created by qinbingbing on 3/23/16.
 */
public class WelcomeActivity extends FragmentActivity {
    private static int count;
    private final int ID = count++;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_screen_text);
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setBackgroundColor(Color.RED);
        tv.setText("WelcomeActivity" + ID);
        Log.d(Tag.TASK, "taskId:" + getTaskId() + "|" + this + "|onCreate|" + ID);
        mHandler = new Handler();
        /*mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        }, 200);*/
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainActivity();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Tag.TASK, "taskId:" + getTaskId() + "|" + this + "|onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Tag.TASK, "taskId:" + getTaskId() + "|" + this + "|onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Tag.TASK, "taskId:" + getTaskId() + "|" + this + "|onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Tag.TASK, "taskId:" + getTaskId() + "|" + this + "|onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Tag.TASK, "taskId:" + getTaskId() + "|" + this + "|onDestroy");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(Tag.TASK, "taskId:" + getTaskId() + "|" + this + "onNewIntent");
    }

    private void startMainActivity() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
