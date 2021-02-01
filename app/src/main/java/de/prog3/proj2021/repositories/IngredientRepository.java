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
import de.prog3.proj2021.models.Ingredient;

public class IngredientRepository {

    private LiveData<List<Ingredient>> dataSet;
    private final IngredientDao ingredientDao;

    //constructor
    public IngredientRepository(Application application){
        AppDatabase ingredientDB = AppDatabase.getInstance(application);
        ingredientDao = ingredientDB.ingredientDao();
        updateIngredients();
    }

    //getter and setter for ingredient dataSet
    public LiveData<List<Ingredient>> getIngredients() { return dataSet; }

    private void updateIngredients() { dataSet = ingredientDao.getIngredients();}

    /*
     * database operations communicating with IngredientDao
     */
    public void insert(Ingredient ingredient){
        ingredientDao.insertIngredient(ingredient);
        System.out.println("ingredient inserted");
    }

    public void update(Ingredient ingredient){
        ingredientDao.updateIngredient(ingredient);
        System.out.println("ingredient updated");
    }

    public void delete(Ingredient ingredient){
        ingredientDao.deleteIngredient(ingredient);
        System.out.println("ingredient deleted");
    }

    public void deleteAllIngredients(){
        ingredientDao.deleteAllIngredients();
        System.out.println("all recipes deleted");
    }

    /*
     * getters for different queries here
     */
    public LiveData<List<Ingredient>> getIngredientsAlphabetical(){
        return dataSet = ingredientDao.getIngredientsASC();
    }


}
