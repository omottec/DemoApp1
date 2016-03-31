package com.omottec.demoapp.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.omottec.demoapp.R;

import java.util.concurrent.TimeUnit;

/**
 * Created by qinbingbing on 3/31/16.
 */
public class LeakActivity extends FragmentActivity {
    private byte[] largeObj = new byte[10 * 1024 * 1024];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_screen_text);
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setBackgroundColor(Color.GREEN);
        tv.setText("LeakActivity");
        new LeakClass().start();
    }

    class LeakClass extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
