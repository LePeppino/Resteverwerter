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
* */

public class RecipeRepository {

    //private static RecipeRepository instance;
    private LiveData<List<Recipe>> dataSet;
    private final RecipeDao recipeDao;

    //constructors
    public RecipeRepository(Application application){
        AppDatabase recipeDB = AppDatabase.getInstance(application);
        recipeDao = recipeDB.recipeDao();
        updateRecipes();
    }

    //getter and updater for recipe set
    public LiveData<List<Recipe>> getRecipes(){
        return dataSet;
    }

    private void updateRecipes(){
        setExampleRecipe();
        dataSet = recipeDao.getRecipes();
    }

    /*
     * database operations communicating with the DAO
     * in asynchronous threads and update dataSet
     */
    public void insert(Recipe recipe){
        Runnable runnable = () -> {
            recipeDao.insertRecipe(recipe);
            System.out.println("recipe inserted");
        };
        new Thread(runnable).start();
    }

    public void update(Recipe recipe){
        Runnable runnable = () -> {
            recipeDao.updateRecipe(recipe);
            System.out.println("recipe updated");
        };
        new Thread(runnable).start();
    }

    public void delete(Recipe recipe){
        Runnable runnable = () -> {
            recipeDao.deleteRecipe(recipe);
            System.out.println("recipe deleted");
        };
        new Thread(runnable).start();
    }

    public void deleteAllRecipes(){
        recipeDao.deleteAllRecipes();
        System.out.println("all recipes deleted");
    }

    private void setExampleRecipe(){
        deleteAllRecipes();
        Recipe doener = new Recipe("döner", 100, true, "legga", "machen und essen", "no url");
        Recipe pizza = new Recipe("pizza", 1200, true, "legga2", "machen und auch essen", "no url");
        Recipe burrito = new Recipe("burrito", 120034, false, "legga3", "ebenfalls machen und auch essen", "no url");
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
