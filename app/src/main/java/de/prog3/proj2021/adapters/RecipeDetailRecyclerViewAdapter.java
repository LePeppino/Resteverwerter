package de.prog3.proj2021.adapters;

/*
 * RecyclerViewAdapter holds all the ViewHolders filled
 * with the Views to display the recipe details on RecipeDetailActivity.
 *
 * File author: Giuseppe Buccellato
 * */

import android.content.Context;
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

import java.util.List;

import de.prog3.proj2021.R;
import de.prog3.proj2021.db.RecipeWithIngredients;
import de.prog3.proj2021.models.Ingredient;

public class RecipeDetailRecyclerViewAdapter  extends RecyclerView.Adapter<RecipeDetailRecyclerViewAdapter.RecipeDetailHolder>{

    private final Context mContext;
    private List<RecipeWithIngredients> recipesWithIngredients;
    private RecipeWithIngredients currentRecipe = new RecipeWithIngredients();

    //constructor
    public RecipeDetailRecyclerViewAdapter(Context mContext){
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecipeDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_recipedetail_ingredient_listitem, parent, false);
        return new RecipeDetailHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeDetailHolder holder, int position) {
        Ingredient currentIngredient = currentRecipe.ingredients.get(position);

        //pass currentIngredient details to layout via ViewHolder
        holder.ingredientName.setText(currentIngredient.getName());
        holder.ingredientUnit.setText(currentIngredient.getUnit());

        //get typeImage by number from assets
        String typeImageUri = "file:///android_asset/typeImages/"
                + currentIngredient.getType() + ".webp";

        Glide.with(mContext)
                .asBitmap()
                .load(Uri.parse(typeImageUri))          // takes String from above
                .error(R.mipmap.ic_launcher)            // on error placeholder
                .placeholder(R.mipmap.ic_launcher)      // placeholder image
                .into(holder.typeImage);                // destination View

        //set onClickListener for ingredient list item
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get ID of currentIngredient and pass to new Activity
                //TODO: open new Activity with Ingredient Info
            }
        });
    }

    //return number of items. if this returns 0, nothing will display
    @Override
    public int getItemCount() {
        if(currentRecipe.ingredients == null){
            System.out.println("currentRecipe.ingredients does not exist.");
            return 0;
        }else{
            return currentRecipe.ingredients.size();
        }
    }

    //setter
    public void setRecipes(List<RecipeWithIngredients> recipeList, int chosenRecipeId){
        for(RecipeWithIngredients recipeWithIngredients : recipeList){
            if(recipeWithIngredients.recipe.getId() == chosenRecipeId){
                currentRecipe = recipeWithIngredients;
            }
        }
    }

    /*
    * ViewHolder class that holds all the views, duh
    * */
    public static class RecipeDetailHolder extends RecyclerView.ViewHolder{

        RelativeLayout parentLayout;
        private final ImageView typeImage;
        private final TextView ingredientName;
        private final TextView ingredientUnit;

        //constructor
        public RecipeDetailHolder(@NonNull View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parentLayout);
            typeImage = itemView.findViewById(R.id.ingredientImageId);
            ingredientName = itemView.findViewById(R.id.ingredientNameId);
            ingredientUnit = itemView.findViewById(R.id.ingredientUnitId);

        }
    }
}
