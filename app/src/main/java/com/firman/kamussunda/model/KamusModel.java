package com.firman.kamussunda.model;

import java.io.Serializable;

/**
 * Created by Firmanz on 8/31/2016.
 */
public class KamusModel implements Serializable {
    int id;
    String kata, arti;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKata() {
        return kata;
    }

    public void setKata(String kata) {
        this.kata = kata;
    }

    public String getArti() {
        return arti;
    }

    public void setArti(String arti) {
        this.arti = arti;
    }


    public static KamusModel getKamusModel(int id, String kata, String arti){
        KamusModel kamusModel = new KamusModel();
        kamusModel.setArti(arti);
        kamusModel.setKata(kata);
        kamusModel.setId(id);

        return kamusModel;
    }
}
