package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SQLiteHelper helper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new SQLiteHelper(MainActivity.this);
        String dataBase_name = helper.getDatabaseName();
        database = helper.getWritableDatabase();

        Button btnNew = findViewById(R.id.btnNew);

        ArrayList<Recipe> recipes = Recipe.getAll(MainActivity.this);
        RecyclerView recycler = findViewById(R.id.recycler);
        Log.d("SQLiteDB", "Size: "+recipes.size());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

        RecipeAdapter adapter = new RecipeAdapter(MainActivity.this, recipes);
        recycler.setAdapter(adapter);

        btnNew.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewRecipeActivity.class);
            startActivity(intent);
        });

    }
}