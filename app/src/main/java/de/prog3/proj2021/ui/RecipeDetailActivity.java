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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import de.prog3.proj2021.R;
import de.prog3.proj2021.adapters.RecipeDetailRecyclerViewAdapter;
import de.prog3.proj2021.db.FavouritesWithRecipes;
import de.prog3.proj2021.db.RecipeWithIngredients;
import de.prog3.proj2021.viewmodels.FavouritesViewModel;
import de.prog3.proj2021.viewmodels.RecipeViewModel;

public class RecipeDetailActivity extends AppCompatActivity {

    //ViewModels
    RecipeViewModel mRecipeViewModel;
    FavouritesViewModel mFavouritesViewModel;

    //Ingredient RecyclerView
    RecipeDetailRecyclerViewAdapter recipeDetailRecyclerViewAdapter;
    RecyclerView recipeDetailRecyclerView;

    //Recipe data
    RecipeWithIngredients currentRecipe = new RecipeWithIngredients();
    int currentRecipeId = 1;

    //Recipe Info Views
    ImageView headerImage;
    TextView recipeTitle;
    TextView description;
    TextView instructions;

    //Buttons and stuff
    ImageView toggleFavouriteButton;


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
        //instantiate ViewModel
        initRecipeViewModel();
        initFavouritesViewModel();
        //assign Views
        initViews();

    }

    private void initViews(){
        headerImage = findViewById(R.id.detailHeaderImage);
        recipeTitle = findViewById(R.id.recipeDetailTitleText);
        description = findViewById(R.id.recipeDetailDescriptionText);
        instructions = findViewById(R.id.recipeDetailInstructions);
        toggleFavouriteButton = findViewById(R.id.toggleFavouriteButtonId);
        checkIsFavourite(currentRecipe);

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
     * Initiate RecipeViewModel, query recipes from
     * RecipeRepository and pass data
     * to RecipeRecyclerViewAdapter and Activity
     */
    private void initRecipeViewModel(){
        mRecipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);

        List<RecipeWithIngredients> recipeList = mRecipeViewModel.getMRecipesWithIngredients();

        setRecipes(recipeList, currentRecipeId);
        recipeDetailRecyclerViewAdapter.setRecipes(recipeList, currentRecipeId);
    }

    private void initFavouritesViewModel(){
        mFavouritesViewModel = new ViewModelProvider(this).get(FavouritesViewModel.class);

        List<FavouritesWithRecipes> favouriteList = mFavouritesViewModel.getMFavouriteRecipes();
    }

    //set currentRecipe for this Activity
    private void setRecipes(List<RecipeWithIngredients> recipeList, int currentRecipeId){
        for(RecipeWithIngredients recipe : recipeList){
            if(recipe.recipe.getId() == currentRecipeId){
                currentRecipe = recipe;
            }
        }
    }

    //initial check if recipe is among favourites
    private void checkIsFavourite(RecipeWithIngredients currentRecipe){
            if(currentRecipe.recipe.isFavourite() != 1){
                toggleFavouriteButton.setImageResource(R.drawable.heart_empty);
            }else{
                toggleFavouriteButton.setImageResource(R.drawable.heart_full);
            }
    }

    //toggleFavouriteButton onClick
    //0 = false, 1 = true
    public void toggleFavourite(View view) {
        //do when clicked and not yet favourite
        if(currentRecipe.recipe.isFavourite() != 1){
            currentRecipe.recipe.setFavourite(1);
            toggleFavouriteButton.setImageResource(R.drawable.heart_full);
            Toast.makeText(this, "Added to favourite list!", Toast.LENGTH_SHORT).show();
        }else{
            currentRecipe.recipe.setFavourite(0);
            toggleFavouriteButton.setImageResource(R.drawable.heart_empty);
            Toast.makeText(this, "Removed from favourite list.", Toast.LENGTH_SHORT).show();
        }
        //update recipe and favouriteList database entry
        mRecipeViewModel.update(currentRecipe.recipe);
        updateFavouriteList(currentRecipe);
    }

    private void updateFavouriteList(RecipeWithIngredients recipe){
        //TODO: call mFavouritesViewModel to add favourite + crossRef to db
        // and increment numOfFavourites

    }
}