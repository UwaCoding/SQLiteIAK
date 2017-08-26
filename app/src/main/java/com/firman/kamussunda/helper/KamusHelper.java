package com.firman.kamussunda.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.firman.kamussunda.db.DatabaseHelper;
import com.firman.kamussunda.model.KamusModel;

import java.util.ArrayList;

/**
 * Created by Firmanz on 8/31/2016.
 */
public class KamusHelper {

    private static String DATABASE_TABLE = DatabaseHelper.TABLE_NAME;

    private Context context;

    private DatabaseHelper databaseHelper;

    private SQLiteDatabase database;

    public KamusHelper(Context context){
        this.context = context;
    }

    public KamusHelper open() throws SQLException{
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        databaseHelper.close();
    }

    public Cursor searchQuery(String kata){

        return database.rawQuery
                ("SELECT * FROM "+DATABASE_TABLE+" WHERE kata LIKE '%"+kata+"%'", null);
    }


    public ArrayList<KamusModel> getSearchResult(String keyword){
        ArrayList<KamusModel> arrayList = new ArrayList<KamusModel>();
        Cursor cursor = searchQuery(keyword);
        cursor.moveToFirst();
        KamusModel kamusModel;
        if (cursor.getCount()>0) {
            do {

                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(0));
                kamusModel.setKata(cursor.getString(1));
                kamusModel.setArti(cursor.getString(2));

                arrayList.add(kamusModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public String getData(String kata){
        String result = "";
        Cursor cursor = searchQuery(kata);
        cursor.moveToFirst();
        //KamusModel kamusModel;
        if (cursor.getCount()>0) {
            result = cursor.getString(2);
            for (; !cursor.isAfterLast(); cursor.moveToNext()) {
                result = cursor.getString(2);
            }
        }
        cursor.close();
        return result;
    }

    public Cursor queryAllData(){
        return database.rawQuery
                ("SELECT * FROM "+DATABASE_TABLE+" ORDER BY kata ASC", null);
    }

    public ArrayList<KamusModel> getAllData(){
        ArrayList<KamusModel> arrayList = new ArrayList<KamusModel>();
        Cursor cursor = queryAllData();
        //startManagingCursor(cursor);
        cursor.moveToFirst();
        KamusModel kamusModel;
        if (cursor.getCount()>0) {
            do {

                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(0));
                kamusModel.setKata(cursor.getString(1));
                kamusModel.setArti(cursor.getString(2));

                arrayList.add(kamusModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(KamusModel kamus){
        ContentValues initialValues =  new ContentValues();
        initialValues.put(DatabaseHelper.FIELD_KATA, kamus.getKata());
        initialValues.put(DatabaseHelper.FIELD_ARTI, kamus.getArti());
        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    public void update(KamusModel kamus){
        ContentValues args = new ContentValues();
        args.put(DatabaseHelper.FIELD_ARTI, kamus.getArti());
        args.put(DatabaseHelper.FIELD_KATA, kamus.getKata());
        database.update(DATABASE_TABLE, args,
                DatabaseHelper.FIELD_ID + "=" + kamus.getId(), null);
    }

    public void delete(int id){
        database.delete(DatabaseHelper.TABLE_NAME,
                DatabaseHelper.FIELD_ID + " = '"+id+"'", null);
    }
}
