package de.prog3.proj2021.adapters;

/**
 * RecyclerViewAdapter holds all the ViewHolders filled
 * with the Views to display the recipes on favourite screen.
 *
 * @author Giuseppe Buccellato, Eric Walter
 * */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import de.prog3.proj2021.db.FavouritesWithRecipes;
import de.prog3.proj2021.models.Recipe;
import de.prog3.proj2021.ui.RecipeDetailActivity;

public class FavouriteRecyclerViewAdapter extends RecyclerView.Adapter<FavouriteRecyclerViewAdapter.RecipeHolder>{

    private final Context mContext;
    private List<Recipe> mRecipes = new ArrayList<>();

    /**
     * constructor
     */
    public FavouriteRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * onCreateViewHolder
     * @return passes a newly created Holder
     */
    @NonNull
    @Override
    public RecipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_recipe_listitem, parent, false);
        return new FavouriteRecyclerViewAdapter.RecipeHolder(itemView);
    }

    /**
     *Binds the data to the view
     */
    @Override
    public void onBindViewHolder(@NonNull RecipeHolder holder, int position) {
        Recipe currentRecipe = mRecipes.get(position);
        //set recipe info from resource
        holder.name.setText(currentRecipe.getName());
        holder.description.setText(currentRecipe.getDescription());

        //For conversion to webp format: https://medium.com/android-core/usage-of-image-resource-files-in-android-studio-263305c8f4db
        //For usage of images from assets: https://stackoverflow.com/questions/29982341/using-glide-for-android-how-do-i-load-images-from-asset-and-resources
        String pathUri = "file:///android_asset/" + currentRecipe.getHeaderImageUrl();

        //set main image from resource with Glide
        Glide.with(mContext)
                .asBitmap()
                .load(Uri.parse(pathUri))               // gets headerImageUrl as String
                .error(R.mipmap.ic_launcher)            // on error display placeholder
                .placeholder(R.mipmap.ic_launcher)      // placeholder image
                .into(holder.image);

        //set onClickListener for recipe list item
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get ID of currentRecipe and pass to new Activity
                int currentRecipeId = currentRecipe.getId();

                Intent intent = new Intent(mContext, RecipeDetailActivity.class);
                intent.putExtra("currentRecipeId", currentRecipeId);
                mContext.startActivity(intent);
            }
        });
    }

    /**
     * return number of items. if this returns 0, nothing will display
     * @return number of items
     */
    @Override
    public int getItemCount() {
        return mRecipes.size();
    }


    /**
     * sets the recipes for default display
     * @param favouriteRecipes
     */
    public void setMFavourites(FavouritesWithRecipes favouriteRecipes){
        this.mRecipes = favouriteRecipes.recipes;
    }

    /**
     * sets recipes by query from search bar in FragmentFavorites
     * @param recipeList
     */
    public void setMFavouritesByQuery(List<Recipe> recipeList){
        this.mRecipes = recipeList;
    }

    /**
     * ViewHolder class that holds all the views
     * */
    public static class RecipeHolder extends RecyclerView.ViewHolder{
        private final ImageView image;
        private final TextView name;
        private final TextView description;
        RelativeLayout parentLayout;

        //constructor
        public RecipeHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.recipeImageId);
            name = itemView.findViewById(R.id.recipeTitleId);
            description = itemView.findViewById(R.id.recipeDescriptionId);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
}
