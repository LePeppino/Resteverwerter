package de.prog3.proj2021.ui;

/*
 * UI ShoppingListDetailActivity.
 * Gets passed the shoppingListId of the chosen ShoppingList.
 * Instantiates a ViewModel to retrieve
 * ShoppingList data from repository.
 *
 *
 * File authors: Giuseppe Buccellato
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import de.prog3.proj2021.R;

public class ShoppingListDetailActivity extends AppCompatActivity {

    int currentShoppingList = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_detail);

        //get passed shoppingListId from chosen ShoppingList
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            currentShoppingList = extras.getInt("currentShoppingListId");
        }
    }
}