package com.omottec.demoapp.fragment;

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

import com.omottec.demoapp.R;

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
        ActivityManager am = (ActivityManager) mActivity.getSystemService(Context.ACTIVITY_SERVICE);
        int memoryClass = am.getMemoryClass();
        long maxMemory = Runtime.getRuntime().maxMemory();
        StringBuilder sb = new StringBuilder();
        sb.append("memoryClass: ").append(memoryClass)
                .append("\n").append("maxMemory: ").append(maxMemory);
        mTV.setText(sb.toString());
    }
}
