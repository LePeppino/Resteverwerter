package de.prog3.proj2021.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import de.prog3.proj2021.db.AppDatabase;
import de.prog3.proj2021.models.Recipe;
import de.prog3.proj2021.db.RecipeDao;

/*
* This class interacts with the Recipe DAO
* to retrieve and cache recipes from database
*
* File author: Giuseppe Buccellato
* */

public class RecipeRepository {

    //private static RecipeRepository instance;
    private LiveData<List<Recipe>> dataSet;
    private final RecipeDao recipeDao;

    //constructors
    public RecipeRepository(Application application){
        AppDatabase recipeDB = AppDatabase.getInstance(application);
        recipeDao = recipeDB.recipeDao();
        //deleteAllRecipes();
        //setExampleRecipe(); //TODO: to be removed later
        updateRecipes();
    }

    //getter and setter for recipe dataSet
    public LiveData<List<Recipe>> getRecipes(){
        return dataSet;
    }

    private void updateRecipes(){
        dataSet = recipeDao.getRecipes();
    }

    //getters for different orders
    public LiveData<List<Recipe>> getRecipesAlphabetical() {
        return dataSet = recipeDao.getRecipesASC();
    }
    public LiveData<List<Recipe>> getRecipesReverseAlphabetical() {
        return dataSet = recipeDao.getRecipesDESC();
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

    public void deleteAllRecipes(){
        recipeDao.deleteAllRecipes();
        System.out.println("all recipes deleted");
    }

    /*
     * Usage of threads may not be necessary as query is quick enough.
     * Also the results were not always correct, like wrong order.
     */
//    public void insert(Recipe recipe){
//        Runnable runnable = () -> {
//            recipeDao.insertRecipe(recipe);
//            System.out.println("recipe inserted");
//        };
//        new Thread(runnable).start();
//    }

    //TODO: remove once database is pre-populated
    private void setExampleRecipe(){
        deleteAllRecipes();
        Recipe doener = new Recipe("döner", 100, 1, "legga", "machen und essen", "no url");
        Recipe pizza = new Recipe("pizza", 1200, 1, "legga2", "machen und auch essen", "no url");
        Recipe burrito = new Recipe("burrito", 120034, 0, "legga3", "ebenfalls machen und auch essen", "no url");
        insert(doener);
        insert(pizza);
        insert(burrito);
    }

    /*
     * Singleton pattern only works with parameterless private constructor
     * */
//    private RecipeRepository(){}
//
//    public static RecipeRepository getInstance(){
//        if(instance == null){
//            instance = new RecipeRepository();
//        }
//        return instance;
//    }

}
