package de.prog3.proj2021.repositories;

/*
 * This class interacts with the Ingredient DAO
 * to retrieve and cache ingredient objects from database
 * to pass them to a ViewModel
 *
 * File author: Giuseppe Buccellato
 * */

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import de.prog3.proj2021.db.AppDatabase;
import de.prog3.proj2021.db.IngredientDao;
import de.prog3.proj2021.db.IngredientWithRecipes;
import de.prog3.proj2021.models.Ingredient;

public class IngredientRepository {

    private LiveData<List<Ingredient>> dataSet;
    private LiveData<List<IngredientWithRecipes>> crossRefDataSet;

    private final IngredientDao ingredientDao;

    //constructor
    public IngredientRepository(Application application){
        AppDatabase ingredientDB = AppDatabase.getInstance(application);
        ingredientDao = ingredientDB.ingredientDao();
        updateIngredients();
    }

    //setter
    private void updateIngredients() {
        dataSet = ingredientDao.getIngredientsByTypeASC();
        crossRefDataSet = ingredientDao.getIngredientWithRecipes();
    }

    //getter for ingredient dataSet
    public LiveData<List<Ingredient>> getIngredients() { return dataSet; }
    public LiveData<List<IngredientWithRecipes>> getIngredientWithRecipes(){
        return crossRefDataSet;
    }

    /*
     * database operations communicating with IngredientDao
     */
    public void insert(Ingredient ingredient){
        AppDatabase.databaseExecutor.execute(() -> {
            ingredientDao.insertIngredient(ingredient);
        });
        System.out.println("ingredient inserted");
    }
    public void update(Ingredient ingredient){
        AppDatabase.databaseExecutor.execute(() -> {
            ingredientDao.updateIngredient(ingredient);
        });
        System.out.println("ingredient updated");
    }
    public void delete(Ingredient ingredient){
        AppDatabase.databaseExecutor.execute(() -> {
            ingredientDao.deleteIngredient(ingredient);
        });
        System.out.println("ingredient deleted");
    }

    /*
     * getters for different queries here
     */
    public LiveData<List<Ingredient>> getMIngredientsByQuery(String query){
        return ingredientDao.getIngredientsByQuery(query);
    }

    public LiveData<Ingredient> getSingleIngredientByQuery(String query){
        return ingredientDao.getSingleIngredientByQuery(query);
    }

}
