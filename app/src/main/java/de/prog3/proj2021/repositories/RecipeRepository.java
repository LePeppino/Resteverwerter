package de.prog3.proj2021.repositories;

/*
 * This class interacts with the Recipe DAO
 * to retrieve and cache recipes from database
 * to pass them to a ViewModel
 *
 * File author: Giuseppe Buccellato
 * */

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import de.prog3.proj2021.db.AppDatabase;
import de.prog3.proj2021.db.RecipeIngredientCrossRef;
import de.prog3.proj2021.db.RecipeWithIngredients;
import de.prog3.proj2021.models.Recipe;
import de.prog3.proj2021.db.RecipeDao;

public class RecipeRepository {

    private LiveData<List<Recipe>> dataSet;
    private LiveData<List<RecipeWithIngredients>> crossRefDataSet;
    private final RecipeDao recipeDao;

    //constructor
    public RecipeRepository(Application application){
        AppDatabase recipeDB = AppDatabase.getInstance(application);
        recipeDao = recipeDB.recipeDao();
        updateRecipes();
        updateRecipesWithIngredients();
    }

    //getter and setter for recipe dataSet
    public LiveData<List<Recipe>> getRecipes(){
        return dataSet;
    }
    public LiveData<List<RecipeWithIngredients>> getRecipesWithIngredients(){
        return crossRefDataSet;
    }
    private void updateRecipes(){
        dataSet = recipeDao.getRecipes();
    }
    private void updateRecipesWithIngredients(){
        crossRefDataSet = recipeDao.getRecipesWithIngredients();
    }
    /*
     * database operations communicating with RecipeDao
     */
    public void insert(Recipe recipe){
        recipeDao.insertRecipe(recipe);
        System.out.println("recipe inserted");
    }
    public void update(Recipe recipe){
        recipeDao.updateRecipe(recipe);
        System.out.println("recipe updated");
    }
    public void delete(Recipe recipe){
        recipeDao.deleteRecipe(recipe);
        System.out.println("recipe deleted");
    }

    public void insertCrossRef(RecipeIngredientCrossRef recipeIngredientCrossRef){
        recipeDao.insertRecipeWithIngredients(recipeIngredientCrossRef);
        System.out.println("recipeIngredientCrossRef inserted");
    }
    public void updateCrossRef(RecipeIngredientCrossRef recipeIngredientCrossRef){
        recipeDao.updateRecipeWithIngredients(recipeIngredientCrossRef);
        System.out.println("recipeIngredientCrossRef updated");
    }
    public void deleteCrossRef(RecipeIngredientCrossRef recipeIngredientCrossRef){
        recipeDao.deleteRecipeWithIngredients(recipeIngredientCrossRef);
        System.out.println("recipeIngredientCrossRef deleted");
    }

    /*
     * getters for different queries here
     */
    public LiveData<List<Recipe>> getRecipesAlphabetical() {
        return dataSet = recipeDao.getRecipesASC();
    }
    public LiveData<List<Recipe>> getRecipesReverseAlphabetical() {
        return dataSet = recipeDao.getRecipesDESC();
    }
//    public LiveData<RecipeWithIngredients> getRecipeWithIngredientsById(int recipeId){
//        return recipeDao.getRecipeWithIngredientsById(recipeId);
//    }

}
