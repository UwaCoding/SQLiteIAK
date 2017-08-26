package com.firman.kamussunda.model;

import java.util.Observable;

/**
 * Created by Firmanz on 8/31/2016.
 */
public class KamusObserver extends Observable {
    public static String NEED_TO_REFRESH = "refresh";

    public void refresh(){
        setChanged();
        notifyObservers(NEED_TO_REFRESH);
    }
}
