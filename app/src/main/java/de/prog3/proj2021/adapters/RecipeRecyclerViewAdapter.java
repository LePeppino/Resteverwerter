package de.prog3.proj2021.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.prog3.proj2021.R;
import de.prog3.proj2021.models.Recipe;

/*
* RecyclerViewAdapter holds all the ViewHolders filled
* with the Views to display the recipes on home screen.
*
* File author: Giuseppe Buccellato
* */

public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.RecipeHolder>{

    private final Context mContext;
    private List<Recipe> mRecipes = new ArrayList<>();

    public RecipeRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_recipe_listitem, parent, false);
        return new RecipeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeHolder holder, int position) {
        Recipe currentRecipe = mRecipes.get(position);
        //set recipe info from resource
        holder.name.setText(currentRecipe.getName());
        holder.description.setText(currentRecipe.getDescription());

        //set main image from resource with Glide
        Glide.with(mContext)
                .asBitmap()
                .load(currentRecipe.getHeaderImageUrl())    // gets headerImageUrl as String
                .error(R.mipmap.ic_launcher)                // on error display placeholder
                .placeholder(R.mipmap.ic_launcher)          // placeholder image
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

    public void setmRecipes(List<Recipe> mRecipes){
        this.mRecipes = mRecipes;
    }

    public static class RecipeHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView name;
        private TextView description;
        RelativeLayout parentLayout;

        public RecipeHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.recipeImageId);
            name = itemView.findViewById(R.id.recipeTitleId);
            description = itemView.findViewById(R.id.recipeDescriptionId);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
}
