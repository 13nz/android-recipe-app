package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        TextView txtTitle = findViewById(R.id.txtTitle);
        TextView txtBody = findViewById(R.id.txtBody);

        ImageView img = findViewById(R.id.imageView);

        int id = getIntent().getExtras().getInt("id", 0);
        Recipe recipe =  new Recipe(RecipeActivity.this, id);

        txtTitle.setText(recipe.getTitle());
        txtBody.setText(recipe.getBody());

        Bitmap bitmap = new ImageSaver(RecipeActivity.this).
                setFileName("img"+recipe.getTitle()).
                setDirectoryName("images").
                load();

        img.setImageBitmap(bitmap);

    }
}