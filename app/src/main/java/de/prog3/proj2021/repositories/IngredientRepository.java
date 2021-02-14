package de.prog3.proj2021.repositories;

/**
 * This class interacts with the Ingredient DAO
 * to retrieve and cache ingredient objects from database
 * to pass them to a ViewModel
 *
 * @author Giuseppe Buccellato, Eric Walter
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
    private List<IngredientWithRecipes> crossRefDataSet;
    private final IngredientDao ingredientDao;

    //constructor
    public IngredientRepository(Application application){
        AppDatabase ingredientDB = AppDatabase.getInstance(application);
        ingredientDao = ingredientDB.ingredientDao();
        updateIngredients();
    }

    //getter and setter for ingredient dataSet
    /**
     * Gets live data of all ingredients
     * @return live data list of ingredients
     */
    public LiveData<List<Ingredient>> getIngredients() { return dataSet; }

    /**
     * Gets the crossrefs of recipes containing the ingredient
     * @return list of cross references between ingredient and recipes
     */
    public List<IngredientWithRecipes> getIngredientWithRecipes(){
        return crossRefDataSet;
    }

    /**
     * Updates the ingredients
     */
    private void updateIngredients() {
        dataSet = ingredientDao.getIngredientsByTypeASC();
        crossRefDataSet = ingredientDao.getIngredientWithRecipes();
    }

    /**
     * database insert of ingredient
     * @param ingredient to be inserted
     */
    public void insert(Ingredient ingredient){
        ingredientDao.insertIngredient(ingredient);
        System.out.println("ingredient inserted");
    }
    /**
     * database update of ingredient
     * @param ingredient to be updated
     */
    public void update(Ingredient ingredient){
        ingredientDao.updateIngredient(ingredient);
        System.out.println("ingredient updated");
    }
    /**
     * database deletion of ingredient
     * @param ingredient to be deleted
     */
    public void delete(Ingredient ingredient){
        ingredientDao.deleteIngredient(ingredient);
        System.out.println("ingredient deleted");
    }

    /**
     * Getter for all ingredients
     * @param query gets live data of list of all ingredients from the db
     * @return live data of list of ingredients
     */
    public LiveData<List<Ingredient>> getIngredientsByQuery(String query){
        return ingredientDao.getIngredientsByQuery(query);
    }

    /**
     * Getter for single ingredient
     * @param query gets live data of single ingredient from the db
     * @return specific ingredient
     */
    public LiveData<Ingredient> getSingleIngredientByQuery(String query){
        return ingredientDao.getSingleIngredientByQuery(query);
    }

}
