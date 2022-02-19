package com.example.recipeapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;


public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "recipes_db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null,  DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE recipes (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, body TEXT NOT NULL, image TEXT NOT NULL)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
