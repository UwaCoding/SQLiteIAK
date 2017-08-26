package com.firman.kamussunda.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Firmanz on 8/31/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    public static String DATABASE_NAME = "dbkamussunda";
    public static String TABLE_NAME = "kamus";
    public static String FIELD_KATA = "kata";
    public static String FIELD_ARTI = "arti";
    public static String FIELD_ID = "_id";

    private static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_KAMUS =
            "create table "+TABLE_NAME+" " +
                    "("+FIELD_ID+" integer primary key autoincrement, " +
            FIELD_KATA+" text not null, " +
            FIELD_ARTI+" text not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_KAMUS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

}
