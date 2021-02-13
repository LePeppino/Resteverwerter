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
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import de.prog3.proj2021.db.AppDatabase;
import de.prog3.proj2021.db.RecipeIngredientCrossRef;
import de.prog3.proj2021.db.RecipeWithIngredients;
import de.prog3.proj2021.models.Recipe;
import de.prog3.proj2021.db.RecipeDao;

public class RecipeRepository {

    private LiveData<RecipeWithIngredients> singleRecipe;
    private LiveData<List<Recipe>> dataSet;
    private LiveData<List<RecipeWithIngredients>> crossRefDataSet;

    private final RecipeDao recipeDao;

    //constructor
    public RecipeRepository(Application application){
        AppDatabase recipeDB = AppDatabase.getInstance(application);
        recipeDao = recipeDB.recipeDao();
        updateRecipes();
    }

    //setter
    private void updateRecipes() {
            dataSet = recipeDao.getRecipesASC();
            crossRefDataSet = recipeDao.getRecipesWithIngredients();
    }

    //getter for recipe dataSet
    public LiveData<List<Recipe>> getRecipes(){
        return dataSet;
    }
    public LiveData<List<RecipeWithIngredients>> getRecipesWithIngredients(){
        return crossRefDataSet;
    }

    /*
     * database operations communicating with RecipeDao
     */
    public void insert(Recipe recipe){
        AppDatabase.databaseExecutor.execute(() ->
                recipeDao.insertRecipe(recipe));
        System.out.println("recipe inserted");
    }
    public void update(Recipe recipe){
        AppDatabase.databaseExecutor.execute(() ->
                recipeDao.updateRecipe(recipe));
        System.out.println("recipe updated");
    }
    public void delete(Recipe recipe){
        AppDatabase.databaseExecutor.execute(() ->
                recipeDao.deleteRecipe(recipe));
        System.out.println("recipe deleted");
    }

    public void insertRecipeIngredientCrossRef(RecipeIngredientCrossRef recipeIngredientCrossRef){
        AppDatabase.databaseExecutor.execute(() ->
                recipeDao.insertRecipeIngredientCrossRef(recipeIngredientCrossRef));
        System.out.println("recipeIngredientCrossRef inserted");
    }
    public void updateRecipeIngredientCrossRef(RecipeIngredientCrossRef recipeIngredientCrossRef){
        AppDatabase.databaseExecutor.execute(() ->
                recipeDao.updateRecipeIngredientCrossRef(recipeIngredientCrossRef));
        System.out.println("recipeIngredientCrossRef updated");
    }
    public void deleteRecipeIngredientCrossRef(RecipeIngredientCrossRef... recipeIngredientCrossRef){
        AppDatabase.databaseExecutor.execute(() ->
                recipeDao.deleteRecipeIngredientCrossRef(recipeIngredientCrossRef));
        System.out.println("recipeIngredientCrossRef deleted");
    }

    /*
     * getters for different queries here
     */
    public LiveData<List<Recipe>> getRecipesByQuery(String query) {
        return recipeDao.getRecipesByQuery(query);
    }

    public LiveData<Recipe> getSingleRecipeByQuery(String query) {
        return recipeDao.getSingleRecipeByQuery(query);
    }

    public LiveData<RecipeWithIngredients> getRecipeWithIngredientsById(int id){
        AppDatabase.databaseExecutor.execute(() ->
                singleRecipe = recipeDao.getRecipeWithIngredientsById(id));
        return singleRecipe;
    }

}
