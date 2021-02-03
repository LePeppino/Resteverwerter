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

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.prog3.proj2021.R;
import de.prog3.proj2021.adapters.RecipeDetailRecyclerViewAdapter;
import de.prog3.proj2021.db.RecipeWithIngredients;
import de.prog3.proj2021.viewmodels.RecipeViewModel;

public class RecipeDetailActivity extends AppCompatActivity {

    RecipeDetailRecyclerViewAdapter recipeDetailRecyclerViewAdapter;
    RecyclerView recipeDetailRecyclerView;
    RecipeWithIngredients currentRecipe = new RecipeWithIngredients();

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
        //assign Views
        initViews();

    }

    private void initViews(){
        final ImageView headerImage;
        final TextView recipeTitle;
        final TextView description;
        final TextView instructions;

        headerImage = findViewById(R.id.detailHeaderImage);
        recipeTitle = findViewById(R.id.recipeDetailTitleText);
        description = findViewById(R.id.recipeDetailDescriptionText);
        instructions = findViewById(R.id.recipeDetailInstructions);

        //pass chosen recipe details to layout
        recipeTitle.setText(currentRecipe.recipe.getName());
        description.setText(currentRecipe.recipe.getDescription());
        instructions.setText(currentRecipe.recipe.getInstructions());

        //set header image uri from resource with Glide
        String headerUri = "file:///android_asset/" + currentRecipe.recipe.getHeaderImageUrl();
        Glide.with(this)
                .asBitmap()
                .load(Uri.parse(headerUri))             // takes String from above
                .error(R.mipmap.ic_launcher)            // on error placeholder
                .placeholder(R.mipmap.ic_launcher)      // placeholder image
                .into(headerImage);                     // destination View
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
     * RecipeRepository and pass data
     * to RecipeRecyclerViewAdapter and Activity
     */
    private void initViewModel(){
        RecipeViewModel mRecipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);

        List<RecipeWithIngredients> recipeList = mRecipeViewModel.getmRecipesWithIngredients();

        setRecipes(recipeList, currentRecipeId);
        recipeDetailRecyclerViewAdapter.setRecipes(recipeList, currentRecipeId);
    }

    //set currentRecipe for this Activity
    private void setRecipes(List<RecipeWithIngredients> recipeList, int currentRecipeId){
        for(RecipeWithIngredients recipe : recipeList){
            if(recipe.recipe.getId() == currentRecipeId){
                currentRecipe = recipe;
            }
        }
    }

}