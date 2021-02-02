package de.prog3.proj2021.ui;

/*
 * UI RecipeDetailActivity.
 * Gets passed the recipeId of the chosen recipe.
 * Instantiates a ViewModel to retrieve
 * recipe + ingredient data from repository.
 *
 *
 * File authors: Giuseppe Buccellato
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import de.prog3.proj2021.R;
import de.prog3.proj2021.viewmodels.MainActivityViewModel;

public class RecipeDetailActivity extends AppCompatActivity {

    private MainActivityViewModel mMainActivityViewModel;

    int currentRecipeId = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        //get recipeId from chosen recipe
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            currentRecipeId = extras.getInt("currentRecipeId");
        }

        //TODO: show details for chosen recipe


    }


}