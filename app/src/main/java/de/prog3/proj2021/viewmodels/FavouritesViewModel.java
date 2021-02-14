package de.prog3.proj2021.viewmodels;

/**
 * This ViewModel class retrieves recipe data from
 * FavouriteRecipeRepository and passes it to the UI
 *
 * File author: Giuseppe Buccellato
 */

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import de.prog3.proj2021.db.FavouriteRecipeCrossRef;
import de.prog3.proj2021.db.FavouritesWithRecipes;
import de.prog3.proj2021.db.RecipeIngredientCrossRef;
import de.prog3.proj2021.db.RecipeWithIngredients;
import de.prog3.proj2021.models.FavouriteList;
import de.prog3.proj2021.models.Recipe;
import de.prog3.proj2021.repositories.FavouriteRecipeRepository;
import de.prog3.proj2021.repositories.RecipeRepository;

public class FavouritesViewModel extends AndroidViewModel {

    private final FavouriteRecipeRepository favouriteRecipeRepository;
    private final List<FavouritesWithRecipes> mFavouriteRecipes;
    private final FavouritesWithRecipes mFavouriteList;

    public FavouritesViewModel(@NonNull Application application) {
        super(application);
        favouriteRecipeRepository = new FavouriteRecipeRepository(application);
        mFavouriteRecipes = favouriteRecipeRepository.getFavouritesWithRecipes();
        mFavouriteList = favouriteRecipeRepository.getFavouriteList();
    }

    //getters for cached repository data
    public List<FavouritesWithRecipes> getAllFavouriteListsWithRecipes() {
        return mFavouriteRecipes;
    }

    public FavouritesWithRecipes getMFavouriteList(){
        return mFavouriteList;
    }

    public void update(FavouriteList favouriteList){
        favouriteRecipeRepository.update(favouriteList);
    }

    public void insertFavouriteCrossRef(FavouriteRecipeCrossRef favouriteRecipeCrossRef){
        favouriteRecipeRepository.insertCrossRef(favouriteRecipeCrossRef);
    }

    public void updateFavouriteCrossRef(FavouriteRecipeCrossRef favouriteRecipeCrossRef){
        favouriteRecipeRepository.updateCrossRef(favouriteRecipeCrossRef);
    }

    public void deleteFavouriteCrossRef(FavouriteRecipeCrossRef favouriteRecipeCrossRef){
        favouriteRecipeRepository.deleteCrossRef(favouriteRecipeCrossRef);
    }

    /*
     * getters for different queries here
     */
    public LiveData<List<Recipe>> getFavouriteRecipesByQuery(String query){
        return favouriteRecipeRepository.getFavouriteRecipesByQuery(query);
    }

}