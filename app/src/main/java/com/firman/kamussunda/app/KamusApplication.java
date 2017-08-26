package com.firman.kamussunda.app;

import android.app.Application;

import com.firman.kamussunda.model.KamusObserver;

/**
 * Created by Firmanz on 8/31/2016.
 */
public class KamusApplication extends Application {
    KamusObserver kamusObserver;
    @Override
    public void onCreate(){
        super.onCreate();
        kamusObserver = new KamusObserver();
    }

    public KamusObserver getKamusObserver() {
        return kamusObserver;
    }
}
