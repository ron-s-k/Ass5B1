package com.example.ass5b1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class myDatabase extends SQLiteOpenHelper {

    private static final String name_DB = "myDatabase";
    private static final int version = 1;

    private static final String name_Table = "login";
    private static final String name_Col1 = "user";
    private static final String name_Col2 = "password";

    public myDatabase (Context context) {
        super(context, name_DB, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + name_Table + "(" + name_Col1 + " TEXT , " + name_Col2 + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String user = "ronit";
        String password = "12345";
        Log.d("ronit","insertData");
        ContentValues cv = new ContentValues();
        cv.put(name_Col1,user);
        cv.put(name_Col2,password);
        db.insert(name_Table,null,cv);
    }
    public Cursor validateData(String user,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("ronit","validateData");
        Log.d("ronit","user: "+user+" password: "+password);

        return db.rawQuery("SELECT * FROM "+name_Table+" WHERE "+name_Col1+"='"+user+"' AND "+name_Col2+"='"+password+"'",null);
    }
}
