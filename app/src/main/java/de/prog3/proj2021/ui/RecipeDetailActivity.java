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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import de.prog3.proj2021.R;
import de.prog3.proj2021.adapters.RecipeDetailRecyclerViewAdapter;
import de.prog3.proj2021.adapters.RecipeRecyclerViewAdapter;
import de.prog3.proj2021.db.RecipeWithIngredients;
import de.prog3.proj2021.viewmodels.RecipeViewModel;

public class RecipeDetailActivity extends AppCompatActivity {

    private RecipeViewModel mRecipeViewModel;
    RecipeDetailRecyclerViewAdapter recipeDetailRecyclerViewAdapter;
    RecyclerView recipeDetailRecyclerView;

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

        //instantiate RecipeDetailRecyclerView and Adapter
        initRecyclerView();
        //instantiate ViewModel and Observer
        initViewModel();

    }

    /*
     * initialise RecipeRecyclerView with Adapter
     * */
    private void initRecyclerView(){
        recipeDetailRecyclerView = findViewById(R.id.recipeDetailIngredientsRecyclerView);
        recipeDetailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipeDetailRecyclerView.setHasFixedSize(true);

        recipeDetailRecyclerViewAdapter = new RecipeDetailRecyclerViewAdapter(this);
        recipeDetailRecyclerView.setAdapter(recipeDetailRecyclerViewAdapter);
    }

    /*
     * Observe ViewModel for changes, query recipes from
     * RecipeRepository and pass data to RecipeRecyclerViewAdapter
     */
    private void initViewModel(){
        mRecipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);

        mRecipeViewModel.getmRecipeWithIngredientsById(currentRecipeId).observe(this, recipeWithIngredients -> {
            //update currentRecipe list data in RecyclerView
            recipeDetailRecyclerViewAdapter.setCurrentRecipe(recipeWithIngredients);
            recipeDetailRecyclerViewAdapter.notifyDataSetChanged();
            Toast.makeText(RecipeDetailActivity.this, "observed onChanged RecyclerView", Toast.LENGTH_SHORT).show();
        });

    }

}