package com.example.martinruiz.snapnotes.utils;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by MartinRuiz on 11/20/2017.
 */

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
