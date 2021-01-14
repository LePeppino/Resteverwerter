package de.prog3.proj2021.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import de.prog3.proj2021.R;
import de.prog3.proj2021.db.Recipe;

/*
* RecyclerViewAdapter holds all the ViewHolders filled
* with the Views to display the recipes on home screen.
*
* File author: Giuseppe Buccellato
* */

public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.ViewHolder>{

    private final Context mContext;
    private final List<Recipe> mRecipes;

    public RecipeRecyclerViewAdapter(Context mContext, List<Recipe> mRecipes) {
        this.mContext = mContext;
        this.mRecipes = mRecipes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recipe_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //set recipe title from resource
        holder.name.setText(mRecipes.get(position).name);

        //set recipe description from resource
        holder.description.setText(mRecipes.get(position).description);

        //set main image from resource with Glide
        Glide.with(mContext)
                .asBitmap()
                .load(mRecipes.get(position).getHeaderImageUrl())
                .error(R.mipmap.ic_launcher)        // on error display placeholder
                .placeholder(R.mipmap.ic_launcher)  // placeholder image
                .into(holder.image);

        //set onClickListener for recipe layout
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO: open up recipe details


            }
        });
    }

    // return number of items. if this returns 0, nothing will display
    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;
        TextView description;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.recipeImageId);
            name = itemView.findViewById(R.id.recipeTitleId);
            description = itemView.findViewById(R.id.recipeDescriptionId);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
}
