package de.prog3.proj2021.adapters;

/*
 * RecyclerViewAdapter holds all the ViewHolders filled
 * with the Views to display the associated ShoppingList details on FragmentShoppingList.
 *
 * File author: Giuseppe Buccellato
 * */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.prog3.proj2021.R;
import de.prog3.proj2021.db.AppDatabase;
import de.prog3.proj2021.db.ShoppingListWithIngredients;
import de.prog3.proj2021.ui.ShoppingListDetailActivity;


public class ShoppingFragmentRecyclerViewAdapter extends RecyclerView.Adapter<ShoppingFragmentRecyclerViewAdapter.ShoppingFragmentHolder> {

    private final Context mContext;
    private List<ShoppingListWithIngredients> mShoppingLists = new ArrayList<>();
    private ShoppingListWithIngredients currentShoppingList;

    //constructor
    public ShoppingFragmentRecyclerViewAdapter(Context mContext){
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ShoppingFragmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_shopping_listitem, parent, false);
        return new ShoppingFragmentHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingFragmentHolder holder, int position) {
        currentShoppingList = mShoppingLists.get(position);

        //calculate progress for current ShoppingList and set as String to TextView
        int numOfItems = currentShoppingList.ingredients.size();
        int numOfCheckedItems = numOfItems - currentShoppingList.shoppingList.getNumUncheckedItems();
        String shoppingProgress = numOfCheckedItems + " / " + numOfItems;

        //set info
        holder.name.setText(currentShoppingList.shoppingList.getName());
        holder.progress.setText(shoppingProgress);

        //set onClickListener for shopping list item
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get ID of currentShoppingList and pass to new Activity
                int shoppingListId = currentShoppingList.shoppingList.getId();

                Intent intent = new Intent(mContext, ShoppingListDetailActivity.class);
                intent.putExtra("currentShoppingListId", shoppingListId);
                mContext.startActivity(intent);
            }
        });

        //set OnClickListener for deleteButton
        holder.deleteListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteListClick(currentShoppingList, position);
            }
        });
    }

    // return number of ShoppingLists. if this returns 0, nothing will display
    @Override
    public int getItemCount() {
        return mShoppingLists.size();
    }

    /*
    * setter
    * */
    public void setShoppingListWithIngredients(List<ShoppingListWithIngredients> shoppingListsWithIngredients){
        this.mShoppingLists = shoppingListsWithIngredients;
    }

    /*
     * show prompt and in case of yes, delete ShoppingList on current position
     * */
    private void deleteListClick(ShoppingListWithIngredients currentShoppingList, int position){
        // create alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        //set dialog message and title
        builder.setMessage("Do you really want to delete this shopping list?")
                .setTitle("Confirm deletion");
        //set dialog positive option
        builder.setPositiveButton("Yes, please", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteShoppingList(currentShoppingList, position);
                Toast.makeText(mContext, "Shopping list deleted.", Toast.LENGTH_SHORT).show();
            }
        });
        //set dialog negative option as null to dismiss
        builder.setNegativeButton("No, wait!", null);
        builder.setIcon(R.drawable.ic_baseline_announcement_24);
        //create and show configured dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /*
     * deletes chosen ShoppingList from UI and from database
     * including ShoppingListIngredientCrossRefs
     * */
    private void deleteShoppingList(ShoppingListWithIngredients currentShoppingList, int position){
        //update database
        final AppDatabase db = AppDatabase.getInstance(mContext);
        db.shoppingListDao().deleteShoppingList(currentShoppingList.shoppingList);
        //remove from UI list
        mShoppingLists.remove(currentShoppingList);
        //notify adapter about removal
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    public static class ShoppingFragmentHolder extends RecyclerView.ViewHolder{

        private final TextView name;
        private final TextView progress;
        RelativeLayout parentLayout;
        ImageView deleteListButton;

        //constructor
        public ShoppingFragmentHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.shoppingListOverviewName);
            progress = itemView.findViewById(R.id.shoppingListOverviewProgressText);
            parentLayout = itemView.findViewById(R.id.parentLayout);
            deleteListButton = itemView.findViewById(R.id.deleteListIcon);
        }
    }
}
