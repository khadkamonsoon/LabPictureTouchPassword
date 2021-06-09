package com.example.lab212security.AdminSubActivity.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME= "password.db";
    public static final String TABLE_NAME= "password_table.db";
    public static final String COL_1= "password";
    public static final String COL_2= "Image";

    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null,1);
        SQLiteDatabase db = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(password Integer primary key, BackgroundImage Integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table if exists contacts");
        onCreate(db);
    }

    public void insert(){

    }
}
