package com.omottec.demoapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.didi.passenger.onehttpdns.HttpDnsException;
import com.didi.passenger.onehttpdns.HttpDnsManager;
import com.didi.passenger.onehttpdns.security.SigChecker;
import com.omottec.demoapp.R;
import com.omottec.demoapp.service.DemoService;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by qinbingbing on 4/19/16.
 */
public class SimpleFragment extends Fragment {
    private static final String PUBLIC_KEY =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC2yF1O45xFOJ9vAziLMC4VRcne\n" +
                    "u4ht7vtI5sDffgEjXJE3pIjOkBdsYUothx9lrL3NF1iFmKeTCJ5UZETMF0Jmt4Uc\n" +
                    "A1KSeejx9eE0cDcNt8lQSmu7eMICrtkp0VhWiRPaAkPR8g0TAx+hcsCssLW83t5y\n" +
                    "AmCHXXfBaZ9SFKufdQIDAQAB";

    public static final String TAG = "SimpleFragment";
    private View mRootView;
    private TextView mTV;
    private Context mContext;
    private Timer mTimer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity().getApplicationContext();
        mTimer = new Timer();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.full_screen_text, null);
        mTV = (TextView) mRootView.findViewById(R.id.tv);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String ip = HttpDnsManager.INSTANCE.getIpByDomain("api.diditaxi.com");
                    Log.d(TAG, "ip:" + ip);
                    /*String httpDomains = "[{\"domain\":\"api.diditaxi.com\",\"host\":\"10.0.0.1|10.10.0.100\"},{\"domain\":\"dns.didichuxing.com\",\"host\":\"10.231.135.100|10.231.135.101\"},{\"domain\":\"gs.diditaxi.com\",\"host\":\"10.10.0.12|10.10.0.4|10.10.0.5|10.10.0.6\"},{\"domain\":\"pay.diditaxi.com\",\"host\":\"10.10.0.10|10.10.0.11|10.10.0.7|10.10.0.8|10.10.0.9\"}]";
                    String pushDomains = "[{\"domain\":\"push.didichuxing.com\",\"type\":\"online\",\"host\":\"10.9.0.1|10.9.0.2|10.9.1.1|10.9.1.10\"},{\"domain\":\"push.didichuxing.com\",\"type\":\"offline\",\"host\":\"10.9.0.3|10.9.0.4\"}]";
                    String sig = "aqFttTN5j8JXiG+dV3EvIaEWBUOxvx0tHOLLSfp98Nk/9wDnqi/nIa/NXSWjV9YcdNn1Fa3GJUsxloW1uC4+WReFXu3K8oR9/CMmLmitFA7jB/94K5uLdsxU4TGig1n6J1TqY68M2zmbNHv4sSVJjvjk+K2JdvBwdkwTDtrpka4=";
                    SigChecker sigChecker = new SigChecker();
                    String md5 = SigChecker.getMd5(httpDomains + pushDomains);
                    byte[] decryptBytes = sigChecker.decrypt(sigChecker.getPublicKey(), Base64.decode(sig, Base64.DEFAULT));
                    String decryptedMd5 = new String(decryptBytes);
                    Log.d(TAG, md5);
                    Log.d(TAG, md5);*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
