package de.prog3.proj2021.adapters;

/*
 * RecyclerViewAdapter holds all the ViewHolders filled
 * with the Views to display the ingredients on FragmentPicker.
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

import java.util.ArrayList;
import java.util.List;

import de.prog3.proj2021.R;
import de.prog3.proj2021.db.ShoppingListWithIngredients;
import de.prog3.proj2021.models.Ingredient;
import de.prog3.proj2021.ui.IngredientDetailActivity;

public class ShoppingDetailRecyclerViewAdapter extends RecyclerView.Adapter<ShoppingDetailRecyclerViewAdapter.ShoppingDetailHolder> {

    private final Context mContext;
    private ShoppingListWithIngredients currentShoppingList;

    //constructor
    public ShoppingDetailRecyclerViewAdapter(Context mContext){this.mContext = mContext;}

    @NonNull
    @Override
    public ShoppingDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_shoppinglist_ingredient_listitem, parent, false);
        return new ShoppingDetailHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingDetailHolder holder, int position) {
        //set current ingredient
        Ingredient currentIngredient = currentShoppingList.ingredients.get(position);
        //convert ingredient param from int to String value
        String ingredientUnit = fromIntegerToUnitString(currentIngredient.getUnit());
        //concatenate numAvailable + ingredientUnit
        String amountToBuy = currentIngredient.getNumToBuy() + " " + ingredientUnit;

        //pass ingredient details to layout via ViewHolder
        holder.ingredientName.setText(currentIngredient.getName());
        holder.ingredientAmount.setText(amountToBuy);

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
        try {
            return currentShoppingList.ingredients.size();
        }catch (Exception e){
            System.out.println("Null object reference: ingredient list is null");
        }
        return 0;
    }

    //setter for current ShoppingList
    public void setMShoppingListWithIngredients(ShoppingListWithIngredients shoppingListWithIngredients){
        this.currentShoppingList = shoppingListWithIngredients;
    }

    /*
     * Ingredient Unit to String converter
     */
    private String fromIntegerToUnitString(int unitValue){
        String unitString;

        if(unitValue == 1){unitString = "g";}
        else if(unitValue == 2){unitString = "ml";}
        else if(unitValue == 3){unitString = "pcs";}
        else{unitString = "units";}

        return unitString;
    }

    public static class ShoppingDetailHolder extends RecyclerView.ViewHolder{

        RelativeLayout parentLayout;
        private final ImageView typeImage;
        private final TextView ingredientName;
        private final TextView ingredientAmount;
        private final ImageView checkBox;

        public ShoppingDetailHolder(@NonNull View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parentLayout);
            typeImage = itemView.findViewById(R.id.ingredientImageId);
            ingredientName = itemView.findViewById(R.id.ingredientNameId);
            ingredientAmount = itemView.findViewById(R.id.numToBuy);
            checkBox = itemView.findViewById(R.id.ingredientCheckBox);
        }
    }

}
