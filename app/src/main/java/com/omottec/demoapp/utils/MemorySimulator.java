package com.omottec.demoapp.utils;

import java.util.Arrays;
import java.util.concurrent.Executors;

/**
 * Created by qinbingbing on 4/5/16.
 * adb shell am kill [--user <USER_ID> | all | current] <PACKAGE>
 * adb shell pm list packages
 */
public final class MemorySimulator {
    private MemorySimulator() {
    }

    public static void asyncDrainMemory() {
        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {
                drainMemory();
            }
        });
    }

    public static void drainMemory() {
        byte[] bytes = new byte[1 * 1024 * 1024];
        System.out.println("alloc memory " + bytes.length / 1024 / 1024 + "M");
        for (int i = 0; i <= bytes.length; i++) {
            if (i == bytes.length) {
                bytes = Arrays.copyOf(bytes, bytes.length * 2);
                System.out.println("alloc memory " + bytes.length / 1024 / 1024 + "M");
            }
            bytes[i] = 1;
        }
    }
}

