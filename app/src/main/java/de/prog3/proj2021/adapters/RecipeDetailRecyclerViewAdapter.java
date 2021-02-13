package de.prog3.proj2021.adapters;

/*
 * RecyclerViewAdapter holds all the ViewHolders filled
 * with the Views to display the recipe details on RecipeDetailActivity.
 *
 * File author: Giuseppe Buccellato
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

import java.util.List;

import de.prog3.proj2021.R;
import de.prog3.proj2021.converters.DataConverter;
import de.prog3.proj2021.db.RecipeWithIngredients;
import de.prog3.proj2021.models.Ingredient;
import de.prog3.proj2021.ui.IngredientDetailActivity;
import de.prog3.proj2021.ui.RecipeDetailActivity;

public class RecipeDetailRecyclerViewAdapter  extends RecyclerView.Adapter<RecipeDetailRecyclerViewAdapter.RecipeDetailHolder>{

    private final Context mContext;
    private RecipeWithIngredients currentRecipe;

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
        //set current ingredient
        Ingredient currentIngredient = currentRecipe.ingredients.get(position);
        //convert ingredient unit from int to actual value
        String ingredientUnit = fromIntegerToUnit(currentIngredient.unit);
        String ingredientAmount = "" + currentIngredient.getNumRequired();

        //pass currentIngredient details to layout via ViewHolder
        holder.ingredientName.setText(currentIngredient.getName());
        holder.ingredientAmount.setText(ingredientAmount);
        holder.ingredientUnit.setText(ingredientUnit);

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
                int currentIngredientId = currentIngredient.getId();

                Intent intent = new Intent(mContext, IngredientDetailActivity.class);
                intent.putExtra("currentIngredientId", currentIngredientId);
                mContext.startActivity(intent);
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
    public void setRecipes(RecipeWithIngredients recipeWithIngredients){
        this.currentRecipe = new RecipeWithIngredients(recipeWithIngredients);
    }

    //Ingredient Unit to String converter
    public String fromIntegerToUnit(int unitValue){
        String unitString;

        if(unitValue == 1){unitString = "g";}
        else if(unitValue == 2){unitString = "ml";}
        else if(unitValue == 3){unitString = "pcs";}
        else{unitString = "units";}

        return unitString;
    }

    /*
    * ViewHolder class that holds all the views, duh
    * */
    public static class RecipeDetailHolder extends RecyclerView.ViewHolder{

        RelativeLayout parentLayout;
        private final ImageView typeImage;
        private final TextView ingredientName;
        private final TextView ingredientAmount;
        private final TextView ingredientUnit;

        //constructor
        public RecipeDetailHolder(@NonNull View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parentLayout);
            typeImage = itemView.findViewById(R.id.ingredientImageId);
            ingredientName = itemView.findViewById(R.id.ingredientNameId);
            ingredientAmount = itemView.findViewById(R.id.ingredientAmountId);
            ingredientUnit = itemView.findViewById(R.id.ingredientUnitId);

        }
    }
}
