package com.omottec.demoapp.task;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.omottec.demoapp.R;
import com.omottec.demoapp.Tag;

/**
 * Created by qinbingbing on 3/23/16.
 */
public class MainActivity extends FragmentActivity {
    private static int count;
    private static final int ID = count++;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_screen_text);
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setBackgroundColor(Color.GREEN);
        tv.setText("MainActivity" + ID);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWelcomeActivity();
            }
        });
        Log.d(Tag.TASK, "taskId:" + getTaskId() + "|" + this + "|onCreate|" + ID);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Tag.TASK, "taskId:" + getTaskId() + "|" + this + "|onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Tag.TASK, "taskId:" + getTaskId() + "|" + this + "|onResume");
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
        Log.d(Tag.TASK, "taskId:" + getTaskId() + "|" + this + "|onNewIntent");
    }

    private void startWelcomeActivity() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}
