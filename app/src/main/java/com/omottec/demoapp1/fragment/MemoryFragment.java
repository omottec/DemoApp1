package com.omottec.demoapp1.fragment;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.omottec.demoapp1.R;
import com.omottec.demoapp1.utils.MemorySimulator;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by qinbingbing on 3/31/16.
 */
public class MemoryFragment extends Fragment {
    public static final String TAG = "MemoryFragment";
    private View mRootView;
    private TextView mTV;
    private Activity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.full_screen_text, null);
        mTV = (TextView) mRootView.findViewById(R.id.tv);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateMemory();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateMemory();
                    }
                });
            }
        }, 1000, 1000);
        MemorySimulator.asyncDrainMemorySlow(2000);
    }

    private void updateMemory() {
        ActivityManager am = (ActivityManager) mActivity.getSystemService(Context.ACTIVITY_SERVICE);
        int memoryClass = am.getMemoryClass();
        int largeMemoryClass = am.getLargeMemoryClass();
        long maxMemory = Runtime.getRuntime().maxMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();

        StringBuilder sb = new StringBuilder();
        sb.append("am.getMemoryClass(): ").append(memoryClass)
                .append("\nam.getLargeMemoryClass(): ").append(largeMemoryClass)
                .append("\nRuntime.getRuntime().maxMemory(): ").append(maxMemory)
                .append("\nRuntime.getRuntime().totalMemory(): ").append(totalMemory)
                .append("\nRuntime.getRuntime().freeMemory(): ").append(freeMemory);
        mTV.setText(sb.toString());
    }
}
