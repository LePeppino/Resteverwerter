package de.prog3.proj2021.repositories;

/**
 * This class interacts with the Recipe DAO
 * to retrieve and cache recipes from database
 * to pass them to a ViewModel
 *
 * @author Giuseppe Buccellato
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
    private List<RecipeWithIngredients> crossRefDataSet;
    private final RecipeDao recipeDao;

    /**
     * constructor
     * @param application
     */
    public RecipeRepository(Application application){
        AppDatabase recipeDB = AppDatabase.getInstance(application);
        recipeDao = recipeDB.recipeDao();
        updateRecipes();
        updateRecipesWithIngredients();
    }

    /**
     * gets dataset with list of FavouritesWithRecipes
     * @return live data list
     */
    public LiveData<List<Recipe>> getRecipes(){
        return dataSet;
    }

    /**
     * gets live data list of RecipesWithIngredient
     * @return live data list
     */
    public List<RecipeWithIngredients> getRecipesWithIngredients(){
        return crossRefDataSet;
    }

    /**
     * updates the recipes
     */
    private void updateRecipes(){
        dataSet = recipeDao.getRecipes();
    }

    /**
     * updates RecipesWithIngredients
     */
    private void updateRecipesWithIngredients(){
        crossRefDataSet = recipeDao.getRecipesWithIngredients();
    }
    /**
     * database operations communicating with RecipeDao
     */
    public void insert(Recipe recipe){
        recipeDao.insertRecipe(recipe);
        System.out.println("recipe inserted");
    }

    /**
     * update specific recipe
     * @param recipe
     */
    public void update(Recipe recipe){
        recipeDao.updateRecipe(recipe);
        System.out.println("recipe updated");
    }

    /**
     * delete specific recipe
     * @param recipe
     */
    public void delete(Recipe recipe){
        recipeDao.deleteRecipe(recipe);
        System.out.println("recipe deleted");
    }

    /**
     * insert RecipeIngredientCrossRef
     * @param recipeIngredientCrossRef
     */
    public void insertRecipeIngredientCrossRef(RecipeIngredientCrossRef recipeIngredientCrossRef){
        recipeDao.insertRecipeIngredientCrossRef(recipeIngredientCrossRef);
        System.out.println("recipeIngredientCrossRef inserted");
    }

    /**
     * update RecipeIngredientCrossRef
     * @param recipeIngredientCrossRef
     */
    public void updateRecipeIngredientCrossRef(RecipeIngredientCrossRef recipeIngredientCrossRef){
        recipeDao.updateRecipeIngredientCrossRef(recipeIngredientCrossRef);
        System.out.println("recipeIngredientCrossRef updated");
    }

    /**
     * delete RecipeIngredientCrossRef
     * @param recipeIngredientCrossRef
     */
    public void deleteRecipeIngredientCrossRef(RecipeIngredientCrossRef... recipeIngredientCrossRef){
        recipeDao.deleteRecipeIngredientCrossRef(recipeIngredientCrossRef);
        System.out.println("recipeIngredientCrossRef deleted");
    }

    /**
     * getter for different queries, ordered listing
     */
    public LiveData<List<Recipe>> getRecipesAlphabetical() {
        return dataSet = recipeDao.getRecipesASC();
    }
    /**
     * getter for different queries
     */
    public LiveData<List<Recipe>> getRecipesByQuery(String query) {
        return dataSet = recipeDao.getRecipesByQuery(query);
    }

}
