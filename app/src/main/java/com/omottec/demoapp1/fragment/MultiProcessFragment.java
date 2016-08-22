package com.omottec.demoapp1.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.omottec.demoapp1.R;
import com.omottec.demoapp1.service.DemoService;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by qinbingbing on 4/19/16.
 */
public class MultiProcessFragment extends Fragment {
    private View mRootView;
    private TextView mTV;
    private Context mContext;
    private Timer mTimer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity().getApplicationContext();
        mTimer = new Timer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.full_screen_text, null);
        mTV = (TextView) mRootView.findViewById(R.id.tv);
        mTV.setText("MultiProcessFragment");
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        startService();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                stopService();
                SystemClock.sleep(3000);
                startService();
            }
        }, 3000);
    }

    private void startService() {
        Intent intent = new Intent(mContext, DemoService.class);
        mContext.startService(intent);
    }

    private void stopService() {
        Intent intent = new Intent(mContext, DemoService.class);
        mContext.stopService(intent);
    }
}
