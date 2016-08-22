package com.omottec.demoapp1.activity;

import android.support.v4.app.Fragment;

import com.omottec.demoapp1.fragment.MultiProcessFragment;

/**
 * Created by qinbingbing on 3/31/16.
 */
public class DemoActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new MultiProcessFragment();
    }
}
