package de.prog3.proj2021.viewmodels;

/*
 * This ViewModel class retrieves data from
 * RecipeRepository and passes it to the UI
 *
 * File author: Giuseppe Buccellato
 */

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import de.prog3.proj2021.db.RecipeIngredientCrossRef;
import de.prog3.proj2021.db.RecipeWithIngredients;
import de.prog3.proj2021.models.Recipe;
import de.prog3.proj2021.repositories.RecipeRepository;

public class RecipeViewModel extends AndroidViewModel {

    private final RecipeRepository recipeRepository;
    private LiveData<List<Recipe>> mRecipes;
    private LiveData<List<RecipeWithIngredients>> mRecipesWithIngredients;

    public RecipeViewModel(@NonNull Application application) {
        super(application);
        recipeRepository = new RecipeRepository(application);
        setRecipes();
        setRecipesWithIngredients();
    }

    private void setRecipes(){
        this.mRecipes = recipeRepository.getRecipes();
    }
    private void setRecipesWithIngredients(){
        this.mRecipesWithIngredients = recipeRepository.getRecipesWithIngredients();
    }

    public void insert(Recipe recipe){
        recipeRepository.insert(recipe);
    }
    public void update(Recipe recipe){
        recipeRepository.update(recipe);
    }
    public void delete(Recipe recipe){
        recipeRepository.delete(recipe);
    }

    public void insertRecipeIngredientCrossRef(RecipeIngredientCrossRef recipeIngredientCrossRef){
        recipeRepository.insertRecipeIngredientCrossRef(recipeIngredientCrossRef);
    }
    public void updateRecipeIngredientCrossRef(RecipeIngredientCrossRef recipeIngredientCrossRef){
        recipeRepository.updateRecipeIngredientCrossRef(recipeIngredientCrossRef);
    }
    public void deleteRecipeIngredientCrossRef(RecipeIngredientCrossRef recipeIngredientCrossRef){
        recipeRepository.deleteRecipeIngredientCrossRef(recipeIngredientCrossRef);
    }

    //getters for cached repository LiveData
    public LiveData<List<Recipe>> getMRecipes(){
        if(mRecipes == null){
            mRecipes = recipeRepository.getRecipes();
        }
        return mRecipes;
    }
    public LiveData<List<RecipeWithIngredients>> getMRecipesWithIngredients() {
        if(mRecipesWithIngredients == null){
            mRecipesWithIngredients = recipeRepository.getRecipesWithIngredients();
        }
        return mRecipesWithIngredients;
    }

    /*
    * other getters
    * */
    public LiveData<List<Recipe>> getMRecipesByQuery(String query){
        return recipeRepository.getRecipesByQuery(query);
    }
    public RecipeWithIngredients getMRecipeById(int id){
        return recipeRepository.getRecipeWithIngredientsById(id);
    }



}
