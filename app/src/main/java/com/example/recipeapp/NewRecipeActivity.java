package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;

public class NewRecipeActivity extends AppCompatActivity {

    Button btnPic;
    ImageView imgvw;

    int SELECT_PICTURE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);

        EditText edtxtTitle = findViewById(R.id.edtxtTitle);
        EditText edtxtBody = findViewById(R.id.edtxtBody);

        Button btn = findViewById(R.id.button);
        btnPic = findViewById(R.id.btnPic);

        imgvw = findViewById(R.id.imgvw);


        btnPic.setOnClickListener(view -> {
            imageChooser();
        });

        btn.setOnClickListener(view -> {
            String title = edtxtTitle.getText().toString();
            String body = edtxtBody.getText().toString();
            Uri uri = Uri.parse(imgvw.getTag().toString());
            Bitmap bitmap = null;
            try {
                bitmap = useImage(uri);
                new ImageSaver(getApplicationContext()).
                        setFileName("img"+title).
                        setDirectoryName("images").
                        save(bitmap);
                String image = "images/img"+title;

                if(!title.isEmpty() && !body.isEmpty()) {

                    Recipe recipe = new Recipe();
                    recipe.setTitle(title);
                    recipe.setBody(body);
                    recipe.setImage(image);


                    recipe.save(NewRecipeActivity.this);

                    Intent intent = new Intent(NewRecipeActivity.this, MainActivity.class);
                    startActivity(intent);
                    NewRecipeActivity.this.finish();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //String image = bitmap.toString();



        });
    }

    public void imageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        startActivityForResult(Intent.createChooser(intent, "Select an image"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();

                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    imgvw.setImageURI(selectedImageUri);
                    imgvw.setTag(selectedImageUri.toString());

                }
            }
        }
    }


    Bitmap useImage(Uri uri) throws IOException {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
        //use the bitmap as you like
        //Drawable d = new BitmapDrawable(getResources(), bitmap);
        imgvw.setImageBitmap(bitmap);
        return bitmap;
    }




}