package de.prog3.proj2021.repositories;

import android.app.ActivityManager;
import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import de.prog3.proj2021.MainActivity;
import de.prog3.proj2021.db.AppDatabase;
import de.prog3.proj2021.db.Recipe;
import de.prog3.proj2021.db.RecipeDao;
import io.github.mthli.sugartask.SugarTask;

/*
* This class interacts with the Recipe DAO
* to retrieve and cache recipes from database
* */

public class RecipeRepository {

    //private static RecipeRepository instance;
    private LiveData<List<Recipe>> dataSet;
    private final RecipeDao recipeDao;
    private final Executor executor = Executors.newSingleThreadExecutor();

    //constructors
    public RecipeRepository(Application application){
        AppDatabase recipeDB = AppDatabase.getInstance(application);
        recipeDao = recipeDB.recipeDao();
        updateRecipes();
    }

    //getter and updater for recipe set
    public LiveData<List<Recipe>> getRecipes(){
        updateRecipes();
        return dataSet;
    }

    private void updateRecipes(){
        setExampleRecipe();
        dataSet = recipeDao.getRecipes();
    }

    private void setExampleRecipe(){
        Recipe doener = new Recipe("döner", 100, true, "legga", "machen und essen", "no url");
        Recipe pizza = new Recipe("pizza", 1200, true, "legga2", "machen und auch essen", "no url");
        insert(doener);
        insert(pizza);
    }

    /*
     * database operations communicating with the DAO
     * in asynchronous threads and update dataSet
     */

    //Test with THREAD
    public void insert(Recipe recipe){
        Runnable runnable = () -> {
            recipeDao.insertRecipe(recipe);
            System.out.println("recipe inserted");
        };
        new Thread(runnable).start();

//        executor.execute(() -> { //Runnable lambda expression
//            recipeDao.insertRecipe(recipe);
//            System.out.println("recipe inserted");
//        });
    }

    //Test with EXECUTOR
    public void update(Recipe recipe){
        executor.execute(() -> {
            recipeDao.updateRecipe(recipe);
        });
    }

    public void delete(Recipe recipe){
        executor.execute(() -> {
            recipeDao.deleteRecipe(recipe);
        });
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
