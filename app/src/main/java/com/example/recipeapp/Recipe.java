package com.example.recipeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Recipe {
    private int id;
    private String title;
    private String body;
    private String image;

    public Recipe() {

    }

    public Recipe(Context context, int id) {
        SQLiteHelper helper = new SQLiteHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM recipes WHERE id = ?", new String[]{String.valueOf(id)});
        cursor.moveToFirst();

        this.id = id;
        this.title = cursor.getString(1);
        this.body = cursor.getString(2);
        this.image = cursor.getString(3);
        cursor.close();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static ArrayList<Recipe> getAll(Context context) {
        ArrayList<Recipe> recipes = new ArrayList<>();

        SQLiteHelper helper = new SQLiteHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();

        Cursor cursor = database.query("recipes", new String[]{"id", "title", "body", "image"}, null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Recipe recipe = new Recipe();

            recipe.setId(cursor.getInt(0));
            recipe.setTitle(cursor.getString(1));
            recipe.setBody(cursor.getString(2));
            recipe.setImage(cursor.getString(3));

            recipes.add(recipe);
            cursor.moveToNext();
        }
        cursor.close();
        return recipes;
    }

    public void save(Context context) {
        SQLiteHelper helper = new SQLiteHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", this.title);
        values.put("body", this.body);
        values.put("image", this.image);

        database.insert("recipes", null, values);
    }

    public void update(Context context) {
        SQLiteHelper helper = new SQLiteHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", this.title);
        values.put("body", this.body);
        values.put("image", this.image);

        database.update("recipes", values, "WHERE id=?", new String[] {String.valueOf(this.id)});
    }

    public void delete(Context context) {
        SQLiteHelper helper = new SQLiteHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();

        database.delete("recipes", "WHERE id=?", new String[] {String.valueOf(this.id)});
    }
}
