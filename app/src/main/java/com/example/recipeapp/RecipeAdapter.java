package com.example.recipeapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<Recipe> recipes;

    public  RecipeAdapter(Context context, ArrayList<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.recipe_item, parent, false);
        MyViewHolder item = new MyViewHolder(row);

        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).txtvwTitle.setText(recipes.get(position).getTitle());
        ((MyViewHolder)holder).txtvwBody.setText(recipes.get(position).getBody());

        Bitmap bitmap = new ImageSaver(context).
                setFileName("img"+recipes.get(position).getTitle()).
                setDirectoryName("images").
                load();

        ((MyViewHolder)holder).photo.setImageBitmap(bitmap);

        holder.itemView.setOnClickListener(view -> {
            Recipe recipe = recipes.get(position);
            int id = recipe.getId();

            Intent intent = new Intent(view.getContext(), RecipeActivity.class);
            intent.putExtra("id", id);
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return this.recipes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtvwTitle;
        TextView txtvwBody;
        ImageView photo;

        public MyViewHolder(View view) {
            super(view);
            txtvwTitle = view.findViewById(R.id.txtvwTitle);
            txtvwBody = view.findViewById(R.id.txtvwBody);
            photo = view.findViewById(R.id.photo);
        }
    }
}
