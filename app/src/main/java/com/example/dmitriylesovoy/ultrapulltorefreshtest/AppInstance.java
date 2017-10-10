package com.example.dmitriylesovoy.ultrapulltorefreshtest;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by dimal on 10.10.2017.
 */

public class AppInstance extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
    }
}
